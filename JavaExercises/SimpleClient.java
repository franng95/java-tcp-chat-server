import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SimpleClient {
    public static void main(String[] args) {
        try {
            // Connect to server at localhost (same computer) on port 5000
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server!");

            // Create a way to receive messages from server
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            // Read the message
            String message = in.readLine();
            System.out.println("Server said: " + message);

            // Close everithing
            in.close();
            socket.close();

            System.out.println("Client finished!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}