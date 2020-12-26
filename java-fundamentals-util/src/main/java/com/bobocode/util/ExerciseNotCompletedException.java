package com.bobocode.util;

public class ExerciseNotCompletedException extends RuntimeException {
    public ExerciseNotCompletedException() {
        super("Implement this method and remove exception OR switch to branch exercise/completed if you got stuck.");
    }
}
