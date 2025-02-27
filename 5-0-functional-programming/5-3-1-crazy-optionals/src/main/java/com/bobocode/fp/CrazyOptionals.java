package com.bobocode.fp;

import com.bobocode.data.Accounts;
import com.bobocode.fp.exception.AccountNotFoundException;
import com.bobocode.fp.function.AccountProvider;
import com.bobocode.fp.function.AccountService;
import com.bobocode.fp.function.CreditAccountProvider;
import com.bobocode.model.Account;
import com.bobocode.model.CreditAccount;
import com.bobocode.util.ExerciseNotCompletedException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.*;

/**
 * {@link CrazyOptionals} is an exercise class. Each method represents some operation with a {@link Account} and
 * should be implemented using Optional API. Every method that is not implemented yet throws
 * {@link ExerciseNotCompletedException}.
 * <p>
 * TODO: remove exception and implement each method of this class using Optional API
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com">visit our website</a></strong>
 * <p>
 *
 * @author Taras Boychuk
 */
public class CrazyOptionals {

    /**
     * Creates an instance of {@link Optional<String>} using a text parameter
     *
     * @param text
     * @return optional object that holds text
     */
    public static Optional<String> optionalOfString(@Nullable String text) {
        return Optional.ofNullable(text);
    }

    /**
     * Adds a provided amount of money to the balance of an optional account.
     *
     * @param accountProvider
     * @param amount          money to deposit
     */
    public static void deposit(AccountProvider accountProvider, BigDecimal amount) {
        Optional.ofNullable(accountProvider)
                .flatMap(AccountProvider::getAccount)
                .ifPresent(account->account.setBalance(account.getBalance().add(amount)));
    }

    /**
     * Creates an instance of {@link Optional<Account>} using an account parameter
     *
     * @param account
     * @return optional object that holds account
     */
    public static Optional<Account> optionalOfAccount(@Nonnull Account account) {
        return Optional.of(account);
    }

    /**
     * Returns the {@link Account} got from {@link AccountProvider}. If account provider does not provide an account,
     * returns a defaultAccount
     *
     * @param accountProvider
     * @param defaultAccount
     * @return account from provider or defaultAccount
     */
    public static Account getAccount(AccountProvider accountProvider, Account defaultAccount) {
        return Optional.of(accountProvider)
                .flatMap(AccountProvider::getAccount)
                .orElse(defaultAccount);
    }

    /**
     * Passes account to {@link AccountService#processAccount(Account)} when account is provided.
     * Otherwise calls {@link AccountService#processWithNoAccount()}
     *
     * @param accountProvider
     * @param accountService
     */
    public static void processAccount(AccountProvider accountProvider, AccountService accountService) {
        Optional.of(accountProvider)
                .flatMap(AccountProvider::getAccount)
                .ifPresentOrElse(accountService::processAccount
                ,accountService::processWithNoAccount);
    }

    /**
     * Returns account provided by {@link AccountProvider}. If no account is provided it generates one using {@link Accounts}
     * Please note that additional account should not be generated if {@link AccountProvider} returned one.
     *
     * @param accountProvider
     * @return provided or generated account
     */
    public static Account getOrGenerateAccount(AccountProvider accountProvider) {
        return Optional.of(accountProvider)
                .flatMap(AccountProvider::getAccount)
                .orElseGet(Accounts::generateAccount);
    }

    /**
     * Retrieves a {@link BigDecimal} balance using null-safe mappings.
     *
     * @param accountProvider
     * @return optional balance
     */
    public static Optional<BigDecimal> retrieveBalance(AccountProvider accountProvider) {
       return Optional.ofNullable(accountProvider)
               .flatMap(AccountProvider::getAccount)
               .map(Account::getBalance);
    }

