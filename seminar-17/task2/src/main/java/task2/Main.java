package task2;

public class Main {
    public static void main(String[] args) {
        new EmailChannel().send("alice@example.com", "Hello", "Welcome!");
        new SmsChannel().send("alice@example.com", "Welcome!");
        new PushChannel().send("device-token-123", "Hello", "Welcome!");
    }
}
