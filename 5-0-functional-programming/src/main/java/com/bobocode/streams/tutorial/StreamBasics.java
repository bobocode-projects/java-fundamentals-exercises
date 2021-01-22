package com.bobocode.streams.tutorial;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.time.Month;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * {@link Stream} is not a data collection by itself. It's an API that allows to perform bulk operation on top of some existing
 * data collection in a declarative way.
 * <p>
 * {@link Stream} provides a lot of useful methods to work with data sequences. All intermediate operation produce a new
 * stream. Those operations are performed lazily. E.g. the iteration on elements and all intermediate operations
 * are performed only when terminal operation is called. After that stream cannot be reused.
 */


public class StreamBasics {
    public static void main(String[] args) {
        List<Account> accounts = Accounts.generateAccountList(10);
        Stream<Account> accountStream = accounts.stream(); // you can create stream directly from a collection object

        List<Account> augustAccounts = accountStream.
                filter(a -> a.getCreationDate().getMonth().equals(Month.AUGUST)) // 'filter' is an intermediate operation
                .collect(toList());// 'collect' is a terminal operation

        System.out.println("August accounts: " + augustAccounts);
        // accountStream.forEach(System.out::println); // stream cannot be reused. You'll get IllegalStateException
    }
}
