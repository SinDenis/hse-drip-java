package factorymethod;

import core.NotificationChannel;
import strategy.EmailChannel;

public class EmailFactory extends NotificationFactory {
    @Override
    protected NotificationChannel createChannel() {
        return new EmailChannel();
    }
}
