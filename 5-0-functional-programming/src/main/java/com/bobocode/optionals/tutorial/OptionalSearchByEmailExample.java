package com.bobocode.optionals.tutorial;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.util.List;
import java.util.Optional;

public class OptionalSearchByEmailExample {
    public static void main(String[] args) {
        List<Account> accounts = Accounts.generateAccountList(10);

        Account account = getAccountByEmail(accounts, "jsushi@gmail.com");

        printAccount(account);
    }

    private static Optional<Account> findAccountByEmail(List<Account> accounts, String email) {
        return accounts.stream()
                .filter(a -> a.getEmail().equals(email))
                .findAny();
    }

    private static Account getAccountByEmail(List<Account> accounts, String email) {
        return findAccountByEmail(accounts, email)
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * Wrong way of using Optional
     */
    private static Account getAccountByEmailImperatively(List<Account> accounts, String email) {
        Optional<Account> optionalAccount = findAccountByEmail(accounts, email);
        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        } else {
            throw new NoSuchElementException();
        }

    }

    private static void printAccount(Account account) {
        System.out.println("This account belongs to " + account.getFirstName() +
                " " + account.getLastName());
    }

    static class NoSuchElementException extends RuntimeException {

    }
}
