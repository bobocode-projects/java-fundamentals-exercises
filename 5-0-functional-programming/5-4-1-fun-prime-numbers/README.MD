# <img src="https://raw.githubusercontent.com/bobocode-projects/resources/master/image/logo_transparent_background.png" height=50/> Functional programming tutorial

This is the tutorial on functional programming basics
### Pre-conditions ❗
You're supposed to be familiar with OOP, have basic knowledge of JDK, and be able to write Java code. 
### Related exercises 💪
* [Sum of squares](to do)
### See also 🔽
* [Tutorial on Lambdas](to do)
* [Tutorial on Stream API](to do)
* [Tutorial on Optional](to do)
##
The concept of **functional programming** is based on [λ-calculus](https://en.wikipedia.org/wiki/Lambda_calculus). Which
brings the idea of program that **does not use mutable variables**. Why is it so important? Because all race conditions,
deadlock conditions, and concurrent update problem are due to mutable variables.

Various functional languages like [Clojure](https://clojure.org/) have been using this approach for the long time. **Java** 
was initially created as pure OO language, but since **version 8** it offers an ability to use either **OO and functional
 approaches.** 
 
Consider an program that calculates **the sum of first 20 prime numbers**.

Here's an OO-based implementation:

```java
public class OOSumOfPrimes {
    public static void main(String[] args) {
        int sumOfPrimes = 0;
        for (int i = 0, primes = 0; primes < 20; i++) {
            if (isPrime(i)) {
                sumOfPrimes += i;
                primes++;
            }
        }

        System.out.println("Sum of first 20 primes: " + sumOfPrimes);
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
``` 
     
And this is the functional-based implementation:
```java
public class FunctionalSumOfPrimes {
    public static void main(String[] args) {
        IntStream.iterate(0, i -> i + 1)
                .filter(FunctionalSumOfPrimes::isPrime)
                .limit(20)
                .reduce((a, b) -> a + b)
                .ifPresent(sum -> System.out.println("Sum of first 20 primes: " + sum));
    }

    private static boolean isPrime(int n) {
        return IntStream.range(2, n)
                .noneMatch(i -> n % i == 0);
    }
}
```     

As you can see there is **no mutable variables** in the second example.

### Event Sourcing 
Now suppose you have enough resources (like CPU and storage), you can try to make you program functional. For instance, 
instead of updating bank account balance, you can **store each transaction.** When state is required, you can simple 
apply all transactions. This strategy, where you store transactions and not a state is called [**Event Sourcing**](https://martinfowler.com/eaaDev/EventSourcing.html). It 
allows you to **avoid concurrent update issues.**  
