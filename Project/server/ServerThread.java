package Project.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import Project.common.CellData;
import Project.common.CellPayload;
import Project.common.Character;
import Project.common.CharacterPayload;
import Project.common.Constants;
import Project.common.Payload;
import Project.common.PayloadType;
import Project.common.Phase;
import Project.common.PositionPayload;
import Project.common.RoomResultPayload;

/**
 * A server-side representation of a single client
 */
public class ServerThread extends Thread {
    private Socket client;
    private String clientName;
    private boolean isRunning = false;
    private ObjectOutputStream out;// exposed here for send()
    // private Server server;// ref to our server so we can call methods on it
    // more easily
    private Room currentRoom;
    private static Logger logger = Logger.getLogger(ServerThread.class.getName());
    private long myClientId;
    private Map<String, Set<ServerThread>> mutedClients = new HashMap<>();

    public void setClientId(long id) {
        myClientId = id;
    }

    public long getClientId() {
        return myClientId;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public ServerThread(Socket myClient, Room room) {
        logger.info("ServerThread created");
        // get communication channels to single client
        this.client = myClient;
        this.currentRoom = room;

    }

    protected void setClientName(String name) {
        if (name == null || name.isBlank()) {
            logger.warning("Invalid name being set");
            return;
        }
        clientName = name;
    }

    public String getClientName() {
        return clientName;
    }

    protected synchronized Room getCurrentRoom() {
        return currentRoom;
    }

    protected synchronized void setCurrentRoom(Room room) {
        if (room != null) {
            currentRoom = room;
        } else {
            logger.info("Passed in room was null, this shouldn't happen");
        }
    }

    public void disconnect() {
        sendConnectionStatus(myClientId, getClientName(), false);
        logger.info("Thread being disconnected by server");
        isRunning = false;
        cleanup();
    }

    // send methods
    public boolean sendGridReset() {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.GRID_RESET);
        return send(p);
    }

    public boolean sendCells(List<CellData> cells) {
        CellPayload cp = new CellPayload();
        cp.setCellData(cells);
        return send(cp);
    }

    public boolean sendGridDimensions(int x, int y) {
        PositionPayload pp = new PositionPayload();
        pp.setCoord(x, y);
        pp.setPayloadType(PayloadType.GRID); // override default payload type
        return send(pp);
    }

