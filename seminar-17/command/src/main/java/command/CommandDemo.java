package command;

import core.Notification;
import strategy.EmailChannel;
import strategy.SmsChannel;

public class CommandDemo {
    public static void run() {
        System.out.println("=== Command ===");
        CommandHistory history = new CommandHistory();

        history.execute(new SendCommand(
                new EmailChannel(),
                new Notification("jack@example.com", "Invoice", "See attached.")));

        history.execute(new SendCommand(
                new SmsChannel(),
                new Notification("+1555000111", "", "Your code: 9876")));

        System.out.println("--- undo last ---");
        history.undoLast();
        System.out.println("--- undo again ---");
        history.undoLast();
    }
}
