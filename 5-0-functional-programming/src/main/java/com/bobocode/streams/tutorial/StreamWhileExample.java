package com.bobocode.streams.tutorial;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.util.List;

public class StreamWhileExample {
    public static void main(String[] args) {
        List<Account> accounts = Accounts.generateAccountList(10);

        printAllEmails(accounts);
        printAllEmailsWhileGmail(accounts);
        printAllEmailsWhileNotGmail(accounts);
    }

    private static void printAllEmails(List<Account> accounts) {
        System.out.println("Whole emails list:");
        accounts.stream()
                .map(Account::getEmail)
                .forEach(System.out::println);
    }

    private static void printAllEmailsWhileGmail(List<Account> accounts) {
        System.out.println("\nAll while gmail:");
        accounts.stream()
                .takeWhile(a -> a.getEmail().endsWith("@gmail.com"))
                .map(Account::getEmail)
                .forEach(System.out::println);
    }

    private static void printAllEmailsWhileNotGmail(List<Account> accounts) {
        System.out.println("\nAll while not gmail:");
        accounts.stream()
                .dropWhile(a -> a.getEmail().endsWith("@gmail.com"))
                .map(Account::getEmail)
                .forEach(System.out::println);
    }
}
