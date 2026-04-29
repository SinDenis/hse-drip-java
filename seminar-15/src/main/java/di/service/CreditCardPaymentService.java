package di.service;

import di.annotation.Component;

@Component
public class CreditCardPaymentService implements PaymentService {

    @Override
    public void processPayment() {
        System.out.println("Обработка платежа кредитной картой...");
    }
}
