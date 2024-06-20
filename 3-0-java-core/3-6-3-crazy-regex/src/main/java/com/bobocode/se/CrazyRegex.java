package com.bobocode.se;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@link CrazyRegex} is an exercise class. Each method returns Pattern class which
 * should be created using regex expression. Every method that is not implemented yet
 * throws {@link ExerciseNotCompletedException}
 * <p>
 * TODO: remove exception and implement each method of this class using {@link Pattern}
 *
 * @author Andriy Paliychuk
 */
public class CrazyRegex {

    /**
     * A Pattern that that finds all words "Curiosity" in text
     *
     * @return a pattern that looks for the word "Curiosity"
     */
    public Pattern findSpecificWord() {
        return Pattern.compile("Curiosity");
    }

    /**
     * A Pattern that finds first word in text
     *
     * @return a pattern that looks for the first word in text
     */
    public Pattern findFirstWord() {
        return Pattern.compile("^\\w+");
    }

    /**
     * A Pattern that finds last word in text
     *
     * @return a pattern that looks for the last word in text
     */
    public Pattern findLastWord() {
        return Pattern.compile("\\w+$");
    }

    /**
     * A Pattern that finds all numbers in text. When we have "555-555", "(555)555" and "30th" in text
     * our pattern must grab all that numbers:
     * "555" - four times, and one "30"
     *
     * @return a pattern that looks for numbers
     */
    public Pattern findAllNumbers() {
        return Pattern.compile("\\d+");
    }

    /**
     * A Pattern that finds all dates. For instance: "1971-11-23"
     *
     * @return a pattern that looks for dates
     */
    public Pattern findDates() {
        return Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    }

    /**
     * A Pattern that finds different variations of word "color".
     * We are looking for: "color", "colour", "colors", "colours"
     *
     * @return a pattern that looks for different variations of word "color"
     */
    public Pattern findDifferentSpellingsOfColor() {
        return Pattern.compile("colou?rs?");
    }

    /**
     * A Pattern that finds all zip codes in text.
     * Zip code is a 5-digit number without any characters or special symbols.
     * For example: 72300
     *
     * @return a pattern that looks for zip codes
     */
    public Pattern findZipCodes() {
        return Pattern.compile("\\s\\d{5}\\s");
    }

    /**
     * A Pattern that finds different variations of word "link".
     * We are looking for: "lynk", "link", "l nk", "l(nk"
     *
     * @return a pattern that looks for different variations of word "link"
     */
    public Pattern findDifferentSpellingsOfLink() {
        return Pattern.compile("l[yi (]nk");
    }

    /**
     * A Pattern that finds phone numbers.
     * For example: "555-555-5555"
     *
     * @return a pattern that looks for phone numbers
     */
    public Pattern findSimplePhoneNumber() {
        return Pattern.compile("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d");
    }

    /**
     * A Pattern that finds numbers with following requirements:
     * - inside the number can be only digits from 0 to 5
     * - length 3
     *
     * @return a pattern that looks for numbers with length 3 and digits from 0 to 5 in the middle
     */
    public Pattern findNumbersFromZeroToFiveWithLengthThree() {
        return Pattern.compile("[0-5]{3}");
    }

    /**
     * A Pattern that finds all words in text that have length 5
     *
     * @return a pattern that looks for the words that have length 5
     */
    public Pattern findAllWordsWithFiveLength() {
        return Pattern.compile("\\b[A-Za-z]{5}\\b");
    }

    /**
     * A Pattern that finds words and numbers with following constraints:
     * - not shorter than two symbols
     * - not longer than three symbols
     *
     * @return a pattern that looks for words and numbers that not shorter 2 and not longer 3
     */
    public Pattern findAllLettersAndDigitsWithLengthThree() {
        return Pattern.compile("\\b\\w{2,3}\\b");
    }

