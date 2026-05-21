package facade;

import core.Notification;

public class NotificationFacade {
    private final AuditLog     audit  = new AuditLog();
    private final RateLimiter  limiter = new RateLimiter(5);
    private final ChannelRouter router = new ChannelRouter();

    public boolean notify(String channelType, Notification n) {
        if (!limiter.allow(n.recipient())) {
            System.out.println("[FACADE]  rate-limited for " + n.recipient());
            return false;
        }
        boolean result = router.route(channelType, n);
        audit.record(n, result);
        return result;
    }
}
