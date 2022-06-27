# <img src="https://raw.githubusercontent.com/bobocode-projects/resources/master/image/logo_transparent_background.png" height=50/>Crazy Regex

### Pre-conditions ❗
You're supposed to know how to work regex and be able to build Patterns and Matchers

### Objectives
* **build Patterns to extract** necessary parts from text ✅
* **manipulate** extracted text with **Matcher** object ✅

### Regular expressions - sequence of characters that define a search pattern for text

---

There 2 peace pf puzzle:
* Literal characters - I want to match literally the character I specified (like 'a')
* Meta characters - I want to match any character of this kind (more generic/abstract thing)

Single char

* \\d -> 0-9                  
* \\D -> negate of \\d             
* \\w -> A-Za-z0-9        
* \\W -> negate of \\w                           
* \\s -> whitespace, tab  
* \\S -> negate of \\s                         
* .  -> anything but newline              
* \\. -> literal dot                       


Quantifiers - modify single characters how many of them you want match in a row
* \* -> Occurs zero or more times           
* \+ -> 1 or more                           
* ? -> zero or one                         
* {min, max} -> some range
* {n} -> precise quantity


Position
*   ^  -> beginning
*   $  -> end
*  \\b -> word boundary 

---

Character class -> is the thing that appears in between []. For example [abc] -> match 'a' or 'b' or 'c'.
Another example [-.] -> match dash or period. Here . is not meta character anymore and ^ are special characters inside []
* [0-5] -> match all numbers  from 0 to 5. [^0-5] -> match anything that NOT 0-5
BUT it works like meta character only when it on first position, otherwise - its literal, [a^bc] - like this

---

Capturing Groups - whenever u do regex search it matches whole result as a group 0.
* \\d{3}-\\d{3}-\\d{4}  ->  212-555-1234 = GROUP 0

Parentheses can capture a subgroup:
\\d{3}-(\\d{3})-(\\d{4}) where 212-555-1234 = GROUP 0, 555 = GROUP 1, 1234 = GROUP 2

We can refer to this groups by $1 ($ when we want to replace) and \1 (within regex itself referring to capture group 
it's called back reference)

---

#### 🆕 First time here? – [See Introduction](https://github.com/bobocode-projects/java-fundamentals-exercises/tree/main/0-0-intro#introduction)
#### ➡️ Have any feedback? – [Please fill the form ](https://forms.gle/jhXEAzG4TB81S43CA)

##
<div align="center"><img src="https://raw.githubusercontent.com/bobocode-projects/resources/master/animation/GitHub%20Star_3.gif" height=50/></div>