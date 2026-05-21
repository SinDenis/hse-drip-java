package abstractfactory;

import core.Notification;

public class EmailFormatter implements NotificationFormatter {
    @Override
    public String format(Notification n) {
        return "Subject: " + n.subject() + "\nTo: " + n.recipient() + "\n" + n.body();
    }
}
