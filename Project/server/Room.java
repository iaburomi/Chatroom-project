package Project.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Project.common.Constants;

public class Room implements AutoCloseable {
    // protected static Server server;// used to refer to accessible server
    // functions
    private String name;
    protected List<ServerThread> clients = new ArrayList<ServerThread>();
    private boolean isRunning = false;
    // Commands
    private final static String COMMAND_TRIGGER = "/";
    private final static String CREATE_ROOM = "createroom";
    private final static String JOIN_ROOM = "joinroom";
    private final static String DISCONNECT = "disconnect";
    private final static String LOGOUT = "logout";
    private final static String LOGOFF = "logoff";
    private final static String FLIP = "flip";
    private final static String Roll = "roll";
    private static Logger logger = Logger.getLogger(Room.class.getName());
    public static Server server;

    public Room(String name) {
        this.name = name;
        isRunning = true;
    }

    public String getName() {
        return name;
    }

    public boolean isRunning() {
        return isRunning;
    }

    protected synchronized void addClient(ServerThread client) {
        if (!isRunning) {
            return;
        }
        client.setCurrentRoom(this);
        if (clients.indexOf(client) > -1) {
            logger.warning("Attempting to add client that already exists in room");
        } else {
            clients.add(client);
            client.sendResetUserList();
            syncCurrentUsers(client);
            sendConnectionStatus(client, true);
        }
    }

    protected synchronized void removeClient(ServerThread client) {
        if (!isRunning) {
            return;
        }
        // attempt to remove client from room
        try {
            clients.remove(client);
        } catch (Exception e) {
            logger.severe(String.format("Error removing client from room %s", e.getMessage()));
            e.printStackTrace();
        }
        // if there are still clients tell them this person left
        if (clients.size() > 0) {
            sendConnectionStatus(client, false);
        }
        checkClients();
    }

    private void syncCurrentUsers(ServerThread client) {
        Iterator<ServerThread> iter = clients.iterator();
        while (iter.hasNext()) {
            ServerThread existingClient = iter.next();
            if (existingClient.getClientId() == client.getClientId()) {
                continue;// don't sync ourselves
            }
            boolean messageSent = client.sendExistingClient(existingClient.getClientId(),
                    existingClient.getClientName());
            if (!messageSent) {
                handleDisconnect(iter, existingClient);
                break;// since it's only 1 client receiving all the data, break if any 1 send fails
            }
        }
    }

    /***
     * Checks the number of clients.
     * If zero, begins the cleanup process to dispose of the room
     */
    private void checkClients() {
        // Cleanup if room is empty and not lobby
        if (!name.equalsIgnoreCase(Constants.LOBBY) && (clients == null || clients.size() == 0)) {
            close();
        }
    }

