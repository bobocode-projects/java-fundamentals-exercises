package com.bobocode.concurrency;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.lang.Thread.State.RUNNABLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

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
    void waitForThreadToComplete() {
        var thread = mock(Thread.class);

        HelloThreads.waitForThreadToComplete(thread);

        assertAll(
            () -> verify(thread, times(1)).start(),
            () -> verify(thread, times(1)).join()
        );
    }

    @SneakyThrows
    @Test
    @Order(6)
    void getListThreads() {
        var thread1 = mock(Thread.class);
        var thread2 = mock(Thread.class);
        var thread3 = mock(Thread.class);

        var threads = HelloThreads.getListThreads(thread1, thread2, thread3);

        assertAll(
            () -> assertThat(threads).hasSize(3),
            () -> verify(thread1, times(1)).start(),
            () -> verify(thread1, times(1)).join(),
            () -> verify(thread2, times(1)).start(),
            () -> verify(thread2, times(1)).join(),
            () -> verify(thread3, times(1)).start(),
            () -> verify(thread3, times(1)).join()
        );
    }
}