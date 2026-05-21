package abstractfactory;

import core.Notification;
import core.NotificationChannel;

public class AbstractFactoryDemo {
    public static void run() {
        System.out.println("=== Abstract Factory ===");
        Notification n = new Notification("carol@example.com", "Update", "System updated.");
        send(new EmailSuite(), n);
        send(new SmsSuite(), n);
    }

    private static void send(NotificationSuite suite, Notification n) {
        NotificationFormatter fmt = suite.createFormatter();
        NotificationChannel ch = suite.createChannel();
        System.out.println("Formatted:\n" + fmt.format(n));
        ch.send(n);
    }
}
