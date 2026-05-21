package facade;

import core.Notification;

public class FacadeDemo {
    public static void run() {
        System.out.println("=== Facade ===");
        NotificationFacade facade = new NotificationFacade();
        facade.notify("email", new Notification("gina@example.com", "Promo", "50% off!"));
        facade.notify("sms",   new Notification("gina@example.com", "Promo2", "Weekend deal!"));
    }
}
