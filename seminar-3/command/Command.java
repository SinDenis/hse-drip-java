package command;

public interface Command {
    String getName();
    String getHelp();
    String execute(String[] args);
}
