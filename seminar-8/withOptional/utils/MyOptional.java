package withOptional.utils;

import java.util.function.Supplier;

public class MyOptional<T> {

    private final T value;

    private MyOptional(T value) {
        this.value = value;
    }

    public boolean ifPresent() {
        return value != null;
    }

    public static <T> MyOptional<T> of(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value is null");
        }
        return new MyOptional(value);
    }

    public static <T> MyOptional<T> ofNullable(T value) {
        return new MyOptional<>(value);
    }

    public T ifPresent(Supplier<? extends T> supplier) {
        if (ifPresent()) {
            return value;
        } else {
            return supplier.get();
        }
    }

    public T orElse(T defaultValue) {
        if (ifPresent()) {
            return value;
        } else {
            return defaultValue;
        }
    }

    public T orElse(Supplier<? extends T> supplier) {
        if (ifPresent()) {
            return value;
        } else {
            return supplier.get();
        }
    }

}
