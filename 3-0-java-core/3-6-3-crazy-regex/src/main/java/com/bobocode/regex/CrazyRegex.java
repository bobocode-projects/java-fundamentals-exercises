package com.bobocode.regex;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/**
 * Regular expressions - sequence of characters that define a search pattern for text
 * --------------------------
 * There 2 peace pf puzzle:
 * Literal characters - I want to match literally the character I specified (like 'a')
 * Meta characters - I want to match any character of this kind (more generic/abstract thing)
 * -------------------------
 * Single char                               Quantifiers                                 Position
 *                 (modify single characters how many of them u wanna match in a row)
 * \\d -> 0-9              \\D -> negate    | * -> Occurs zero or more times           |  ^  -> beginning
 * \\w -> A-Za-z0-9        \\W -> negate    | + -> 1 or more                           |  $  -> end
 * \\s -> whitespace, tab  \\S -> negate    | ? -> zero or one                         | \\b -> word boundary
 *  .  -> anything but newline              | {min, max} -> some range
 * \\. -> literal dot                       | {n} -> precise quantity
 * -------------------------
 * Character class -> is the thing that appears in between []. For example [abc] -> match 'a' or 'b' or 'c'.
 *                    Another example [-.] -> match dash or period. Here . is not meta character anymore.
 * ------------------------
 * - and ^ are special characters inside [].
 * [0-5] -> match all numbers  from 0 to 5. [^0-5] -> match anything that NOT 0-5
 * BUT it works like meta character only when it on first position, otherwise - its literal, [a^bc] - like this
 * -------------------------
 * Capturing Groups
 * Whenever u do regex search it matches whole result as a group 0.
 * \\d{3}-\\d{3}-\\d{4}  ->  212-555-1234 = GROUP 0
 *
 * Parentheses can capture a subgroup:
 * \\d{3}-(\\d{3})-(\\d{4})  -> 212-555-1234 = GROUP 0
 *                                       555 = GROUP 1
 *                                      1234 = GROUP 2
 * We can refer to this groups by $1 ($ when we want to replace) and \1 (within regex itself referring to capture
 * group it's called back reference)
 */
public class CrazyRegex {

    private final String text;
    private final String json;

    public CrazyRegex() {
        this.text = readWholeFile("note.txt");
        this.json = readWholeFile("nasa.json");
    }

    public String findSpecificWord() {
        return null;
    }

    public String findFirstWord() {
        return null;
    }

    public String findLastWord() {
        return null;
    }

    public String findAllNumbers() {
        return null;
    }

    public String findDates() {
        return null;
    }

    public String findDifferentSpellingsOfColor() {
        return null;
    }

    public String findZipCodes() {
        return null;
    }

    public String findDifferentSpellingsOfLink() {
        return null;
    }

    public String findSimplePhoneNumber() {
        return null;
    }

    public String findNumbersFromZeroToFiveWithLengthThree() {
        return null;
    }

    public String findAllWordsWithFiveLength() {
        return null;
    }

    public String findAllLettersAndDigitsWithLengthThree() {
        return null;
    }

    public String findAllWordsWhichBeginWithCapitalLetter() {
        return null;
    }

    public String findAbbreviation() {
        return null;
    }

    public String findAllOpenBraces() {
        return null;
    }

    public String findOnlyResources() {
        return null;
    }

    public String findOnlyLinksInNote() {
        return null;
    }

    public String findOnlyLinksInJson() {
        return null;
    }

    public String findAllEmails() {
        return null;
    }

    public String findAllPatternsForPhoneNumbers() {
        return null;
    }

    public String findOnlyDuplicates() {
        return null;
    }

    public String replaceFirstAndLastNames() {
        String names = "Tarasenko, Nazar ... Petrashyk, Petro ... Zlepko, Andrii";
        return null;
    }

    public String replaceLastSevenDigitsOfPhoneNumberToX() {
        String phones = "(948)333-5656 1235-889-7897 111.747.6236";
        return null;
    }

    public String insertLinksAndResourcesIntoHref() {
        String links = "[Bobocode](https://www.bobocode.com)" +
                "\n[LinkedIn](https://www.linkedin.com)" +
                "\n[Netflix](https://www.netflix.com)";
        return null;
    }

    public String regexChecker(String theRegex, String str2Check) {
        Matcher regexMatcher = getMatcher(theRegex, str2Check);
        StringBuilder stringBuilder = new StringBuilder();
        while (regexMatcher.find()) {
            if(regexMatcher.group().length() != 0) {
                stringBuilder.append("\n").append(regexMatcher.group());
            }
        }
        return stringBuilder.toString();
    }

    public Matcher getMatcher(String theRegex, String str2Replace) {
        Pattern replace = Pattern.compile(theRegex);
        return replace.matcher(str2Replace);
    }

    @SneakyThrows
    public String readWholeFile(String fileName) {
        Path filePath = Paths.get(CrazyRegex.class.getClassLoader()
                                                  .getResource(fileName)
                                                  .toURI());
        try (Stream<String> fileLinesStream = Files.lines(filePath)) {
            return fileLinesStream.collect(joining("\n"));
        }
    }
}
