package factorymethod;

import core.Notification;

import java.util.List;

public class FactoryMethodDemo {
    public static void run() {
        System.out.println("=== Factory Method ===");
        Notification n = new Notification("bob@example.com", "News", "New article!");
        List<NotificationFactory> factories = List.of(
                new EmailFactory(),
                new SmsFactory(),
                new PushFactory()
        );
        for (NotificationFactory f : factories) {
            f.send(n);
        }
    }
}
