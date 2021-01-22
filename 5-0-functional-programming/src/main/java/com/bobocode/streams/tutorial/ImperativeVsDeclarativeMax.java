package com.bobocode.streams.tutorial;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.util.List;

import static java.util.Comparator.comparing;

public class ImperativeVsDeclarativeMax {
    public static void main(String[] args) {
        List<Account> accounts = Accounts.generateAccountList(10);

        printAccountWithMaxBalanceImperatively(accounts);
        printAccountWithMaxBalanceDeclaratively(accounts);
    }

    private static void printAccountWithMaxBalanceDeclaratively(List<Account> accounts) {
        accounts.stream()
                .max(comparing(Account::getBalance))
                .ifPresentOrElse(System.out::println,
                        () -> System.out.println("No accounts found!"));

    }

    private static void printAccountWithMaxBalanceImperatively(List<Account> accounts) {
        if (accounts.size() > 0) {
            Account accountWithMaxBalance = accounts.get(0);
            for (Account account : accounts) {
                if (account.getBalance().compareTo(accountWithMaxBalance.getBalance()) > 0) {
                    accountWithMaxBalance = account;
                }
            }
            System.out.println(accountWithMaxBalance);
        } else {
            System.out.println("No accounts found!");
        }
    }
}
