# Contributing to Java Fundamentals Course

Thank you for taking the time to contribute to the open-source education! 👏

Before moving forward, please **make sure that you understand what is an exercise and how this repository is organized.** The best place to learn about that is 
[Introduction module](https://github.com/bobocode-projects/java-fundamentals-exercises/tree/main/0-0-intro#introduction) 🤓

## Branching strategy

In this repository we maintain **two key branches** [main](https://github.com/bobocode-projects/java-fundamentals-exercises) and 
[completed](https://github.com/bobocode-projects/java-fundamentals-exercises/tree/completed). Since the `main` branch stores the exercises, and the `completed` stores the 
completed solution, the latter is always ahead of the first one. Here's the rule:

#### The [Completed Solution PR](https://github.com/bobocode-projects/java-fundamentals-exercises/pull/33) should always show only those changes that participats implement in the scope of the exercises❗️

It means two things:
* any changes that are related to the exercise task, the tests, or the project configuration should be added first to the `main` branch and then merged from `main` to `completed`
* only changes related to the completed solution should be added to the `completed` branch

## Creating a Pull Request

### PR with a small fix

If case you found a mistake and want to provide a fix, you can easily create a PR with corresponding changes. 
Please make sure that you have read [the branching strategy](#branching-strategy) so your PR targets to the right place.

### Double pull request
In case you need to **change both the exercise and the completed solution**, you will need to create **two pull requests.** In order to follow [the branching strategy](#branching-strategy),
you will need to do some **extra work with branches** ⚠️. 

Imagine you need to add a new `methodX()` to the exercise. Here's the list of actions you will need to do:
1. Create a local branch from `main` (let's call it `exercise branch`)
2. Add `methodX()` that throws `ExerciseNotCompletedException`, and a corresponding `testMethodX()`
3. Commit these changes to the `exercise branch`
4. Push the changes
5. Checkout `completed`
6. Create a local branch from `completed` (let's call it `solution branch`)
7. Merge your `exercise branch` into the `solution branch`
8. Implement `methodX()` and make sure that tests pass
9. Commit these changes to the `solution branch`
10. Push the changes

**If everything is fine, then you can create two pull requests:**

11. Create a pull request from the `exercise branch` to the `main`
12. Create a pull request from the `solution branch` to the `completed`

### Branching naming convention 
A branch name should start from the ticket or issue number if one is available, following the short changes summary. Like this:

* ```101-insane-lambdas-exercise```

In case the branch targets `completed` it should have a corresponding suffix. Like this:

* ```101-insane-lambdas-exercise-completed```

### Commits
* In case you are working on the JIRA ticket, or the GitHub issue, please make sure that you always add its full number to every commit message
* Please provide a descriptive messages
* Use squash and rebase to avoid redundant commits

### Authors
* if you created a new exercise, please specify your name in the javadoc as @author
* if you did a substantial change in the class or added new tests, please add you name to the list of authors


## Submitting an issue
In case you have faced **an issue that you couldn't resolve** and/or you have **a good idea how this repository can be improved**, feel free to [submit an issue](https://github.com/bobocode-projects/java-fundamentals-exercises/issues/new).

Please note that **if you just have a question, you can ask it in one of the three different ways** depending on the quesiton:
* add you comment to the [Completed PR](https://github.com/bobocode-projects/java-fundamentals-exercises/pull/33#issue-790887000) – if you want to ask about the completed solution
* [start a new discussion](https://github.com/bobocode-projects/java-fundamentals-exercises/discussions/new) under Q&A – if you have question related to this repo
* contact us via info@bobocode.com – if you have other questions
