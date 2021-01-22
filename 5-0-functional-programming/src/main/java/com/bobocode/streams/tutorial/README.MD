# <img src="https://raw.githubusercontent.com/bobocode-projects/resources/master/image/logo_transparent_background.png" height=50/> Stream API tutorial

This is the tutorial on Stream API and functional programming techniques
### Pre-conditions ❗
You're supposed to be familiar with OOP, have basic knowledge of JDK, and be able to write Java code. 
### Related exercises 💪
* [Account analytics](to do)
* [File Reader](to do)
* [File Stats](to do)
### See also 🔽
* [Tutorial on Lambdas](to do)
* [Tutorial on Optional](to do)
##
*Stream API* provide an ability to **process sequences of data elements in a declarative way** and **simplify the task of 
performing operations in parallel**

The simplest example is the task of filtering a collection. Using **imperative** old-style approach we specify **HOW the task 
should be done**. E.g. dealing with iteration, and storing each element in a new `ArrayList`. This way of processing is also 
called *external iteration*. *Stream API* and it's **declarative** approach allows us to specify **WHAT should be done**, without 
actually dealing with iteration and elements. This approach is also called *internal iteration*. 

Imperative style:
```java
        List<Account> gmailAccounts = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getEmail().endsWith("@gmail.com")) {
                gmailAccounts.add(account);
            }
        }
```
Declarative style using *Stream API*:
```java
        List<Account> gmailAccounts = accounts.stream()
                .filter(a -> a.getEmail().endsWith("@gmail"))
                .collect(toList());
```

### Best practices
* **use streams** wheneve it can make the code clear and concise
* use **clear names for lambda parameters** in stream pipelines
* **use helper methods** for better readability
* be carefull using a stream of chars
* **prefer pure functions** for stream operations, **avoid side-effect function**
* always use **static import** for better readability
* **always use `joining()` collector** for `CharSequence` elements to avoid performance issues
* prefer collections and not stream as method return-type
* use **parallel stream only when it's critical**, when you know that it helps, and **always measure it using real data**
* **ALWAYS USE SPECIAL CLASSES FOR PRIMITIVES.** (E.g. `IntStream` instead of `Stream<Integer>`)
