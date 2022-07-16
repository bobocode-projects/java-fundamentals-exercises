package com.bobocode;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;
import lombok.SneakyThrows;

import java.util.Stack;
import java.util.stream.Collector;

public class CustomCollectorDemoApp {
    @SneakyThrows
    public static void main(String[] args) {
        Stack<String> emailStack = Accounts.generateAccountList(10)
                .stream()
                .map(Account::getEmail)
                .collect(toStack());

        while (!emailStack.isEmpty()) {
            System.out.println(emailStack.pop());
        } 
    }

    static <T> Collector<T, ?, Stack<T>> toStack() {
        return Collector.of(
                Stack::new,
                Stack::push,
                (stack1, stack2) -> {
                    stack2.forEach(stack1::push);
                    return stack1;
                });
    }


}
