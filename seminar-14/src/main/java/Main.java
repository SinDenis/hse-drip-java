import csv.Person;
import csv.SimpleCsvParser;
import jdbc.DBConfig;
import jdbc.Student;
import jdbc.StudentRepository;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("=== Часть 1: JDBC ===\n");
        runJdbcDemo();

        System.out.println("\n=== Часть 2: CSV Parser (Reflection) ===\n");
        runCsvDemo();
    }

    private static void runJdbcDemo() throws Exception {
        try (Connection connection = DBConfig.getConnection()) {
            StudentRepository repo = new StudentRepository(connection);

            // Шаг 1: Создание таблицы
            System.out.println("--- Шаг 1: Создание таблицы ---");
            repo.createTable();
            System.out.println("Таблица создана.\n");

            // Шаг 2: Вставка записей
            System.out.println("--- Шаг 2: Вставка записей ---");
            long id1 = repo.insert(new Student("Иван Иванов", 20, 4.5));
            long id2 = repo.insert(new Student("Мария Петрова", 22, 4.8));
            System.out.println("Вставлен студент с id=" + id1);
            System.out.println("Вставлен студент с id=" + id2 + "\n");

            // Шаг 3: Выборка всех записей
            System.out.println("--- Шаг 3: Выборка всех записей ---");
            List<Student> students = repo.findAll();
            students.forEach(System.out::println);
            System.out.println();

            // Шаг 4: Batch-вставка
            System.out.println("--- Шаг 4: Batch-вставка ---");
            List<Student> batch = Arrays.asList(
                    new Student("Алексей Смирнов", 21, 3.9),
                    new Student("Елена Козлова", 23, 4.2),
                    new Student("Дмитрий Волков", 20, 3.5)
            );
            repo.batchInsert(batch);
            System.out.println("Batch-вставка завершена.");

            System.out.println("\nВсе студенты после batch-вставки:");
            repo.findAll().forEach(System.out::println);
        }
    }

    private static void runCsvDemo() throws Exception {
        SimpleCsvParser parser = new SimpleCsvParser();

        // Шаг 5-6: Чтение из CSV
        System.out.println("--- Чтение из people.csv ---");
        String csvPath = Main.class.getClassLoader().getResource("people.csv").getPath();
        List<Person> people = parser.parseFile(csvPath, Person.class);
        people.forEach(System.out::println);

        // Шаг 7: Запись в CSV
        System.out.println("\n--- Запись в output.csv ---");
        parser.writeFile("output.csv", people, Person.class);
        System.out.println("Файл output.csv записан.");

        // Проверка: читаем обратно
        System.out.println("\n--- Проверка: чтение из output.csv ---");
        List<Person> reloaded = parser.parseFile("output.csv", Person.class);
        reloaded.forEach(System.out::println);
    }
}
