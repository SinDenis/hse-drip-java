package adapter;

import core.Notification;
import core.NotificationChannel;

public class LegacyAlertAdapter implements NotificationChannel {
    private final LegacyAlertService legacy;

    public LegacyAlertAdapter(LegacyAlertService legacy) {
        this.legacy = legacy;
    }

    @Override
    public boolean send(Notification n) {
        String message = n.subject() + " — " + n.body();
        legacy.triggerAlert(n.recipient(), message);
        return true;
    }
}
