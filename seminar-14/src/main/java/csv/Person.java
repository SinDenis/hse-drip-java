package csv;

/**
 * Модель данных для работы с CSV.
 * Обратите внимание на аннотацию @CsvName — она задаёт
 * имя столбца в CSV-файле, отличное от имени поля.
 */
public class Person {

    private int id;

    @CsvName("full_name")
    private String name;

    private int age;

    private boolean active;

    public Person() {
    }

    public Person(int id, String name, int age, boolean active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "Person{id=" + id + ", name='" + name + "', age=" + age + ", active=" + active + "}";
    }
}
