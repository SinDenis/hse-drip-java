import java.util.Scanner;

/**
 * Пример 2: Чтение чисел и арифметические операции
 *
 * Демонстрация работы с числовыми типами данных
 */
public class Example02_ReadNumbers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите целое число a: ");
        int a = scanner.nextInt();

        System.out.print("Введите целое число b: ");
        int b = scanner.nextInt();

        System.out.println("\n=== Арифметические операции ===");
        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b));
        System.out.println("a % b = " + (a % b));

        System.out.print("\nВведите дробное число: ");
        double x = scanner.nextDouble();

        System.out.println("x * 2 = " + (x * 2));
        System.out.println("x / 3 = " + (x / 3));

        scanner.close();
    }
}

/*
 * ПРИМИТИВНЫЕ ТИПЫ ДАННЫХ В JAVA:
 *
 * Целые числа:
 *   byte   - 1 байт  (-128 до 127)
 *   short  - 2 байта (-32768 до 32767)
 *   int    - 4 байта (~-2 млрд до ~2 млрд) - ОСНОВНОЙ ТИП
 *   long   - 8 байт  (очень большие числа)
 *
 * Дробные числа:
 *   float  - 4 байта (7 значащих цифр)
 *   double - 8 байт  (15 значащих цифр) - ОСНОВНОЙ ТИП
 *
 * Другие:
 *   boolean - true или false
 *   char    - один символ Unicode (2 байта)
 *
 * ВАЖНО: int / int = int (целочисленное деление!)
 * Пример: 5 / 2 = 2, а не 2.5
 * Для дробного результата: 5.0 / 2 или (double) 5 / 2
 */
