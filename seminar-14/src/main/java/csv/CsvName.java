package csv;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания имени столбца в CSV-файле.
 *
 * Если поле помечено @CsvName("custom_name"), то в CSV используется "custom_name"
 * вместо имени поля Java.
 *
 * Обратите внимание на мета-аннотации:
 * - @Retention(RUNTIME) — аннотация доступна через рефлексию во время выполнения
 * - @Target(FIELD) — аннотация применяется только к полям класса
 *
 * Эта аннотация — аналог того, что нужно будет реализовать в домашнем задании (CsvParser).
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CsvName {
    String value();
}
