package bank;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("BankAccount")
class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount("Alice", new BigDecimal("100.00"));
    }

    @AfterEach
    void tearDown() {
        account = null;
    }

    @Test
    @DisplayName("новый счёт хранит владельца и баланс")
    void newAccountStoresOwnerAndBalance() {
        assertNotNull(account);
        assertEquals("Alice", account.owner());
        assertEquals(new BigDecimal("100.00"), account.balance());
    }

    @Test
    @DisplayName("deposit увеличивает баланс")
    void depositIncreasesBalance() {
        account.deposit(new BigDecimal("50.00"));
        assertEquals(new BigDecimal("150.00"), account.balance());
    }

    @Test
    @DisplayName("withdraw уменьшает баланс")
    void withdrawDecreasesBalance() {
        account.withdraw(new BigDecimal("30.00"));
        assertEquals(new BigDecimal("70.00"), account.balance());
    }

    @Test
    @DisplayName("снятие сверх баланса бросает InsufficientFundsException")
    void withdrawTooMuchThrows() {
        InsufficientFundsException ex = assertThrows(
                InsufficientFundsException.class,
                () -> account.withdraw(new BigDecimal("1000.00"))
        );
        assertEquals(new BigDecimal("100.00"), account.balance(),
                "баланс не должен измениться после неудачного снятия");
        assertNotNull(ex.getMessage());
    }

    @Test
    @DisplayName("deposit отклоняет неположительную сумму")
    void depositRejectsNonPositiveAmount() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(new BigDecimal("-1")));
    }

    @Test
    @DisplayName("конструктор отклоняет отрицательный начальный баланс")
    void constructorRejectsNegativeBalance() {
        assertThrows(IllegalArgumentException.class,
                () -> new BankAccount("Bob", new BigDecimal("-1")));
    }

    @Test
    @DisplayName("transferTo перемещает деньги между счетами")
    void transferMovesMoneyBetweenAccounts() {
        BankAccount target = new BankAccount("Bob", BigDecimal.ZERO);
        account.transferTo(target, new BigDecimal("40.00"));
        assertEquals(new BigDecimal("60.00"), account.balance());
        assertEquals(new BigDecimal("40.00"), target.balance());
    }

    @Test
    @DisplayName("hasFunds: баланс равен сумме → true")
    void hasFundsWhenBalanceEquals() {
        assertTrue(account.hasFunds(new BigDecimal("100.00")));
    }

    @Test
    @DisplayName("hasFunds: баланс больше суммы → true")
    void hasFundsWhenBalanceExceeds() {
        assertTrue(account.hasFunds(new BigDecimal("50.00")));
    }

    @Test
    @DisplayName("hasFunds: баланс меньше суммы → false")
    void hasFundsWhenBalanceInsufficient() {
        assertFalse(account.hasFunds(new BigDecimal("150.00")));
    }
}
