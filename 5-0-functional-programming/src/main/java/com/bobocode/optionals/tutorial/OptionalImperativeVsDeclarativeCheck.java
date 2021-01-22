package com.bobocode.optionals.tutorial;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.util.Optional;

public class OptionalImperativeVsDeclarativeCheck {
    public static void main(String[] args) {
        Account account = Accounts.generateAccount();
        Optional<Account> optionalAccount = Optional.of(account);

        printAccount(account);
        printOptionalAccount(optionalAccount);
    }

    /**
     * An if statement is a classic example of imperative check
     */
    private static void printAccount(Account account) {
        if (account != null) {
            System.out.println(account);
        } else {
            System.out.println("No such element exists!");
        }
    }

    /**
     * This is an example of declarative check. You don't write an if statement yourself, it is performed inside the
     * method ifPresentOrElse()
     */
    private static void printOptionalAccount(Optional<Account> optionalAccount) {
        optionalAccount.ifPresentOrElse(System.out::println,
                () -> System.out.println("No such element exists!"));
    }
}
