package task2;

public class PushChannel {

    public boolean send(String deviceToken, String title, String body) {
        System.out.println("[LOG] sending push to=" + deviceToken);
        System.out.printf("[PUSH]  device=%s title=%s body=%s%n", deviceToken, title, body);
        boolean result = true;
        System.out.println("[LOG] push result=" + result);
        return result;
    }
}
