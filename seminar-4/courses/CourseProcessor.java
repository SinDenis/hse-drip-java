package courses;

/**
 * Создайте абстрактный класс `CourseProcessor`. В нем должны быть:
 * <p>
 * Абстрактные методы:
 * - `protected abstract boolean validatePrerequisites(Student student, List<String> prerequisites)`
 * - `protected abstract boolean checkCapacity(int currentEnrollment, int maxCapacity)`
 * <p>
 * Реалзиованные методы:
 * - `public final boolean enrollStudent(...)` - Метод-шаблон, который вызывает абстрактные методы
 * - `protected void logEnrollment(Student student, String courseCode)` - Логирует успешную запись
 * <p>
 * Метод `enrollStudent()` определяет алгоритм записи:
 * 1. Вызвать validatePrerequisites()
 * 2. Если валидно → Вызвать checkCapacity()
 * 3. Если есть места → Вызвать logEnrollment()
 * 4. Вернуть true/false в зависимости от успеха
 */
public abstract class CourseProcessor {
}
