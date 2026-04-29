package di.service;

import di.annotation.Autowired;
import di.annotation.Component;

@Component
public class OrderService {

    @Autowired
    private PaymentService paymentService;

    public OrderService() {
    }

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void processOrder() {
        System.out.println("Обработка заказа...");
        paymentService.processPayment();
    }
}
