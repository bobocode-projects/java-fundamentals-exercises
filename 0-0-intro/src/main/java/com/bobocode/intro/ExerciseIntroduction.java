package com.bobocode.intro;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.Base64;

/**
 * Welcome! This is an introduction exercise that will show you a simple example of Bobocode exercises.
 * <p>
 * JavaDoc is a way of communication with other devs. We use Java Docs in every exercise to define the task.
 * So PLEASE MAKE SURE you read the Java Docs carefully.
 * <p>
 * Every exercise is covered with tests, see {@link ExerciseIntroductionTest}.
 * <p>
 * In this repo you'll find dozens of exercises covering various fundamental topics.
 * They all have the same structure helping you to focus on practice and build strong skills!
 *
 * @author Taras Boychuk
 */
public class ExerciseIntroduction {
    /**
     * This method returns a very important message. If understood well, it can save you years of inefficient learning,
     * and unlock your potential!
     *
     * @return "The key to efficient learning is practice!"
     */
    public String getWelcomeMessage() {
        return "The key to efficient learning is practice!";
    }

    /**
     * Method encodeMessage accepts one {@link String} parameter and returns encoded {@link String}.
     * <p>
     * PLEASE NOTE THAT YOU WILL GET STUCK ON THIS METHOD INTENTIONALLY! ;)
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
