package factorymethod;

import core.NotificationChannel;
import strategy.SmsChannel;

public class SmsFactory extends NotificationFactory {
    @Override
    protected NotificationChannel createChannel() {
        return new SmsChannel();
    }
}
