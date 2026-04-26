package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для работы со студентами в базе данных.
 * Все методы используют JDBC API для взаимодействия с H2.
 *
 * Примечание: H2 использует INT AUTO_INCREMENT (не SERIAL как в PostgreSQL).
 */
public class StudentRepository {

    private final Connection connection;

    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Шаг 1: Создание таблицы students.
     *
     * Создайте таблицу с полями:
     * - id: INT AUTO_INCREMENT PRIMARY KEY
     * - name: VARCHAR(100)
     * - age: INT
     * - grade: DOUBLE
     *
     * Используйте Statement и метод executeUpdate().
     * Оберните в try-with-resources.
     */
    public void createTable() throws SQLException {
        // TODO: Создайте таблицу students
        // Подсказка: используйте conn.createStatement() и stmt.executeUpdate(sql)
    }

    /**
     * Шаг 2: Вставка одного студента.
     *
     * Используйте PreparedStatement с параметрами (?) вместо конкатенации строк.
     * Это защищает от SQL-инъекций и позволяет кэшировать запрос.
     *
     * Верните сгенерированный id (используйте Statement.RETURN_GENERATED_KEYS).
     *
     * @return сгенерированный id записи
     */
    public long insert(Student student) throws SQLException {
        // TODO: Вставьте студента с помощью PreparedStatement
        // Подсказка:
        // 1. Создайте PreparedStatement с флагом RETURN_GENERATED_KEYS
        // 2. Установите параметры: ps.setString(1, ...), ps.setInt(2, ...), ps.setDouble(3, ...)
        // 3. Выполните ps.executeUpdate()
        // 4. Получите сгенерированный ключ через ps.getGeneratedKeys()
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
        // TODO: Выполните SELECT * FROM students и заполните список
        // Подсказка:
        // 1. Создайте Statement или PreparedStatement
        // 2. Выполните executeQuery(sql)
        // 3. Итерируйтесь по ResultSet: while (rs.next()) { ... }
        // 4. Для каждой строки: rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getDouble("grade")
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
        // TODO: Реализуйте batch-вставку с транзакцией
        // Подсказка:
        // 1. connection.setAutoCommit(false)
        // 2. Создайте PreparedStatement для INSERT
        // 3. В цикле: установите параметры и вызовите ps.addBatch()
        // 4. ps.executeBatch()
        // 5. connection.commit()
        // 6. В finally: connection.setAutoCommit(true)
    }
}
