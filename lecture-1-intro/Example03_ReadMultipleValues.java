import java.util.Scanner;

/**
 * Пример 3: Чтение нескольких значений в одной строке
 *
 * Разные способы парсинга ввода
 */
public class Example03_ReadMultipleValues {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите 3 числа через пробел:");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int z = scanner.nextInt();
        System.out.printf("Вы ввели: x=%d, y=%d, z=%d%n", x, y, z);
        System.out.println("Сумма: " + (x + y + z));

        scanner.nextLine();

        System.out.println("\nВведите имя и возраст через пробел:");
        String line = scanner.nextLine();
        String[] parts = line.split(" ");

        if (parts.length >= 2) {
            String name = parts[0];
            int age = Integer.parseInt(parts[1]);
            System.out.println("Имя: " + name + ", Возраст: " + age);
        }

        System.out.println("\nВведите название товара и цену:");
        String productName = scanner.next();
        double price = scanner.nextDouble();
        System.out.printf("Товар: %s, Цена: %.2f руб.%n", productName, price);

        scanner.close();
    }
}

/*
 * ВАЖНЫЕ МОМЕНТЫ:
 *
 * 1. nextInt(), nextDouble() и т.д. НЕ читают символ новой строки
 *    После них нужно вызвать nextLine() для очистки буфера
 *
 * 2. split(String regex) - разбивает строку по регулярному выражению
 *    "a b c".split(" ") -> ["a", "b", "c"]
 *
 * 3. Integer.parseInt(String) - преобразует строку в int
 *    Double.parseDouble(String) - преобразует строку в double
 *
 * 4. next() читает до пробела, nextLine() читает до конца строки
 */
