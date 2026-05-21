package abstractfactory;

import core.Notification;

public class SmsFormatter implements NotificationFormatter {
    @Override
    public String format(Notification n) {
        String body = n.body().length() > 160 ? n.body().substring(0, 157) + "..." : n.body();
        return n.recipient() + ": " + body;
    }
}
