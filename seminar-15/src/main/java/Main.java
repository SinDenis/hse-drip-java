import di.container.SimpleIoCContainer;
import di.service.CreditCardPaymentService;
import di.service.OrderService;
import di.service.PaymentService;

public class Main {

    public static void main(String[] args) {
        // Шаг 1: Constructor Injection
        System.out.println("=== Шаг 1: Constructor Injection ===\n");
        PaymentService paypal = new di.service.PayPalPaymentService();
        OrderService withPayPal = new OrderService(paypal);
        withPayPal.processOrder();

        // Шаг 3: Простой IoC контейнер
        System.out.println("\n=== Шаг 3: Простой IoC контейнер ===\n");
        SimpleIoCContainer container = new SimpleIoCContainer();
        container.register(PaymentService.class, new CreditCardPaymentService());
        PaymentService ps = container.getBean(PaymentService.class);
        container.register(OrderService.class, new OrderService(ps));
        OrderService orderService = container.getBean(OrderService.class);
        orderService.processOrder();

        // Шаги 5-6: Автоматический контейнер
        System.out.println("\n=== Шаги 5-6: Автоматический IoC контейнер ===\n");
        SimpleIoCContainer autoContainer = new SimpleIoCContainer();
        autoContainer.scanComponents("di");
        autoContainer.wireDependencies();
        OrderService autoOrderService = autoContainer.getBean(OrderService.class);
        autoOrderService.processOrder();

    }
}
