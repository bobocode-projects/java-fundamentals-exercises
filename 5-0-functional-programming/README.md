# <img src="https://raw.githubusercontent.com/bobocode-projects/resources/master/image/logo_transparent_background.png" height=50/>Java Functional Programming 
&nbsp;
### Master Java functional features and write more consice and easy to parallel code 💪
&nbsp;

### Why should you care?
⚡️ Nowadays **every Java developer uses some functional programming features in an everyday job**. So make sure you've 
checked out the rest of the materials in this module and build strong skills using these language features! 

The main idea is to keep Java OO language but **enable some functional programming features**. It does two things:
* makes the code more concise
* allows easier parallelization

Java SE 8+ provides a rich API that enables functional programming features based on
* Functional Interfaces
* Lambdas
* Stream API
* Optional API

### At the end of this module you will be able to
Write this
```java
public List<Account> findAllGmailAccounts(List<Account> accounts) {
        return accounts.stream()
                .filter(a -> a.getEmail().endsWith("@gmail.com"))
                .collect(toList());
}
```
instead of this
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
Among other you will be able to
* use **Funtional Interfaces** and **Lambdas** in order to **pass around functions** like first-class citizens ✅
* **process data collections** in a **concise** and **easy way to understand** using **Stream API** ✅
* write **null-safe code** using **Optional API** ✅

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
