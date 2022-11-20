package com.bobocode.intro;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This is a {@link ExerciseIntroductionTest} that is meant to verify if you properly implement {@link ExerciseIntroduction}. 
 * It is a simple example that shows how each exercise is organized: todo section + tests.
 * <p>
 * A typical Java test uses JUnit framework to run the test, and may also use some other frameworks for assertions.
 * In our exercises we use JUnit 5 + AssertJ
 * <p>
 * PLEASE NOTE:
 * - annotation @{@link Order} is used to help you to understand which method should be implemented first.
 * - annotation @{@link DisplayName} is used to provide you more detailed instructions.
 *
 * @author Taras Boychuk
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ExerciseIntroductionTest {
    private ExerciseIntroduction exerciseIntroduction = new ExerciseIntroduction();
    private String EXPECTED_MESSAGE = "The key to efficient learning is practice!";

    @Test
    @Order(1)
    @DisplayName("getWelcomeMessage method returns correct phrase")
    void getWelcomeMessage() {
        String message = exerciseIntroduction.getWelcomeMessage();

        assertThat(message).isEqualTo(EXPECTED_MESSAGE);
    }

    @Test
    @Order(2)
    @DisplayName("encodeMessage returns correct encoded message")
    @SneakyThrows
    void encodeMessageReturnsCorrectPhrase() {
        var encodeMessageMethod = Arrays.stream(ExerciseIntroduction.class.getDeclaredMethods())
                .filter(method -> method.getName().equals("encodeMessage"))
                .findAny()
                .orElseThrow();

        var encodedMessage = encodeMessageMethod.invoke(new ExerciseIntroduction(), EXPECTED_MESSAGE);

        assertThat(encodedMessage).isEqualTo("VGhlIGtleSB0byBlZmZpY2llbnQgbGVhcm5pbmcgaXMgcHJhY3RpY2Uh");
    }
}
