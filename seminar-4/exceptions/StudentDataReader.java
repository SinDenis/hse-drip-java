package exceptions;

import courses.Student;

import java.util.Arrays;
import java.util.List;

/**
 * TODO Реализует `AutoCloseable` для использования с try-with-resources:
 * <p>
 * TODO Методы для реализации:
 * - Конструктор: `StudentDataReader(String filename) throws IOException`
 * - `Student readStudent() throws IOException, InvalidStudentDataException` - Читает одного студента, возвращает null в конце файла
 * - `void close() throws IOException` - Закрывает файл
 */
public class StudentDataReader {

    private Student parseStudent(String line) throws InvalidStudentDataException {
        try {
            String[] parts = line.split(",");
            if (parts.length != 4) {
                throw new InvalidStudentDataException("Invalid format: expected 4 fields, got " + parts.length);
            }
            String id = parts[0];
            String name = parts[1];
            double gpa = Double.parseDouble(parts[2]);
            List<String> courses = parts[3].isEmpty() ?
                    List.of() : Arrays.asList(parts[3].split(";"));
            return new Student(id, name, gpa, courses);
        } catch (NumberFormatException e) {
            throw new InvalidStudentDataException("Invalid GPA format", e);
        }
    }
}
