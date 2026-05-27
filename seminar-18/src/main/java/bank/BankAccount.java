package bank;

import java.math.BigDecimal;
import java.util.Objects;

public class BankAccount {

    private final String owner;
    private BigDecimal balance;

    public BankAccount(String owner, BigDecimal initialBalance) {
        Objects.requireNonNull(owner, "owner must not be null");
        Objects.requireNonNull(initialBalance, "initialBalance must not be null");
        if (initialBalance.signum() < 0) {
            throw new IllegalArgumentException("Initial balance must be non-negative");
        }
        this.owner = owner;
        this.balance = initialBalance;
    }

    public String owner() {
        return owner;
    }

    public BigDecimal balance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        Objects.requireNonNull(amount, "amount must not be null");
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        Objects.requireNonNull(amount, "amount must not be null");
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientFundsException(amount, balance);
        }
        balance = balance.subtract(amount);
    }

    public void transferTo(BankAccount target, BigDecimal amount) {
        Objects.requireNonNull(target, "target must not be null");
        withdraw(amount);
        target.deposit(amount);
    }
}
