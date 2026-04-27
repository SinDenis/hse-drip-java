package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private final Connection connection;

    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "age INT, " +
                "grade DOUBLE" +
                ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    public long insert(Student student) throws SQLException {
        String sql = "INSERT INTO students (name, age, grade) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setDouble(3, student.getGrade());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getLong(1);
                }
            }
        }
        return -1;
    }

    /**
     * Шаг 3: Выборка всех студентов.
     *
     * Выполните SELECT-запрос и обойдите ResultSet,
     * создавая объекты Student из каждой строки.
     *
     * @return список всех студентов
     */
    public List<Student> findAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT id, name, age, grade FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("grade")
                );
                students.add(student);
            }
        }
        return students;
    }

    /**
     * Шаг 4: Batch-вставка нескольких студентов в одной транзакции.
     *
     * 1. Отключите autoCommit
     * 2. Добавьте каждого студента в batch (ps.addBatch())
     * 3. Выполните ps.executeBatch()
     * 4. Зафиксируйте транзакцию conn.commit()
     * 5. Не забудьте вернуть autoCommit в true
     */
    public void batchInsert(List<Student> students) throws SQLException {
        String sql = "INSERT INTO students (name, age, grade) VALUES (?, ?, ?)";
        connection.setAutoCommit(false);
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (Student student : students) {
                ps.setString(1, student.getName());
                ps.setInt(2, student.getAge());
                ps.setDouble(3, student.getGrade());
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
