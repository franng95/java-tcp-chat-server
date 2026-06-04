import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class InteractiveClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server!");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            // Read welcome message from server
            String welcome = in.readLine();
            System.out.println("Server: " + welcome);

            // Setup Scanner to read input
            Scanner scanner = new Scanner(System.in);

            System.out.println("Type messages (type 'bye' to quit):");

            while (true) {
                System.out.print("> ");
                String message = scanner.nextLine();

                // send to server
                out.println(message);

                // Get response
                String response = in.readLine();
                System.out.println("Server: " + response);

                if (message.equals("bye")) {
                    break;
                }
            }

            scanner.close();
            in.close();
            socket.close();
            System.out.println("Disconnected");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}