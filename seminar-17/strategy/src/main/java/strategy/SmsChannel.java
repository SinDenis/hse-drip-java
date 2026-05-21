package strategy;

import core.Notification;
import core.NotificationChannel;

public class SmsChannel implements NotificationChannel {
    @Override
    public boolean send(Notification n) {
        System.out.printf("[SMS]   to=%s body=%s%n",
                n.recipient(), n.body());
        return true;
    }
}
