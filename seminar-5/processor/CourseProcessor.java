package processor;

import model.Student;
import java.util.List;

/**
 * Абстрактный класс для обработки записи студентов на курсы
 * Реализует паттерн Template Method
 */
public abstract class CourseProcessor {

    /**
     * Template Method - определяет алгоритм записи студента на курс
     * TODO: Реализовать метод enrollStudent
     *
     * Алгоритм:
     * 1. Вызвать validatePrerequisites()
     * 2. Если валидно → Вызвать checkCapacity()
     * 3. Если есть места → Вызвать logEnrollment()
     * 4. Вернуть true/false в зависимости от успеха
     *
     * @param student - студент для записи
     * @param courseCode - код курса
     * @param prerequisites - список требуемых курсов
     * @param currentEnrollment - текущее количество записанных студентов
     * @param maxCapacity - максимальная вместимость
     * @return true если запись успешна, false в противном случае
     */
    public final boolean enrollStudent(Student student, String courseCode,
                                      List<String> prerequisites,
                                      int currentEnrollment,
                                      int maxCapacity) {
        // TODO: Реализовать
        return false;
    }

    /**
     * Абстрактный метод для проверки предварительных требований
     * TODO: Каждый подкласс должен реализовать свою логику проверки
     *
     * @param student - студент для проверки
     * @param prerequisites - список требуемых курсов
     * @return true если требования выполнены
     */
    protected abstract boolean validatePrerequisites(Student student, List<String> prerequisites);

    /**
     * Абстрактный метод для проверки вместимости курса
     * TODO: Каждый подкласс должен реализовать свою логику проверки
     *
     * @param currentEnrollment - текущее количество студентов
     * @param maxCapacity - максимальная вместимость
     * @return true если есть свободные места
     */
    protected abstract boolean checkCapacity(int currentEnrollment, int maxCapacity);

    /**
     * Логирует успешную запись студента на курс
     * TODO: Реализовать метод для вывода информации о записи
     *
     * @param student - записанный студент
     * @param courseCode - код курса
     */
    protected void logEnrollment(Student student, String courseCode) {
        // TODO: Реализовать логирование
    }
}
