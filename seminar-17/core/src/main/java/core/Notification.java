package core;

public record Notification(
        String recipient,
        String subject,
        String body
) {
}
