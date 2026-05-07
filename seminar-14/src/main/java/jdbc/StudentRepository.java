package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class StudentRepository {

    private final String TABLE_NAME = "students";

    private final Connection connection;

    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "age INT," +
                "grade DOUBLE" +
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    public long insert(Student student) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " (name, age, grade) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setDouble(3, student.getGrade());
            statement.execute();

            try(ResultSet set = statement.getGeneratedKeys()) {
                if (set.next()) {
                    return set.getLong(1);
                }
            }
        }
        return -1;
    }

    public List<Student> findAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT id, name, age, grade FROM students";

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                Student student = new Student(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getInt("age"),
                        result.getDouble("grade")
                );
                students.add(student);
            }
        }

        return students;
    }

    public void batchInsert(List<Student> students) throws SQLException {
        String sql = "INSERT INTO (name, age, grade) VALUES (?, ?, ?)";
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Student student: students) {
                statement.setString(1, student.getName());
                statement.setInt(2, student.getAge());
                statement.setDouble(3, student.getGrade());
                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
