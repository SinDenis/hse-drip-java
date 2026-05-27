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

@DisplayName("Lifecycle hooks and method ordering")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LifecycleDemoTest {

    private static final List<String> events = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        events.add("beforeAll");
    }

    @BeforeEach
    void beforeEach() {
        events.add("beforeEach");
    }

    @AfterEach
    void afterEach() {
        events.add("afterEach");
    }

    @AfterAll
    static void afterAll() {
        events.add("afterAll");
        System.out.println("Lifecycle events: " + events);
        assertEquals("beforeAll", events.get(0));
        assertEquals("afterAll", events.get(events.size() - 1));
    }

    @Test
    @Order(1)
    void firstTest() {
        events.add("firstTest");
        assertTrue(events.contains("beforeAll"));
        assertTrue(events.contains("beforeEach"));
    }

    @Test
    @Order(2)
    void secondTest() {
        events.add("secondTest");
        assertTrue(events.contains("firstTest"),
                "Tests with @Order must run in the declared order");
    }
}
