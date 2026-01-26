import command.Command;
import command.CommandProvider;
import repository.InMemoryToDoRepository;
import repository.ToDoRepository;

import java.util.Scanner;

public class ToDoApp {
    static void main() {
        ToDoRepository repository = new InMemoryToDoRepository();
        CommandProvider commandProvider = new CommandProvider(new Command[]{

        });
        Scanner scanner = new Scanner(System.in);

        // Прочитать команду

        // Выполнить команду, если не нашлось команды по имени - напечатать help
        // add {name} - добавить элемент по имени
        // remove {name} - добавить элемент по имени
        // list - вывести все элементы
        // list {id} - вывести элемент по id
        // done {id} - пометить как выполненный
    }
}
