package model;

import java.util.List;

/**
 * Неизменяемый record для представления студента
 *
 * @param id ID студента
 * @param name Имя студента
 * @param gpa Средний балл
 * @param completedCourses Список завершенных курсов (коды курсов)
 */
public record Student(
    String id,
    String name,
    double gpa,
    List<String> completedCourses
) {
}
