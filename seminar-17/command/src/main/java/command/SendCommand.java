package command;

import core.Notification;
import core.NotificationChannel;

public class SendCommand implements NotificationCommand {
    private final NotificationChannel channel;
    private final Notification notification;
    private boolean wasExecuted = false;

    public SendCommand(NotificationChannel channel, Notification notification) {
        this.channel      = channel;
        this.notification = notification;
    }

    @Override
    public void execute() {
        channel.send(notification);
        wasExecuted = true;
    }

    @Override
    public void undo() {
        if (wasExecuted) {
            System.out.printf("[UNDO]  cancellation sent to=%s subject=%s%n",
                    notification.recipient(), notification.subject());
            wasExecuted = false;
        } else {
            System.out.println("[UNDO]  nothing to undo");
        }
    }
}
