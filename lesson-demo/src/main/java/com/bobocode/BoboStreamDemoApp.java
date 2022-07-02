package com.bobocode;

import com.bobocode.model.Account;

import java.util.function.Function;

public class BoboStreamDemoApp {
    public static void main(String[] args) {
        BoboStream<Account> accountStream = null;
        
        Function<Object, Integer> hashCodeFunction = Object::hashCode;
        // you can pass this function because of ? super T
        accountStream.map(hashCodeFunction)
                .forEach(System.out::println);

        Function<Account, String> emailFunction = Account::getEmail;
        // you can pass this function because of ? extends R
        BoboStream<Object> objectStream = accountStream.map(emailFunction);
    }
}
