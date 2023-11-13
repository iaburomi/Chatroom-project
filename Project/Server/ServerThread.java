package Project.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Random;

import Project.Commons.Payload;
import Project.Commons.PayloadType;

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

    private void info(String message) {
        System.out.println(String.format("Thread[%s]: %s", getId(), message));
    }

    public ServerThread(Socket myClient, Room room) {
        info("Thread created");
        // get communication channels to single client
        this.client = myClient;
        this.currentRoom = room;

    }

    protected void setClientName(String name) {
        if (name == null || name.isBlank()) {
            System.err.println("Invalid client name being set");
            return;
        }
        clientName = name;
    }

    protected String getClientName() {
        return clientName;
    }

    protected synchronized Room getCurrentRoom() {
        return currentRoom;
    }

    protected synchronized void setCurrentRoom(Room room) {
        if (room != null) {
            currentRoom = room;
        } else {
            info("Passed in room was null, this shouldn't happen");
        }
    }

    public void disconnect() {
        info("Thread being disconnected by server");
        isRunning = false;
        cleanup();
    }

    // send methods
    public boolean sendMessage(String from, String message) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.MESSAGE);
        p.setClientName(from);
        p.setMessage(message);
        return send(p);
    }

    public boolean sendConnectionStatus(String who, boolean isConnected) {
        Payload p = new Payload();
        p.setPayloadType(isConnected ? PayloadType.CONNECT : PayloadType.DISCONNECT);
        p.setClientName(who);
        p.setMessage(isConnected ? "connected" : "disconnected");
        return send(p);
    }

    private boolean send(Payload payload) {
        // added a boolean so we can see if the send was successful
        try {
            out.writeObject(payload);
            return true;
        } catch (IOException e) {
            info("Error sending message to client (most likely disconnected)");
            // comment this out to inspect the stack trace
            // e.printStackTrace();
            cleanup();
            return false;
        } catch (NullPointerException ne) {
            info("Message was attempted to be sent before outbound stream was opened");
            return true;// true since it's likely pending being opened
        }
    }

    // end send methods
    @Override
    public void run() {
        info("Thread starting");
        try (ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());) {
            this.out = out;
            isRunning = true;
            Payload fromClient;
            while (isRunning && // flag to let us easily control the loop
                    (fromClient = (Payload) in.readObject()) != null // reads an object from inputStream (null would
                                                                     // likely mean a disconnect)
            ) {

                info("Received from client: " + fromClient);
                processMessage(fromClient);

            } // close while loop
        } catch (Exception e) {
            // happens when client disconnects
            e.printStackTrace();
            info("Client disconnected");
        } finally {
            isRunning = false;
            info("Exited thread loop. Cleaning up connection");
            cleanup();
        }
    }

    void processMessage(Payload p) {
        switch (p.getPayloadType()) {
            case CONNECT:
                setClientName(p.getClientName());
                break;
            case DISCONNECT://TBD
                break;
            case MESSAGE:
                if (currentRoom != null) {
                    if (p.getMessage().startsWith("/answer")) {
                        handleAnswerSelection(p);
                    } else if (p.getMessage().equalsIgnoreCase("/ready")) {
                        handleReady();
                    } else {
                        currentRoom.sendMessage(this, p.getMessage());
                    }
                } else {
                    Room.joinRoom("lobby", this);
                }
                break;
            case READY:
            // Handle READY payload
                handleReady(p);
                break;
            case PICK:
            // Handle PICK payload
                handlePick(p);
                break;
            case PASS:
            // Handle PASS payload
                handlePass(p);
                break;
            case SCORE:
                // Handle SCORE payload
                handleScore(p);
                break;
            default:
                break;
    }
}
private void handleReady() {
    }

private void handleAnswerSelection(Payload p) {
    // Handle the answer selection logic
    // You can record the selected answer and selection order in the server
    // Example: server.recordAnswerSelection(p.getSelectedAnswer(), p.getSelectionOrder());
}

    private boolean isReady = false;

    public boolean isReady() {
        return isReady;
    }
    public ServerThread() {
    }

    private void handleReady(Payload p) {
        boolean readyStatus = Boolean.parseBoolean(p.getMessage());

        // Update the ready status for the client
        isReady = readyStatus;

        // Broadcast the ready status to the current room
        if (getCurrentRoom() != null) {
            getCurrentRoom().sendMessage(this, p.getMessage());
        }

    }

    private void handlePick(Payload p) {
        if (getCurrentRoom() != null) {
            // Check if the client is ready before picking a question
            if (isReady()) {
                // Get a random question from the available questions
                String randomQuestion = getRandomQuestion();
    
                // Broadcast the selected question to all clients in the current room
                getCurrentRoom().sendMessage(this, "Picked question: " + randomQuestion);
    
                // Additional logic if needed
            } else {
                // Inform the client that they need to be ready to pick a question
                sendMessage("Server", "You must be ready to pick a question.");
            }
        }
    }
    private List<String> availableQuestions;
    private String getRandomQuestion() {
        if (availableQuestions != null && !availableQuestions.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(availableQuestions.size());
            return availableQuestions.get(randomIndex);
        } else {
            return "No questions available.";}
        }

    private void handlePass(Payload p) {
        // Check if the current room is not null
    if (getCurrentRoom() != null) {
        // Check if the client is ready before passing the question
        if (isReady()) {
            // Get the current question from the Payload
            String passedQuestion = p.getMessage();

            // Broadcast the passed question to all clients in the current room
            getCurrentRoom().sendMessage(this, "Passed question: " + passedQuestion);

            // Additional logic if needed
        } else {
            // Inform the client that they must be ready to pass a question
            sendMessage("Server", "You must be ready to pass a question.");
        }
    }
    }

    private void handleScore(Payload p) {
        if (getCurrentRoom() != null) {
            // Check if the client is ready before awarding a point
            if (isReady()) {
                // Increment the client's score (assuming each correct answer earns one point)
                incrementScore();
    
                // Broadcast the updated score to all clients in the current room
                getCurrentRoom().sendMessage(this, "Scored a point! Current score: " + getScore());
    
                // Additional logic if needed
            } else {
                // Inform the client that they must be ready to score a point
                sendMessage("Server", "You must be ready to score a point.");
            }
        }
    }
    private String getScore() {
        return null;
    }

    private void incrementScore() {
        // Increment the score by 1 (modify as needed based on your scoring logic)
        // This assumes that the 'number' field in Payload is being used to represent the score
        int currentScore = getNumber();
        setNumber(currentScore + 1);
    }

    private void setNumber(int i) {
    }

    private int getNumber() {
        return 0;
    }

    private void cleanup() {
        info("Thread cleanup() start");
        try {
            client.close();
        } catch (IOException e) {
            info("Client already closed");
        }
        info("Thread cleanup() complete");
    }

    public boolean send(String from, Payload payload) {
        return false;
    }
}
