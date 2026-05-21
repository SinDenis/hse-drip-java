package decorator;

import core.Notification;
import core.NotificationChannel;

public class RetryChannel implements NotificationChannel {
    private final NotificationChannel delegate;
    private final int attempts;

    public RetryChannel(NotificationChannel delegate, int attempts) {
        if (attempts < 1) throw new IllegalArgumentException("attempts must be >= 1");
        this.delegate = delegate;
        this.attempts = attempts;
    }

    @Override
    public boolean send(Notification n) {
        for (int i = 0; i < attempts; i++) {
            if (delegate.send(n)) return true;
        }
        return false;
    }
}
