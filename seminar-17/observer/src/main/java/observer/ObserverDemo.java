package observer;

import core.Notification;
import strategy.EmailChannel;

public class ObserverDemo {
    public static void run() {
        System.out.println("=== Observer ===");
        ObservableChannel channel = new ObservableChannel("email", new EmailChannel());
        channel.subscribe(new AuditObserver());
        channel.subscribe(new MetricsObserver());

        channel.send(new Notification("hank@example.com", "Test", "Ping"));
        channel.send(new Notification("iris@example.com", "Test", "Pong"));
    }
}
