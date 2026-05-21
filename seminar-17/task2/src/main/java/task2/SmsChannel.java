package task2;

public class SmsChannel {

    public boolean send(String recipient, String body) {
        System.out.println("[LOG] sending sms to=" + recipient);
        System.out.printf("[SMS]   to=%s body=%s%n", recipient, body);
        boolean result = true;
        System.out.println("[LOG] sms result=" + result);
        return result;
    }
}
