package examples;

public class Example5_DIP {

    static class MySQLDatabase {
        public void save(String data) {
            System.out.println("Подключение к MySQL...");
            System.out.println("INSERT INTO orders VALUES ('" + data + "')");
            System.out.println("Данные сохранены в MySQL");
        }

        public String find(String id) {
            System.out.println("SELECT * FROM orders WHERE id = '" + id + "'");
            return "Заказ #" + id + " из MySQL";
        }
    }

    // OrderService напрямую зависит от MySQLDatabase!
    static class OrderService {
        private MySQLDatabase database = new MySQLDatabase();  // Жесткая зависимость!

        public void createOrder(String orderId, String customerName) {
            System.out.println("Создание заказа " + orderId + " для " + customerName);
            database.save(orderId + ":" + customerName);
        }

        public String getOrder(String orderId) {
            System.out.println("Получение заказа " + orderId);
            return database.find(orderId);
        }

        // Проблемы:
        // 1. Что если нужно переключиться на PostgreSQL? → менять класс
        // 2. Как тестировать без реальной БД? → невозможно
        // 3. OrderService зависит от конкретной реализации, а не от абстракции
    }

    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        System.out.println("=== Создание заказа ===");
        orderService.createOrder("ORD-001", "Иван Иванов");

        System.out.println("\n=== Получение заказа ===");
        String order = orderService.getOrder("ORD-001");
        System.out.println("Результат: " + order);

        // Проблемы:
        // 1. Жесткая зависимость от MySQLDatabase
        // 2. Невозможно легко сменить БД
        // 3. Сложно тестировать (нельзя подменить mock)
        // 4. Нарушение DIP: высокоуровневый модуль зависит от низкоуровневого
    }
}
