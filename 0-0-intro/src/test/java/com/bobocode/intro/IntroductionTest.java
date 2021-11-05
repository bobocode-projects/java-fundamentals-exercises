package com.bobocode.intro;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This is a {@link IntroductionTest} that is meant to verify if you properly implement {@link Introduction}. It is a
 * simple example that show how each exercise is organized: a class to implement + test to verify if the implementation
 * is correct.
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
class IntroductionTest {
    private Introduction introduction = new Introduction();
    private String EXPECTED_MESSAGE = "The key to efficient learning is practice!";

    @Test
    @Order(1)
    @DisplayName("getWelcomeMessage method returns correct phrase")
    void getWelcomeMessage() {
        String message = introduction.getWelcomeMessage();

        assertThat(message).isEqualTo(EXPECTED_MESSAGE);
    }

    @Test
    @Order(2)
    @DisplayName("encodeMessage method exists")
    @SneakyThrows
    void encodeMessageExists() {
        var encodeMessageMethod = Arrays.stream(Introduction.class.getDeclaredMethods())
                .filter(method -> method.getName().equals("encodeMessage"))
                .findAny();

        assertThat(encodeMessageMethod).isPresent();
    }

    @Test
    @Order(3)
    @DisplayName("encodeMessage returns correct encoded message")
    @SneakyThrows
    void encodeMessageReturnsCorrectPhrase() {
        var encodeMessageMethod = Arrays.stream(Introduction.class.getDeclaredMethods())
                .filter(method -> method.getName().equals("encodeMessage"))
                .findAny()
                .orElseThrow();

        var encodedMessage = encodeMessageMethod.invoke(new Introduction(), EXPECTED_MESSAGE);

        assertThat(encodedMessage).isEqualTo("VGhlIGtleSB0byBlZmZpY2llbnQgbGVhcm5pbmcgaXMgcHJhY3RpY2Uh");

    }
}
