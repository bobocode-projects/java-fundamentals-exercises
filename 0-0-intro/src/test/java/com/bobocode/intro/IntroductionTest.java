package com.bobocode.intro;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This is a {@link IntroductionTest} that is meant to verify if you properly implement {@link Introduction}. It is a
 * simple example that show how each exercise is organized: a class to implement + test to verify if the implementation
 * is correct.
 * <p>
 * A typical Java test uses JUnit framework to run the test, and may also use some other frameworks for assertions.
 * In our exercises we use JUnit 5 + AssertJ
 */
class IntroductionTest {
    private Introduction introduction = new Introduction();

    @Test
    void welcomeMessage() {
        String message = introduction.welcomeMessage();

        assertThat(message).isEqualTo("The key to efficient learning is practice!");
    }
}
