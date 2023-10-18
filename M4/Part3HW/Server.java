package M4.Part3HW;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Server {
    int port = 3001; // Default port
    private List<ServerThread> clients = new ArrayList<>();

    private void start(int port) {
        this.port = port;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                System.out.println("Waiting for the next client");
                Socket incomingClient = serverSocket.accept();
                System.out.println("Client connected");

                ServerThread clientThread = new ServerThread(incomingClient, this);
                clients.add(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
            System.err.println("Error accepting connection");
            e.printStackTrace();
        } finally {
            System.out.println("Closing server socket");
        }
    }

    protected synchronized void disconnect(ServerThread client) {
        long id = client.getId();
        client.disconnect();
        broadcast("Disconnected", id);
    }

    protected synchronized void broadcast(String message, long id) {
        if (processCommand(message, id)) {
            return;
        }
        message = String.format("User[%d]: %s", id, message);

        Iterator<ServerThread> it = clients.iterator();
        while (it.hasNext()) {
            ServerThread client = it.next();
            boolean wasSuccessful = client.send(message);
            if (!wasSuccessful) {
                System.out.println(String.format("Removing disconnected client[%s] from the list", client.getId()));
                it.remove();
                broadcast("Disconnected", client.getId());
            }
        }
    }

    private boolean processCommand(String message, long clientId) {
        if (message.equalsIgnoreCase("disconnect")) {
            Iterator<ServerThread> it = clients.iterator();
            while (it.hasNext()) {
                ServerThread client = it.next();
                if (client.getId() == clientId) {
                    it.remove();
                    disconnect(client);
                    return true;
                }
            }
            //roll dice
            //iaa47
        } else if (message.matches("^roll \\d+d\\d+$")) {
            String[] tok = message.split(" ");
            String[] diceTok = tok[1].split("d");
            int numDice = Integer.parseInt(diceTok[0]);
            int numSides = Integer.parseInt(diceTok[1]);
            String result = rollDice(numDice, numSides);
            broadcast(String.format("User[%d] rolled %s and got %s", clientId, tok[1], result), clientId);
            return true;
            //flip coin
            //iaa47
        } else if (message.equalsIgnoreCase("flip") || message.equalsIgnoreCase("toss") || message.equalsIgnoreCase("coin")) {
            Random random = new Random();
            String result = random.nextBoolean() ? "heads" : "tails";
            broadcast(String.format("User[%d] flipped a coin and got %s", clientId, result), clientId);
            return true;
        }
        return false;
    }

    private String rollDice(int numDice, int numSides) {
        Random random = new Random();
        int total = 0;
        for (int i = 0; i < numDice; i++) {
            total += random.nextInt(numSides) + 1;
        }
        return Integer.toString(total);
    }

    public static void main(String[] args) {
        System.out.println("Starting Server");
        Server server = new Server();
        int port = 3001; // Default port
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port number. Using the default port (3001).");
            }
        }
        server.start(port);
        System.out.println("Server Stopped");
    }
}
