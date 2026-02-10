package processor;

import model.Student;
import java.util.List;

/**
 * Процессор для курсов бакалавриата
 * TODO: Реализовать класс, наследующий CourseProcessor
 */
public class UndergraduateCourseProcessor extends CourseProcessor {

    /**
     * Проверка предварительных требований для бакалавриата
     * TODO: Реализовать проверку
     *
     * Требования:
     * - Студент должен пройти все курсы из списка prerequisites
     *
     * Подсказка: используйте List.containsAll(Collection)
     */
    @Override
    protected boolean validatePrerequisites(Student student, List<String> prerequisites) {
        // TODO: Реализовать проверку prerequisites
        return false;
    }

    /**
     * Проверка вместимости для бакалавриата
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
