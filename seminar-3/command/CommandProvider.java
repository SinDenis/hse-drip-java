package command;

public class CommandProvider {
    private final Command[] commands;

    public CommandProvider(Command[] commands) {
        this.commands = commands;
    }

    public Command getCommand(String name) {
        for (Command command : commands) {
            if (command.getName().equals(name)) {
                return command;
            }
        }
        return null;
    }

    public Command[] getAvailableCommands() {
        return commands;
    }
}
