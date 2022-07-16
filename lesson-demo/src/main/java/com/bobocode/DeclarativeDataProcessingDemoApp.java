package com.bobocode;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;
import com.bobocode.model.CreditAccount;
import lombok.SneakyThrows;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class DeclarativeDataProcessingDemoApp {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("Stream API");
        Accounts.generateAccountList(10)
                .stream()
                .map(Account::getEmail)
                .flatMapToInt(String::chars)
                .forEach(System.out::println);

        System.out.println("Optional API");
        Optional.ofNullable(Accounts.generateCreditAccount())
                .flatMap(CreditAccount::getCreditBalance)
                .ifPresent(System.out::println);

        System.out.println("CompletableFuture API");
        CompletableFuture.supplyAsync(Accounts::generateAccount)
                .thenCompose(a -> CompletableFuture.supplyAsync(a::getEmail))
                .thenAccept(System.out::println)
                .join();

    }
    
    
    

}
