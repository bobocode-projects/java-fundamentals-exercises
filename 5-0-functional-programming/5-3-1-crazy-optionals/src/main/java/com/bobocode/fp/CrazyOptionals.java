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
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

/**
 * {@link CrazyOptionals} is an exercise class. Each method represents some operation with a {@link Account} and
 * should be implemented using Optional API. Every method that is not implemented yet throws
 * {@link ExerciseNotCompletedException}.
 * <p>
 * TODO: remove exception and implement each method of this class using Optional API
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
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
        throw new ExerciseNotCompletedException();
    }

    /**
     * Adds a provided amount of money to the balance of an optional account.
     *
     * @param accountProvider
     * @param amount          money to deposit
     */
    public static void deposit(AccountProvider accountProvider, BigDecimal amount) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Creates an instance of {@link Optional<Account>} using an account parameter
     *
     * @param account
     * @return optional object that holds account
     */
    public static Optional<Account> optionalOfAccount(@Nonnull Account account) {
        throw new ExerciseNotCompletedException();
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
        throw new ExerciseNotCompletedException();
    }

    /**
     * Passes account to {@link AccountService#processAccount(Account)} when account is provided.
     * Otherwise calls {@link AccountService#processWithNoAccount()}
     *
     * @param accountProvider
     * @param accountService
     */
    public static void processAccount(AccountProvider accountProvider, AccountService accountService) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Returns account provided by {@link AccountProvider}. If no account is provided it generates one using {@link Accounts}
     * Please note that additional account should not be generated if {@link AccountProvider} returned one.
     *
     * @param accountProvider
     * @return provided or generated account
     */
    public static Account getOrGenerateAccount(AccountProvider accountProvider) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Retrieves a {@link BigDecimal} balance using null-safe mappings.
     *
     * @param accountProvider
     * @return optional balance
     */
    public static Optional<BigDecimal> retrieveBalance(AccountProvider accountProvider) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Returns an {@link Account} provided by {@link AccountProvider}. If no account is provided,
     * throws {@link AccountNotFoundException} with a message "No Account provided!"
     *
     * @param accountProvider
     * @return provided account
     */
    public static Account getAccount(AccountProvider accountProvider) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Retrieves a {@link BigDecimal} credit balance using null-safe mappings.
     *
     * @param accountProvider
     * @return optional credit balance
     */
    public static Optional<BigDecimal> retrieveCreditBalance(CreditAccountProvider accountProvider) {
        throw new ExerciseNotCompletedException();
    }


    /**
     * Retrieves an {@link Account} with gmail email using {@link AccountProvider}. If no account is provided or it gets
     * not gmail then returns {@link Optional#empty()}
     *
     * @param accountProvider
     * @return optional gmail account
     */
    public static Optional<Account> retrieveAccountGmail(AccountProvider accountProvider) {
        throw new ExerciseNotCompletedException();
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
        throw new ExerciseNotCompletedException();
    }

    /**
     * Returns an {@link Accounts} with the highest balance. Throws {@link java.util.NoSuchElementException} if input
     * list is empty
     *
     * @param accounts
     * @return account with the highest balance
     */
    public static Account getAccountWithMaxBalance(List<Account> accounts) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Returns the lowest balance values or empty if accounts list is empty
     *
     * @param accounts
     * @return the lowest balance values
     */
    public static OptionalDouble findMinBalanceValue(List<Account> accounts) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Finds an {@link Account} with max balance and processes it using {@link AccountService#processAccount(Account)}
     *
     * @param accounts
     * @param accountService
     */
    public static void processAccountWithMaxBalance(List<Account> accounts, AccountService accountService) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Calculates a sum of {@link CreditAccount#getCreditBalance()} of all accounts
     *
     * @param accounts
     * @return total credit balance
     */
    public static double calculateTotalCreditBalance(List<CreditAccount> accounts) {
        throw new ExerciseNotCompletedException();
    }
}

