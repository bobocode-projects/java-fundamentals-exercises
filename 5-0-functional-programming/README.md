# <img src="https://raw.githubusercontent.com/bobocode-projects/resources/master/image/logo_transparent_background.png" height=50/>Java Functional Programming Basics 💪
Learn Java functional programming

>*We can develop a set of consistent questions that each person should ask when start learning new topics.*

For instance:
###When you(I) can encounter with it?

###Our answer:
Today, you can face with functional programing every minute of your work. This approach is must have in modern development world.

###Why you(I) should be prepared for it?

###Our answer:
```java
accounts.stream()
        .filter(a -> a.getEmail().endsWith("@gmail.com"))
        .collect(toList());
```
Code like this one is very popular and is used very often so understanding it and using it is crucial if you want to be a pro.

###What benefits can you(I) got if you(I) spend time on it?

###Our answer:
The popularity of functional programming is based on simplifying and reducing of code also it makes code more legible.
Collaboration of OOP and functional programing is the most powerful approach today, and you have a chance to use it.

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

###What benefits can you have if you(I) learn it deeply?

It's a secret... 👀️