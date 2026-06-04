public class Message {
    private String sender;
    private String content;
    private long timestamp;

    public Message(String sender, String content) {
        this.sender =sender;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getFormattedTime() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
        return sdf.format(new java.util.Date(timestamp));
    }

    @Override
    public String toString() {
        return "[" + sender + "]: " + content;
    }
}