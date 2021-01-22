package com.bobocode.optionals.tutorial;


import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.util.Optional;
import java.util.function.Supplier;

public class  OptionalExamples {
    private static Account DEFAULT_ACCOUNT = Accounts.generateAccount();

    public static void main(String[] args) {
        Account account = Accounts.generateAccount();
        Optional<Account> optionalAccount = Optional.of(account);

        printAccountUsingWrongApproach(optionalAccount);
        printAccountUsingRightApproach(optionalAccount);

        printAccountOrDefault(optionalAccount);
        printAccountOrRandomLazily(optionalAccount);
    }

    /**
     * It is not correct to process to process {@link Optional} values in the old imperative way, as it is shown below
     */
    private static void printAccountUsingWrongApproach(Optional<Account> optionalAccount) {
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            System.out.println(account);
        }
    }

    /**
     * The right way is to pass a consumer that will be used in case {@link Optional} value is not empty
     */
    private static void printAccountUsingRightApproach(Optional<Account> optionalAccount) {
        optionalAccount.ifPresent(System.out::println);
    }

    /**
     * or add a default value. Now you're safely trying to get an account instance, because in case it's null
     * you'll get a default value
     */
    private static void printAccountOrDefault(Optional<Account> optionalAccount) {
        Account account = optionalAccount.orElse(DEFAULT_ACCOUNT);
        System.out.println(account);
    }

    /**
     * The version with {@link Supplier} should be used in case getting default value requires
     * additional resources. In this case default instance will be only created if optional account is empty
     */
    private static void printAccountOrRandomLazily(Optional<Account> optionalAccount) {
        Account account = optionalAccount.orElseGet(() -> Accounts.generateAccount());
        System.out.println(account);
    }
}
