package com.bobocode.concurrency;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.State.RUNNABLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HelloThreadsTest {

    private Queue<Integer> concurrentLinkedQueue;

    @BeforeEach
    void setup() {
        concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
    }

    @SneakyThrows
    @Test
    @Order(1)
    void createThread() {
        var thread = HelloThreads.createThread(() -> concurrentLinkedQueue.add(5));

        thread.start();

        assertAll(
            () -> assertThat(concurrentLinkedQueue).hasSize(1),
            () -> assertThat(concurrentLinkedQueue.element().intValue()).isEqualTo(5)
        );
    }

    @SneakyThrows
    @Test
    @Order(2)
    void startThread() {
        var thread = new Thread(() -> concurrentLinkedQueue.add(5));

        HelloThreads.startThread(thread);

        assertAll(
            () -> assertThat(concurrentLinkedQueue).hasSize(1),
            () -> assertThat(concurrentLinkedQueue.element().intValue()).isEqualTo(5)
        );
    }

    @SneakyThrows
    @Test
    @Order(3)
    void getThreadName() {
        var thread = new Thread(() -> concurrentLinkedQueue.add(5), "Hello Thread");

        var name = HelloThreads.getThreadName(thread);

        assertThat(name).isEqualTo("Hello Thread");
    }

    @SneakyThrows
    @Test
    @Order(4)
    void getThreadState() {
        var thread = new Thread(() -> concurrentLinkedQueue.add(5));

        var state = HelloThreads.getThreadState(thread);

        assertThat(state).isEqualTo(RUNNABLE);
    }

    @SneakyThrows
    @Test
    @Order(5)
    void runInNewThreadAndWaitForExecution() {
        HelloThreads.runInNewThreadAndWaitForExecution(new Thread(() -> {
            concurrentLinkedQueue.add(5);
            assertThat(concurrentLinkedQueue).hasSize(0);
        }));

        assertAll(
            () -> assertThat(concurrentLinkedQueue).hasSize(1),
            () -> assertThat(concurrentLinkedQueue.element().intValue()).isEqualTo(5)
        );
    }

    @SneakyThrows
    @Test
    @Order(6)
    void runInMultipleThreads() {
        var threads = HelloThreads.runInMultipleThreads(
            new Thread(() -> concurrentLinkedQueue.add(ThreadLocalRandom.current().nextInt(100))),
            new Thread(() -> concurrentLinkedQueue.add(ThreadLocalRandom.current().nextInt(100))),
            new Thread(() -> concurrentLinkedQueue.add(ThreadLocalRandom.current().nextInt(100)))
        );

        assertAll(
            () -> assertThat(concurrentLinkedQueue).hasSize(3),
            () -> assertThat(threads).hasSize(3)
        );
    }
}