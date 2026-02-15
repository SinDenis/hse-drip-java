package ru.centraluniversity;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WordsCalculatorTest {

    private final WordsCalculator instance = new StreamWordsCalculator();

    @Test
    void shouldMatchResult() throws Exception {
        var wap12Path = getResourcePath("WAP12.txt");
        var wap34Path = getResourcePath("WAP34.txt");

        var expected = getExpected();
        var result = instance.getResult(wap12Path, wap34Path);
        assertEquals(expected, result);
    }

    private String getExpected() throws IOException, URISyntaxException {
        var resultPath = getResourcePath("WAPResult.txt");
        return String.join("\n", Files.readAllLines(resultPath));
    }

    private Path getResourcePath(String resourceName) throws URISyntaxException {
        var resourceUrl = Objects.requireNonNull(getClass().getClassLoader().getResource(resourceName));
        return Path.of(resourceUrl.toURI());
    }
}
