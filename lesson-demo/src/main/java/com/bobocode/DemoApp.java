package com.bobocode;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import static java.util.Comparator.comparing;// todo: comment this line 

public class DemoApp {
    public static void main(String[] args) {
        Accounts.generateAccountList(10)
                .stream()
                .max(comparing(Account::getBalance))
                .ifPresent(System.out::println);
    }

    // todo: implement a custom method "comparing"
}