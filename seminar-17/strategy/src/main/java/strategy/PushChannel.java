package strategy;

import core.Notification;
import core.NotificationChannel;

public class PushChannel implements NotificationChannel {
    @Override
    public boolean send(Notification n) {
        System.out.printf("[PUSH]  device=%s title=%s body=%s%n",
                n.recipient(), n.subject(), n.body());
        return true;
    }
}
