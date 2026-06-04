import java.util.ArrayList;
import java.util.List;

public class MockClient implements Observer {
    private String clientId;
    private List<String> receivedMessages = new ArrayList<>();
    private boolean alive = true;

    public MockClient(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public void sendMessage(String message) {
        receivedMessages.add(message);
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public String getAddress() {
        return "test-address";
    }

    public List<String> getReceivedMessages() {
        return receivedMessages;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}