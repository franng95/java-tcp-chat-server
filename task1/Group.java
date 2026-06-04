import java.util.ArrayList;
import java.util.List;

public class Group {
    private List<Observer> members;
    private Observer coordinator;
    private List<Message> messageLog = new ArrayList<>();

    public Group() {
        this.members = new ArrayList<>();
        this.coordinator = null;
    }

    public synchronized void addMember (Observer client) {
        members.add(client);
        if (coordinator == null) {
            coordinator = client;
            client.sendMessage("You are the coordinator of this group.");
        }
        broadcast(client.getClientId() + " has joined the group.", null);
    }

    public synchronized void removeMember(Observer client) {
        members.remove(client);
        broadcast(client.getClientId() + " has left the group.", null);
        if (client == coordinator) {
            assignNewCoordinator();
        }
    }

    public synchronized void broadcast(String message, Observer sender) {
        for (Observer member : members) {
            if (member != sender) {
                member.sendMessage(message);
            }
        }
    }

    private void assignNewCoordinator() {
        if (members.size() > 0) {
            coordinator = members.get(0);
            coordinator.sendMessage("You are now the coordinator!");
            broadcast(coordinator.getClientId() + " is the new coordinator.", null);
        } else {
            coordinator = null;
        }
    }

    public synchronized void logMessage(Message message) {
        messageLog.add(message);
        System.out.println("[LOG] " + message.getFormattedTime() + " " + message.toString());
    }

    public synchronized List<Observer> getMembers() {
        return members;
    }

    public Observer getCoordinator() {
        return coordinator;
    }

    public void startPing() {
        Thread pingThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(20000);
                    synchronized (members) {
                        List<Observer> deadClients = new ArrayList<>();
                        for (Observer member : members) {
                            if (!member.isAlive()) {
                                deadClients.add(member);
                            }
                        }
                        for (Observer dead : deadClients) {
                            System.out.println(dead.getClientId() + " failed ping. Removing");
                            removeMember(dead);
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("Ping thread interrupted.");
                }
            }
        });
        pingThread.setDaemon(true);
        pingThread.start();
    }
}