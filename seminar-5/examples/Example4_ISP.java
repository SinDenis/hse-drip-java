package examples;

public class Example4_ISP {

    interface MultiFunctionDevice {
        void print(String document);
        void scan(String document);
        void fax(String document);
    }

    // Многофункциональное устройство - реализует все методы
    static class ModernPrinter implements MultiFunctionDevice {
        @Override
        public void print(String document) {
            System.out.println("Печать документа: " + document);
        }

        @Override
        public void scan(String document) {
            System.out.println("Сканирование документа: " + document);
        }

        @Override
        public void fax(String document) {
            System.out.println("Отправка факса: " + document);
        }
    }

    // Простой принтер - НЕ нужны scan() и fax(), но приходится реализовывать!
    static class SimplePrinter implements MultiFunctionDevice {
        @Override
        public void print(String document) {
            System.out.println("Простая печать: " + document);
        }

        @Override
        public void scan(String document) {
            // Простой принтер не умеет сканировать!
            throw new UnsupportedOperationException("Этот принтер не умеет сканировать!");
        }

        @Override
        public void fax(String document) {
            // Простой принтер не умеет отправлять факсы!
            throw new UnsupportedOperationException("Этот принтер не умеет отправлять факсы!");
        }
    }

    // Сканер - только сканирует, но приходится реализовывать print() и fax()!
    static class Scanner implements MultiFunctionDevice {
        @Override
        public void print(String document) {
            throw new UnsupportedOperationException("Сканер не умеет печатать!");
        }

        @Override
        public void scan(String document) {
            System.out.println("Сканирование: " + document);
        }

        @Override
        public void fax(String document) {
            throw new UnsupportedOperationException("Сканер не умеет отправлять факсы!");
        }
    }

    public static void main(String[] args) {
        MultiFunctionDevice modernPrinter = new ModernPrinter();
        MultiFunctionDevice simplePrinter = new SimplePrinter();
        MultiFunctionDevice scanner = new Scanner();

        System.out.println("=== Многофункциональный принтер ===");
        modernPrinter.print("Отчет.pdf");
        modernPrinter.scan("Договор.pdf");
        modernPrinter.fax("Счет.pdf");

        System.out.println("\n=== Простой принтер ===");
        simplePrinter.print("Документ.pdf");
        try {
            simplePrinter.scan("Документ.pdf");  // Бросит исключение!
        } catch (UnsupportedOperationException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Сканер ===");
        scanner.scan("Фото.jpg");
        try {
            scanner.print("Фото.jpg");  // Бросит исключение!
        } catch (UnsupportedOperationException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Проблемы:
        // 1. Простые устройства вынуждены реализовывать ненужные методы
        // 2. Приходится бросать исключения
        // 3. Интерфейс MultiFunctionDevice слишком "жирный"
    }
}