    public boolean sendCurrentTurn(long clientId) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.TURN);
        p.setClientId(clientId);
        return send(p);
    }

    public boolean sendCharacter(long clientId, Character character) {
        CharacterPayload cp = new CharacterPayload();
        cp.setCharacter(character);
        cp.setClientId(clientId);
        return send(cp);
    }

    public boolean sendPhaseSync(Phase phase) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.PHASE);
        p.setMessage(phase.name());
        return send(p);
    }

    public boolean sendReadyStatus(long clientId) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.READY);
        p.setClientId(clientId);
        return send(p);
    }

    public boolean sendRoomName(String name) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.JOIN_ROOM);
        p.setMessage(name);
        return send(p);
    }

    public boolean sendRoomsList(String[] rooms, String message) {
        RoomResultPayload payload = new RoomResultPayload();
        payload.setRooms(rooms);
        if (message != null) {
            payload.setMessage(message);
        }
        return send(payload);
    }

    public boolean sendExistingClient(long clientId, String clientName) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.SYNC_CLIENT);
        p.setClientId(clientId);
        p.setClientName(clientName);
        return send(p);
    }

    public boolean sendResetUserList() {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.RESET_USER_LIST);
        return send(p);
    }

    public boolean sendClientId(long id) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.CLIENT_ID);
        p.setClientId(id);
        return send(p);
    }

    public boolean sendMessage(long clientId, String message) {

        Payload p = new Payload();
        p.setPayloadType(PayloadType.MESSAGE);
        p.setClientId(clientId);
        p.setMessage(message);
        return send(p);
    }

    public boolean sendConnectionStatus(long clientId, String who, boolean isConnected) {
        Payload p = new Payload();
        p.setPayloadType(isConnected ? PayloadType.CONNECT : PayloadType.DISCONNECT);
        p.setClientId(clientId);
        p.setClientName(who);
        // p.setMessage(isConnected ? "connected" : "disconnected");
        p.setMessage(String.format("%s the room %s", (isConnected ? "Joined" : "Left"), currentRoom.getName()));
        return send(p);
    }

    private boolean send(Payload payload) {
        try {
            logger.log(Level.FINE, "Outgoing payload: " + payload);
            out.writeObject(payload);
            logger.log(Level.INFO, "Sent payload: " + payload);
            return true;
        } catch (IOException e) {
            logger.info("Error sending message to client (most likely disconnected)");
            // uncomment this to inspect the stack trace
            // e.printStackTrace();
            cleanup();
            return false;
        } catch (NullPointerException ne) {
            logger.info("Message was attempted to be sent before outbound stream was opened: " + payload);
            // uncomment this to inspect the stack trace
            // e.printStackTrace();
            return true;// true since it's likely pending being opened
        }
    }

    // end send methods
    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());) {
            this.out = out;
            isRunning = true;
            Payload fromClient;
            while (isRunning && // flag to let us easily control the loop
                    (fromClient = (Payload) in.readObject()) != null // reads an object from inputStream (null would
                                                                     // likely mean a disconnect)
            ) {

                logger.info("Received from client: " + fromClient);
                processPayload(fromClient);

            } // close while loop
        } catch (Exception e) {
            // happens when client disconnects
            e.printStackTrace();
            logger.info("Client disconnected");
        } finally {
            isRunning = false;
            logger.info("Exited thread loop. Cleaning up connection");
            cleanup();
        }
    }

    void processPayload(Payload p) {
        switch (p.getPayloadType()) {
            case CONNECT:
                setClientName(p.getClientName());
                break;
            case DISCONNECT:
                Room.disconnectClient(this, getCurrentRoom());
                break;
            // iaa47
            // 11/26/23
            case MESSAGE:
                if (currentRoom != null) {
                    String message = p.getMessage().trim();
                    if (message.startsWith("@")) {
                        // Private message format: "@username message"
                        String[] parts = message.split(" ", 2);
                        if (parts.length == 2) {
                            String receiverUsername = parts[0].substring(1); // Exclude the '@'
                            String privateMessage = parts[1];
                            // Check if the receiver is valid (exists in the room)
                            ServerThread receiverThread = currentRoom.getClientByName(receiverUsername);
                            if (receiverThread != null) {
                                // Send the private message to the sender and the receiver
                                currentRoom.sendMessageToPrivateParticipants(this, receiverThread, privateMessage);
                            } else {
                                // Handle invalid receiver username
                                sendMessage(Constants.DEFAULT_CLIENT_ID, "Invalid username: " + receiverUsername);
                            }
                        } else {
                            // Handle incorrect private message format
                            sendMessage(Constants.DEFAULT_CLIENT_ID, "Invalid private message format");
                        }
                    } else {
                        // Broadcast normal messages to the room
                        currentRoom.sendMessage(this, p.getMessage());
                    }
                } else {
                    // TODO migrate to lobby
                    logger.log(Level.INFO, "Migrating to lobby on message with null room");
                    Room.joinRoom(Constants.LOBBY, this);
                }
                break;
            case GET_ROOMS:
                Room.getRooms(p.getMessage().trim(), this);
                break;
            case CREATE_ROOM:
                Room.createRoom(p.getMessage().trim(), this);
                break;
            case JOIN_ROOM:
                Room.joinRoom(p.getMessage().trim(), this);
                break;
            case READY:
                try {
                    ((GameRoom) currentRoom).setReady(this);
                } catch (Exception e) {
                    logger.severe(String.format("There was a problem during readyCheck %s", e.getMessage()));
                    e.printStackTrace();
                }
                break;
            case CHARACTER:
                try {
                    CharacterPayload cp = (CharacterPayload) p;
                    // Here I'm making the assumption if the passed Character is null, it's likely a
                    // create request,
                    // if the passed character is not null, then some of the properties will be used
                    // for loading
                    if (cp.getCharacter() == null) {
                        ((GameRoom) currentRoom).createCharacter(this, cp.getCharacterType());
                    } else {
                        ((GameRoom) currentRoom).loadCharacter(this, cp.getCharacter());
                    }
                } catch (Exception e) {
                    logger.severe(String.format("There was a problem during character handling %s", e.getMessage()));
                    e.printStackTrace();
                }
                break;
            case MOVE:
                try {
                    PositionPayload pp = (PositionPayload) p;
                    ((GameRoom) currentRoom).handleMove(pp.getX(), pp.getY(), this);
                } catch (Exception e) {
                    logger.severe(String.format("There was a problem during position handling %s", e.getMessage()));
                    e.printStackTrace();
                }
                break;
                case MUTE:
                case UNMUTE:
                    System.out.println("Received MUTE/UNMUTE request");
                    handleMuteUnmuteRequest(p);
                    break;
            default:
                break;
        }
    }

    private void handleMuteUnmuteRequest(Payload p) {
        if (currentRoom != null) {
            String targetUsername = p.getMessage().trim();
            ServerThread targetThread = currentRoom.getClientByName(targetUsername);
    
            if (targetThread != null) {
                switch (p.getPayloadType()) {
                    case MUTE:
                        muteClient(this, targetThread); // Mute the user
                        break;
                    case UNMUTE:
                        unmuteClient(this, targetThread); // Unmute the user
                        break;
                    default:
                        // Handle other payload types if necessary
                        break;
                }
            } else {
                // Handle invalid target username
                sendMessage(Constants.DEFAULT_CLIENT_ID, "Invalid username: " + targetUsername);
            }
        } else {
            // TODO: Migrate to lobby
            logger.log(Level.INFO, "Migrating to lobby on mute/unmute with null room");
            Room.joinRoom(Constants.LOBBY, this);
        }
    }
    
    // Method to mute a client
    public void muteClient(ServerThread muter, ServerThread target) {
        synchronized (mutedClients) {
            if (!mutedClients.containsKey(target.getClientName())) {
                // Mute the client
                mutedClients.computeIfAbsent(target.getClientName(), k -> new HashSet<>()).add(muter);
                muter.sendMessage(Constants.DEFAULT_CLIENT_ID, "User @" + target.getClientName() + " has been muted.");
            } else {
                // Client is already muted
                muter.sendMessage(Constants.DEFAULT_CLIENT_ID, "User @" + target.getClientName() + " is already muted.");
            }
        }
    }
    
    // Method to unmute a client
    public void unmuteClient(ServerThread unmuter, ServerThread target) {
        synchronized (mutedClients) {
            if (mutedClients.containsKey(target.getClientName())) {
                // Unmute the client
                mutedClients.get(target.getClientName()).remove(unmuter);
                unmuter.sendMessage(Constants.DEFAULT_CLIENT_ID,
                        "User @" + target.getClientName() + " has been unmuted.");
            } else {
                // Client is not muted
                unmuter.sendMessage(Constants.DEFAULT_CLIENT_ID, "User @" + target.getClientName() + " is not muted.");
            }
        }
    }
    private void cleanup() {
        logger.info("Thread cleanup() start");
        try {
            client.close();
        } catch (IOException e) {
            logger.info("Client already closed");
        }
        logger.info("Thread cleanup() complete");
    }
}