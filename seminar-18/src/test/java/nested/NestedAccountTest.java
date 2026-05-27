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

@DisplayName("BankAccount: behaviour grouped by scenario")
class NestedAccountTest {

    private BankAccount account;

    @BeforeEach
    void initAccount() {
        account = new BankAccount("Alice", new BigDecimal("100"));
    }

    @Nested
    @DisplayName("when newly created")
    class WhenCreated {

        @Test
        @DisplayName("balance equals the initial deposit")
        void balanceEqualsInitial() {
            assertEquals(new BigDecimal("100"), account.balance());
        }

        @Test
        @DisplayName("owner is the one provided to constructor")
        void ownerIsProvided() {
            assertEquals("Alice", account.owner());
        }
    }

    @Nested
    @DisplayName("when funds are withdrawn")
    class WhenWithdrawing {

        @Test
        @DisplayName("balance decreases by the withdrawn amount")
        void balanceDecreases() {
            account.withdraw(new BigDecimal("30"));
            assertEquals(new BigDecimal("70"), account.balance());
        }

        @Test
        @DisplayName("withdrawing more than balance throws")
        void overdraftThrows() {
            assertThrows(InsufficientFundsException.class,
                    () -> account.withdraw(new BigDecimal("9999")));
        }

        @Test
        @Disabled("Overdraft protection is not implemented yet — see ticket BANK-42")
        @DisplayName("overdraft protection eventually allows -100 limit")
        void overdraftAllowedWithinLimit() {
            account.withdraw(new BigDecimal("150"));
            assertEquals(new BigDecimal("-50"), account.balance());
        }
    }
}
