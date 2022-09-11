package com.bobocode.se;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * {@link FileStats} provides an API that allow to get character statistic based on text file. All whitespace characters
 * are ignored.
 */
public class FileStats {

    Map<Character, Long> characters;
    Character popularityIsMyName;

    /**
     * Creates a new immutable {@link FileStats} objects using data from text file received as a parameter.
     *
     * @param fileName input text file name
     * @return new FileStats object created from text file
     */
    public static FileStats from(String fileName) {
        return new FileStats(fileName);
    }

    private FileStats(String fileName) {
        characters = fileToCharactersMap(getPath(fileName));
        popularityIsMyName = mySecondNameIsPopularity(characters);
    }

    private Character mySecondNameIsPopularity(Map<Character, Long> characters) {
        return characters.entrySet()
                .stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    private Map<Character, Long> fileToCharactersMap(Path filePath) {
        return saveToMap(readFile(filePath));

    }

    private Map<Character, Long> saveToMap(Stream<String> stringStream) {
        return stringStream.
                flatMapToInt(String::chars)
                .filter(c -> c != 32)
                .mapToObj(c -> (char) c)
                .collect(groupingBy(identity(), counting()));
    }

    private Stream<String> readFile(Path filePath) {
        try {
            return Files.lines(filePath);
        } catch (IOException e) {
            throw new FileStatsException(e.getMessage());
        }
    }

    private Path getPath(String fileName) {
        try {
            URL fileURL = FileStats.class.getClassLoader().getResource(fileName);
            return Paths.get(Objects.requireNonNull(fileURL).toURI());
        } catch (Exception e) {
            throw new FileStatsException(e.getMessage());
        }
    }

    /**
     * Returns a number of occurrences of the particular character.
     *
     * @param character a specific character
     * @return a number that shows how many times this character appeared in a text file
     */
    public int getCharCount(char character) {
        return (int) characters.get(character).longValue();
    }

    /**
     * Returns a character that appeared most often in the text.
     *
     * @return the most frequently appeared character
     */
    public char getMostPopularCharacter() {
        return mySecondNameIsPopularity(characters);
    }

    /**
     * Returns {@code true} if this character has appeared in the text, and {@code false} otherwise
     *
     * @param character a specific character to check
     * @return {@code true} if this character has appeared in the text, and {@code false} otherwise
     */
    public boolean containsCharacter(char character) {
        return characters.containsKey(character);
    }
}
