import bank.BankAccount;
import calculator.Calculator;
import notification.ConsoleNotifier;
import notification.NotificationService;
import notification.User;
import strings.StringUtils;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println("2 + 3 = " + calculator.add(2, 3));

        BankAccount account = new BankAccount("Alice", new BigDecimal("100.00"));
        account.deposit(new BigDecimal("50.00"));
        account.withdraw(new BigDecimal("30.00"));
        System.out.println(account.owner() + " balance: " + account.balance());

        System.out.println("Is 'level' palindrome? " + StringUtils.isPalindrome("level"));

        NotificationService notifications = new NotificationService(new ConsoleNotifier());
        notifications.notifyDeposit(new User("alice@example.com"), new BigDecimal("50.00"));
    }
}
