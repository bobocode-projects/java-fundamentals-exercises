# <img src="https://raw.githubusercontent.com/bobocode-projects/resources/master/image/logo_transparent_background.png" height=50/>Java Functional Programming Basics :muscle:
Learn Java functional programming

### Into
You know that **Java is an Object-Oriented programming language**, we use objects everywhere (except primitives 😉) and
**functions are not first-class citizens**. So you **cannot store function as variables, pass as arguments or return
as method results❗️**

Yes, however... 🙂

Having functions as **first-class citizens is useful!** When building real application quite often **you need to pass
some logic around as function.** But in Java you have to create an object, usually anonymous.

### Case Study: Search by criteria
Suppose you have a list of accounts, and you need to build a search method that will allow you to find account by
a certain criteria. In java, you will need to create something like this
```java
interface Criteria<T> {
    boolean passed(T obj);
}
```
then you can use that in your search method like this:
```java
public List<Account> searchBy(Criteria criteria){
    List<Account> foundAccount = new ArrayList<>();
    for (Account a: accounts){
        if (criteria.passed(a)){
            foundAccount.add(a);
        }
    }
    return foundAccounts;
}
```
Agree? Then if you want to use this method, it will look like this
```java
searchBy(new Criteria<Account>() {
    @Override
    public boolean passed(Account a) {
        return a.getEmail().endsWith("@gmail.com");
    }
});
```
Most probably you've seen similar examples where you pass a `Runnable` implementation to execute some logic in
a new thread, or you pass `Comparator` implementation to sort a list of objects. That's why having a function
as first-class citizens is not a bad idea. In all cases mentioned **we pass objects, but what we want to pass
is a function❗️** How would passing a function look like? 🤔 Maybe something like
```java
searchBy(a -> a.getEmail().endsWith("@gmail.com"));
```
### Summary
The main idea is to keep Java OO language but **enable some functional programming features**. It does two things:
* makes the code more concise
* allows easier parallelization

Java SE 8+ provides a rich API that enables functional programming features based on
* Functional Interfaces
* Lambdas
* Stream API
* Optional API

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