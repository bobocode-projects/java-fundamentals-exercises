package com.bobocode.streams.tutorial;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Stream API provides a lot of useful features features that help you process data even more concise.
 */
public class StreamAdditionalFeatures {
    public static void main(String[] args) {
        List<Account> accounts = Accounts.generateAccountList(10);

        printSortedFirstNames(accounts);
        printThirdAndFourthFirstNames(accounts);
        printBalanceStatistic(accounts);
        printFirstNamesAndCollectEmails(accounts);
    }

    private static void printSortedFirstNames(List<Account> accounts) {
        System.out.println("Sorted first names: ");
        accounts.stream()
                .map(Account::getFirstName)
                .sorted()
                .forEach(System.out::println);
    }

    /**
     * Stream API allow you to skip some elements, and limit the number of elements in the stream
     */
    private static void printThirdAndFourthFirstNames(List<Account> accounts) {
        System.out.println("\nThird and fourth first names: ");
        accounts.stream()
                .map(Account::getFirstName)
                .sorted()
                .skip(2)
                .limit(2)
                .forEach(System.out::println);
    }

    private static void printBalanceStatistic(List<Account> accounts) {
        DoubleSummaryStatistics balanceStatistic = accounts.stream()
                .map(Account::getBalance)
                .mapToDouble(BigDecimal::doubleValue)
                .summaryStatistics();
        System.out.println("\nAccounts balance statistic: " + balanceStatistic);
    }

    /**
     * Since forEach() is a terminal operation, you can not continue working with stream when it's performed.
     * That's why Stream API provides another method, that has the similar capabilities but does not terminate a stream
     */
    private static void printFirstNamesAndCollectEmails(List<Account> accounts) {
        System.out.println("\nAccount first names: ");
        Set<String> emailsSet = accounts.stream()
                .peek(a -> System.out.println(a.getFirstName()))
                .map(Account::getEmail)
                .collect(toSet());
        System.out.println("\nEmails set: " + emailsSet);
    }
}
