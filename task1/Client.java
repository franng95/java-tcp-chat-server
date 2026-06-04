import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverAddress = args.length > 0 ? args[0] : "localhost";
        int serverPort = args.length > 1 ? Integer.parseInt(args[1]) : 5000;

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nDisconnecting...");
        }));

        try{
            Socket socket = new Socket(serverAddress, serverPort);
            System.out.println("Connected to chat server!");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // This is to receive the messages from the server
            Thread receiveThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) !=null) {
                        System.out.println(message);
                    }
                } catch (Exception e) {
                    System.out.println("Disconnected from server");
                }
            });
            receiveThread.start();

            // Main threads sends messages
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type your messages (Type 'quit' to exit:");

            while (true) {
                String message = scanner.nextLine();
                out. println(message);

                if (message.equalsIgnoreCase("quit")) {
                    break;
                }
            }

            scanner.close();
            socket.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}