package com.bobocode.streams.tutorial;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ImperativeVsDeclarativeFiltering {
    public static void main(String[] args) {
        List<Account> accounts = Accounts.generateAccountList(10);

        findAllGmailAccountsImperatively(accounts);
        findAllGmailAccountsDeclaratively(accounts);

    }

    private static List<Account> findAllGmailAccountsImperatively(List<Account> accounts) {
        List<Account> gmailAccounts = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getEmail().endsWith("@gmail.com")) {
                gmailAccounts.add(account);
            }
        }
        return gmailAccounts;
    }

    private static List<Account> findAllGmailAccountsDeclaratively(List<Account> accounts) {
        return accounts.stream()
                .filter(a -> a.getEmail().endsWith("@gmail.com"))
                .collect(toList());
    }

}
