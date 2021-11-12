package com.bobocode.basics;

/**
 * {@link HelloAnnotationsExercise} is an exercise class that is marked with be corresponding @{@link Exercise}
 * annotation. The annotation value specifies exercise name "hello-annotation-basic". It does not specify any custom
 * complexity level, because this exercise is a basic, which correspond to the default value provided by annotation.
 * <p>
 * todo: Create an annotation @{@link Exercise}.
 * todo: Set its retention policy so it is visible at runtime
 * todo: Set its target so it can only be applied to a class
 * todo: Add String value that will store exercise name
 * todo: Add complexityLevel with a default {@link Level} basic
 *
 * @author Taras Boychuk
 */
@Exercise("hello-annotation-basic")
public class HelloAnnotationsExercise { // todo: mark class with the annotation according to the javadoc
}
