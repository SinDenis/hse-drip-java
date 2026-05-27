package notification;

import java.math.BigDecimal;
import java.util.Objects;

public class NotificationService {

    private final Notifier notifier;

    public NotificationService(Notifier notifier) {
        this.notifier = Objects.requireNonNull(notifier, "notifier must not be null");
    }

    public void notifyDeposit(User user, BigDecimal amount) {
        Objects.requireNonNull(user, "user must not be null");
        Objects.requireNonNull(amount, "amount must not be null");
        notifier.send(user.email(), "Deposit confirmed: " + amount);
    }

    public void notifyWithdrawal(User user, BigDecimal amount) {
        Objects.requireNonNull(user, "user must not be null");
        Objects.requireNonNull(amount, "amount must not be null");
        notifier.send(user.email(), "Withdrawal processed: " + amount);
    }
}
