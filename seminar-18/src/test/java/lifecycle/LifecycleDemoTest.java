package lifecycle;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Жизненный цикл тестов")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LifecycleDemoTest {

    private static final List<String> events = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        events.add("beforeAll");
        System.out.println("▶ beforeAll");
    }

    @BeforeEach
    void beforeEach() {
        events.add("beforeEach");
        System.out.println("  → beforeEach");
    }

    @AfterEach
    void afterEach() {
        events.add("afterEach");
        System.out.println("  ← afterEach");
    }

    @AfterAll
    static void afterAll() {
        events.add("afterAll");
        System.out.println("◀ afterAll");
        System.out.println("Все события: " + events);

        assertEquals("beforeAll", events.get(0));
        assertEquals("afterAll",  events.get(events.size() - 1));
    }

    @Test
    @Order(1)
    @DisplayName("первый тест — видит beforeAll и beforeEach")
    void firstTest() {
        events.add("firstTest");
        assertTrue(events.contains("beforeAll"));
        assertTrue(events.contains("beforeEach"));
    }

    @Test
    @Order(2)
    @DisplayName("второй тест — выполняется строго после первого")
    void secondTest() {
        events.add("secondTest");
        assertTrue(events.contains("firstTest"),
                "@Order гарантирует порядок выполнения");
    }
}
