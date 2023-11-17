package Project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Room implements AutoCloseable {
    protected static Server server;// used to refer to accessible server functions
    private String name;
    private List<ServerThread> clients = new ArrayList<ServerThread>();
    private boolean isRunning = false;
    // Commands
    private final static String COMMAND_TRIGGER = "/";
    private final static String CREATE_ROOM = "createroom";
    private final static String JOIN_ROOM = "joinroom";
    private final static String DISCONNECT = "disconnect";
    private final static String LOGOUT = "logout";
    private final static String LOGOFF = "logoff";
    private final static String Flip = "flip";
    private final static String Roll = "roll";
    //private static final String BOLD_REGEX = "\\*\\*(.*?)\\*\\*";
    //private static final String ITALICS_REGEX = "__(.*?)__";
    //private static final String COLOR_REGEX = "\\{color=(red|green|blue)\\}(.*?)\\{color\\}";
    //private static final String UNDERLINE_REGEX = "_(.*?)_";

    public Room(String name) {
        this.name = name;
        isRunning = true;
    }

    private void info(String message) {
        System.out.println(String.format("Room[%s]: %s", name, message));
    }

    public String getName() {
        return name;
    }

    protected synchronized void addClient(ServerThread client) {
        if (!isRunning) {
            return;
        }
        client.setCurrentRoom(this);
        if (clients.indexOf(client) > -1) {
            info("Attempting to add a client that already exists");
        } else {
            clients.add(client);
            new Thread() {
                @Override
                public void run() {
                    // slight delay to let potentially new client to finish
                    // binding input/output streams
                    // comment out the Thread.sleep to see what happens
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // sendMessage(client, "joined the room " + getName());
                    sendConnectionStatus(client, true);
                }
            }.start();

        }
    }

    protected synchronized void removeClient(ServerThread client) {
        if (!isRunning) {
            return;
        }
        clients.remove(client);
        // we don't need to broadcast it to the server
        // only to our own Room
        if (clients.size() > 0) {
            // sendMessage(client, "left the room");
            sendConnectionStatus(client, false);
        }
        checkClients();
    }

    /***
     * Checks the number of clients.
     * If zero, begins the cleanup process to dispose of the room
     */
    private void checkClients() {
        // Cleanup if room is empty and not lobby
        if (!name.equalsIgnoreCase("lobby") && clients.size() == 0) {
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
    private boolean processCommands(String message, ServerThread client) {
        boolean wasCommand = false;
        try {
            if (message.startsWith(COMMAND_TRIGGER)) {
                String[] comm = message.split(COMMAND_TRIGGER);
                String part1 = comm[1];
                String[] comm2 = part1.split(" ");
                String command = comm2[0];
                String roomName;
                System.out.println("Part 1" + part1 + "command " + command);
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
                        //Dsp82 11/15/2023
                        //Flip code. Use random gen to generate two ints. IF int is 0, user rolled heads, else they rolled tails 
                    case Flip: 
                            Random gen = new Random();
                            int coin_Flip = gen.nextInt(2);
                            if (coin_Flip == 0) {
                                sendMessage(client, "Flipped a coin and got heads");
                            } else {
                                sendMessage(client, "Flipped a coin and got tails");
                            }
                        break;
                        //iaa47
                        //11/13/23
                    case Roll:  
                         try{
                            String Dice = comm2[1];
                            String RollType1 = "(\\d+)d(\\d+)";
                            String RollType2 = "(\\d+)";
                            Pattern roll1 = Pattern.compile(RollType1);
                            Pattern roll2 = Pattern.compile(RollType2);
                            Matcher type1 = roll1.matcher(Dice);
                            Matcher type2 = roll2.matcher(Dice);
                            if (type1.find()){
                                String DN =  type1.group(1);
                                String DS = type2.group(2);
                                int NumberOfDice = Integer.parseInt(DN);
                                int NumberOfSides = Integer.parseInt(DS);

                                int total = 0;
                                for (int x = 0; x < NumberOfDice; x ++) {
                                    
                                    int roll =(int)( Math.random() * NumberOfSides) + 1;
                                    total += roll;
                            }
                            sendMessage( null, String.format("%s rolled " + Dice + ", the result is " + total,client.getClientName()));
                        }
                        else if(type2.find()){
                            String range = type2.group(1);
                            int num = Integer.parseInt(range);
                            int total = (int)(Math.random() * num) +1;
                            sendMessage( null, String.format("%s rolled " + Dice + " the result is " + total,client.getClientName()));
                        }
                    }catch(Exception e){
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
    protected static void createRoom(String roomName, ServerThread client) {
        if (server.createNewRoom(roomName)) {
            // server.joinRoom(roomName, client);
            Room.joinRoom(roomName, client);
        } else {
            client.sendMessage("Server", String.format("Room %s already exists", roomName));
        }
    }

    protected static void joinRoom(String roomName, ServerThread client) {
        if (!server.joinRoom(roomName, client)) {
            client.sendMessage("Server", String.format("Room %s doesn't exist", roomName));
        }
    }

    protected static void disconnectClient(ServerThread client, Room room) {
        client.setCurrentRoom(null);
        client.disconnect();
        room.removeClient(client);
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
    // dsp82 11/15/2023
    protected synchronized void sendMessage(ServerThread sender, String message) {
        if (!isRunning) {
            return;
        }
        info("Sending message to " + clients.size() + " clients");
        if (sender != null && processCommands(message, sender)) {
            // it was a command, don't broadcast
            return;
        }
            //iaa47
            //11/13/23
        message = message.replaceAll("!@(.*?)@!", "<b>$1</b>");
        message = message.replaceAll("&@(.*?)@&", "<i>$1</i>");
        message = message.replaceAll("-c@\\{(.*?)\\}(.*?)\\-c@", "<font color='#$1'>$2</font>");
        message = message.replaceAll("_@(.*?)@_", "<u>$1</u>");
        //String formattedMessage = FormatMessage(message);
        String from = (sender == null ? "Room" : sender.getClientName());
        Iterator<ServerThread> iter = clients.iterator();
        while (iter.hasNext()) {
            ServerThread client = iter.next();
            boolean messageSent = client.sendMessage(from, message);
            if (!messageSent) {
                handleDisconnect(iter, client);
            }
        }
    }

  

    protected synchronized void sendConnectionStatus(ServerThread sender, boolean isConnected) {
        Iterator<ServerThread> iter = clients.iterator();
        while (iter.hasNext()) {
            ServerThread client = iter.next();
            boolean messageSent = client.sendConnectionStatus(sender.getClientName(), isConnected);
            if (!messageSent) {
                handleDisconnect(iter, client);
            }
        }
    }

    private void handleDisconnect(Iterator<ServerThread> iter, ServerThread client) {
        iter.remove();
        info("Removed client " + client.getClientName());
        checkClients();
        sendMessage(null, client.getClientName() + " disconnected");
    }

    public void close() {
        server.removeRoom(this);
        server = null;
        isRunning = false;
        clients = null;
    }
}