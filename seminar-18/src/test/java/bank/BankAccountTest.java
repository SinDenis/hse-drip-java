package bank;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("BankAccount lifecycle and invariants")
class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void initAccount() {
        account = new BankAccount("Alice", new BigDecimal("100.00"));
    }

    @AfterEach
    void clearAccount() {
        account = null;
    }

    @Test
    void newAccountStoresOwnerAndBalance() {
        assertNotNull(account);
        assertEquals("Alice", account.owner());
        assertEquals(new BigDecimal("100.00"), account.balance());
    }

    @Test
    void depositIncreasesBalance() {
        account.deposit(new BigDecimal("50.00"));
        assertEquals(new BigDecimal("150.00"), account.balance());
    }

    @Test
    void withdrawDecreasesBalance() {
        account.withdraw(new BigDecimal("30.00"));
        assertEquals(new BigDecimal("70.00"), account.balance());
    }

    @Test
    @DisplayName("withdraw above balance throws InsufficientFundsException")
    void withdrawTooMuchThrows() {
        InsufficientFundsException error = assertThrows(
                InsufficientFundsException.class,
                () -> account.withdraw(new BigDecimal("1000.00"))
        );
        assertEquals(new BigDecimal("100.00"), account.balance(),
                "Balance must not change after a failed withdrawal");
        assertNotNull(error.getMessage());
    }

    @Test
    void depositRejectsNonPositiveAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> account.deposit(BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class,
                () -> account.deposit(new BigDecimal("-1")));
    }

    @Test
    void constructorRejectsNegativeInitialBalance() {
        assertThrows(IllegalArgumentException.class,
                () -> new BankAccount("Bob", new BigDecimal("-1")));
    }

    @Test
    void transferMovesMoneyBetweenAccounts() {
        BankAccount target = new BankAccount("Bob", BigDecimal.ZERO);
        account.transferTo(target, new BigDecimal("40.00"));
        assertEquals(new BigDecimal("60.00"), account.balance());
        assertEquals(new BigDecimal("40.00"), target.balance());
    }
}
