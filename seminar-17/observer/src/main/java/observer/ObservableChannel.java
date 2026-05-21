package observer;

import core.Notification;
import core.NotificationChannel;

import java.util.ArrayList;
import java.util.List;

public class ObservableChannel implements NotificationChannel {
    private final NotificationChannel delegate;
    private final String name;
    private final List<ChannelObserver> observers = new ArrayList<>();

    public ObservableChannel(String name, NotificationChannel delegate) {
        this.name     = name;
        this.delegate = delegate;
    }

    public void subscribe(ChannelObserver observer)   { observers.add(observer); }
    public void unsubscribe(ChannelObserver observer) { observers.remove(observer); }

    @Override
    public boolean send(Notification n) {
        boolean result = delegate.send(n);
        ChannelEvent event = new ChannelEvent(name, n, result);
        for (ChannelObserver obs : observers) {
            obs.onEvent(event);
        }
        return result;
    }
}
