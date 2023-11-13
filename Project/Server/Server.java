package Project.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Project.Commons.AnswerSelection;
import Project.Commons.Payload;
import Project.Commons.PayloadType;
import Project.Commons.Question;


public class Server {
    int port = 3001;
    // connected clients
    // private List<ServerThread> clients = new ArrayList<ServerThread>();
    private List<Room> rooms = new ArrayList<Room>();
    private List<Question> questions = new ArrayList<>();
    private List<AnswerSelection> answerSelections = new ArrayList<>();
    private Room lobby = null;// default room
    private Timer roundTimer;
    private int roundDuration = 60; // Round duration in seconds
    private boolean roundInProgress = false;
    private List<ServerThread> readyPlayers = new ArrayList<>();

    protected synchronized void playerReady(ServerThread player) {
        if (!readyPlayers.contains(player)) {
            readyPlayers.add(player);
        }
    }
    public Server() {
    }

    private void initializeQuestions() {
        questions.add(new Question("General Knowledge", "What is the capital of France?"));
        questions.add(new Question("Science", "What is the chemical symbol for gold?"));
    }
    private Question pickRandomQuestion() {
        Random random = new Random();
        int randomCategoryIndex = random.nextInt(questions.size());
        Question randomQuestion = questions.get(randomCategoryIndex);
        return randomQuestion;
    }
    private void start(int port) {
        this.port = port;
        // server listening
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            Socket incoming_client = null;
            System.out.println("Server is listening on port " + port);
            // Reference server statically
            Room.server = this;// all rooms will have the same reference
            // create a lobby on start
            lobby = new Room("Lobby");
            initializeQuestions();
            rooms.add(lobby);
            startRoundTimer();
            do {
                // Check if round has ended
                if (roundTimerExpired() || allPlayersPicked()) {
                    handleRoundEnd();
                    // Start a new round
                    startRoundTimer();
                } } while ((incoming_client = serverSocket.accept()) != null);
            do {
                // Pick a random question for the round
                handleAnswerSelections();
                Question randomQuestion = pickRandomQuestion();
                broadcast("New round! Category: " + randomQuestion.getCategory() + "\nQuestion: " + randomQuestion.getContent());
            } while ((incoming_client = serverSocket.accept()) != null);
            do {
                // Pick a random question for the round
                Question randomQuestion = pickRandomQuestion();
                // Get potential answers for the question (you need to implement this)
                List<String> potentialAnswers = getPotentialAnswers(randomQuestion);
                // Broadcast category, question, and potential answers
                broadcast("New round! Category: " + randomQuestion.getCategory() + "\nQuestion: " + randomQuestion.getContent());
                broadcastPotentialAnswers(potentialAnswers);
                // Other existing logic or commands can go here
            } while ((incoming_client = serverSocket.accept()) != null);
            do {
                System.out.println("waiting for next client");
                if (incoming_client != null) {
                    System.out.println("Client connected");
                    ServerThread sClient = new ServerThread(incoming_client, lobby);
                    sClient.start();
                    joinRoom("lobby", sClient);
                    incoming_client = null;
                }
            } while ((incoming_client = serverSocket.accept()) != null);
        } catch (IOException e) {
            System.err.println("Error accepting connection");
            e.printStackTrace();
        } finally {
            System.out.println("closing server socket");
        }
    }
    private void startRoundTimer() {
        roundTimer = new Timer();
        roundTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Round expired, handle accordingly
                handleRoundEnd();
                // Start a new round
                startRoundTimer();
            }
        }, roundDuration * 1000); // Convert seconds to milliseconds
        roundInProgress = true;
    }
    private boolean roundTimerExpired() {
        // Check if the round timer has expired
        return !roundInProgress;
    }
    private boolean allPlayersPicked() {
        return false; // Placeholder, implement based on your requirements
    }
    private void handleRoundEnd() {
        roundInProgress = false;
        // Clear the list of ready players for the next round
        readyPlayers.clear();
    }
    private List<String> getPotentialAnswers(Question question) {
        return List.of("Option A", "Option B", "Option C", "Option D");
    }
    private void broadcastPotentialAnswers(List<String> potentialAnswers) {
        Payload payload = new Payload();
        payload.setPayloadType(PayloadType.MESSAGE);
        payload.setMessage("Potential Answers:");
        payload.setPotentialAnswers(potentialAnswers);
        broadcast(payload);
    }
    private void broadcast(Payload payload) {
    }
    /***
     * Helper function to check if room exists by case insensitive name
     * 
     * @param roomName The name of the room to look for
     * @return matched Room or null if not found
     */
    private void handleAnswerSelections() {
    }
    protected synchronized void recordAnswerSelection(String selectedAnswer, int selectionOrder) {
        answerSelections.add(new AnswerSelection(selectedAnswer, selectionOrder));
    }
    private Room getRoom(String roomName) {
        for (int i = 0, l = rooms.size(); i < l; i++) {
            if (rooms.get(i).getName().equalsIgnoreCase(roomName)) {
                return rooms.get(i);
            }
        }
        return null;
    }

    /***
     * Attempts to join a room by name. Will remove client from old room and add
     * them to the new room.
     * 
     * @param roomName The desired room to join
     * @param client   The client moving rooms
     * @return true if reassign worked; false if new room doesn't exist
     */
    protected synchronized boolean joinRoom(String roomName, ServerThread client) {
        Room newRoom = roomName.equalsIgnoreCase("lobby")?lobby:getRoom(roomName);
        Room oldRoom = client.getCurrentRoom();
        if (newRoom != null) {
            if (oldRoom != null) {
                System.out.println(client.getName() + " leaving room " + oldRoom.getName());
                oldRoom.removeClient(client);
            }
            System.out.println(client.getName() + " joining room " + newRoom.getName());
            newRoom.addClient(client);
            return true;
        } else {
            client.sendMessage("Server",
                    String.format("Room %s wasn't found, please try another", roomName));
        }
        return false;
    }

    /***
     * Attempts to create a room with given name if it doesn't exist already.
     * 
     * @param roomName The desired room to create
     * @return true if it was created and false if it exists
     */
    protected synchronized boolean createNewRoom(String roomName) {
        if (getRoom(roomName) != null) {
    
            System.out.println(String.format("Room %s already exists", roomName));
            return false;
        } else {
            Room room = new Room(roomName);
            rooms.add(room);
            System.out.println("Created new room: " + roomName);
            return true;
        }
    }
    public synchronized void switchRoom(String roomName, ServerThread serverThread) {
        Room currentRoom = findRoomByClient(serverThread);
        if (currentRoom != null) {
            currentRoom.removeClient(serverThread);
        }
        Room targetRoom = findRoomByName(roomName);
        if (targetRoom == null) {
            targetRoom = new Room(roomName);
            rooms.add(targetRoom);
        }
        targetRoom.addClient(serverThread);
    }
    private Room findRoomByClient(ServerThread serverThread) {
        for (Room room : rooms) {
            if (room.containsClient(serverThread)) {
                return room;
            }
        }
        return null;
    }

    private Room findRoomByName(String roomName) {
        for (Room room : rooms) {
            if (room.getName().equals(roomName)) {
                return room;
            }
        }
        return null;
    }

    protected synchronized void removeRoom(Room r) {
        if (rooms.removeIf(room -> room == r)) {
            System.out.println("Removed empty room " + r.getName());
        }
    }

    protected synchronized void broadcast(String message) {
        if (processCommand(message)) {

            return;
        }
        // loop over rooms and send out the message
        Iterator<Room> it = rooms.iterator();
        while (it.hasNext()) {
            Room room = it.next();
            if (room != null) {
                room.sendMessage(null, message);
            }
        }
    }

    private boolean processCommand(String message) {
        System.out.println("Checking command: " + message);
        //
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Starting Server");
        Server server = new Server();
        int port = 3000;
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
            // can ignore, will either be index out of bounds or type mismatch
            // will default to the defined value prior to the try/catch
        }
        server.start(port);
        System.out.println("Server Stopped");
    }
}