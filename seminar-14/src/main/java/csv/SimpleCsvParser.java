package csv;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Простой CSV-парсер, использующий Java Reflection API.
 * Этот класс подготовит вас к выполнению домашнего задания CsvParser.
 *
 * Ключевые методы рефлексии, которые понадобятся:
 * - clazz.getDeclaredFields() — получить все поля класса
 * - field.isAnnotationPresent(CsvName.class) — проверить наличие аннотации
 * - field.getAnnotation(CsvName.class) — получить значение аннотации
 * - field.setAccessible(true) — разрешить доступ к приватному полю
 * - field.set(obj, value) — установить значение поля
 * - field.get(obj) — прочитать значение поля
 * - clazz.getDeclaredConstructor().newInstance() — создать объект
 */
public class SimpleCsvParser {

    /**
     * Вспомогательный метод: преобразует строку в нужный тип.
     * Этот метод уже реализован — используйте его в parseLine().
     */
    private Object convertValue(String value, Class<?> type) {
        if (type == int.class || type == Integer.class) {
            return Integer.parseInt(value);
        } else if (type == long.class || type == Long.class) {
            return Long.parseLong(value);
        } else if (type == double.class || type == Double.class) {
            return Double.parseDouble(value);
        } else if (type == boolean.class || type == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (type == String.class) {
            return value;
        }
        throw new IllegalArgumentException("Неподдерживаемый тип: " + type);
    }

    /**
     * Вспомогательный метод: находит поле по имени заголовка CSV.
     * Проверяет @CsvName, если не найдено — ищет по имени поля.
     * Этот метод уже реализован — используйте его в parseLine().
     */
    private Field findFieldByHeader(Class<?> clazz, String header) {
        for (Field field : clazz.getDeclaredFields()) {
            CsvName csvName = field.getAnnotation(CsvName.class);
            if (csvName != null && csvName.value().equals(header)) {
                return field;
            }
            if (field.getName().equals(header)) {
                return field;
            }
        }
        return null;
    }

    // ==================== МЕТОДЫ ДЛЯ РЕАЛИЗАЦИИ ====================

    /**
     * Шаг 5: Получить заголовки CSV из полей класса.
     *
     * Для каждого поля класса:
     * - Если поле помечено @CsvName — использовать значение аннотации
     * - Иначе — использовать имя поля (field.getName())
     *
     * @param clazz класс, из которого извлекаем заголовки
     * @return список заголовков CSV
     */
    public List<String> getHeaders(Class<?> clazz) {
        List<String> headers = new ArrayList<>();
        // TODO: Получите все поля класса через clazz.getDeclaredFields()
        // Для каждого поля:
        //   - Проверьте field.isAnnotationPresent(CsvName.class)
        //   - Если да: headers.add(field.getAnnotation(CsvName.class).value())
        //   - Если нет: headers.add(field.getName())
        return headers;
    }

    /**
     * Шаг 6: Распарсить одну строку CSV в объект.
     *
     * 1. Создайте новый экземпляр класса через рефлексию
     * 2. Разбейте строку по запятой
     * 3. Для каждого значения найдите соответствующее поле по заголовку
     * 4. Установите значение поля через рефлексию
     *
     * @param line строка CSV (например: "1,Ivan Ivanov,20,true")
     * @param headers массив заголовков (например: ["id", "full_name", "age", "active"])
     * @param clazz целевой класс
     * @return объект типа T с заполненными полями
     */
    public <T> T parseLine(String line, String[] headers, Class<T> clazz) throws Exception {
        // TODO: Реализуйте парсинг строки CSV в объект
        // Подсказка:
        // 1. T obj = clazz.getDeclaredConstructor().newInstance();
        // 2. String[] values = line.split(",");
        // 3. Для каждого i от 0 до headers.length:
        //    a. Найдите поле: Field field = findFieldByHeader(clazz, headers[i]);
        //    b. field.setAccessible(true);
        //    c. field.set(obj, convertValue(values[i], field.getType()));
        // 4. return obj;
        return null;
    }

    /**
     * Шаг 7: Преобразовать объект в строку CSV.
     *
     * Для каждого поля класса прочитайте значение через рефлексию
     * и соедините значения запятой.
     *
     * @param obj объект для сериализации
     * @param clazz класс объекта
     * @return строка CSV (например: "1,Ivan Ivanov,20,true")
     */
    public String toCsvLine(Object obj, Class<?> clazz) throws Exception {
        // TODO: Реализуйте конвертацию объекта в строку CSV
        // Подсказка:
        // 1. StringJoiner joiner = new StringJoiner(",");
        // 2. Для каждого поля из clazz.getDeclaredFields():
        //    a. field.setAccessible(true);
        //    b. Object value = field.get(obj);
        //    c. joiner.add(String.valueOf(value));
        // 3. return joiner.toString();
        return "";
    }

    // ==================== ГОТОВЫЕ МЕТОДЫ (не трогать) ====================

    /**
     * Читает CSV-файл и возвращает список объектов.
     * Использует getHeaders() и parseLine() — реализуйте их выше.
     */
    public <T> List<T> parseFile(String filename, Class<T> clazz) throws Exception {
        List<T> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Первая строка — заголовки
            String headerLine = reader.readLine();
            if (headerLine == null) {
                return result;
            }
            String[] headers = headerLine.split(",");

            // Остальные строки — данные
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    T obj = parseLine(line, headers, clazz);
                    if (obj != null) {
                        result.add(obj);
                    }
                }
            }
        }

        return result;
    }

    /**
     * Записывает список объектов в CSV-файл.
     * Использует getHeaders() и toCsvLine() — реализуйте их выше.
     */
    public <T> void writeFile(String filename, List<T> objects, Class<T> clazz) throws Exception {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // Записываем заголовки
            List<String> headers = getHeaders(clazz);
            writer.println(String.join(",", headers));

            // Записываем данные
            for (T obj : objects) {
                writer.println(toCsvLine(obj, clazz));
            }
        }
    }
}
