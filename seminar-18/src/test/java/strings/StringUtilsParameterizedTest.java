package strings;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("StringUtils: параметризованные тесты")
class StringUtilsParameterizedTest {

    @ParameterizedTest(name = "\"{0}\" — палиндром")
    @ValueSource(strings = {"level", "racecar", "madam", "A man a plan a canal Panama", ""})
    void detectsPalindromes(String input) {
        assertTrue(StringUtils.isPalindrome(input));
    }

    @ParameterizedTest(name = "\"{0}\" — не палиндром")
    @ValueSource(strings = {"hello", "world", "junit"})
    void detectsNonPalindromes(String input) {
        assertFalse(StringUtils.isPalindrome(input));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("null — не палиндром")
    void nullIsNotPalindrome(String input) {
        assertFalse(StringUtils.isPalindrome(input));
    }

    @ParameterizedTest(name = "reverse(\"{0}\") = \"{1}\"")
    @CsvSource({
            "abc,   cba",
            "JUnit, tinUJ",
            "'',   ''",
            "a,     a"
    })
    void reverse(String input, String expected) {
        assertEquals(expected, StringUtils.reverse(input));
    }

    @ParameterizedTest(name = "isAnagram(\"{0}\", \"{1}\") = {2}")
    @CsvSource({
            "listen,     silent,       true",
            "hello,      world,        false",
            "Astronomer, Moon starer,  true",
            "abc,        cba,          true",
            "rat,        car,          false",
            "dusty,      study,        true",
            "abc,        abcd,         false"
    })
    void detectsAnagrams(String a, String b, boolean expected) {
        assertEquals(expected, StringUtils.isAnagram(a, b));
    }

    @ParameterizedTest(name = "countVowels(\"{0}\") = {1}")
    @MethodSource("vowelSamples")
    void countsVowels(String input, int expected) {
        assertEquals(expected, StringUtils.countVowels(input));
    }

    static Stream<Arguments> vowelSamples() {
        return Stream.of(
                Arguments.of("hello",  2),
                Arguments.of("JUnit",  2),
                Arguments.of("",       0),
                Arguments.of("rhythm", 1),
                Arguments.of("AEIOU",  5)
        );
    }
}
