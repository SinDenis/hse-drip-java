package exceptions;

import courses.Student;

import java.util.Arrays;
import java.util.List;

/**
 * Управляет данными студентов с правильной обработкой исключений:
 * <p>
 * Методы для реализации:
 * - `void saveStudent(Student student)` - Сохраняет студента (выбрасывает DuplicateStudentException, если существует)
 * - `Student findStudent(String id)` - Находит студента (выбрасывает StudentNotFoundException, если не найден)
 * - `void saveToFile()` - Сохраняет всех студентов в файл
 * - `void loadFromFile()` - Загружает студентов из файла используя try-with-resources
 * <p>
 * Вспомогательный метод:
 * - `Student parseStudent(String line)` - Парсит "id,name,gpa,course1;course2"
 * <p>
 * Подсказки
 * - Используйте HashMap<String, Student> для хранения студентов
 * - Проверяйте `students.containsKey(id)` перед добавлением
 * - Форматируйте данные студента: `String.format("%s,%s,%.1f,%s", id, name, gpa, courses)`
 * - Формат курсов: `String.join(";", student.completedCourses())`
 * - В `loadFromFile()` используйте try-with-resources с StudentDataReader
 */
public class StudentDataManager {

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

    private String formatStudent(Student student) {
        String courses = String.join(";", student.completedCourses());
        return String.format("%s,%s,%.1f,%s",
                student.id(), student.name(), student.gpa(), courses);
    }
}