    /***
     * Helper function to process messages to trigger different functionality.
     * 
     * @param message The original message being sent
     * @param client  The sender of the message (since they'll be the ones
     *                triggering the actions)
     */
    @Deprecated // not used in my project as of this lesson, keeping it here in case things
                // change
    private boolean processCommands(String message, ServerThread client) {
        boolean wasCommand = false;
        try {
            if (message.startsWith(COMMAND_TRIGGER)) {
                String[] comm = message.split(COMMAND_TRIGGER);
                String part1 = comm[1];
                String[] comm2 = part1.split(" ");
                String command = comm2[0];
                String roomName;
                wasCommand = true;
                
                switch (command) {
                    case CREATE_ROOM:
                        roomName = comm2[1];
                        Room.createRoom(roomName, client);
                        break;
                    case JOIN_ROOM:
                        roomName = comm2[1];
                        Room.joinRoom(roomName, client);
                        break;
                    case DISCONNECT:
                    case LOGOUT:
                    case LOGOFF:
                        Room.disconnectClient(client, this);
                        break;
                    // iaa47
                    // Flip code. Use random gen to generate two ints. IF int is 0, user rolled
                    // heads, else they roll tails
                    case FLIP:
                        Random gen = new Random();
                        int coin_Flip = gen.nextInt(2);
                        if (coin_Flip == 0) {
                            sendMessage(client, "*Flipped a coin and got heads*");
                        } else {
                            sendMessage(client, "*Flipped a coin and got tails*");
                        }
                        break;
                        //roll code to generate random number on a die
                    case Roll:
                        try {
                            String Dice = comm2[1];
                            String RollType1 = "(\\d+)d(\\d+)";
                            String RollType2 = "(\\d+)";
                            Pattern roll1 = Pattern.compile(RollType1);
                            Pattern roll2 = Pattern.compile(RollType2);
                            Matcher type1 = roll1.matcher(Dice);
                            Matcher type2 = roll2.matcher(Dice);
                            if (type1.find()) {
                                String DN = type1.group(1);
                                String DS = type2.group(2);
                                int NumberOfDice = Integer.parseInt(DN);
                                int NumberOfSides = Integer.parseInt(DS);
                                int total = 0;//for statement that rolls a random number 
                                for (int x = 0; x < NumberOfDice; x++) {
                                    int roll = (int) (Math.random() * NumberOfSides) + 1;
                                    total += roll;
                                }//output message with the result number and message
                                sendMessage(null, String.format("*%s rolled* " + Dice + "*, the result is* " + total,
                                        client.getClientName()));
                            } else if (type2.find()) {//the second result for a number in a a bigger range
                                String range = type2.group(1);
                                int num = Integer.parseInt(range);
                                int total = (int) (Math.random() * num) + 1;
                                sendMessage(null, String.format("*%s rolled* " + Dice + "* the result is* " + total,
                                        client.getClientName()));
                            }//another result if the user inputs the roll method incorrectly
                        } catch (Exception e) {
                            sendMessage(null, "Make sure to type the right format of /roll #d# or /roll 0-x or 1-x");
                        }

                        break;
                    default:
                        wasCommand = false;
                        break;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wasCommand;
    }

    // Command helper methods
    protected static void getRooms(String query, ServerThread client) {
        String[] rooms = Server.INSTANCE.getRooms(query).toArray(new String[0]);
        client.sendRoomsList(rooms,
                (rooms != null && rooms.length == 0) ? "No rooms found containing your query string" : null);
    }

    protected static void createRoom(String roomName, ServerThread client) {
        if (Server.INSTANCE.createNewRoom(roomName)) {
            Room.joinRoom(roomName, client);
        } else {
            client.sendMessage(Constants.DEFAULT_CLIENT_ID, String.format("Room %s already exists", roomName));
        }
    }
    protected synchronized ServerThread getClientByName(String username) {
        for (ServerThread client : clients) {
            if (client.getClientName().equalsIgnoreCase(username)) {
                return client;
            }
        }
        return null;
    }
    /**
     * Will cause the client to leave the current room and be moved to the new room
     * if applicable
     * 
     * @param roomName
     * @param client
     */
    protected static void joinRoom(String roomName, ServerThread client) {
        if (!Server.INSTANCE.joinRoom(roomName, client)) {
            client.sendMessage(Constants.DEFAULT_CLIENT_ID, String.format("Room %s doesn't exist", roomName));
        }
    }

    protected static void disconnectClient(ServerThread client, Room room) {
        client.disconnect();
        room.removeClient(client);
    }
    protected synchronized void sendMessageToPrivateParticipants(ServerThread sender, ServerThread receiver, String message) {
        if (!isRunning) {
            return;
        }
    
        logger.info(String.format("Sending private message from %s to %s", sender.getClientName(), receiver.getClientName()));
    
        // Process special formatting commands
        message = processFormattingCommands(message);
    
        // Notify sender and receiver
        boolean senderMessageSent = sender.sendMessage(receiver.getClientId(), message);
        boolean receiverMessageSent = receiver.sendMessage(sender.getClientId(), message);
    
        // Handle disconnects
        if (!senderMessageSent) {
            handleDisconnect(clients.iterator(), sender);
        }
        if (!receiverMessageSent) {
            handleDisconnect(clients.iterator(), receiver);
        }
    }
    // end command helper methods

    /***
     * Takes a sender and a message and broadcasts the message to all clients in
     * this room. Client is mostly passed for command purposes but we can also use
     * it to extract other client info.
     * 
     * @param sender  The client sending the message
     * @param message The message to broadcast inside the room
     */
    //iaa47
    protected synchronized void sendMessage(ServerThread sender, String message) {
        if (!isRunning) {
            return;
        }
        logger.info(String.format("Sending message to %s clients", clients.size()));
        // Process special formatting commands
        message = processFormattingCommands(message);
        if (sender != null && processCommands(message, sender)) {
            // It was a command, don't broadcast
            return;
        }
        long from = sender == null ? Constants.DEFAULT_CLIENT_ID : sender.getClientId();
        Iterator<ServerThread> iter = clients.iterator();
        while (iter.hasNext()) {
            ServerThread client = iter.next();
            boolean messageSent = client.sendMessage(from, message);
            if (!messageSent) {
                handleDisconnect(iter, client);
            }
        }
    }

    private String processFormattingCommands(String message) {
        // Handle formatting commands here and convert them to HTML or other formatting
        // codes
        message = message.replaceAll("\\*(.*?)\\*", "<b>$1</b>"); // Bold
        message = message.replaceAll("_\\{(.*?)}_", "<u>$1</u>"); // Underline
        message = message.replaceAll("&\\{(.*?)}&", "<i>$1</i>"); // Italicize
        message = message.replaceAll("-c@\\{(.*?)}@(.*?)\\-c@", "<font color='$1'>$2</font>"); // Change color
        return message;
    }

    protected synchronized void sendConnectionStatus(ServerThread sender, boolean isConnected) {
        Iterator<ServerThread> iter = clients.iterator();
        while (iter.hasNext()) {
            ServerThread receivingClient = iter.next();
            boolean messageSent = receivingClient.sendConnectionStatus(
                    sender.getClientId(),
                    sender.getClientName(),
                    isConnected);
            if (!messageSent) {
                handleDisconnect(iter, receivingClient);
            }
        }
    }

    protected void handleDisconnect(Iterator<ServerThread> iter, ServerThread client) {
        iter.remove();
        logger.info(String.format("Removed client %s", client.getClientName()));
        sendMessage(null, client.getClientName() + " disconnected");
        checkClients();
    }

    public void close() {
        Server.INSTANCE.removeRoom(this);
        isRunning = false;
        clients.clear();
    }

    public void broadcast(String string) {
    }
}