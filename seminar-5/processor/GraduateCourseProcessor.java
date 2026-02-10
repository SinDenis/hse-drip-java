package processor;

import model.Student;
import java.util.List;

/**
 * Процессор для курсов магистратуры
 * TODO: Реализовать класс, наследующий CourseProcessor
 */
public class GraduateCourseProcessor extends CourseProcessor {

    /**
     * Проверка предварительных требований для магистратуры
     * TODO: Реализовать проверку
     *
     * Требования:
     * - Студент должен пройти все курсы из списка prerequisites
     * - Студент должен иметь GPA >= 3.0
     *
     * Подсказка: используйте List.containsAll(Collection) и проверьте student.gpa()
     */
    @Override
    protected boolean validatePrerequisites(Student student, List<String> prerequisites) {
        // TODO: Реализовать проверку prerequisites и GPA
        return false;
    }

    /**
     * Проверка вместимости для магистратуры
     * TODO: Реализовать проверку
     *
     * Требования:
     * - Проверить что currentEnrollment < maxCapacity
     */
    @Override
    protected boolean checkCapacity(int currentEnrollment, int maxCapacity) {
        // TODO: Реализовать проверку вместимости
        return false;
    }
}
