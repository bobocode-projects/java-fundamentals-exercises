import org.junit.jupiter.api.*;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HelloThreadsTest {

    private Queue<Integer> concurrentLinkedQueue;

    @BeforeEach
    void prepare() {
        concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
    }

    @Test
    @Order(1)
    void runningThread() throws InterruptedException {
        Thread runningThread = HelloThreads.runningThread(() -> concurrentLinkedQueue.add(5));
        assertEquals(0, concurrentLinkedQueue.size());

        runningThread.start();
        runningThread.join();

        checkContentQueue();
    }

    @Test
    @Order(2)
    void runningThreadViaStart() throws InterruptedException {
        var thread = new Thread(() -> concurrentLinkedQueue.add(5));
        HelloThreads.runningThreadViaStart(thread);
        thread.join();

        checkContentQueue();
    }

    @Test
    @Order(3)
    void runningThreadGetNameThread() throws InterruptedException {
        var thread = new Thread(() -> concurrentLinkedQueue.add(5), "name");
        var threadName = HelloThreads.runningThreadGetNameThread(thread);
        thread.join();
        assertEquals("name", threadName);

        checkContentQueue();
    }

    @Test
    @Order(3)
    void runningThreadGetStateThread() throws InterruptedException {
        var thread = new Thread(() -> concurrentLinkedQueue.add(5));
        var state = HelloThreads.runningThreadGetStateThread(thread);
        thread.join();
        assertSame(state, Thread.State.RUNNABLE);

        checkContentQueue();
    }

    @Test
    @Order(4)
    void getSomeLogicRunningTreadAndReturnThisThread() throws InterruptedException {
        var thread = HelloThreads.getSomeLogicRunningTreadAndReturnThisThread(new Thread(() -> {
            assertEquals(0, concurrentLinkedQueue.size());
            concurrentLinkedQueue.add(5);
        }));
        thread.join();

        checkContentQueue();
    }

    private void checkContentQueue() {
        assertEquals(1, concurrentLinkedQueue.size());
        assertEquals(5, concurrentLinkedQueue.element().intValue());
    }
}