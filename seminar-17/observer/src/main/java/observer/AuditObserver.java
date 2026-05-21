package observer;

public class AuditObserver implements ChannelObserver {
    @Override
    public void onEvent(ChannelEvent e) {
        System.out.printf("[AUDIT-OBS]   channel=%s recipient=%s success=%b%n",
                e.channelName(), e.notification().recipient(), e.success());
    }
}
