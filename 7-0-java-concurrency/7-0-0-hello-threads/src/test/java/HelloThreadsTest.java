import lombok.SneakyThrows;
import org.junit.jupiter.api.*;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

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
        Thread runningThread = HelloThreads.createThread(() -> concurrentLinkedQueue.add(5));
        assertEquals(0, concurrentLinkedQueue.size());

        runningThread.start();
        runningThread.join();

        verifyContentQueue();
    }

    @SneakyThrows
    @Test
    @Order(2)
    void startThread() {
        var thread = new Thread(() -> concurrentLinkedQueue.add(5));
        HelloThreads.startThread(thread);
        thread.join();

        verifyContentQueue();
    }

    @SneakyThrows
    @Test
    @Order(3)
    void getThreadName() {
        var thread = new Thread(() -> concurrentLinkedQueue.add(5), "name");
        var threadName = HelloThreads.getThreadName(thread);
        thread.join();
        assertEquals("name", threadName);

        verifyContentQueue();
    }

    @SneakyThrows
    @Test
    @Order(3)
    void getThreadState() {
        var thread = new Thread(() -> concurrentLinkedQueue.add(5));
        var state = HelloThreads.getThreadState(thread);
        thread.join();
        assertSame(state, Thread.State.RUNNABLE);

        verifyContentQueue();
    }

    @SneakyThrows
    @Test
    @Order(4)
    void runInNewThread() {
        var thread = HelloThreads.runInNewThread(new Thread(() -> {
            assertEquals(0, concurrentLinkedQueue.size());
            concurrentLinkedQueue.add(5);
        }));
        thread.join();

        verifyContentQueue();
    }

    @SneakyThrows
    @Test
    @Order(5)
    void runInNewThreadAndWaitForExecution() {
        HelloThreads.runInNewThreadAndWaitForExecution(new Thread(() -> {
            assertEquals(0, concurrentLinkedQueue.size());
            concurrentLinkedQueue.add(5);
        }));

        verifyContentQueue();
    }

    @SneakyThrows
    @Test
    @Order(6)
    void runInMultipleThreads() {
        HelloThreads.runInMultipleThreads(new Thread(() ->
                concurrentLinkedQueue.add(ThreadLocalRandom.current().nextInt(100))));
        assertEquals(3, concurrentLinkedQueue.size());
    }

    private void verifyContentQueue() {
        assertEquals(1, concurrentLinkedQueue.size());
        assertEquals(5, concurrentLinkedQueue.element().intValue());
    }
}