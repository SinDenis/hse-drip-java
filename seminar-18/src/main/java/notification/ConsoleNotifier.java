package notification;

public class ConsoleNotifier implements Notifier {

    @Override
    public void send(String to, String message) {
        System.out.println("[notification to " + to + "] " + message);
    }
}
