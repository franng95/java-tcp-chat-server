import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MultiClientServer {
    
    // This handles ONE client in its own thread
    static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private int clientNumber;
        
        public ClientHandler(Socket socket, int clientNumber) {
            this.clientSocket = socket;
            this.clientNumber = clientNumber;
        }
        
        public void run() {
            try {
                System.out.println("Client " + clientNumber + " connected!");
                
                // Setup input/output streams
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
                );
                
                // Send welcome message
                out.println("Hello! You are client #" + clientNumber);
                
                // Read messages from client
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Client " + clientNumber + " says: " + message);
                    out.println("Server received: " + message);
                    
                    if (message.equals("bye")) {
                        break;
                    }
                }
                
                // Cleanup
                in.close();
                out.close();
                clientSocket.close();
                System.out.println("Client " + clientNumber + " disconnected!");
                
            } catch (Exception e) {
                System.out.println("Error with client " + clientNumber + ": " + e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Multi-client server started on port 5000");
            System.out.println("Waiting for clients...");
            
            int clientCounter = 0;
            
            // INFINITE LOOP - keeps accepting new clients
            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientCounter++;
                
                // Create a new thread for this client
                ClientHandler handler = new ClientHandler(clientSocket, clientCounter);
                Thread thread = new Thread(handler);
                thread.start();
                
                System.out.println("Started thread for client " + clientCounter);
            }
            
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}