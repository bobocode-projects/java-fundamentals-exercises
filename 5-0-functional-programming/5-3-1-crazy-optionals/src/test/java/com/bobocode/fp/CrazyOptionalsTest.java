package com.bobocode.fp;

import com.bobocode.data.Accounts;
import com.bobocode.fp.exception.AccountNotFoundException;
import com.bobocode.fp.function.AccountService;
import com.bobocode.model.Account;
import com.bobocode.model.CreditAccount;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Comparator.comparing;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * A test class for {@link CrazyOptionals}.
 *
 * @author Taras Boychuk
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CrazyOptionalsTest {

    @Test
    @Order(1)
    void optionalOfStringShouldAcceptNull() {
        Optional<String> optionalString = CrazyOptionals.optionalOfString(null);

        assertEquals(Optional.empty(), optionalString);
    }

    @Test
    @Order(2)
    void optionalOfStringShouldHoldSting() {
        Optional<String> optionalString = CrazyOptionals.optionalOfString("Hello");

        assertEquals("Hello", optionalString.get());
    }

    @Test
    @Order(3)
    void depositWhenAccountExistsShouldUpdateBalance() {
        Account account = Accounts.generateAccount();
        BigDecimal balanceBeforeUpdate = account.getBalance();
        BigDecimal amountToAdd = BigDecimal.valueOf(1000);

        CrazyOptionals.deposit(() -> Optional.of(account), amountToAdd);

        assertEquals(balanceBeforeUpdate.add(amountToAdd), account.getBalance());
    }

    @Test
    @Order(4)
    void depositWhenOptionalIsEmptyShouldDoNothing() {
        CrazyOptionals.deposit(Optional::empty, BigDecimal.ZERO);
    }

    @Test
    @Order(5)
    void optionalOfAccountShouldNotAcceptNull() {
        assertThrows(NullPointerException.class, () -> CrazyOptionals.optionalOfAccount(null));
    }

    @Test
    @Order(6)
    void optionalOfAccountShouldHoldAccount() {
        Account account = Accounts.generateAccount();
        Optional<Account> optionalAccount = CrazyOptionals.optionalOfAccount(account);

        assertEquals(account, optionalAccount.get());
    }

    @Test
    @Order(7)
    void getAccountShouldReturnAccountGotFromProvider() {
        Account providedAccount = Accounts.generateAccount();
        Account defaultAccount = Accounts.generateAccount();

        Account receivedAccount = CrazyOptionals.getAccount(() -> Optional.of(providedAccount), defaultAccount);

        assertEquals(providedAccount, receivedAccount);
    }

    @Test
    @Order(8)
    void getAccountWhenNoAccountIsProvidedShouldReturnDefaultAccount() {
        Account defaultAccount = Accounts.generateAccount();

        Account receivedAccount = CrazyOptionals.getAccount(Optional::empty, defaultAccount);

        assertEquals(defaultAccount, receivedAccount);
    }

    @Test
    @Order(9)
    void processAccountWhenAccountIsProvidedShouldPassAccountToService() {
        Account account = Accounts.generateAccount();
        BigDecimal initialBalance = account.getBalance();
        BigDecimal bonus = BigDecimal.valueOf(1000);

        CrazyOptionals.processAccount(() -> Optional.of(account), a -> a.setBalance(account.getBalance().add(bonus)));

        assertEquals(initialBalance.add(bonus), account.getBalance());
    }

    @Test
    @Order(10)
    void processAccountWhenNoAccountProvidedShouldProcessWithNoAccount() {
        Optional<Account> optionalAccountSpy = spy(Optional.empty());
        AccountService accountService = mock(AccountService.class);

        CrazyOptionals.processAccount(() -> optionalAccountSpy, accountService);

        verify(accountService, times(1)).processWithNoAccount();
        verify(accountService, never()).processAccount(any());

    }

    @Test
    @Order(11)
    void processAccountShouldUseNullSafeDeclarativeIfStatement() {
        Optional<Account> optionalAccountSpy = spy(Optional.empty());

        CrazyOptionals.processAccount(() -> optionalAccountSpy, account -> {
        });

        verify(optionalAccountSpy, times(1)).ifPresentOrElse(any(), any());
    }

    @Test
    @Order(12)
    void getOrGenerateAccountShouldReturnAccountGotFromProvider() {
        Account providedAccount = Accounts.generateAccount();

        Account receivedAccount = CrazyOptionals.getOrGenerateAccount(() -> Optional.of(providedAccount));

        assertEquals(providedAccount, receivedAccount);
    }

    @Test
    @Order(13)
    void getOrGenerateAccountWhenNoAccountIsProvidedShouldGenerateAccount() {
        Account receivedAccount = CrazyOptionals.getOrGenerateAccount(Optional::empty);

        assertNotNull(receivedAccount);
    }

    @Test
    @Order(14)
    void getOrGenerateAccountWhenNoAccountIsProvidedShouldUseLazyInitialization() {
        Optional<Account> optionalAccountSpy = spy(Optional.empty());

        CrazyOptionals.getOrGenerateAccount(() -> optionalAccountSpy);

        verify(optionalAccountSpy, never()).orElse(any());
        verify(optionalAccountSpy, never()).get();
        verify(optionalAccountSpy, times(1)).orElseGet(any());
    }

    @Test
    @Order(15)
    void retrieveBalanceWhenAccountIsNotProvidedShouldReturnEmptyOptional() {
        Optional<BigDecimal> balance = CrazyOptionals.retrieveBalance(Optional::empty);

        assertFalse(balance.isPresent());
    }

    @Test
    @Order(16)
    void retrieveBalanceWhenBalanceIsNullShouldReturnEmptyOptional() {
        Account account = Accounts.generateAccount();
        account.setBalance(null);

        Optional<BigDecimal> balance = CrazyOptionals.retrieveBalance(() -> Optional.of(account));

        assertFalse(balance.isPresent());
    }

    @Test
    @Order(17)
    void retrieveBalanceShouldReturnOptionalBalance() {
        Account account = Accounts.generateAccount();

        Optional<BigDecimal> balance = CrazyOptionals.retrieveBalance(() -> Optional.of(account));

        assertEquals(account.getBalance(), balance.get());
    }

    @Test
    @Order(18)
    void retrieveBalanceShouldUseNullSafeMapping() {
        Account account = Accounts.generateAccount();
        Optional<Account> optionalAccountSpy = spy(Optional.of(account));

        CrazyOptionals.retrieveBalance(() -> optionalAccountSpy);

        verify(optionalAccountSpy, never()).get();
        verify(optionalAccountSpy, never()).isEmpty();
        verify(optionalAccountSpy, times(1)).isPresent();
        verify(optionalAccountSpy, never()).orElse(any());
        verify(optionalAccountSpy, never()).orElseGet(any());
        verify(optionalAccountSpy, times(1)).map(any());
    }

    @Test
    @Order(19)
    void getAccountShouldReturnProvidedAccount() {
        Account account = Accounts.generateAccount();

        Account receivedAccount = CrazyOptionals.getAccount(() -> Optional.of(account));

        assertEquals(account, receivedAccount);
    }

    @Test
    @Order(20)
    void getAccountWhenNoAccountIsProvidedShouldThrowAccountNotFoundException() {
        AccountNotFoundException exception = assertThrows(AccountNotFoundException.class,
                () -> CrazyOptionals.getAccount(Optional::empty));
        assertEquals("No Account provided!", exception.getMessage());
    }

    @Test
    @Order(21)
    void retrieveCreditBalanceWhenAccountIsNotProvidedShouldReturnEmptyOptional() {
        Optional<BigDecimal> creditBalance = CrazyOptionals.retrieveCreditBalance(Optional::empty);

        assertFalse(creditBalance.isPresent());
    }

    @Test
    @Order(22)
    void retrieveCreditBalanceWhenBalanceIsNullShouldReturnEmptyOptional() {
        CreditAccount account = Accounts.generateCreditAccount();
        account.setCreditBalance(null);

        Optional<BigDecimal> creditBalance = CrazyOptionals.retrieveCreditBalance(() -> Optional.of(account));

        assertFalse(creditBalance.isPresent());
    }

    @Test
    @Order(23)
    void retrieveCreditBalanceShouldReturnOptionalBalance() {
        CreditAccount account = Accounts.generateCreditAccount();

        Optional<BigDecimal> creditBalance = CrazyOptionals.retrieveCreditBalance(() -> Optional.of(account));

        assertEquals(account.getCreditBalance(), creditBalance);
    }

    @Test
    @Order(24)
    void retrieveCreditBalanceShouldUseNullSafeMapping() {
        CreditAccount creditAccount = Accounts.generateCreditAccount();
        Optional<CreditAccount> optionalCreditAccountSpy = spy(Optional.of(creditAccount));

        CrazyOptionals.retrieveCreditBalance(() -> optionalCreditAccountSpy);

        verify(optionalCreditAccountSpy, never()).get();
        verify(optionalCreditAccountSpy, never()).isEmpty();
        verify(optionalCreditAccountSpy, times(1)).isPresent();
        verify(optionalCreditAccountSpy, never()).orElse(any());
        verify(optionalCreditAccountSpy, never()).orElseGet(any());
        verify(optionalCreditAccountSpy, times(1)).flatMap(any());
    }

    @Test
    @Order(25)
    void retrieveAccountGmailWhenNoAccountProvidedShouldReturnEmptyOptional() {
        Optional<Account> optionalGmailAccount = CrazyOptionals.retrieveAccountGmail(Optional::empty);

        assertEquals(Optional.empty(), optionalGmailAccount);
    }

    @Test
    @Order(26)
    void retrieveAccountGmailWhenAccountEmailIsNotGmailShouldReturnEmptyOptional() {
        Account account = Accounts.generateCreditAccount();
        account.setEmail("bobby@yahoo.com");

        Optional<Account> optionalGmailAccount = CrazyOptionals.retrieveAccountGmail(() -> Optional.of(account));

        assertEquals(Optional.empty(), optionalGmailAccount);
    }

    @Test
    @Order(27)
    void retrieveAccountGmailWhenEmailIsGmailShouldReturnEmail() {
        Account account = Accounts.generateCreditAccount();
        account.setEmail("johnny@gmail.com");

        Optional<Account> optionalGmailAccount = CrazyOptionals.retrieveAccountGmail(() -> Optional.of(account));

        assertEquals(account, optionalGmailAccount.get());
    }

    @Test
    @Order(28)
    void retrieveAccountGmailShouldUseNullSafeFiltering() {
        Account account = Accounts.generateAccount();
        account.setEmail("johnny@gmail.com");
        Optional<Account> optionalAccountSpy = spy(Optional.of(account));

        CrazyOptionals.retrieveAccountGmail(() -> optionalAccountSpy);

        verify(optionalAccountSpy, never()).get();
        verify(optionalAccountSpy, times(1)).isPresent();
        verify(optionalAccountSpy, never()).isEmpty();
        verify(optionalAccountSpy, never()).orElse(any());
        verify(optionalAccountSpy, times(1)).filter(any());
    }

    @Test
    @Order(29)
    void getAccountWithFallbackShouldBeRetrievedFromMainProvider() {
        Account account = Accounts.generateAccount();
        Account fallbackAccount = Accounts.generateAccount();

        Account retrievedAccount = CrazyOptionals.getAccountWithFallback(() -> Optional.of(account), () -> Optional.of(fallbackAccount));

        assertEquals(account, retrievedAccount);
    }

    @Test
    @Order(30)
    void getAccountWithFallbackWhenNoAccountIsProvidedByMainProviderShouldUseFallback() {
        Account fallbackAccount = Accounts.generateAccount();

        Account retrievedAccount = CrazyOptionals.getAccountWithFallback(Optional::empty, () -> Optional.of(fallbackAccount));

        assertEquals(fallbackAccount, retrievedAccount);
    }

    @Test
    @Order(31)
    void getAccountWithFallbackWhenNoAccountShouldThrowException() {
        assertThrows(NoSuchElementException.class,
                () -> CrazyOptionals.getAccountWithFallback(Optional::empty, Optional::empty));
    }

    @Test
    @Order(32)
    void getAccountWithFallbackShouldUseNullSafeFallbackStrategy() {
        Optional<Account> optionalAccountSpy = spy(Optional.empty());

        CrazyOptionals.getAccountWithFallback(() -> optionalAccountSpy, () -> Optional.of(Accounts.generateAccount()));

        verify(optionalAccountSpy, times(1)).isPresent();
        verify(optionalAccountSpy, never()).isEmpty();
        verify(optionalAccountSpy, never()).get();
        verify(optionalAccountSpy, never()).orElse(any());
        verify(optionalAccountSpy, never()).orElseGet(any());
        verify(optionalAccountSpy, times(1)).or(any());
    }

    @Test
    @Order(33)
    void getAccountWithMaxBalance() {
        List<Account> accounts = Accounts.generateAccountList(5);
        Account richestAccount = getMaxAccount(accounts, comparing(Account::getBalance));

        Account receivedAccount = CrazyOptionals.getAccountWithMaxBalance(accounts);

        assertEquals(richestAccount, receivedAccount);
    }

    @Test
    @Order(34)
    void getAccountWithMaxBalanceWhenListIsEmptyShouldThrowException() {
        assertThrows(NoSuchElementException.class,
                () -> CrazyOptionals.getAccountWithMaxBalance(Collections.emptyList()));
    }

    @Test
    @Order(35)
    void findMinBalanceValueShouldReturnCorrectDoubleValue() {
        List<Account> accounts = Accounts.generateAccountList(5);
        Account accountWithLowestBalance = getMaxAccount(accounts, comparing(Account::getBalance).reversed());
        double expectedBalance = accountWithLowestBalance.getBalance().doubleValue();

        OptionalDouble optionalMinBalance = CrazyOptionals.findMinBalanceValue(accounts);

        assertEquals(expectedBalance, optionalMinBalance.getAsDouble(), 0.001);
    }

    @Test
    @Order(36)
    void findMinBalanceValueWhenListIsEmptyShouldReturnOptionalEmpty() {
        OptionalDouble optionalMinBalance = CrazyOptionals.findMinBalanceValue(Collections.emptyList());

        assertEquals(OptionalDouble.empty(), optionalMinBalance);
    }

    @Test
    @Order(37)
    void processAccountWithMaxBalance() {
        List<Account> accounts = Accounts.generateAccountList(5);
        Account richestAccount = getMaxAccount(accounts, comparing(Account::getBalance));
        AccountService accountServiceSpy = Mockito.spy(AccountService.class);

        CrazyOptionals.processAccountWithMaxBalance(accounts, accountServiceSpy);

        verify(accountServiceSpy, times(1)).processAccount(richestAccount);
    }

    @Test
    @Order(38)
    void calculateTotalCreditBalanceShouldCalculateCorrectTotal() {
        List<CreditAccount> accounts = Accounts.generateCreditAccountList(5);
        double expectedTotal = calculateTotalCreditBalance(accounts);

        double calculatedTotal = CrazyOptionals.calculateTotalCreditBalance(accounts);

        assertEquals(expectedTotal, calculatedTotal, 0.001);
    }

    @Test
    @Order(39)
    void calculateTotalCreditBalanceWhenListIsEmptyShouldReturnZero() {
        double calculatedTotal = CrazyOptionals.calculateTotalCreditBalance(Collections.emptyList());

        assertEquals(0.0, calculatedTotal, 0.001);
    }

    private Account getMaxAccount(List<Account> accounts, Comparator<Account> comparator) {
        return accounts.stream()
                .max(comparator)
                .orElseThrow();
    }

    private double calculateTotalCreditBalance(List<CreditAccount> accounts) {
        return accounts.stream()
                .map(CreditAccount::getCreditBalance)
                .flatMap(Optional::stream)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
    }
}
