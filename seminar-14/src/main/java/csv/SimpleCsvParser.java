package csv;

import java.io.*;
import java.lang.annotation.Annotation;
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

    //id,full_name,age,active
    //1,Ivan Ivanov,20,true
    private Object parseLine(String line, String[] headers, Class<?> clazz) {
        String[] args = line.split(",");

        try {
            Object object = clazz.getDeclaredConstructor().newInstance();
            int index = 0;
            for (String header: headers) {
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(CsvName.class)) {
                        Annotation annotation = field.getAnnotation(CsvName.class);
                        field.set(object, args[index]);
                    } else if (field.getName().equals(header)) {
                        if (index < args.length) {
                            field.set(object, args[index]);
                        }
                    }
                }
                index++;
            }
        } catch (Exception e) {
            //
        }
    }

    public <T> List<T> parseFile(String filename, Class<T> clazz) throws Exception {
        List<T> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // заголовки
            String headerLine = reader.readLine();
            if (headerLine == null) {
                return result;
            }
            String[] headers = headerLine.split(",");

            // данные
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