    /**
     * A Pattern that finds all words that begin with capital letter
     *
     * @return a pattern that looks for the words that begin with capital letter
     */
    public Pattern findAllWordsWhichBeginWithCapitalLetter() {
        return Pattern.compile("\\b[A-Z][a-z]*\\b");
    }

    /**
     * A Pattern that finds only the following abbreviation:
     * - AK, AL, AR, AZ, CA, CO, CT, PR, PA, PD
     *
     * @return a pattern that looks for the abbreviations above
     */
    public Pattern findAbbreviation() {
        return Pattern.compile("A[KLRZ]|C[AOT]|P[RAD]");
    }

    /**
     * A Pattern that finds all open braces
     *
     * @return a pattern that looks for all open braces
     */
    public Pattern findAllOpenBraces() {
        return Pattern.compile("(\\{+)");
    }

    /**
     * A Pattern that finds everything inside []
     *
     * @return a pattern that looks for everything inside []
     */
    public Pattern findOnlyResources() {
        return Pattern.compile("(?<=\\[).+?(?=\\])");
    }

    /**
     * A Pattern that finds all https links in note.txt
     *
     * @return a pattern that looks for all https links in note.txt
     */
    public Pattern findOnlyLinksInNote() {
        return Pattern.compile("https://((www.)?+[\\w]+(.))com");
    }

    /**
     * A Pattern that finds all http links in nasa.json
     *
     * @return a pattern that looks for all http links in nasa.json
     */
    public Pattern findOnlyLinksInJson() {
        return Pattern.compile("http://(.*)JPG");
    }

    /**
     * A Pattern that finds all .com, .net and .edu emails
     *
     * @return a pattern that looks for all .com, .net and .edu emails
     */
    public Pattern findAllEmails() {
        return Pattern.compile("[\\w.]+@[\\w]+\\.(net|com|edu)");
    }

    /**
     * A Pattern that finds the following examples of phone numbers:
     * -  555-555-5555
     * -  555.555.5555
     * -  (555)555-5555
     *
     * @return a pattern that looks for phone numbers patterns above
     */
    public Pattern findAllPatternsForPhoneNumbers() {
        return Pattern.compile("\\(?\\d{3}[-.)]\\d{3}[-.]\\d{4}");
    }

    /**
     * A Pattern that finds only duplicates
     *
     * @return a pattern that looks for duplicates
     */
    public Pattern findOnlyDuplicates() {
        return Pattern.compile("\\b(\\w+)\\s\\1\\b");
    }

    /**
     * You have a text where all names recorded as first name, last name.
     * Create matcher and use method replaceAll to record that names as:
     * - last name first name
     *
     * @return String where all names recorded as last name first name
     */
    public String replaceFirstAndLastNames(String names) {
        Matcher matcher = Pattern.compile("(\\w+),\\s+(\\w+)").matcher(names);
        return matcher.replaceAll("$2 $1");
    }

    /**
     * You have a text with phone numbers.
     * Create matcher and use method replaceAll to replace last digits:
     * -  555-XXX-XXXX
     *
     * @return String where in all phone numbers last 7 digits replaced to X
     */
    public String replaceLastSevenDigitsOfPhoneNumberToX(String phones) {
        Matcher matcher = Pattern.compile("\\(?(\\d{3})[-.)]\\d{3}[-.]\\d{4}").matcher(phones);
        return matcher.replaceAll("$1-XXX-XXXX");
    }

    /**
     * You have a text with resources and links to those resources:
     * - [Bobocode](https://www.bobocode.com)
     * Create matcher and use method replaceAll to get the following result:
     * - <a href="https://www.bobocode.com">Bobocode</a>
     *
     * @return String where all resources embraced in href
     */
    public String insertLinksAndResourcesIntoHref(String links) {
        Matcher matcher = Pattern.compile("\\[(.*?)]\\((http.*?)\\)").matcher(links);
        return matcher.replaceAll("<a href=\"$2\">$1</a>");
    }
}
