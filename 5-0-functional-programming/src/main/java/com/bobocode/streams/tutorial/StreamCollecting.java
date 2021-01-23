package com.bobocode.streams.tutorial;


import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.time.Month;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

/**
 * StreamCollecting stream elements with method collect() that receives a {@link java.util.stream.Collector} instance as input
 * parameter. Method collect() transforms a stream into a real collection like {@link List}, or a {@link Map}.
 * A {@link java.util.stream.Collector} is a complex structure, that describes a collecting logic. It provides
 * an instruction about how elements should collected, and what collection should be used.
 * <p>
 * The simplest example is collecting all elements into a list. However this mechanism is very powerful, and allows
 * to perform various complex data transformations. E.g. "Group all account by it's birthday month"
 */
public class StreamCollecting {
    public static void main(String[] args) {
        List<Account> accounts = Accounts.generateAccountList(10);

        printAllGmailAccounts(accounts);
        printAccountsByItsBirthdaysMonth(accounts);
        printAccountFirstNamesByItsBirthdaysMonth(accounts);
        printFirstNamesSeparatingGmailAccountsAndOthers(accounts);
        printCommaSeparatedNames(accounts);
        printNamesSeparatedGmailAccountAndOthersAndGroupedByBirthdayMonth(accounts);
    }

    private static void printAllGmailAccounts(List<Account> accounts) {
        List<String> accountEmails = accounts.stream()
                .map(Account::getEmail)
                .filter(email -> email.endsWith("gmail.com"))
                .collect(toList());

        System.out.println("\nGmail accounts list: " + accountEmails);

    }

    private static void printAccountsByItsBirthdaysMonth(List<Account> accounts) {
        Map<Month, List<Account>> accountsByBirthdayMonthMap = accounts.stream()
                .collect(groupingBy(account -> account.getBirthday().getMonth()));

        System.out.println("\nGroup accounts by birthday month: " + accountsByBirthdayMonthMap);
    }

    private static void printAccountFirstNamesByItsBirthdaysMonth(List<Account> accounts) {
        Map<Month, List<String>> accountFirstNamesBirthdayMonth = accounts.stream()
                .collect(groupingBy(a -> a.getBirthday().getMonth(), mapping(Account::getFirstName, toList())));

        System.out.println("\nGroup account first names by birthday month: " + accountFirstNamesBirthdayMonth);
    }

    private static void printFirstNamesSeparatingGmailAccountsAndOthers(List<Account> accounts) {
        Map<Boolean, List<String>> googleEmailAccounts = accounts.stream()
                .collect(partitioningBy(a -> a.getEmail().endsWith("gmail.com"), mapping(Account::getFirstName, toList())));

        System.out.println("\nSeparate gmail accounts from others: " + googleEmailAccounts);

    }

    /**
     * Please always use joining() method to avoid performance issues when concatenating {@link String}
     */
    private static void printCommaSeparatedNames(List<Account> accounts) {

        String concatenatedName = accounts.stream()
                .map(Account::getFirstName)
                .collect(joining(", "));

        System.out.println("\nComma-separated names: " + concatenatedName);
    }

    /**
     * Use streams carefully. Always keep the logic simple and clear. In case it becomes too complex, think how to
     * simplify it use imperative style
     */
    private static void printNamesSeparatedGmailAccountAndOthersAndGroupedByBirthdayMonth(List<Account> accounts) {
        Map<Boolean, Map<Month, List<String>>> accountByNameLengthByBirthdayMonth = accounts.stream()
                .collect(partitioningBy(a -> a.getFirstName().length() > 4,
                        groupingBy(a -> a.getBirthday().getMonth(),
                                mapping(Account::getFirstName, toList()))));

        System.out.println("\nNames of gmail account owners and others groped by birthday month: " +
                accountByNameLengthByBirthdayMonth);

    }
}
