package com.bobocode.concurrency;

import com.bobocode.util.ExerciseNotCompletedException;
import lombok.SneakyThrows;

import java.util.List;

import static java.lang.Thread.State;

/**
 * {@link HelloThreads} provide a simple API for working with Java threads. A {@link Thread} class represents a key
 * concept in Java Concurrency. So this class is meant to teach you the basics like creating a thread or checking its
 * state.
 * <p>
 * todo: implement each method according to javadoc and verify your impl by running {@link HelloThreadsTest}
 *
 * @author Taras Kuzub
 */
public class HelloThreads {

    /**
     * Receives a {@link Runnable} parameter that holds the logic and creates a {@link Thread} instance based on it.
     *
     * @param runnable the code you want to run in new thread
     * @return a new thread
     */
    public static Thread createThread(Runnable runnable) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Receives a {@link Thread} parameter created based on {@link Runnable},
     *
     * @param thread the code you want to run
     */
    public static void startThread(Thread thread) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Receives a {@link Thread} parameter created based on {@link Runnable},
     * and return {@link String} that contains the name this thread
     *
     * @param thread the code you want run
     * @return the name thread
     */
    public static String getThreadName(Thread thread) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Receives a {@link Thread} parameter created based on {@link Runnable},
     * and return a {@link State} that contains the state this thread
     *
     * @param thread the code you want run
     * @return the thread state
     */
    public static State getThreadState(Thread thread) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Receives a {@link Thread} parameter created based on {@link Runnable},
     * start and wait when thread completed
     *
     * @param thread the code you want run
     */
    @SneakyThrows
    public static void waitForThreadToComplete(Thread thread) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Receives three {@link Thread} parameters created based on {@link Runnable}
     * start all threads and wait when all threads completed.
     * You should return {@link List} of threads that have already been completed
     *
     * @param thread1 the code you want run
     * @param thread2 the code you want run
     * @param thread3 the code you want run
     *
     * @return the list of running threads
     */
    @SneakyThrows
    public static List<Thread> getListThreads(Thread thread1, Thread thread2, Thread thread3) {
        throw new ExerciseNotCompletedException();
    }
}

