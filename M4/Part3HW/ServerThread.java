package M4.Part3HW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class ServerThread extends Thread {
    private Socket socket;
    private Server server;
    private PrintWriter out;

    public ServerThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            this.out = out;
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(String.format("Message from [%d]: %s", getId(), message));
                if (message.equalsIgnoreCase("disconnect")) {
                    server.disconnect(this);
                    break;
                }
                if (processCommand(message)) {
                    // command processed successfully
                } else {
                    // broadcast message to all clients
                    server.broadcast(message, getId());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean send(String message) {
        if (out != null && !out.checkError()) {
            out.println(message);
            return true;
        }
        return false;
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean processCommand(String message) {
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
