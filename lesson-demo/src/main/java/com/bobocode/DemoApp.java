package com.bobocode;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;


public class DemoApp {
    public static void main(String[] args) {
        var accounts = Accounts.generateAccountList(10);
        Comparator<Account> accountBalanceComparator = comparing(Account::getFirstName);
        var maxAccount = max(accounts, thenComparing(accountBalanceComparator, Account::getLastName));
        System.out.println(maxAccount);
    }

    // todo: DISABLE AI Assistant
    // todo: implement a custom method comparing
    private static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T, ? extends U> mappingFunction) {
        return (o1, o2) -> mappingFunction.apply(o1).compareTo(mappingFunction.apply(o2));
    }

    // todo: implement a custom method max
    private static <T> T max(List<? extends T> list, Comparator<? super T> comparator) {
        var maxElement = list.get(0);
        for (var e : list) {
            if (comparator.compare(e, maxElement) > 0) {
                maxElement = e;
            }
        }
        return maxElement;
    }

    // todo: implement a custom method thenComparing
    private static <T, U extends Comparable<? super U>> Comparator<T> thenComparing(
            Comparator<? super T> comparator, 
            Function<? super T, ? extends U> mappingFunction) {
        return (o1, o2) ->{
            var result = comparator.compare(o1, o2);
            if (result != 0) {
                return result;
            }
            return mappingFunction.apply(o1).compareTo(mappingFunction.apply(o2));
        };
    }


    // todo: post a screenshot of your code to zoom chat
}
