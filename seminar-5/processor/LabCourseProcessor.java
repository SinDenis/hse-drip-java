package processor;

import model.Student;
import java.util.List;

/**
 * Процессор для лабораторных курсов
 * TODO: Реализовать класс, наследующий CourseProcessor
 */
public class LabCourseProcessor extends CourseProcessor {

    /**
     * Проверка предварительных требований для лабораторных курсов
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
     * Проверка вместимости для лабораторных курсов
     * TODO: Реализовать проверку
     *
     * Требования:
     * - 2 места на курсе зарезервированы под оборудование
     * - Максимальная вместимость = maxCapacity - 2
     * - Проверить что currentEnrollment < (maxCapacity - 2)
     */
    @Override
    protected boolean checkCapacity(int currentEnrollment, int maxCapacity) {
        // TODO: Реализовать проверку вместимости с учетом резерва под оборудование
        return false;
    }
}
