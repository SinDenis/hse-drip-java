package task1;

public class NotificationSender {

    public void send(String type, String recipient, String subject, String body) {
        if (type.equals("EMAIL")) {
            System.out.printf("[EMAIL] to=%s subject=%s body=%s%n", recipient, subject, body);
        } else if (type.equals("SMS")) {
            System.out.printf("[SMS]   to=%s body=%s%n", recipient, body);
        } else if (type.equals("PUSH")) {
            System.out.printf("[PUSH]  device=%s title=%s body=%s%n", recipient, subject, body);
        } else {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}
