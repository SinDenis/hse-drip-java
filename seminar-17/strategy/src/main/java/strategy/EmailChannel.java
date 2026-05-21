package strategy;

import core.Notification;
import core.NotificationChannel;

public class EmailChannel implements NotificationChannel {
    @Override
    public boolean send(Notification n) {
        System.out.printf("[EMAIL] to=%s subject=%s body=%s%n",
                n.recipient(), n.subject(), n.body());
        return true;
    }
}
