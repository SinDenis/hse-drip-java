package abstractfactory;

import core.NotificationChannel;
import strategy.SmsChannel;

public class SmsSuite implements NotificationSuite {
    @Override
    public NotificationChannel createChannel() { return new SmsChannel(); }

    @Override
    public NotificationFormatter createFormatter() { return new SmsFormatter(); }
}
