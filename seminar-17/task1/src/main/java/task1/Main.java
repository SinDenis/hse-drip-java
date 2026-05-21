package task1;

public class Main {
    public static void main(String[] args) {
        NotificationSender sender = new NotificationSender();
        sender.send("EMAIL", "alice@example.com", "Hello", "Welcome!");
        sender.send("SMS",   "alice@example.com", "Hello", "Welcome!");
        sender.send("PUSH",  "device-token-123",  "Hello", "Welcome!");
    }
}
