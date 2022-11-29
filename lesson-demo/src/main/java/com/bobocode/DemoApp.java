package com.bobocode;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.util.Comparator;
import java.util.stream.Stream;

public class DemoApp {
    public static void main(String[] args) {
        Accounts.generateAccountList(10)
                .stream()
                .max(comparing(Account::getBalance))
                .ifPresent(System.out::println);
    }
    
    private static Comparator comparing() {
        throw new UnsupportedOperationException();//todo:
    }
  
    
}
