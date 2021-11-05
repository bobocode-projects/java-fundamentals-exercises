package com.bobocode.intro;

import java.util.Base64;

/**
 * Welcome! This is an introduction class that will show you a simple example of Bobocode exercise.
 * <p>
 * JavaDoc is a way of communication with other developers. We use Java Docs in every exercise in order to help you
 * understand how to implement each method. So PLEASE MAKE SURE you read the Java Doc when start implementing a method.
 * <p>
 * Every exercise is covered with tests. So when you're implementing some class like {@link Introduction}, you should
 * also be able to find a corresponding class like {@link IntroductionTest}.
 * <p>
 * Throughout the course you will need to implement hundreds of methods. And this is a simple example of how it's all
 * organized. You read the Java Doc, implement a method and run the test.
 *
 * @author Taras Boychuk
 */
public class Introduction {
    /**
     * This method returns a very important message, that if understood can save you years and
     * unlock infinite opportunities.
     *
     * @return "The key to efficient learning is practice!"
     */
    public String getWelcomeMessage() {
        return "The key to efficient learning is practice!";
    }

    /**
     * Method encodeMessage accepts one {@link String} parameter and returns encoded {@link String}.
     * <p>
     * PLEASE NOTE that you will get stuck on this method intentionally.
     * <p>
     * Every exercise has a completed solution that is stored in the branch "completed". So in case you got stuck
     * and don't know what to do, go check out completed solution.
     *
     * @param message input message
     * @return encoded message
     */
    public String encodeMessage(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
