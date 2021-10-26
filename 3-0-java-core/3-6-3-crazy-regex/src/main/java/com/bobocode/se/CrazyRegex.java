package com.bobocode.se;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.regex.Pattern;

/**
 * {@link CrazyRegex} is an exercise class. Each method returns Pattern class which
 * should be created using regex expression. Every method that is not implemented yet
 * throws {@link ExerciseNotCompletedException}
 * TODO: remove exception and implement each method of this class using java.util.regex.Pattern
 */
public class CrazyRegex {

    /**
     * Create Pattern that accepts String with regex inside and can
     * find all words "Curiosity" in text
     *
     * @return Pattern with regex expression
     */
    public Pattern findSpecificWord() {
        throw new ExerciseNotCompletedException();
    }

    /**
     *  Create Pattern that accepts String with regex inside and can
     *  find first word in text
     *
     * @return Pattern with regex expression
     */
    public Pattern findFirstWord() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * find last word in text
     *
     * @return Pattern with regex expression
     */
    public Pattern findLastWord() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * find all numbers in text
     *
     * @return Pattern with regex expression
     */
    public Pattern findAllNumbers() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * can find all dates like: 1971-11-23
     *
     * @return Pattern with regex expression
     */
    public Pattern findDates() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * different variations of word 'color'. We are looking for:
     * 'color', 'colour', 'colors', 'colours'
     *
     * @return Pattern with regex expression
     */
    public Pattern findDifferentSpellingsOfColor() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * can find all zip codes in text
     *
     * @return Pattern with regex expression
     */
    public Pattern findZipCodes() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * find different variations of word 'link', We are looking for:
     * 'lynk', 'link', 'l nk', 'l(nk'
     *
     * @return Pattern with regex expression
     */
    public Pattern findDifferentSpellingsOfLink() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * find phone number. For example: 555-555-5555
     *
     * @return Pattern with regex expression
     */
    public Pattern findSimplePhoneNumber() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * can find numbers with following requirements:
     *  - inside the number can be only digits from 0 to 5
     *  - length 3
     *
     * @return Pattern with regex expression
     */
    public Pattern findNumbersFromZeroToFiveWithLengthThree() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * find all words in text that have length 5
     *
     * @return Pattern with regex expression
     */
    public Pattern findAllWordsWithFiveLength() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * find words and numbers with following constraints:
     *  - not shorter than two symbols
     *  - not longer than three symbols
     *
     * @return Pattern with regex expression
     */
    public Pattern findAllLettersAndDigitsWithLengthThree() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * find all words that begin with capital letter
     *
     * @return Pattern with regex expression
     */
    public Pattern findAllWordsWhichBeginWithCapitalLetter() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * find only the following abbreviation:
     *  - AK, AL, AR, AZ, CA, CO, CT, PR, PA, PD
     *
     * @return Pattern with regex expression
     */
    public Pattern findAbbreviation() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * find all open braces
     *
     * @return Pattern with regex expression
     */
    public Pattern findAllOpenBraces() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * find everything inside []
     *
     * @return Pattern with regex expression
     */
    public Pattern findOnlyResources() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * find all http links inside ()
     *
     * @return Pattern with regex expression
     */
    public Pattern findOnlyLinksInNote() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * all http links
     *
     * @return Pattern with regex expression
     */
    public Pattern findOnlyLinksInJson() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * find all .com, .net and .edu emails
     *
     * @return Pattern with regex expression
     */
    public Pattern findAllEmails() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * can find the following examples of phone numbers:
     *  -  555-555-5555
     *  -  555.555.5555
     *  -  (555)555-5555
     *
     * @return Pattern with regex expression
     */
    public Pattern findAllPatternsForPhoneNumbers() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Create Pattern that accepts String with regex inside and can
     * find only duplicates
     *
     * @return Pattern with regex expression
     */
    public Pattern findOnlyDuplicates() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * You have a text where all names recorded as first name, last name.
     * Create matcher and use method replaceAll to record that names as:
     *  - last name first name
     *
     * @return String where all names recorded as last name first name
     */
    public String replaceFirstAndLastNames(String names) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * You have a text with phone numbers.
     * Create matcher and use method replaceAll to replace last digits:
     *  -  555-XXX-XXXX
     *
     * @return String where in all phone numbers last 7 digits replaced to X
     */
    public String replaceLastSevenDigitsOfPhoneNumberToX(String phones) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * You have a text with resources and links to those resources:
     *  - [Bobocode](https://www.bobocode.com)
     * Create matcher and use method replaceAll to get the following result:
     *  - <a href="https://www.bobocode.com">Bobocode</a>
     *
     * @return String where all resources embraced in href
     */
    public String insertLinksAndResourcesIntoHref(String links) {
        throw new ExerciseNotCompletedException();
    }
}
