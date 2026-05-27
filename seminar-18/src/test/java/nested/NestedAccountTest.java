package nested;

import bank.BankAccount;
import bank.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("BankAccount: сценарии использования")
class NestedAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount("Alice", new BigDecimal("100"));
    }

    @Nested
    @DisplayName("после создания")
    class WhenCreated {

        @Test
        @DisplayName("баланс равен начальному взносу")
        void balanceEqualsInitial() {
            assertEquals(new BigDecimal("100"), account.balance());
        }

        @Test
        @DisplayName("владелец совпадает с переданным в конструктор")
        void ownerIsProvided() {
            assertEquals("Alice", account.owner());
        }
    }

    @Nested
    @DisplayName("при снятии средств")
    class WhenWithdrawing {

        @Test
        @DisplayName("баланс уменьшается на сумму снятия")
        void balanceDecreases() {
            account.withdraw(new BigDecimal("30"));
            assertEquals(new BigDecimal("70"), account.balance());
        }

        @Test
        @DisplayName("снятие сверх баланса бросает исключение")
        void overdraftThrows() {
            assertThrows(InsufficientFundsException.class,
                    () -> account.withdraw(new BigDecimal("9999")));
        }

        @Test
        @Disabled("Овердрафт ещё не реализован — тикет BANK-42")
        @DisplayName("овердрафт до -100 будет разрешён")
        void overdraftAllowedWithinLimit() {
            account.withdraw(new BigDecimal("150"));
            assertEquals(new BigDecimal("-50"), account.balance());
        }
    }

    @Nested
    @DisplayName("при пополнении счёта")
    class WhenDepositing {

        @Test
        @DisplayName("баланс увеличивается на сумму пополнения")
        void balanceIncreases() {
            account.deposit(new BigDecimal("50"));
            assertEquals(new BigDecimal("150"), account.balance());
        }

        @Test
        @DisplayName("неположительная сумма бросает IllegalArgumentException")
        void nonPositiveAmountThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> account.deposit(BigDecimal.ZERO));
        }
    }
}
