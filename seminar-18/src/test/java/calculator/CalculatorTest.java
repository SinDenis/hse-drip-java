package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Calculator: basic arithmetic")
class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    @DisplayName("add returns the sum of two integers")
    void addReturnsSum() {
        assertEquals(7, calculator.add(3, 4));
    }

    @Test
    void subtractReturnsDifference() {
        assertEquals(1, calculator.subtract(5, 4));
    }

    @Test
    void multiplyReturnsProduct() {
        assertEquals(20, calculator.multiply(4, 5));
    }

    @Test
    void divideReturnsQuotient() {
        assertEquals(2, calculator.divide(10, 5));
    }

    @Test
    @DisplayName("divide by zero throws ArithmeticException")
    void divideByZeroThrows() {
        ArithmeticException error = assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(10, 0)
        );
        assertEquals("Division by zero", error.getMessage());
    }

    @Test
    @DisplayName("assertAll groups several related assertions")
    void multipleAssertionsAtOnce() {
        assertAll("calculator operations",
                () -> assertEquals(0, calculator.add(-5, 5)),
                () -> assertEquals(-10, calculator.subtract(0, 10)),
                () -> assertEquals(0, calculator.multiply(0, 42)),
                () -> assertEquals(3, calculator.divide(9, 3))
        );
    }
}
