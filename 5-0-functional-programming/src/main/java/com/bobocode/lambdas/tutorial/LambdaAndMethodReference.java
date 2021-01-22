package com.bobocode.lambdas.tutorial;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.util.List;
import java.util.function.Consumer;

/**
 * Method reference is a shorthand for lambda expression that could be use in some cases for better readability.
 */
public class LambdaAndMethodReference {
    public static void main(String[] args) {
        List<Account> accounts = Accounts.generateAccountList(10);

        printAccountsUsingLambda(accounts);
        printAccountsUsingMethodReference(accounts);
    }

    private static void printAccountsUsingLambda(List<Account> accounts) {
        processAccounts(accounts, a -> System.out.println(a));
    }

    private static void printAccountsUsingMethodReference(List<Account> accounts) {
        processAccounts(accounts, System.out::println);
    }

    private static void processAccounts(List<Account> accounts, Consumer<Account> consumer) {
        for (Account account : accounts){
            consumer.accept(account);
        }
    }

}
