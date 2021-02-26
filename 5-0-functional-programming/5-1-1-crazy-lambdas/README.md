# <img src="https://raw.githubusercontent.com/bobocode-projects/resources/master/image/logo_transparent_background.png" height=50/>Lambda exercises :muscle:
Improve your lambda skills
### Task
Every package contains **an exercise (interactive tutorial) on lambdas**, where you need to write code in order 
to complete the implementation. If you need more theory, you can **check [tutorial section](#tutorial)**. 
To verify your solution, you should **run a corresponding test :arrow_forward:.** 

### Pre-conditions :heavy_exclamation_mark:
You're supposed to be familiar with Java 8

### How to start :question:
* Just clone the repository and start implementing the **todo** section, verify your changes by running tests
* If you don't have enough knowledge about this domain, check out the [links below](#related-materials-information_source)
* Don't worry if you got stuck, checkout the **exercise/completed** branch and see the final implementation

### Tutorial 
Java is an OOP language, so it always works with classes and **doesn't support standalone functions**. 
In case you want to **pass some function as a method parameter**, or **store some code into a variable**, 
you should use a *Functional Interface* and a *Lambda expression*.

* A *Functional Interface (FI)* represents a **function signature**. It contains only one abstract method.
* A *Lambda expression* represents a **function body**. Is an anonymous function that implements the abstract method 
  of the functional interface

The purpose of the lambda and functional interfaces is to **make it easier to create function objects** 
and provide an **ability to use some functional programming technics in Java.**

A typical example is interface `Comparator<T>`:

```java
        accounts.sort(new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        });
```
It can be easily simplified using lambda expression:
```java
        accounts.sort((a1, a2) -> a1.getFirstName().compareTo(a2.getFirstName()));
```
In case you are calling some existing method inside the lambda, you can reference that method instead of actually 
calling it. This technique is called *Method Reference*. Combining it with usefull default method `comparing()` 
it can help you to simplify the code even more:
```java
        accounts.sort(comparing(Account::getFirstName));
```

### Best practices
* use **lambdas instead of anonymous classes**
* **avoid lambda parameter types**, unless it can improve code readability
* **keep lambda expression small** (1 line is the best option)
* **always use `@FunctionalInterface` annotation** for custom functional interfaces
* **prefer standard predefined functional interfaces** (`java.util.function`)
* create a **custom FI**, in case it has some **specific contract**, and you can **benefit from self-descriptive name** and **default methods**
* **use special FI for primitives** (e.g. `IntToDoubleFunction` instead of `Function<Integer, Double>`)
* **prefer method reference** in all cases where it helps to improve readability

### Related materials :information_source:
* [State of lambda (JSR 335)](http://htmlpreview.github.io/?https://github.com/bobocode-projects/resources/blob/master/java8/lambda/sotl.html)
* [Modern Java in Action](https://amzn.to/2KwUKW5) :green_book:
    * Passing code with behaviour parameterization - Chapter 2
    * Lambda expressions - Chapter 3
* [Effective Java 3rd Edition](https://amzn.to/3mYA0U1) :blue_book:
    * Prefer Lambdas to anonymous classes – Item 42
    * Prefer method reference to lambdas - Item 43
    * Favor the use of standard functional interfaces - Item 44
