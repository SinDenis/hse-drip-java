package factorymethod;

import core.Notification;
import core.NotificationChannel;

public abstract class NotificationFactory {
    protected abstract NotificationChannel createChannel();

    public boolean send(Notification notification) {
        NotificationChannel channel = createChannel();
        return channel.send(notification);
    }
}
