package com.bobocode.se;

import lombok.SneakyThrows;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * A test class for {@link CrazyRegex}.
 *
 * @author Andriy Paliychuk
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CrazyRegexTest {

    private final CrazyRegex crazyRegex = new CrazyRegex();

    private final String text;
    private final String json;

    public CrazyRegexTest() {
        this.text = readWholeFile("note.txt");
        this.json = readWholeFile("nasa.json");
    }

    @Test
    @Order(1)
    void findSpecificWord() {
        String result = regexChecker(crazyRegex.findSpecificWord(), json);
        assertThat(result).isEqualTo("\nCuriosity\nCuriosity\nCuriosity");
    }

    @Test
    @Order(2)
    void findFirstWord() {
        String result = regexChecker(crazyRegex.findFirstWord(), text);
        assertThat(result).isEqualTo("\nThe");
    }

    @Test
    @Order(3)
    void findLastWord() {
        String result = regexChecker(crazyRegex.findLastWord(), text);
        assertThat(result).isEqualTo("\nfish");
    }

    @Test
    @Order(4)
    void findAllNumbers() {
        String result = regexChecker(crazyRegex.findAllNumbers(), text);
        assertThat(result).isEqualTo("\n01001\n03148\n02132\n412\n555\n1212\n412\n555" +
                                     "\n1234\n412\n555\n1234\n646\n555\n1234\n1");
    }

    @Test
    @Order(5)
    void findDates() {
        String result = regexChecker(crazyRegex.findDates(), json);
        assertThat(result).isEqualTo("\n2015-05-30\n2012-08-06\n2011-11-26\n2015-05-30\n2012-08-06\n" +
                                     "2011-11-26\n2015-05-30\n2012-08-06\n2011-11-26");
    }

    @Test
    @Order(6)
    void findDifferentSpellingsOfColor() {
        String result = regexChecker(crazyRegex.findDifferentSpellingsOfColor(), text);
        assertThat(result).isEqualTo("\ncolors\ncolours\ncolour");
    }

    @Test
    @Order(7)
    void findZipCodes() {
        String result = regexChecker(crazyRegex.findZipCodes(), text);
        assertThat(result).isEqualTo("\n 01001 \n 03148 \n 02132 ");
    }

    @Test
    @Order(8)
    void findDifferentSpellingsOfLink() {
        String result = regexChecker(crazyRegex.findDifferentSpellingsOfLink(), text);
        assertThat(result).isEqualTo("\nlynk\nlink\nl nk\nl(nk");
    }

    @Test
    @Order(9)
    void findSimplePhoneNumber() {
        String result = regexChecker(crazyRegex.findSimplePhoneNumber(), text);
        assertThat(result).isEqualTo("\n412-555-1234");
    }

    @Test
    @Order(10)
    void findNumbersFromZeroToFiveWithLengthThree() {
        String result = regexChecker(crazyRegex.findNumbersFromZeroToFiveWithLengthThree(), text);
        assertThat(result).isEqualTo("\n010\n031\n021\n412\n555\n121\n412" +
                                     "\n555\n123\n412\n555\n123\n555\n123");
    }

    @Test
    @Order(11)
    void findAllWordsWithFiveLength() {
        String result = regexChecker(crazyRegex.findAllWordsWithFiveLength(), json);
        assertThat(result).isEqualTo("\nFront\nrover\nFront\nrover\nrover");
    }

    @Test
    @Order(12)
    void findAllLettersAndDigitsWithLengthThree() {
        String result = regexChecker(crazyRegex.findAllLettersAndDigitsWithLengthThree(), text);
        assertThat(result).isEqualTo("\nThe\nof\nthe\nand\nthe\nnot\nThe\nis\ndon\nyou\nnk\nnk\nThe\nCA\nAK\nPA\n412" +
                                     "\n555\ncom\n412\n555\n412\n555\n646\n555\nof\ncom\nnet\nor\nnyu\nedu\n1Z\naaa\nOf\nwww\ncom\ncom\nwww\ncom" +
                                     "\nis\nis\nam\nnot\nnot\nwhy\nwhy\nam\nok\ncat\ncat\ndog\ndog");
    }

    @Test
    @Order(13)
    void findAllWordsWhichBeginWithCapitalLetter() {
        String result = regexChecker(crazyRegex.findAllWordsWhichBeginWithCapitalLetter(), json);
        assertThat(result).isEqualTo("\nFront\nHazard\nAvoidance\nCamera" +
                                     "\nCuriosity\nFront\nHazard\nAvoidance\nCamera\nCuriosity\nRear\nHazard\nAvoidance\nCamera\nCuriosity");
    }

    @Test
    @Order(14)
    void findAbbreviation() {
        String result = regexChecker(crazyRegex.findAbbreviation(), text);
        assertThat(result).isEqualTo("\nCA\nAK\nPA");
    }

    @Test
    @Order(15)
    void findAllOpenBraces() {
        String result = regexChecker(crazyRegex.findAllOpenBraces(), text);
        assertThat(result).isEqualTo("\n{{{\n{{\n{");
    }

    @Test
    @Order(16)
    void findOnlyResources() {
        String result = regexChecker(crazyRegex.findOnlyResources(), text);
        assertThat(result).isEqualTo("\nGoogle\nStackOverflow\nYoutube");
    }

    @Test
    @Order(17)
    void findOnlyLinksInNote() {
        String result = regexChecker(crazyRegex.findOnlyLinksInNote(), text);
        assertThat(result).isEqualTo("\nhttps://www.google.com\nhttps://stackoverflow.com\nhttps://www.youtube.com");
    }

    @Test
    @Order(18)
    void findOnlyLinksInJson() {
        String result = regexChecker(crazyRegex.findOnlyLinksInJson(), json);
        assertThat(result).isEqualTo(
                "\nhttp://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG\n" +
                "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FRB_486265257EDR_F0481570FHAZ00323M_.JPG\n" +
                "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/rcam/RLB_486265291EDR_F0481570RHAZ00323M_.JPG"
        );
    }

    @Test
    @Order(19)
    void findAllEmails() {
        String result = regexChecker(crazyRegex.findAllEmails(), text);
        assertThat(result).isEqualTo("\njohnsmith@yahoo.com\nterek.koval@gmail.com\nterek@koval.net" +
                                     "\nterek.koval@nyu.edu");
    }

    @Test
    @Order(20)
    void findAllPatternsForPhoneNumbers() {
        String result = regexChecker(crazyRegex.findAllPatternsForPhoneNumbers(), text);
        assertThat(result).isEqualTo("\n(412)555-1212\n412-555-1234\n646.555.1234");
    }

    @Test
    @Order(21)
    void findOnlyDuplicates() {
        String result = regexChecker(crazyRegex.findOnlyDuplicates(), text);
        assertThat(result).isEqualTo("\nis is\ntext text\ndouble double\nI I\nnot not\nwhy why" +
                                     "\ncat cat\ndog\ndog\nfish fish");
    }

    @Test
    @Order(22)
    void replaceFirstAndLastNames() {
        String names = "Tarasenko, Nazar ... Petrashyk, Petro ... Zlepko, Andrii";
        String result = crazyRegex.replaceFirstAndLastNames(names);
        assertThat(result).isEqualTo("Nazar Tarasenko ... Petro Petrashyk ... Andrii Zlepko");
    }

    @Test
    @Order(23)
    void replaceLastSevenDigitsOfPhoneNumberToX() {
        String phones = "(948)333-5656 1235-889-7897 111.747.6236";
        String result = crazyRegex.replaceLastSevenDigitsOfPhoneNumberToX(phones);
        assertThat(result).isEqualTo("948-XXX-XXXX 1235-XXX-XXXX 111-XXX-XXXX");
    }

    @Test
    @Order(24)
    void insertLinksAndResourcesIntoHref() {
        String links = "[Bobocode](https://www.bobocode.com)" +
                       "\n[LinkedIn](https://www.linkedin.com)" +
                       "\n[Netflix](https://www.netflix.com)";
        String result = crazyRegex.insertLinksAndResourcesIntoHref(links);
        assertThat(result).isEqualTo(
                "<a href=\"https://www.bobocode.com\">Bobocode</a>\n" +
                "<a href=\"https://www.linkedin.com\">LinkedIn</a>\n" +
                "<a href=\"https://www.netflix.com\">Netflix</a>"
        );
    }

    private String regexChecker(Pattern pattern, String str2WorkWith) {
        Matcher matcher = pattern.matcher(str2WorkWith);
        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()) {
            if (matcher.group().length() != 0) {
                stringBuilder.append("\n").append(matcher.group());
            }
        }
        return stringBuilder.toString();
    }

    @SneakyThrows
    private String readWholeFile(String fileName) {
        Path filePath = Paths.get(CrazyRegex.class.getClassLoader()
                .getResource(fileName)
                .toURI());
        try (Stream<String> fileLinesStream = Files.lines(filePath)) {
            return fileLinesStream.collect(joining("\n"));
        }
    }
}