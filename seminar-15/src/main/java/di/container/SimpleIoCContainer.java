package di.container;

import di.annotation.Autowired;
import di.annotation.Bean;
import di.annotation.Component;
import di.annotation.Configuration;
import org.reflections.Reflections;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimpleIoCContainer {

    private final Map<Class<?>, Object> beanMap = new HashMap<>();

    public void register(Class<?> clazz, Object instance) {
        beanMap.put(clazz, instance);
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> clazz) {
        return (T) beanMap.get(clazz);
    }

    public void scanComponents(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> componentClasses = reflections.getTypesAnnotatedWith(Component.class);

        for (Class<?> clazz : componentClasses) {
            try {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                beanMap.put(clazz, instance);
            } catch (Exception e) {
                throw new RuntimeException("Не удалось создать бин для " + clazz.getName(), e);
            }
        }
    }

    public void wireDependencies() {
        for (Object bean : beanMap.values()) {
            for (Field field : bean.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Class<?> fieldType = field.getType();
                    Object dependency = findBean(fieldType);
                    if (dependency == null) {
                        throw new RuntimeException("Бин не найден для типа " + fieldType.getName());
                    }
                    try {
                        field.setAccessible(true);
                        field.set(bean, dependency);
                    } catch (Exception e) {
                        throw new RuntimeException("Не удалось внедрить зависимость", e);
                    }
                }
            }
        }
    }

    private Object findBean(Class<?> type) {
        Object bean = beanMap.get(type);
        if (bean != null) return bean;
        for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
            if (type.isAssignableFrom(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
