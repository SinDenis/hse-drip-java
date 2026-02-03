# Задание 2: Система обработки курсов

Создайте систему записи на курсы с различными правилами записи для бакалавриата, магистратуры и лабораторных курсов. 

## Требования

### 1. Record Student
Создайте неизменяемый record `Student` со следующими полями:
- `String id` - ID студента
- `String name` - Имя студента
- `double gpa` - Средний балл
- `List<String> completedCourses` - Список завершенных курсов (коды курсов)

### 2. Абстрактный класс CourseProcessor
Создайте абстрактный класс `CourseProcessor`. В нем должны быть:

Абстрактные методы:
- `protected abstract boolean validatePrerequisites(Student student, List<String> prerequisites)`
- `protected abstract boolean checkCapacity(int currentEnrollment, int maxCapacity)`

Реалзиованные методы:
- `public final boolean enrollStudent(...)` - Метод-шаблон, который вызывает абстрактные методы
- `protected void logEnrollment(Student student, String courseCode)` - Логирует успешную запись

Метод `enrollStudent()` определяет алгоритм записи:

```
enrollStudent():
  1. Вызвать validatePrerequisites()
  2. Если валидно → Вызвать checkCapacity()
  3. Если есть места → Вызвать logEnrollment()
  4. Вернуть true/false в зависимости от успеха
```

### 3. Требования к validatePrerequisites()

#### UndergraduateCourseProcessor
Какие проверки требуются:
- Студент прошел набор из курсов(передаются в аргументе `prerequisites`)
- Проверить что еще есть места на курсе

#### GraduateCourseProcessor
- Студент прошел набор из курсов(передаются в аргументе `prerequisites`)
- Студент имеет GPA >= 3.0
- Проверить что еще есть места на курсе

#### LabCourseProcessor
- Студент прошел набор из курсов(передаются в аргументе `prerequisites`)
- Проверить что еще есть места на курсе. 
  - При этом 2 места на курсе зарезервированы под оборудование. Т.е. макс вместимость = max - 2.

## Подсказки
- Используйте `List.containsAll(Collection)` для проверки наличия всех курсов
