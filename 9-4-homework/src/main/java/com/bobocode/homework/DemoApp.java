package com.bobocode.homework;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DemoApp {
    public static void main(String[] args) {
        var accounts = Accounts.generateAccountList(10);
        var emailToBirthdayTable = new HashTable<String, LocalDate>();
        accounts.forEach(a -> emailToBirthdayTable.put(a.getEmail(), a.getBirthday()));
        emailToBirthdayTable.printTable();
    }
}

class Accounts {
    static Faker faker = new Faker();

    static List<Account> generateAccountList(int amount) {
        return IntStream.range(0, amount).mapToObj(i -> new Account(getEmail(), getBirthday())).collect(Collectors.toList());
    }

    private static String getEmail() {
        return faker.internet().emailAddress();
    }

    private static LocalDate getBirthday() {
        var birthday = faker.date().birthday();
        return LocalDate.ofInstant(birthday.toInstant(), ZoneId.of("UTC"));
    }
}

record Account(String email, LocalDate birthday) {

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
