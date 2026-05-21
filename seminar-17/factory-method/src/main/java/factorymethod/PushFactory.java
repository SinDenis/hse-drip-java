package factorymethod;

import core.NotificationChannel;
import strategy.PushChannel;

public class PushFactory extends NotificationFactory {
    @Override
    protected NotificationChannel createChannel() {
        return new PushChannel();
    }
}