    /**
     * Returns an {@link Account} provided by {@link AccountProvider}. If no account is provided,
     * throws {@link AccountNotFoundException} with a message "No Account provided!"
     *
     * @param accountProvider
     * @return provided account
     */
    public static Account getAccount(AccountProvider accountProvider) {
        return Optional.ofNullable(accountProvider)
                .flatMap(AccountProvider::getAccount)
                .orElseThrow(()-> new AccountNotFoundException("No Account provided!"));
    }

    /**
     * Retrieves a {@link BigDecimal} credit balance using null-safe mappings.
     *
     * @param accountProvider
     * @return optional credit balance
     */
    public static Optional<BigDecimal> retrieveCreditBalance(CreditAccountProvider accountProvider) {
        return Optional.ofNullable(accountProvider)
                .flatMap(CreditAccountProvider::getAccount)
                .flatMap(CreditAccount::getCreditBalance);
    }


    /**
     * Retrieves an {@link Account} with gmail email using {@link AccountProvider}. If no account is provided or it gets
     * not gmail then returns {@link Optional#empty()}
     *
     * @param accountProvider
     * @return optional gmail account
     */
    public static Optional<Account> retrieveAccountGmail(AccountProvider accountProvider) {
        return Optional.ofNullable(accountProvider)
                .flatMap(AccountProvider::getAccount)
                .filter(account -> account.getEmail()!=null && account.getEmail().endsWith("@gmail.com"));

    }

    /**
     * Retrieves account using {@link AccountProvider} and fallbackProvider. In case main provider does not provide an
     * {@link Account} then account should ge retrieved from fallbackProvider. In case both provider returns no account
     * then {@link java.util.NoSuchElementException} should be thrown.
     *
     * @param accountProvider
     * @param fallbackProvider
     * @return account got from either accountProvider or fallbackProvider
     */
    public static Account getAccountWithFallback(AccountProvider accountProvider, AccountProvider fallbackProvider) {
        return Optional.ofNullable(accountProvider)
                .flatMap(AccountProvider::getAccount)
                .or(()->Optional.ofNullable(fallbackProvider).flatMap(AccountProvider::getAccount))
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * Returns an {@link Accounts} with the highest balance. Throws {@link java.util.NoSuchElementException} if input
     * list is empty
     *
     * @param accounts
     * @return account with the highest balance
     */
    public static Account getAccountWithMaxBalance(List<Account> accounts) {
        return Optional.ofNullable(accounts)
                .filter(list -> !list.isEmpty())
                .flatMap(list -> list.stream().max(Comparator.comparing(Account::getBalance)))
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * Returns the lowest balance values or empty if accounts list is empty
     *
     * @param accounts
     * @return the lowest balance values
     */
    public static OptionalDouble findMinBalanceValue(List<Account> accounts) {
        return Optional.ofNullable(accounts)
                .filter(list->!list.isEmpty())
                .flatMap(list->list.stream().min(Comparator.comparing(Account::getBalance)))
                .map(account ->OptionalDouble.of(account.getBalance().doubleValue()))
                .orElseGet(OptionalDouble::empty);
    }

    /**
     * Finds an {@link Account} with max balance and processes it using {@link AccountService#processAccount(Account)}
     *
     * @param accounts
     * @param accountService
     */
    public static void processAccountWithMaxBalance(List<Account> accounts, AccountService accountService) {
         Optional.ofNullable(accounts)
                 .filter(list->!list.isEmpty())
                 .flatMap(list->list.stream().max(Comparator.comparing(Account::getBalance)))
                 .ifPresent(accountService::processAccount);
    }

    /**
     * Calculates a sum of {@link CreditAccount#getCreditBalance()} of all accounts
     *
     * @param accounts
     * @return total credit balance
     */
    public static double calculateTotalCreditBalance(List<CreditAccount> accounts) {

        return Optional.ofNullable(accounts)
                .stream()
                .flatMap(List::stream)
                .map(CreditAccount::getCreditBalance)
                .flatMap(Optional::stream)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
    }
}

