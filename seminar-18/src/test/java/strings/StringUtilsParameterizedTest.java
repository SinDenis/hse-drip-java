package strings;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("StringUtils: parameterized tests")
class StringUtilsParameterizedTest {

    @ParameterizedTest(name = "\"{0}\" is a palindrome")
    @ValueSource(strings = {"level", "racecar", "madam", "A man a plan a canal Panama", ""})
    void detectsPalindromes(String input) {
        assertTrue(StringUtils.isPalindrome(input));
    }

    @ParameterizedTest(name = "\"{0}\" is not a palindrome")
    @ValueSource(strings = {"hello", "world", "junit"})
    void detectsNonPalindromes(String input) {
        assertFalse(StringUtils.isPalindrome(input));
    }

    @ParameterizedTest
    @NullSource
    void nullIsNotPalindrome(String input) {
        assertFalse(StringUtils.isPalindrome(input));
    }

    @ParameterizedTest(name = "reverse(\"{0}\") = \"{1}\"")
    @CsvSource({
            "abc, cba",
            "JUnit, tinUJ",
            "'', ''",
            "a, a"
    })
    void reverseProducesExpectedValue(String input, String expected) {
        assertEquals(expected, StringUtils.reverse(input));
    }

    @ParameterizedTest(name = "countVowels(\"{0}\") = {1}")
    @MethodSource("vowelSamples")
    void countsVowelsCorrectly(String input, int expected) {
        assertEquals(expected, StringUtils.countVowels(input));
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> vowelSamples() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of("hello", 2),
                org.junit.jupiter.params.provider.Arguments.of("JUnit", 2),
                org.junit.jupiter.params.provider.Arguments.of("", 0),
                org.junit.jupiter.params.provider.Arguments.of("rhythm", 1),
                org.junit.jupiter.params.provider.Arguments.of("AEIOU", 5)
        );
    }
}
