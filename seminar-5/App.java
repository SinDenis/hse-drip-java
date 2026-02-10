import model.Student;
import processor.CourseProcessor;
import processor.GraduateCourseProcessor;
import processor.LabCourseProcessor;
import processor.UndergraduateCourseProcessor;

import java.util.List;

public class App {

    public static void main(String[] args) {
        System.out.println("=== Система записи на курсы ===\n");

        // Создание тестовых студентов
        Student student1 = new Student(
            "S001",
            "Иван Иванов",
            3.5,
            List.of("CS101", "CS102", "MATH201")
        );

        Student student2 = new Student(
            "S002",
            "Мария Петрова",
            2.8,
            List.of("CS101")
        );

        Student student3 = new Student(
            "S003",
            "Петр Сидоров",
            3.8,
            List.of("CS101", "CS102", "MATH201", "CS301")
        );

        // ШАГ 1: Откройте файл processor/CourseProcessor.java
        // Реализуйте метод enrollStudent() - Template Method:
        //   1. Вызовите validatePrerequisites(), если false - верните false
        //   2. Вызовите checkCapacity(), если false - верните false
        //   3. Вызовите logEnrollment() и верните true
        // Реализуйте метод logEnrollment() - выведите "Student [имя] enrolled in course [код]"

        // ШАГ 2: Откройте файл processor/UndergraduateCourseProcessor.java
        // В validatePrerequisites() проверьте: student.completedCourses().containsAll(prerequisites)
        // В checkCapacity() проверьте: currentEnrollment < maxCapacity
        CourseProcessor undergrad = new UndergraduateCourseProcessor();

        // ШАГ 3: Откройте файл processor/GraduateCourseProcessor.java
        // В validatePrerequisites() проверьте: prerequisites пройдены И student.gpa() >= 3.0
        // В checkCapacity() проверьте: currentEnrollment < maxCapacity
        CourseProcessor graduate = new GraduateCourseProcessor();

        // ШАГ 4: Откройте файл processor/LabCourseProcessor.java
        // В validatePrerequisites() проверьте: prerequisites пройдены
        // В checkCapacity() проверьте: currentEnrollment < (maxCapacity - 2)
        // Помните: 2 места резервируются под оборудование!
        CourseProcessor lab = new LabCourseProcessor();

        // ШАГ 5: Проверьте результаты
        // Ожидаемые результаты тестов:
        // Тест 1: Успешно (все условия выполнены)
        // Тест 2: Неудача (нет мест, 30/30)
        // Тест 3: Неудача (GPA 2.8 < 3.0)
        // Тест 4: Успешно (GPA 3.8 >= 3.0, все prerequisites есть)
        // Тест 5: Неудача (18 студентов = 18 доступных мест с резервом)
        // Тест 6: Успешно (15 < 18 доступных мест)
        // Тест 7: Неудача (студент не прошел CS102 и CS201)

        // Тест 1: Бакалавриат - успешная запись
        System.out.println("Тест 1: Запись на курс бакалавриата");
        boolean result1 = undergrad.enrollStudent(
            student1,
            "CS201",
            List.of("CS101", "CS102"),
            25,
            30
        );
        System.out.println("Результат: " + (result1 ? "Успешно" : "Неудача"));
        System.out.println();

        // Тест 2: Бакалавриат - нет мест
        System.out.println("Тест 2: Запись на переполненный курс бакалавриата");
        boolean result2 = undergrad.enrollStudent(
            student1,
            "CS202",
            List.of("CS101"),
            30,
            30
        );
        System.out.println("Результат: " + (result2 ? "Успешно" : "Неудача (нет мест)"));
        System.out.println();

        // Тест 3: Магистратура - низкий GPA
        System.out.println("Тест 3: Запись в магистратуру с низким GPA");
        boolean result3 = graduate.enrollStudent(
            student2,
            "CS501",
            List.of("CS101"),
            10,
            20
        );
        System.out.println("Результат: " + (result3 ? "Успешно" : "Неудача (GPA < 3.0)"));
        System.out.println();

        // Тест 4: Магистратура - успешная запись
        System.out.println("Тест 4: Запись в магистратуру с достаточным GPA");
        boolean result4 = graduate.enrollStudent(
            student3,
            "CS502",
            List.of("CS301"),
            15,
            25
        );
        System.out.println("Результат: " + (result4 ? "Успешно" : "Неудача"));
        System.out.println();

        // Тест 5: Лабораторный курс - с учетом резерва
        System.out.println("Тест 5: Запись на лабораторный курс");
        boolean result5 = lab.enrollStudent(
            student1,
            "LAB101",
            List.of("CS101", "CS102"),
            18,
            20
        );
        System.out.println("Результат: " + (result5 ? "Успешно" : "Неудача (макс вместимость = 20 - 2 = 18)"));
        System.out.println();

        // Тест 6: Лабораторный курс - с местами
        System.out.println("Тест 6: Запись на лабораторный курс с местами");
        boolean result6 = lab.enrollStudent(
            student1,
            "LAB102",
            List.of("CS101", "CS102"),
            15,
            20
        );
        System.out.println("Результат: " + (result6 ? "Успешно" : "Неудача"));
        System.out.println();

        // Тест 7: Отсутствие предварительных требований
        System.out.println("Тест 7: Запись без выполненных prerequisites");
        boolean result7 = undergrad.enrollStudent(
            student2,
            "CS301",
            List.of("CS101", "CS102", "CS201"),
            10,
            30
        );
        System.out.println("Результат: " + (result7 ? "Успешно" : "Неудача (не пройдены prerequisites)"));

        System.out.println("\n=== Завершение тестирования ===");
    }
}
