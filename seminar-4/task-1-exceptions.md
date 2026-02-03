# Задание 1: Менеджер данных студентов

Создайте систему управления данными в университете, которая читает и записывает данные студентов в файлы.

## Требования

### 1. Создать три пользовательских исключения

#### InvalidStudentDataException (Проверяемое исключение)
- Расширяет `Exception`
- Выбрасывается, когда формат данных студента неверный
- Конструктор: `InvalidStudentDataException(String message)`
- Конструктор: `InvalidStudentDataException(String message, Throwable cause)`

#### DuplicateStudentException (Непроверяемое исключение)
- Расширяет `RuntimeException`
- Выбрасывается при попытке добавить студента с дублирующимся ID
- Конструктор: `DuplicateStudentException(String studentId)`
- Формат сообщения: "Student with ID {studentId} already exists"

#### StudentNotFoundException (Проверяемое исключение)
- Расширяет `Exception`
- Выбрасывается при попытке найти несуществующего студента
- Конструктор: `StudentNotFoundException(String studentId)`
- Формат сообщения: "Student with ID {studentId} not found"

### 2. Класс StudentDataManager

Управляет данными студентов с правильной обработкой исключений:

Методы для реализации:
- `void saveStudent(Student student)` - Сохраняет студента (выбрасывает DuplicateStudentException, если существует)
- `Student findStudent(String id)` - Находит студента (выбрасывает StudentNotFoundException, если не найден)
- `void saveToFile()` - Сохраняет всех студентов в файл
- `void loadFromFile()` - Загружает студентов из файла используя try-with-resources

Вспомогательный метод:
- `Student parseStudent(String line)` - Парсит "id,name,gpa,course1;course2"

#### Подсказки
- Используйте HashMap<String, Student> для хранения студентов
- Проверяйте `students.containsKey(id)` перед добавлением
- Форматируйте студента: `String.format("%s,%s,%.1f,%s", id, name, gpa, courses)`
- Формат курсов: `String.join(";", student.completedCourses())`
- В `loadFromFile()` используйте try-with-resources с StudentDataReader

### 3. Класс StudentDataReader

Реализует `AutoCloseable` для использования с try-with-resources:

Методы для реализации:
- Конструктор: `StudentDataReader(String filename) throws IOException`
- `Student readStudent() throws IOException, InvalidStudentDataException` - Читает одного студента, возвращает null в конце файла
- `void close() throws IOException` - Закрывает файл

#### Подсказки
- Храните BufferedReader как поле
- В `readStudent()`: возвращайте null когда `readLine()` возвращает null (EOF)
- Используйте предоставленный вспомогательный метод `parseStudent()`
- В `close()`: проверяйте `if (reader != null)` перед закрытием

## Формат данных

Данные студентов хранятся в формате CSV:
```
id,name,gpa,course1;course2;course3
S001,Alice Johnson,3.7,CS101;CS102;MATH201
S002,Bob Smith,2.8,CS101
```
