package service;

import db.DBConfig;
import db.RateRepository;
import http.RatesClient;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RateServiceTest {

    @Test
    void refreshAndStore_savesOnlyRequestedCurrencies() throws Exception {
        try (Connection connection = DBConfig.getConnection()) {
            RateRepository repository = new RateRepository(connection);
            repository.createTable();

            RatesClient fake = () -> Map.of(
                    "RUB", new BigDecimal("92.51"),
                    "EUR", new BigDecimal("0.93"),
                    "JPY", new BigDecimal("150.20")
            );
            RateService service = new RateService(fake, repository);

            int saved = service.refreshAndStore(List.of("RUB", "EUR"));

            assertEquals(2, saved);
            List<String> currencies = repository.findAll().stream()
                    .map(RateRepository.RateRecord::currency)
                    .toList();
            assertEquals(2, currencies.size());
            assertTrue(currencies.contains("RUB"));
            assertTrue(currencies.contains("EUR"));
            assertTrue(!currencies.contains("JPY"));
        }
    }

    @Test
    void refreshAndStore_skipsUnknownCurrencies() throws Exception {
        try (Connection connection = DBConfig.getConnection()) {
            RateRepository repository = new RateRepository(connection);
            repository.createTable();

            RatesClient fake = () -> Map.of("RUB", new BigDecimal("92.51"));
            RateService service = new RateService(fake, repository);

            int saved = service.refreshAndStore(List.of("RUB", "XYZ"));

            assertEquals(1, saved);
            assertEquals(1, repository.findAll().size());
        }
    }
}
