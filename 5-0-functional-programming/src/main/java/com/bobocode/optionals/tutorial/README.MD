# <img src="https://raw.githubusercontent.com/bobocode-projects/resources/master/image/logo_transparent_background.png" height=50/> Optional tutorial

This is the tutorial on `java.util.Optional<T>`
### Pre-conditions ❗
You're supposed to be familiar with OOP, have basic knowledge of JDK, and be able to write Java code. 
### Related exercises 💪
* 
### See also 🔽
* [Tutorial on Lamdbas](to do)
* [Tutorial on Stream API](to do)
##

When writing a **method that is not able to return a value** in some cases, there are three options:
* return `null`
* throw an exception
* return `Optinonal<T>`

Returning `null` is obliously the most **error prone** approach. If you want to be safe, you should somehow **force clients** of your 
method **to handle the possible lack of value**. For that purpose you can use **checked exceptoins**. Although this approach is 
safe it has two disatvantages: thowing exceptoin is an **expansive operatoin**, and it brings **addtional boilerplate**. The 
better approach is to use `Optional<T>`, which is **safe** and **concise**.

`Optional<T>` is a **like a collection that can hold at most one value**. It provides usefull methods to **replace imperative 
old-style null-checks**. It also contains convenient **methods for working with Stream API**. In general, Optional API follows the 
similar functional-style approach as streams. 

### Best practices
* **prefer Optional-returning method** in case the lack of value is possible
* **return Optional from methods** (e.g. getter), **do not wrap instance fields**
* prefer methods like `Optional#ifPresent()` over imperative `if` statements
* **never return `null`** from an Optional-returning method
* always use special supplier-based method `Optional#orElseGet()` in case it is expensive to compute default value
* do not wrap with optional arrays, collections, streams, and maps
* **use special classes for primitive types** (e.g. `OptionalInt`)
* **avoid using Optional for performance-critical cases**

