package decorator;

import core.Notification;
import core.NotificationChannel;
import strategy.EmailChannel;

public class DecoratorDemo {
    public static void run() {
        System.out.println("=== Decorator ===");
        Notification n = new Notification("dan@example.com", "Alert", "Server down!");

        NotificationChannel chain = new LoggingChannel(
                new RetryChannel(new EmailChannel(), 3)
        );
        chain.send(n);
    }
}
