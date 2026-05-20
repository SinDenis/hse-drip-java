package http;

import java.math.BigDecimal;
import java.util.Map;

public interface RatesClient {
    Map<String, BigDecimal> fetchRates();
}
