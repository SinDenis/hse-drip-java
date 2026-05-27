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

@DisplayName("NotificationService с рукописным test double")
class NotificationServiceTest {

    private RecordingNotifier notifier;
    private NotificationService service;

    @BeforeEach
    void setUp() {
        notifier = new RecordingNotifier();
        service  = new NotificationService(notifier);
    }

    @Test
    @DisplayName("notifyDeposit отправляет одно сообщение с суммой")
    void notifyDepositSendsExpectedMessage() {
        service.notifyDeposit(new User("alice@example.com"), new BigDecimal("100"));

        assertEquals(1, notifier.sent().size());
        RecordingNotifier.Message msg = notifier.sent().get(0);
        assertEquals("alice@example.com", msg.to());
        assertTrue(msg.body().contains("Deposit"));
        assertTrue(msg.body().contains("100"));
    }

    @Test
    @DisplayName("notifyWithdrawal отправляет сообщение о снятии")
    void notifyWithdrawalSendsExpectedMessage() {
        service.notifyWithdrawal(new User("bob@example.com"), new BigDecimal("25"));

        assertEquals(1, notifier.sent().size());
        assertTrue(notifier.sent().get(0).body().contains("Withdrawal"));
    }

    @Test
    @DisplayName("notifyTransfer отправляет ровно два сообщения, оба содержат сумму")
    void notifyTransferSendsTwoMessagesWithAmount() {
        service.notifyTransfer(
                new User("alice@example.com"),
                new User("bob@example.com"),
                new BigDecimal("250")
        );

        assertEquals(2, notifier.sent().size());
        assertTrue(notifier.sent().get(0).body().contains("250"));
        assertTrue(notifier.sent().get(1).body().contains("250"));
    }

    @Test
    @DisplayName("null-аргументы отклоняются")
    void nullArgumentsAreRejected() {
        assertThrows(NullPointerException.class,
                () -> service.notifyDeposit(null, BigDecimal.ONE));
        assertThrows(NullPointerException.class,
                () -> service.notifyDeposit(new User("a@b"), null));
    }

    @Test
    @DisplayName("конструктор отклоняет null-notifier")
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

        record Message(String to, String body) {}
    }
}
