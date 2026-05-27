package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Calculator")
class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    @DisplayName("сложение двух чисел")
    void add() {
        assertEquals(7, calculator.add(3, 4));
    }

    @Test
    @DisplayName("вычитание")
    void subtract() {
        assertEquals(1, calculator.subtract(5, 4));
    }

    @Test
    @DisplayName("умножение")
    void multiply() {
        assertEquals(20, calculator.multiply(4, 5));
    }

    @Test
    @DisplayName("деление")
    void divide() {
        assertEquals(2, calculator.divide(10, 5));
    }

    @Test
    @DisplayName("деление на ноль бросает ArithmeticException")
    void divideByZeroThrows() {
        ArithmeticException ex = assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(10, 0)
        );
        assertEquals("Division by zero", ex.getMessage());
    }

    @Test
    @DisplayName("отрицательный показатель бросает IllegalArgumentException")
    void powerNegativeExponentThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.power(2, -1));
    }

    @Test
    @DisplayName("assertAll — несколько утверждений за раз")
    void assertAllDemo() {
        assertAll("базовые операции",
                () -> assertEquals(0,   calculator.add(-5, 5)),
                () -> assertEquals(-10, calculator.subtract(0, 10)),
                () -> assertEquals(0,   calculator.multiply(0, 42)),
                () -> assertEquals(3,   calculator.divide(9, 3))
        );
    }

    @Test
    @DisplayName("возведение в степень")
    void power() {
        assertAll("power",
                () -> assertEquals(8, calculator.power(2, 3)),
                () -> assertEquals(1, calculator.power(5, 0)),
                () -> assertEquals(5, calculator.power(5, 1)),
                () -> assertEquals(1, calculator.power(1, 100))
        );
    }
}
