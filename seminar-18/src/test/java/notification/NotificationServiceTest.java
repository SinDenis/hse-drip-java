package notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("NotificationService with a hand-written test double")
class NotificationServiceTest {

    private RecordingNotifier notifier;
    private NotificationService service;

    @BeforeEach
    void setUp() {
        notifier = new RecordingNotifier();
        service = new NotificationService(notifier);
    }

    @Test
    void notifyDepositSendsExpectedMessage() {
        service.notifyDeposit(new User("alice@example.com"), new BigDecimal("100"));

        assertEquals(1, notifier.sent().size());
        RecordingNotifier.Message message = notifier.sent().get(0);
        assertEquals("alice@example.com", message.to());
        assertTrue(message.body().contains("Deposit"));
        assertTrue(message.body().contains("100"));
    }

    @Test
    void notifyWithdrawalSendsExpectedMessage() {
        service.notifyWithdrawal(new User("bob@example.com"), new BigDecimal("25"));

        assertEquals(1, notifier.sent().size());
        assertTrue(notifier.sent().get(0).body().contains("Withdrawal"));
    }

    @Test
    void nullArgumentsAreRejected() {
        assertThrows(NullPointerException.class,
                () -> service.notifyDeposit(null, BigDecimal.ONE));
        assertThrows(NullPointerException.class,
                () -> service.notifyDeposit(new User("a@b"), null));
    }

    @Test
    void constructorRejectsNullNotifier() {
        assertThrows(NullPointerException.class, () -> new NotificationService(null));
    }

    private static final class RecordingNotifier implements Notifier {
        private final List<Message> sent = new ArrayList<>();

        @Override
        public void send(String to, String message) {
            sent.add(new Message(to, message));
        }

        List<Message> sent() {
            return sent;
        }

        record Message(String to, String body) {
        }
    }
}
