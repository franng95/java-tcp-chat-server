import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientHandler implements Runnable, Observer {
    private Socket socket;
    private String clientId;
    private PrintWriter out;
    private BufferedReader in;
    private Group group;

    public ClientHandler(Socket socket, String clientId, Group group) {
        this.socket = socket;
        this.clientId = clientId;
        this.group = group;
    }

    public String getClientId() {
        return clientId;
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void run() {
        try {
            // Setup streams
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            group.addMember(this);
            sendMessage("Welcome! your ID is: " + clientId);

            Observer coord = group.getCoordinator();
            if (coord != null && coord != this) {
                sendMessage("Current coordinator is: " + coord.getClientId() + " | " + coord.getAddress());
            }

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received from " + clientId + ": " + message);

                if (message.equalsIgnoreCase("quit")) {
                    break;
                } else if (message.startsWith("/")) {
                    handleCommand(message);
                } else {
                    Message msg = new Message(clientId, message);
                    group.logMessage(msg);
                    group.broadcast(msg.toString(), this);
                }
            }
        } catch (Exception e) {
            System.out.println("Client " + clientId + " disconnected.");
        } finally {
            cleanup();
        }
    }

    private void handleCommand(String command) {
        if (command.equalsIgnoreCase("/list")) {
            sendMessage("Connected clients:");
            for (Observer member : group.getMembers()) {
                String label = member.getClientId() + " | " + member.getAddress();
                if (member == group.getCoordinator()) {
                    sendMessage(label + " (Coordinator)");
                } else {
                    sendMessage(label);
                }
            }
        } else if (command.startsWith("/msg")) {
            String[] parts = command.split(" ", 3);
            if (parts.length < 3) {
                sendMessage("Usage: /msg <clientId> <message>");
            } else {
                String targetId = parts[1];
                String messageContent = parts[2];
                boolean found = false;
                for (Observer member : group.getMembers()) {
                    if (member.getClientId().equals(targetId)) {
                        member.sendMessage("[Private from " + clientId + "]: " + messageContent);
                        sendMessage("[Private to " + targetId + "]: " + messageContent);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    sendMessage("Client " + targetId + " not found.");
                }
            }
        } else {
            sendMessage("Unknown command: " + command);
        }
    }

    private void cleanup() {
        try {
            group.removeMember(this);
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
            
        } catch (Exception e) {
            System.out.println("Error during cleanup: " + e.getMessage());
        }
    }

    public boolean isAlive() {
        return socket != null && !socket.isClosed();
    }

    public String getAddress() {
        return socket.getInetAddress().getHostAddress() + ":" + socket.getPort(); 
    }
}