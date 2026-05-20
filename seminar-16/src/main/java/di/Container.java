package di;

import db.DBConfig;
import db.RateRepository;
import http.HttpRatesClient;
import http.RatesClient;
import service.RateService;

import java.sql.Connection;
import java.sql.SQLException;

public class Container implements AutoCloseable {

    private final Connection connection;
    private final RatesClient ratesClient;
    private final RateRepository rateRepository;
    private final RateService rateService;

    public Container(RatesClient ratesClient) {
        try {
            this.connection = DBConfig.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.ratesClient = ratesClient;
        this.rateRepository = new RateRepository(connection);
        this.rateService = new RateService(ratesClient, rateRepository);
    }

    public Container() {
        this(new HttpRatesClient());
    }

    public RateService rateService() {
        return rateService;
    }

    public RateRepository rateRepository() {
        return rateRepository;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
