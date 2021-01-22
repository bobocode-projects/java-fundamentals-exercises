package com.bobocode.streams.tutorial;


import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class StreamMapping {
    public static void main(String[] args) {
        List<Account> accounts = Accounts.generateAccountList(10);

        printAccountEmails(accounts);
        printCharacterCounts();
    }

    /**
     * StreamMapping elements, method map() receives a @{@link java.util.function.Function}. This function transforms(maps)
     * each element of the stream into another value (transforms each account object into its email). Method map()
     * produce a new stream @{@link Stream} of @{@link String} (stream of emails)
     */
    private static void printAccountEmails(List<Account> accounts) {
        accounts.stream()
                .map(Account::getEmail)
                .forEach(System.out::println);

    }

    private static void printCharacterCounts() {
        String text = getSomeText();
        Map<Character, Long> characterCountMap = collectCharactersCountFromText(text);
        System.out.println(characterCountMap);
    }

    /**
     * Count number of occurrences for each letter in each account first and last names
     * flatMap() is used to transform Stream<Stream<T>> into Stream<T>
     * Not you see the problem, that Java 8 doesn't provide a primitive stream API for characters
     */
    private static Map<Character, Long> collectCharactersCountFromText(String text) {
        return text.chars()
                .mapToObj(a -> (char) a)
                .filter(s -> s != ' ')
                .collect(groupingBy(identity(), counting()));

    }

    private static String getSomeText() {
        return "Stream pipeline results may be nondeterministic or incorrect if the behavioral parameters " +
                "to the stream operations are stateful. A stateful lambda (or other object implementing " +
                "the appropriate functional interface) is one whose result depends on any state " +
                "which might change during the execution of the stream pipeline.";
    }
}
