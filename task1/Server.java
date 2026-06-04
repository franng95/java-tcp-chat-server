import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 5000;
    private static int clientCounter = 0;

    public static void main(String[] args) {
        Group group = new Group();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("=== CHAT SERVER STARTED ===");
            System.out.println("Listening on port " + PORT);
            System.out.println("Waiting for clients...\n");
            group.startPing();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientCounter++;
                String clientId = "Client-" + clientCounter;

                System.out.println(clientId + " connected!");

                ClientHandler handler = new ClientHandler(clientSocket, clientId, group);
                Thread thread = new Thread(handler);
                thread.start();
            }

        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}