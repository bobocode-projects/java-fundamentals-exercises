package com.bobocode.optionals.tutorial;

import com.bobocode.data.Accounts;
import com.bobocode.model.CreditAccount;

import java.util.List;
import java.util.Optional;

public class OptionalStream {
    public static void main(String[] args) {
        List<CreditAccount> creditAccounts = Accounts.generateCreditAccountList(10);

        printExistingCreditBalancesJava8(creditAccounts);
        printExistingCreditBalancesJava9(creditAccounts);
    }

    private static void printExistingCreditBalancesJava8(List<CreditAccount> creditAccounts) {
        creditAccounts.stream()
                .map(CreditAccount::getCreditBalance)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(System.out::println);
    }

    private static void printExistingCreditBalancesJava9(List<CreditAccount> creditAccounts) {
        creditAccounts.stream()
                .map(CreditAccount::getCreditBalance)
                .flatMap(Optional::stream)
                .forEach(System.out::println);
    }
}
