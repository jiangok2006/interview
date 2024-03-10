package discord;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.io.*;

/*
 * - The chat server should be capable of handling multiple clients connecting at the same time. -
 * Disconnection should be handled in a clean way. - Should be able to receive and forward messages
 * among clients (message is not echoed back to sender).
 * 
 * netcat tcp socket
 */
public class Server {
    class ClientThread extends Thread {
        Client sender;
        List<Client> clients;

        public ClientThread(Client sender, List<Client> clients) {
            this.sender = sender;
            this.clients = clients;
        }

        public void run() {
            BufferedReader in;
            PrintWriter out;
            try {
                in = new BufferedReader(
                        new InputStreamReader(sender.clientSocket.getInputStream()));
                String greeting = in.readLine();

                if (greeting.equals("\n")) {
                    // client disconnection
                    sender.dispose();
                    clients.remove(sender);
                }

                for (int i = 0; i < clients.size(); ++i) {
                    Client cur = clients.get(i);
                    if (cur.id != sender.id) {
                        out = new PrintWriter(cur.clientSocket.getOutputStream(), true);
                        out.println(String.format("%s: %s", cur.id, greeting));
                    }
                }
            } catch (Exception e) {
                // Throwing an exception
                System.out.println("Exception is caught");
            }
        }
    }

    class Client {
        public UUID id;
        public Socket clientSocket;
        public ClientThread thread;

        public Client(Socket clientSocket, List<Client> clients) {
            id = UUID.randomUUID();
            this.clientSocket = clientSocket;
            thread = new ClientThread(this, clients);
            thread.start();
        }

        public void dispose() throws IOException {
            clientSocket.close();
        }
    }

    private ServerSocket serverSocket;
    private List<Client> clients = new ArrayList<>();

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Client client = new Client(clientSocket, clients);
                clients.add(client);
            }
        } catch (Exception ex) {
            System.out.println(String.format("%s, %s", ex.getMessage(), ex.getStackTrace()));
        }
    }

    public void stop() {
        try {
            // clientSocket.close();
            for (Client c : clients) {
                c.dispose();
            }
            clients.clear();
            serverSocket.close();
        } catch (Exception ex) {
            System.out.println(String.format("%s, %s", ex.getMessage(), ex.getStackTrace()));
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start(6666);
    }
}
