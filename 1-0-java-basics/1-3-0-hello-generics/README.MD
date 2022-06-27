# <img src="https://raw.githubusercontent.com/bobocode-projects/resources/master/image/logo_transparent_background.png" height=50/>Hello Generics :muscle:

Learn how to do **safe type parametrization** using generic types 💪

### Pre-conditions ❗

You're supposed to be familiar **Java classes** and **casting**

### Objectives

* refactor class `Box` to introduce a generic type ✅
* change the value field type to parametrized (generic) type ✅
* change the **constructor parameter type** to parametrized type ✅
* change the **setter parameter type** to parametrized type ✅
* change the **getter return type** to parametrized type ✅
* refactor `BoxDemoApp` to specify generic type as `Integer` and receive **compile time error** ✅

##

In many cases the logic we implement does not depend on the data type. In other words, **many algorithms are the same
regardless if you apply them to Integer, String or any other types.** So in order to leverage the same logic for different 
types we need some form of type parametrization. Of course, we can use `Object` and store anything we want, but
it is not safe. Using `Object` requires runtime casting which can cause runtime exceptions.

---
#### 🆕 First time here? – [See Introduction](https://github.com/bobocode-projects/java-fundamentals-exercises/tree/main/0-0-intro#introduction)
#### ➡️ Have any feedback? – [Please fill the form ](https://forms.gle/Q5aEmF8E86fbZ4rXA)

##
<div align="center"><img src="https://raw.githubusercontent.com/bobocode-projects/resources/master/animation/GitHub%20Star_3.gif" height=50/></div>
