import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;

public class SimpleServer {
    public static void main(String[] args) {
        try {
            // Create a server that listens on port 5000
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started! Waiting for client...");

            // Wait for client to connect (this blocks until someone connects)
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            // Create a way to send messages to the client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Send a message
            out.println("Hello from server!");

            // Close everithing
            out.close();
            clientSocket.close();
            serverSocket.close();

            System.out.println("Server finished");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}