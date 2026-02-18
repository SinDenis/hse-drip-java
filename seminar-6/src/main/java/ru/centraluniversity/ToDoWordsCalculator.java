package ru.centraluniversity;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToDoWordsCalculator implements WordsCalculator {

    @Override
    public String getResult(Path... paths) {
        try (Stream<String> lines = Arrays.stream(paths)
                .flatMap(path -> {
                            try {
                                return Files.lines(path, Charset.forName("cp1251"))
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }
                )
        ) {

            // Старый подход
            String[] words = {"пьер", "Пьер"};
            String[] parsedWords = new String[2];
            for (int i = 0; i < words.length; i++) {
                parsedWords[i] = words[i].toLowerCase();
            }

            // Новый подход
            String[] words2 = {"пьер", "Пьер"};
            Arrays.stream(words2)
                    .map(word -> word.toLowerCase())
                    .collect(Collectors.toSet())
            ;

            // TODO Продолжите обрабатывать поток строк в переменной lines
        }


        // TODO посчитать количество слов в текстах, расположенных по указанным путям.
        //  Результатом должен быть текст, в котором каждая строка является
        //  парой "слово в нижнем регистре - количество вхождений указанного слова в тексте".
        //  Например, если слово "пьер" встречается в тексте 50 раз,
        //  то в результирующем тексте пара должна быть записана как "пьер - 50\n".
        //  Пары в тексте должны быть отсортированы сначала по количеству вхождений слова в порядке убывания,
        //  а затем в лексикографическом порядке.
        //  В результирующий текст не должны попасть слова длиной менее 4 символов
        //  или те, что встречаются менее 10 раз в исходных текстах.
        //  В решении требуется использовать java.util.Stream и классы java.io/java.nio.
        //  Запрещено использовать циклы for и if-выражения.
        return "";
    }
}
