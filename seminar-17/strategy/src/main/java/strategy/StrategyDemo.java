package strategy;

import core.Notification;
import core.NotificationChannel;

import java.util.List;

public class StrategyDemo {
    public static void run() {
        System.out.println("=== Strategy ===");
        Notification n = new Notification("alice@example.com", "Hello", "Welcome!");
        List<NotificationChannel> channels = List.of(
                new EmailChannel(),
                new SmsChannel(),
                new PushChannel()
        );
        for (NotificationChannel ch : channels) {
            ch.send(n);
        }
    }
}
