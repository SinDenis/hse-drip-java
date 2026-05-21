package command;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandHistory {
    private final Deque<NotificationCommand> history = new ArrayDeque<>();

    public void execute(NotificationCommand cmd) {
        cmd.execute();
        history.push(cmd);
    }

    public void undoLast() {
        if (history.isEmpty()) {
            System.out.println("[HISTORY] nothing to undo");
            return;
        }
        history.pop().undo();
    }
}
