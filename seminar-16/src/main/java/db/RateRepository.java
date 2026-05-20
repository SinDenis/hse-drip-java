package db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RateRepository {

    private final Connection connection;

    public RateRepository(Connection connection) {
        this.connection = connection;
    }

    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS rates (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "currency VARCHAR(8), " +
                "rate DECIMAL(18,6), " +
                "day_at DATE" +
                ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    public void save(String currency, BigDecimal rate, LocalDate day) throws SQLException {
        String sql = "INSERT INTO rates (currency, rate, day_at) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, currency);
            ps.setBigDecimal(2, rate);
            ps.setDate(3, java.sql.Date.valueOf(day));
            ps.executeUpdate();
        }
    }

    public List<RateRecord> findAll() throws SQLException {
        String sql = "SELECT id, currency, rate, day_at FROM rates";
        List<RateRecord> result = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                result.add(new RateRecord(
                        rs.getLong("id"),
                        rs.getString("currency"),
                        rs.getBigDecimal("rate"),
                        rs.getDate("day_at").toLocalDate()
                ));
            }
        }
        return result;
    }

    public record RateRecord(long id, String currency, BigDecimal rate, LocalDate dayAt) {}
}
