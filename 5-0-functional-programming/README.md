# <img src="https://raw.githubusercontent.com/bobocode-projects/resources/master/image/logo_transparent_background.png" height=50/>Java Functional Programming Basics :muscle:
Learn Java functional programming

### Intro
Do you remember pattern "Strategy" from "Design patterns"(GOF). Programmers use it quite often, for instance to search
account by some criteria. We will use this case as an example. 

In a simple implementation, there should be an "engine" which is able to look through all accounts and to test them on some criteria. 
If account passes criteria, it is moved to results.
Question: how to pass criteria to engine? In fact criteria is a couple of if-statement, which should reside in
a method(actually the only scope, where if-statement can reside in Java). Java does not allow to pass a method as an 
argument - method should be put in some object. Since you already know about interfaces Comparator and Runnable, objects
with the only method are not weird to you.

So we may create a class with a single method and to pass instance of this class to the search "engine". But in this 
case we get a lot of small classes with the only method with possibly only one if-statement. This approach complicates
navigation on already large project - not attractive.

Also we may create an instance of anonymous class right before passing it to search engine. It looks like this:
```java
interface Criteria<T> {
    boolean passed(T obj);
}
```
```java
... = searchEngine.searchBy(new Criteria<Account>() {
    @Override
    public boolean passed(Account a) {
        return a.getEmail().endsWith("@gmail.com");
    }
});
```
Already better but to verbose.

Java 8 introduced new syntax to overcome such verbosity - lambdas. Here is the beginning of functional programming in Java.
Implementation with lambdas look like this:
```java
... = searchEngine.searchBy(a -> a.getEmail().endsWith("@gmail.com"));
```
One row - that is it.

Lambdas caused creation of other APIs 
* Functional Interfaces
* Stream API
* Optional API

which eliminates a lot of boiler-plate code and even makes parallel calculations easier.

Already mentioned "functional programming" is another programming paradigm(like already familiar procedural and OO).
Looks through this article to understand connection between lambdas and functional programming.

### Summary
The main idea is to keep Java OO language but **enable some functional programming features**. It does two things:
* makes the code more concise
* allows easier parallelization

Once you've mastered all those stuff, instead of this
```java
public List<Account> findAllGmailAccounts(List<Account> accounts) {
    List<Account> gmailAccounts = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getEmail().endsWith("@gmail.com")) {
                gmailAccounts.add(account);
            }
        }
    return gmailAccounts;
}
```
you'll be writing this
```java
public List<Account> findAllGmailAccounts(List<Account> accounts) {
        return accounts.stream()
                .filter(a -> a.getEmail().endsWith("@gmail.com"))
                .collect(toList());
}
```

⚡️ Nowadays **every Java developer uses some functional programming features in an everyday job**. So make sure you've 
check out the rest of the materials in this module and build strong skills using these language features! 💪

### Learn or skip ? 
Think you're cool enough to skip this topic? 😎 Hand on a sec...☝️ Can you easily understand and write lambdas like this?
```java
runnable -> () -> {
            Thread t = new Thread(runnable);
            t.start();
            return t;
};
```
or stream pipelines like this ? 🧐
```java
accounts.stream()
        .flatMap(a -> Stream.of(a.getFirstName(), a.getLastName()))
        .map(String::toLowerCase)
        .flatMapToInt(String::chars)
        .mapToObj(c -> (char) c)
        .collect(groupingBy(identity(), counting()));
```
No worries if you don't! Be patient, **do the exercises in this module**, and you will be able to do not only this 👆.
**You will understand and use functional programming techniques far beyond your expectations** 🔥