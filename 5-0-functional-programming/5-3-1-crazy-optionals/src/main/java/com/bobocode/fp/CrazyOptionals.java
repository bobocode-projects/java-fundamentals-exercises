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

import static java.util.Comparator.comparing;

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
        // Since `text` can be null we should use `Optional.ofNullable()`. It will wrap `text` with `Optional` if it's
        // not null or use `Optional.empty()` if it's is `null`
        return Optional.ofNullable(text);
    }

    /**
     * Adds a provided amount of money to the balance of an optional account.
     *
     * @param accountProvider
     * @param amount          money to deposit
     */
    public static void deposit(AccountProvider accountProvider, BigDecimal amount) {
        // One of the ideas behind Optional API is to provide a declarative "if" statements. E.g. typically when we need
        // to check if account is not null and then perform some logic, we would write an "if" statement and then
        // do some logic inside.
        // But class `Optional` provides a special method for this use case. E.g. if you have an optional account, 
        // you can call `ifPresent` method and provide a `Consumer` of that account which will be used only 
        // if optional is not empty
        accountProvider.getAccount()
                .ifPresent(account -> account.setBalance(account.getBalance().add(amount)));
    }

    /**
     * Creates an instance of {@link Optional<Account>} using an account parameter
     *
     * @param account
     * @return optional object that holds account
     */
    public static Optional<Account> optionalOfAccount(@Nonnull Account account) {
        // Since account must not be null, we use `Optional.of()` that will throw `NullPointerException` on creation 
        // if passed object is null
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
        // As you can see Optional API is a bunch of useful methods for different use cases inside `Optional` class.
        // If you need to provide a default value that will be used if optional is empty, you can use method `orElse`.
        return accountProvider.getAccount()
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
        // We already saw a declarative "if". Now it's a declarative "if-else".
        // A method `ifPresentOrElse` accepts a consumer that will be used if optional is not empty, and a runnable that
        // will be called otherwise
        accountProvider.getAccount()
                .ifPresentOrElse(accountService::processAccount, accountService::processWithNoAccount);
    }

    /**
     * Returns account provided by {@link AccountProvider}. If no account is provided it generates one using {@link Accounts}
     * Please note that additional account should not be generated if {@link AccountProvider} returned one.
     *
     * @param accountProvider
     * @return provided or generated account
     */
    public static Account getOrGenerateAccount(AccountProvider accountProvider) {
        // In case you need to provide a default value, but it's computation is an expansive operation 
        // (e.g. calling other microservice), you SHOULD NOT USE `Optional#orElse()`. Because it FORCES YOU TO COMPUTE
        // A DEFAULT VALUE regardless if optional is empty or not.
        // For such cases it's better to use `Optional#orElseGet()` that functionally works exactly the same, but
        // is based on lazy initialization using `Supplier` interface. Which means that default value
        // will not be computed (created) until supplier method `get()` is called. In this case, 
        // A DEFAULT VALUE WILL BE ONLY COMPUTED WHEN OPTIONAL IS EMPTY
        return accountProvider.getAccount()
                .orElseGet(Accounts::generateAccount);
    }

    /**
     * Retrieves a {@link BigDecimal} balance using null-safe mappings.
     *
     * @param accountProvider
     * @return optional balance
     */
    public static Optional<BigDecimal> retrieveBalance(AccountProvider accountProvider) {
        // When you have an optional object, and want to access its field. In that case you can use `Optional#map`.
        // Which is a null-safe mapping that transforms an optional object into its optional field. 
        return accountProvider.getAccount()
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
        // Un case Optional is empty and you want to throw a custom exception, you can use `orElseThrow`
        return accountProvider.getAccount()
                .orElseThrow(() -> new AccountNotFoundException("No Account provided!"));
    }

    /**
     * Retrieves a {@link BigDecimal} credit balance using null-safe mappings.
     *
     * @param accountProvider
     * @return optional credit balance
     */
    public static Optional<BigDecimal> retrieveCreditBalance(CreditAccountProvider accountProvider) {
        // In case your getter already return Optional, you cannot use `Optional#map` because it will wrap it with
        // another `Optional` like `Optional<Optional<?>>`. In this case `Optional#flatMap` should be used. 
        return accountProvider.getAccount()
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
        // Sometimes you need to check if an optional object meets some criteria and you want to do that in 
        // a null-safe manner. For that purpose you can use `Optional#filter` that will check a provided condition
        // only if optional is not empty, and then if condition is true, it will keep the object wrapped with optional, 
        // or return empty optional otherwise 
        return accountProvider.getAccount()
                .filter(account -> account.getEmail().split("@")[1].equals("gmail.com"));
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
        // In case you have an alternative optional value, you can use `Optional#or`. It will be used only if main 
        // optional is empty. Then if you want to throw exception if optional is empty, but don't need a custom one, you
        // can call `Optional#orElseThrow`
        return accountProvider.getAccount()
                .or(fallbackProvider::getAccount)
                .orElseThrow();
    }

    /**
     * Returns an {@link Accounts} with the highest balance. Throws {@link java.util.NoSuchElementException} if input
     * list is empty
     *
     * @param accounts
     * @return account with the highest balance
     */
    public static Account getAccountWithMaxBalance(List<Account> accounts) {
        // Optionals are used in Stream API. E.e. `Stream#min` and `Stream#max` return `Optional`
        return accounts.stream()
                .max(comparing(Account::getBalance)) // returns Optional<Account>
                .orElseThrow(); // throws NoSuchElementException if optional is empty
    }

    /**
     * Returns the lowest balance values or empty if accounts list is empty
     *
     * @param accounts
     * @return the lowest balance values
     */
    public static OptionalDouble findMinBalanceValue(List<Account> accounts) {
        // As well as Stream API, an Optional API provides special classes for primitives. So in case you work with 
        // stream of primitives and call a method that returns an optional, like `min`, a return type will be 
        // primitive optional
        return accounts.stream()
                .map(Account::getBalance) // map all stream accounts to balances
                .mapToDouble(BigDecimal::doubleValue) // map all balances to primitive double values (returns DoubleStream)
                .min(); // Optional API provides special classes for primitives as well
    }

    /**
     * Finds an {@link Account} with max balance and processes it using {@link AccountService#processAccount(Account)}
     *
     * @param accounts
     * @param accountService
     */
    public static void processAccountWithMaxBalance(List<Account> accounts, AccountService accountService) {
        // Using Steam API and Optional API allows you to write concise declarative expressions. E.g. find an account
        // with max balance and process it if it exists. You use Stream method `max` that returns Optional<Account>
        // and then use `Optional#ifPreset` to provide a logic we want to execute for found account. 
        accounts.stream()
                .max(comparing(Account::getBalance)) // returns Optional<Account>
                .ifPresent(accountService::processAccount); // declarative if statement that accepts Consumer<Account>
    }

    /**
     * Calculates a sum of {@link CreditAccount#getCreditBalance()} of all accounts
     *
     * @param accounts
     * @return total credit balance
     */
    public static double calculateTotalCreditBalance(List<CreditAccount> accounts) {
        // If you have a stream of optionals and you want to filter empty ones, you can do the trick and call
        // `Stream#flatMap` and pass `Optional#stream`. This logic transforms each optional object into a stream of either
        // one of zero elements and then all those streams are flattened into one using `flatMap` which automatically
        // filters all empty optional 
        return accounts.stream()
                .map(CreditAccount::getCreditBalance) // transforms each element of stream into Optional<BigDecimal>
                .flatMap(Optional::stream) // uses special Optional#stream() to filter all elements that are empty
                .mapToDouble(BigDecimal::doubleValue) // transform BigDecimal into primitive double (returns DoubleStream)
                .sum(); // calculates a sum of primitive double
    }
}

