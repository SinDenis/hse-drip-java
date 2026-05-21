package facade;

import core.Notification;
import core.NotificationChannel;
import strategy.EmailChannel;
import strategy.SmsChannel;

import java.util.Map;

class ChannelRouter {
    private final Map<String, NotificationChannel> routes = Map.of(
            "email", new EmailChannel(),
            "sms",   new SmsChannel()
    );

    boolean route(String channelType, Notification n) {
        NotificationChannel ch = routes.getOrDefault(channelType, new EmailChannel());
        return ch.send(n);
    }
}
