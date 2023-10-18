package M4.Part3HW;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * A server-side representation of a single client
 */
public class ServerThread extends Thread {
    private Socket client;
    private boolean isRunning = false;
    private ObjectOutputStream out;//exposed here for send()
    private Server server;// ref to our server so we can call methods on it
    // more easily

    private void info(String message) {
        System.out.println(String.format("Thread[%s]: %s", getId(), message));
    }

    public ServerThread(Socket myClient, Server server) {
        info("Thread created");
        // get communication channels to single client
        this.client = myClient;
        this.server = server;

    }

    public void disconnect() {
        info("Thread being disconnected by server");
        isRunning = false;
        cleanup();
    }

    public boolean send(String message) {
        // added a boolean so we can see if the send was successful
        try {
            out.writeObject(message);
            return true;
        } catch (IOException e) {
            info("Error sending message to client (most likely disconnected)");
            // comment this out to inspect the stack trace
            // e.printStackTrace();
            cleanup();
            return false;
        }
    }

    @Override
    public void run() {
        info("Thread starting");
        try (ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());) {
            this.out = out;
            isRunning = true;
            String fromClient;
            while (isRunning && // flag to let us easily control the loop
                    (fromClient = (String) in.readObject()) != null // reads an object from inputStream (null would
                                                                    // likely mean a disconnect)
            ) {

                info("Received from client: " + fromClient);
                server.broadcast(fromClient, this.getId());
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

    private void cleanup() {
        info("Thread cleanup() start");
        try {
            client.close();
        } catch (IOException e) {
            info("Client already closed");
        }
        info("Thread cleanup() complete");
    }
    boolean processCommand(String message) {
        // coin toss command
        if (message.equalsIgnoreCase("flip") || message.equalsIgnoreCase("toss") || message.equalsIgnoreCase("coin")) {
            String result = flipCoin();
            server.broadcast(String.format("User[%d] flipped a coin and got %s", getId(), result), getId());
            return true;
        }
        // dice roll command
        if (message.matches("^roll \\d+d\\d+$")) {
            String[] tokens = message.split(" ");
            String[] diceTokens = tokens[1].split("d");
            int numDice = Integer.parseInt(diceTokens[0]);
            int numSides = Integer.parseInt(diceTokens[1]);
            String result = rollDice(numDice, numSides);
            server.broadcast(String.format("User[%d] rolled %s and got %s", getId(), tokens[1], result), getId());
            return true;
        }
        return false;
    }

    private String flipCoin() {
        Random random = new Random();
        return random.nextBoolean() ? "heads" : "tails";
    }

    private String rollDice(int numDice, int numSides) {
        Random random = new Random();
        int total = 0;
        for (int i = 0; i < numDice; i++) {
            total += random.nextInt(numSides) + 1;
        }
        return Integer.toString(total);
    }
}
