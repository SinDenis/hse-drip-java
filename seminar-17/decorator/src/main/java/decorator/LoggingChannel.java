package decorator;

import core.Notification;
import core.NotificationChannel;

public class LoggingChannel implements NotificationChannel {
    private final NotificationChannel delegate;

    public LoggingChannel(NotificationChannel delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean send(Notification n) {
        System.out.println("[LOG] sending to=" + n.recipient());
        boolean result = delegate.send(n);
        System.out.println("[LOG] result=" + result);
        return result;
    }
}
