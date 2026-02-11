package examples;

public class Example1_SRP {

    static class Book {
        private String title;
        private String author;
        private String content;

        public Book(String title, String author, String content) {
            this.title = title;
            this.author = author;
            this.content = content;
        }

        // Ответственность 1: Хранение данных книги
        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getContent() {
            return content;
        }

        // Ответственность 2: Форматирование для печати
        public String formatForPrint() {
            System.out.println("Форматирование книги...");
            String formatted = "=".repeat(50) + "\n";
            formatted += "Название: " + title + "\n";
            formatted += "Автор: " + author + "\n";
            formatted += "=".repeat(50) + "\n";
            formatted += content + "\n";
            formatted += "=".repeat(50);
            return formatted;
        }

        // Ответственность 3: Печать книги
        public void printToConsole() {
            System.out.println("Отправка на печать...");
            String formatted = formatForPrint();
            System.out.println(formatted);
            System.out.println("Книга напечатана!");
        }
    }

    public static void main(String[] args) {
        Book book = new Book(
            "Чистый код",
            "Роберт Мартин",
            "Даже плохой код может работать. Но если код нечист, он может поставить организацию на колени."
        );

        book.printToConsole();

        // Проблемы:
        // 1. Если изменится формат печати, нужно менять класс Book
        // 2. Если нужно добавить печать в PDF, нужно менять класс Book
        // 3. Класс Book знает о форматировании и печати - это не его задача!
        // 4. Сложно тестировать отдельно форматирование и печать
    }
}
