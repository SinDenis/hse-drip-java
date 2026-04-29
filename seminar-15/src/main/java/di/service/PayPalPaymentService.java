package di.service;

public class PayPalPaymentService implements PaymentService {

    @Override
    public void processPayment() {
        System.out.println("Обработка платежа через PayPal...");
    }
}
