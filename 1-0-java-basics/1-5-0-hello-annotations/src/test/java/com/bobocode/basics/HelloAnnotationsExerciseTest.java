package com.bobocode.basics;


import lombok.SneakyThrows;
import org.junit.jupiter.api.*;

import java.lang.annotation.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HelloAnnotationsExerciseTest {

    @Test
    @Order(1)
    @DisplayName("Annotation @Exercise exists")
    void exerciseAnnotationExists() {
        assertThatCode(this::getExerciseAnnotation).doesNotThrowAnyException();
    }

    @Test
    @Order(2)
    @DisplayName("@Exercise can be applied to classes and interfaces but not to methods & fields")
    @SneakyThrows
    void exerciseAnnotationCanBeAppliedForClasses() {
        var exerciseAnnotation = getExerciseAnnotation();

        var target = exerciseAnnotation.getAnnotation(Target.class);

        assertThat(target.value()).hasSize(1);
        assertThat(target.value()[0]).isEqualTo(ElementType.TYPE);
    }

    @Test
    @Order(3)
    @DisplayName("@Exercise information is accessible at runtime")
    @SneakyThrows
    void exerciseAnnotationInfoIsAccessibleAtRuntime() {
        var exerciseAnnotation = getExerciseAnnotation();

        var retention = exerciseAnnotation.getAnnotation(Retention.class);

        assertThat(retention.value()).isEqualTo(RetentionPolicy.RUNTIME);
    }

    @Test
    @Order(4)
    @DisplayName("@Exercise has declared value")
    @SneakyThrows
    void exerciseAnnotationHasValueDeclared() {
        var exerciseAnnotation = getExerciseAnnotation();

        assertThatCode(() -> exerciseAnnotation.getDeclaredMethod("value"))
                .doesNotThrowAnyException();
    }

    @Test
    @Order(4)
    @DisplayName("@Exercise has complexityLevel declared")
    @SneakyThrows
    void exerciseAnnotationHasComplexityDeclared() {
        var exerciseAnnotation = getExerciseAnnotation();

        assertThatCode(() -> exerciseAnnotation.getDeclaredMethod("complexityLevel"))
                .doesNotThrowAnyException();
    }

    @Test
    @Order(5)
    @DisplayName("@Exercise complexityLevel is BASIC by default")
    @SneakyThrows
    void exerciseAnnotationComplexityLevelDefaultValue() {
        var exerciseAnnotation = getExerciseAnnotation();

        var complexityLevel = exerciseAnnotation.getDeclaredMethod("complexityLevel");

        assertThat(complexityLevel.getDefaultValue()).isEqualTo(Level.BASIC);
    }

    @Test
    @Order(6)
    @DisplayName("HelloAnnotationExercise is marked as @Exercise with name \"hello-annotation-basic\"")
    @SneakyThrows
    void helloAnnotationExerciseIsAnnotatedWithExercise() {
        var exerciseAnnotationClass = getExerciseAnnotation();
        var basicExerciseAnnotation = HelloAnnotationsExercise.class.getAnnotation(exerciseAnnotationClass);

        var valueMethod = exerciseAnnotationClass.getMethod("value");
        var exerciseName = valueMethod.invoke(basicExerciseAnnotation);

        assertThat(exerciseName).isEqualTo("hello-annotation-basic");
    }

    @SneakyThrows
    private Class<? extends Annotation> getExerciseAnnotation() {
        return Class.forName("com.bobocode.basics.Exercise")
                .asSubclass(Annotation.class);
    }

}
