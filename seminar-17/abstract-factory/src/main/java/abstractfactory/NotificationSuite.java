package abstractfactory;

import core.NotificationChannel;

public interface NotificationSuite {
    NotificationChannel createChannel();
    NotificationFormatter createFormatter();
}
