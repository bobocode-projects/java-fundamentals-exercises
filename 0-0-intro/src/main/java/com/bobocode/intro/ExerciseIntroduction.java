package com.bobocode.intro;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.Base64;

public class ExerciseIntroduction {
    public String getWelcomeMessage() {
        return "The key to efficient learning is practice!";
    }
    public String encodeMessage(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
