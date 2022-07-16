package com.bobocode;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;
import lombok.SneakyThrows;

import java.util.Stack;

public class CustomCollectorDemoApp {
    @SneakyThrows
    public static void main(String[] args) {
        Stack<String> emailStack = Accounts.generateAccountList(10)
                .stream()
                .map(Account::getEmail)
                .collect(toStack());
        
        // todo: implement method toStack that returns a custom Collector 
    }


}
