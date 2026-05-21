package abstractfactory;

import core.NotificationChannel;
import strategy.EmailChannel;

public class EmailSuite implements NotificationSuite {
    @Override
    public NotificationChannel createChannel() { return new EmailChannel(); }

    @Override
    public NotificationFormatter createFormatter() { return new EmailFormatter(); }
}
