package service;

import db.RateRepository;
import http.RatesClient;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class RateService {

    private final RatesClient client;
    private final RateRepository repository;

    public RateService(RatesClient client, RateRepository repository) {
        this.client = client;
        this.repository = repository;
    }

    public int refreshAndStore(List<String> interesting) throws SQLException {
        Map<String, BigDecimal> all = client.fetchRates();
        LocalDate today = LocalDate.now();
        int saved = 0;
        for (String currency : interesting) {
            BigDecimal rate = all.get(currency);
            if (rate != null) {
                repository.save(currency, rate, today);
                saved++;
            }
        }
        return saved;
    }
}
