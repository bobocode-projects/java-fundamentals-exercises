package com.bobocode.optionals.tutorial;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.OptionalDouble;

public class OptionalPrimitives {
    public static void main(String[] args) {
        List<Account> accounts = Accounts.generateAccountList(10);

        OptionalDouble minBalance = findMinBalance(accounts); // TODO: NEVER USE Optional<Double>
        System.out.println("Min balance is " + minBalance.orElse(0));
    }

    /**
     * Always use special classes for optional primitives. See also {@link java.util.OptionalInt},
     * {@link java.util.OptionalLong}
     *
     */
    private static OptionalDouble findMinBalance(List<Account> accounts) {
        return accounts.stream()
                .map(Account::getBalance)
                .mapToDouble(BigDecimal::doubleValue)
                .min();
    }


}
