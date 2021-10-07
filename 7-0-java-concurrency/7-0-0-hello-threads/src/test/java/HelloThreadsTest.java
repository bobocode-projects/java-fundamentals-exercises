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
    void prepare() {
        concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
    }

    @SneakyThrows
    @Test
    @Order(1)
    void runningThread() {
        Thread runningThread = HelloThreads.runningThread(() -> concurrentLinkedQueue.add(5));
        assertEquals(0, concurrentLinkedQueue.size());

        runningThread.start();
        runningThread.join();

        checkContentQueue();
    }

    @SneakyThrows
    @Test
    @Order(2)
    void runningThreadViaStart() {
        var thread = new Thread(() -> concurrentLinkedQueue.add(5));
        HelloThreads.runningThreadViaStart(thread);
        thread.join();

        checkContentQueue();
    }

    @SneakyThrows
    @Test
    @Order(3)
    void runningThreadGetNameThread() {
        var thread = new Thread(() -> concurrentLinkedQueue.add(5), "name");
        var threadName = HelloThreads.runningThreadGetNameThread(thread);
        thread.join();
        assertEquals("name", threadName);

        checkContentQueue();
    }

    @SneakyThrows
    @Test
    @Order(3)
    void runningThreadGetStateThread() {
        var thread = new Thread(() -> concurrentLinkedQueue.add(5));
        var state = HelloThreads.runningThreadGetStateThread(thread);
        thread.join();
        assertSame(state, Thread.State.RUNNABLE);

        checkContentQueue();
    }

    @SneakyThrows
    @Test
    @Order(4)
    void getSomeLogicRunningTreadAndReturnThisThread()  {
        var thread = HelloThreads.getSomeLogicRunningTreadAndReturnThisThread(new Thread(() -> {
            assertEquals(0, concurrentLinkedQueue.size());
            concurrentLinkedQueue.add(5);
        }));
        thread.join();

        checkContentQueue();
    }

    @SneakyThrows
    @Test
    @Order(5)
    void runningThreadAndWhenJoinCompleted()  {
        HelloThreads.runningThreadAndWhenJoinCompleted(new Thread(() -> {
            assertEquals(0, concurrentLinkedQueue.size());
            concurrentLinkedQueue.add(5);
        }));

        checkContentQueue();
    }

    @SneakyThrows
    @Test
    @Order(6)
    void runningMultipleThreadsWithOneTask()  {
        HelloThreads.runningMultipleThreadsWithOneTask(new Thread(() ->
                concurrentLinkedQueue.add(ThreadLocalRandom.current().nextInt(100))));
        assertEquals(3, concurrentLinkedQueue.size());
    }

    private void checkContentQueue() {
        assertEquals(1, concurrentLinkedQueue.size());
        assertEquals(5, concurrentLinkedQueue.element().intValue());
    }
}