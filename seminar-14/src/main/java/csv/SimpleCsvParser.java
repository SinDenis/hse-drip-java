package csv;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SimpleCsvParser {

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

    private List<String> getHeaders(Class<?> clazz) {
        List<String> headers = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(CsvName.class)) {
                CsvName annotation = field.getAnnotation(CsvName.class);
                headers.add(annotation.value());
            } else {
                headers.add(field.getName());
            }
        }
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
    private <T> T parseLine(String line, String[] headers, Class<T> clazz) throws Exception {
        T obj = clazz.getDeclaredConstructor().newInstance();
        String[] values = line.split(",");

        for (int i = 0; i < headers.length; i++) {
            Field field = findFieldByHeader(clazz, headers[i]);
            if (field != null && i < values.length) {
                field.setAccessible(true);
                field.set(obj, convertValue(values[i].trim(), field.getType()));
            }
        }

        return obj;
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
        StringJoiner joiner = new StringJoiner(",");

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);
            joiner.add(String.valueOf(value));
        }

        return joiner.toString();
    }

    // ==================== ГОТОВЫЕ МЕТОДЫ (не трогать) ====================
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
