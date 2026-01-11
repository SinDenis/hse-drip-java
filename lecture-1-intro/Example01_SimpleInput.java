import java.util.Scanner;  // Импортируем класс Scanner для чтения ввода

/**
 * Пример 1: Простое чтение строки из консоли
 *
 * Scanner - основной класс для чтения пользовательского ввода в Java
 */
public class Example01_SimpleInput {

    public static void main(String[] args) {
        // Создаём объект Scanner, который читает из System.in (стандартный ввод)
        // System.in - это поток ввода с клавиатуры
        Scanner scanner = new Scanner(System.in);

        // Выводим приглашение для пользователя
        System.out.print("Как тебя зовут? ");

        // nextLine() читает всю строку до нажатия Enter
        String name = scanner.nextLine();

        // Выводим приветствие с использованием конкатенации строк
        System.out.println("Привет, " + name + "!");

        // ВАЖНО: Scanner нужно закрывать после использования
        // Это освобождает системные ресурсы
        scanner.close();
    }
}

/*
 * ОСНОВНЫЕ МЕТОДЫ Scanner:
 *
 * nextLine()  - читает всю строку (до Enter)
 * next()      - читает одно слово (до пробела или Enter)
 * nextInt()   - читает целое число
 * nextDouble()- читает дробное число
 * nextBoolean()- читает boolean (true/false)
 *
 * hasNext()   - проверяет, есть ли ещё ввод
 * hasNextInt()- проверяет, является ли следующий токен числом
 */