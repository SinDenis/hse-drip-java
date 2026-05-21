package task2;

public class EmailChannel {

    public boolean send(String recipient, String subject, String body) {
        System.out.println("[LOG] sending email to=" + recipient);
        System.out.printf("[EMAIL] to=%s subject=%s body=%s%n", recipient, subject, body);
        boolean result = true;
        System.out.println("[LOG] email result=" + result);
        return result;
    }
}
