package com.bobocode.util;

/**
 * This is a custom exception that we throw in every method which should be implemented as a part of the exercise.
 * If you see that it was thrown it means that you did not implement all required methods yet.
 */
public class ExerciseNotCompletedException extends RuntimeException {
    public ExerciseNotCompletedException() {
        super("Implement this method and remove exception OR switch to branch completed if you got stuck.");
    }
}
