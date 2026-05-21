package adapter;

import core.Notification;
import core.NotificationChannel;

public class AdapterDemo {
    public static void run() {
        System.out.println("=== Adapter ===");
        Notification n = new Notification("ops-team", "Disk full", "Server disk usage at 95%.");
        NotificationChannel ch = new LegacyAlertAdapter(new LegacyAlertService());
        ch.send(n);
    }
}
