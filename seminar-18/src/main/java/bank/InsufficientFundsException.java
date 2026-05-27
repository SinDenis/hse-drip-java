package bank;

import java.math.BigDecimal;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(BigDecimal requested, BigDecimal available) {
        super("Requested " + requested + " but only " + available + " is available");
    }
}
