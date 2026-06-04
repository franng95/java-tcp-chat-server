public interface Observer {
    void sendMessage(String message);
    String getClientId();
    boolean isAlive();
    String getAddress();
}