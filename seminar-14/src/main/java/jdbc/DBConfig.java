package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Конфигурация подключения к базе данных H2 (in-memory).
 * H2 — встроенная БД, не требует отдельной установки.
 * DB_CLOSE_DELAY=-1 означает, что БД живёт пока жив процесс JVM.
 */
public class DBConfig {

    private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
