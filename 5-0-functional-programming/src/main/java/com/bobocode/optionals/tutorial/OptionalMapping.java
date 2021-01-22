package com.bobocode.optionals.tutorial;


import com.bobocode.data.Accounts;
import com.bobocode.model.Account;
import com.bobocode.model.CreditAccount;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OptionalMapping {
    public static void main(String[] args) {
        List<Account> accounts = Accounts.generateAccountList(10);
        List<CreditAccount> creditAccounts = Collections.singletonList(new CreditAccount(BigDecimal.valueOf(1000)));
        Optional<Account> optionalAccount = accounts.stream().findAny();

        printBalanceUsingNullCheck(optionalAccount.get());
        printBalanceUsingOptionalMapping(optionalAccount);

        printBalanceOfFirstGoogleAccount(accounts);
        printCreditBalanceOfFirstGoogleAccount(creditAccounts);
    }

    /**
     * Hierarchical relation with potential null value, like an account that can be null, has a balance
     * field that also can be null
     */
    private static void printBalanceUsingNullCheck(Account account) {
        if (account != null) {
            if (account.getBalance() != null) {
                System.out.println(account.getBalance());
            }
        }
    }

    /**
     * When you want to access a nullable value of account, you can use method map(), that receives a mapper which
     * transform your nullable value into an optional value
     */
    private static void printBalanceUsingOptionalMapping(Optional<Account> optionalAccount) {
        optionalAccount.map(Account::getBalance).ifPresent(System.out::println);
    }

    /**
     * An example, when you process a stream and find an optional element, with an relation that can be null
     */
    private static void printBalanceOfFirstGoogleAccount(List<Account> accounts) {
        accounts.stream()
                .filter(a -> a.getEmail().endsWith("google.com"))
                .findFirst()
                .map(Account::getBalance)
                .ifPresent(System.out::println);
    }

    /**
     * When you use map() it wraps a value within Optional container, and if that field is already an {@link Optional},
     * you get Optional<Optional<T>>. To avoid this use flatMap()
     */
    private static void printCreditBalanceOfFirstGoogleAccount(List<CreditAccount> creditAccounts) {
        creditAccounts.stream()
                .filter(a -> a.getEmail().endsWith("google.com"))
                .filter(a -> a.getBalance().compareTo(BigDecimal.valueOf(90000)) > 0)
                .findFirst()
                .flatMap(CreditAccount::getCreditBalance)
                .ifPresent(System.out::println);

    }
}
