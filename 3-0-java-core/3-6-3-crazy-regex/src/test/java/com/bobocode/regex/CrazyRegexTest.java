package com.bobocode.regex;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CrazyRegexTest {

    private final CrazyRegex crazyRegex = new CrazyRegex();

    @Test
    @Order(1)
    void findSpecificWord() {
        assertThat(crazyRegex.findSpecificWord()).isEqualTo("\nCuriosity\nCuriosity\nCuriosity");
    }

    @Test
    @Order(2)
    void findFirstWord() {
        assertThat(crazyRegex.findFirstWord()).isEqualTo("\nThe");
    }

    @Test
    @Order(3)
    void findLastWord() {
        assertThat(crazyRegex.findLastWord()).isEqualTo("\nDanny");
    }

    @Test
    @Order(4)
    void findAllNumbers() {
        assertThat(crazyRegex.findAllNumbers()).isEqualTo("\n01001\n03148\n02132\n412\n555\n1212\n412\n555" +
                "\n1234\n412\n555\n1234\n646\n555\n1234\n1");
    }

    @Test
    @Order(5)
    void findDates() {
        assertThat(crazyRegex.findDates()).isEqualTo("\n2015-05-30\n2012-08-06\n2011-11-26\n2015-05-30\n2012-08-06\n" +
                                                       "2011-11-26\n2015-05-30\n2012-08-06\n2011-11-26");
    }

    @Test
    @Order(6)
    void findDifferentSpellingsOfColor() {
        assertThat(crazyRegex.findDifferentSpellingsOfColor()).isEqualTo("\ncolors\ncolours\ncolour");
    }

    @Test
    @Order(7)
    void findZipCodes() {
        assertThat(crazyRegex.findZipCodes()).isEqualTo("\n 01001 \n 03148 \n 02132 ");
    }

    @Test
    @Order(8)
    void findDifferentSpellingsOfLink() {
        assertThat(crazyRegex.findDifferentSpellingsOfLink()).isEqualTo("\nlynk\nlink\nl nk\nl(nk");
    }

    @Test
    @Order(9)
    void findSimplePhoneNumber() {
        assertThat(crazyRegex.findSimplePhoneNumber()).isEqualTo("\n412-555-1234");
    }

    @Test
    @Order(10)
    void findNumbersFromZeroToFiveWithLengthThree() {
        assertThat(crazyRegex.findNumbersFromZeroToFiveWithLengthThree()).isEqualTo("\n010\n031\n021\n412\n555\n121\n412" +
                "\n555\n123\n412\n555\n123\n555\n123");
    }

    @Test
    @Order(11)
    void findAllWordsWithFiveLength() {
        assertThat(crazyRegex.findAllWordsWithFiveLength()).isEqualTo("\nFront\nrover\nFront\nrover\nrover");
    }

    @Test
    @Order(12)
    void findAllLettersAndDigitsWithLengthThree() {
        assertThat(crazyRegex.findAllLettersAndDigitsWithLengthThree()).isEqualTo("\nThe\nOf\nThe\nCA\nAK\nPA\n412\n555" +
                "\ncom\n412\n555\n412\n555\n646\n555\n1Z\naaa\nThe\nof\nthe\nand\nthe\nnot\nThe\nis\ndon\nyou\nnk\nnk" +
                "\nof\ncom\nnet\nor\nnyu\nedu\ncom\ncom\nwww\ncom\nis\nis\nam\nnot\nnot\nwhy\nwhy\nam\nok\ncat\ncat\ndog\ndog");
    }

    @Test
    @Order(13)
    void findAllWordsWhichBeginWithCapitalLetter() {
        assertThat(crazyRegex.findAllWordsWhichBeginWithCapitalLetter()).isEqualTo("\nFront\nHazard\nAvoidance\nCamera" +
                "\nCuriosity\nFront\nHazard\nAvoidance\nCamera\nCuriosity\nRear\nHazard\nAvoidance\nCamera\nCuriosity");
    }

    @Test
    @Order(14)
    void findAbbreviation() {
        assertThat(crazyRegex.findAbbreviation()).isEqualTo("\nCA\nAK\nPA");
    }

    @Test
    @Order(15)
    void findAllOpenBraces() {
        assertThat(crazyRegex.findAllOpenBraces()).isEqualTo("\n{{{\n{{\n{");
    }

    @Test
    @Order(16)
    void findOnlyResources() {
        assertThat(crazyRegex.findOnlyResources()).isEqualTo("\n[Google]\n[StackOverflow]\n[Youtube]");
    }

    @Test
    @Order(17)
    void findOnlyLinksInNote() {
        assertThat(crazyRegex.findOnlyLinksInNote()).isEqualTo("\n(https://google.com)\n(https://stackoverflow.com)" +
                "\n(https://www.youtube.com)");
    }

    @Test
    @Order(18)
    void findOnlyLinksInJson() {
        assertThat(crazyRegex.findOnlyLinksInJson()).isEqualTo(
                "\nhttp://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG\",\n" +
                "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FRB_486265257EDR_F0481570FHAZ00323M_.JPG\",\n" +
                "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/rcam/RLB_486265291EDR_F0481570RHAZ00323M_.JPG\","
        );
    }

    @Test
    @Order(19)
    void findAllEmails() {
        assertThat(crazyRegex.findAllEmails()).isEqualTo("\njohnsmith@yahoo.com\nterek.koval@gmail.com\nterek@koval.net" +
                "\nterek.koval@nyu.edu");
    }

    @Test
    @Order(20)
    void findAllPatternsForPhoneNumbers() {
        assertThat(crazyRegex.findAllPatternsForPhoneNumbers()).isEqualTo("\n(412)555-1212\n412-555-1234\n646.555.1234");
    }

    @Test
    @Order(21)
    void findOnlyDuplicates() {
        assertThat(crazyRegex.findOnlyDuplicates()).isEqualTo("\nis is\ntext text\ndouble double\nI I\nnot not\nwhy why" +
                "\ncat cat\ndog\ndog\nfish fish");
    }

    @Test
    @Order(22)
    void replaceFirstAndLastNames() {
        assertThat(crazyRegex.replaceFirstAndLastNames()).isEqualTo("Nazar Tarasenko ... Petro Petrashyk ... Andrii Zlepko");
    }

    @Test
    @Order(23)
    void replaceLastSevenDigitsOfPhoneNumberToX() {
        assertThat(crazyRegex.replaceLastSevenDigitsOfPhoneNumberToX()).isEqualTo("948-XXX-XXXX 1235-XXX-XXXX 111-XXX-XXXX");
    }

    @Test
    @Order(24)
    void insertLinksAndResourcesIntoHref() {
        assertThat(crazyRegex.insertLinksAndResourcesIntoHref()).isEqualTo(
                "<a href=\"https://www.bobocode.com\">Bobocode</a>\n" +
                "<a href=\"https://www.linkedin.com\">LinkedIn</a>\n" +
                "<a href=\"https://www.netflix.com\">Netflix</a>"
        );
    }
}
