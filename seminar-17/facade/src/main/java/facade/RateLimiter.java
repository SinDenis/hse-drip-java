package facade;

import java.util.HashMap;
import java.util.Map;

class RateLimiter {
    private final Map<String, Integer> counts = new HashMap<>();
    private final int maxPerRecipient;

    RateLimiter(int maxPerRecipient) {
        this.maxPerRecipient = maxPerRecipient;
    }

    boolean allow(String recipient) {
        int count = counts.getOrDefault(recipient, 0);
        if (count >= maxPerRecipient) return false;
        counts.put(recipient, count + 1);
        return true;
    }
}
