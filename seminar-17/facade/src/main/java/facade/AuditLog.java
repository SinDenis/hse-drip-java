package facade;

import core.Notification;

class AuditLog {
    void record(Notification n, boolean result) {
        System.out.printf("[AUDIT]   recipient=%s result=%b%n", n.recipient(), result);
    }
}
