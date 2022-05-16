package com.bobocode;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

public class DemoApp {
    public static void main(String[] args) {
        var accountComparator = new RandomFieldComparator<>(Account.class);
        System.out.println(accountComparator);
        Accounts.generateAccountList(10)
                .stream()
                .sorted(accountComparator)
                .forEach(System.out::println);
    }
}
