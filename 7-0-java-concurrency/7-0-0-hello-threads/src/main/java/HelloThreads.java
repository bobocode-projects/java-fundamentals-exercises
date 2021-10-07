import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

public class HelloThreads {
    /**
     * Receives a {@link Runnable} parameter, and returns a {@link Thread}.
     * @param runnable the code you want to run in new thread
     * @return a new thread
     */
    public static Thread runningThread(Runnable runnable) {
        return new Thread(runnable);
    }

    /**
     * Receive a {@link Thread} parameter and start it
     * @param thread the code you want to start thread
     */
    public static void runningThreadViaStart(Thread thread) {
        thread.start();
    }

    /**
     * Receives a {@link Thread} parameter, and return a {@link String}
     * @param thread the code you want start thread and return tread name
     * @return the name thread
     */
    public static String runningThreadGetNameThread(Thread thread) {
        thread.start();
        return thread.getName();
    }

    /**
     * Receives a {@link Thread} parameter, and return a {@link Thread.State}
     * @param thread the code you want start thread and return state
     * @return the thread state
     */
    public static Thread.State runningThreadGetStateThread(Thread thread) {
        thread.start();
        return thread.getState();
    }

    /**
     * Receives a {@link Runnable} parameter, create new {@link Thread}, start and return this {@link Thread}
     * @param runnable the code you want to run in new thread and start
     * @return this thread
     */
    public static Thread getSomeLogicRunningTreadAndReturnThisThread(Runnable runnable) {
        var thread = new Thread(runnable);
        thread.start();
        return thread;
    }

    /**
     * Receives a {@link Runnable} parameter, create new {@link Thread}, start and wait when it has completed
     * @param runnable - the code you want to run in new thread and start
     */
    @SneakyThrows
    public static void runningThreadAndWhenJoinCompleted(Runnable runnable) {
        var thread = new Thread(runnable);
        thread.start();
        thread.join();
    }

    /**
     * Receives a {@link Runnable} parameter, create three new {@link Thread} start it,
     * and return list of threads that are in progress
     * @param runnable the code you want to run in new thread and start
     * @return list these threads
     */
    @SneakyThrows
    public static List<Thread> runningMultipleThreadsWithOneTask(Runnable runnable) {
        var thread1 = new Thread(runnable);
        var thread2 = new Thread(runnable);
        var thread3 = new Thread(runnable);

        var threads = List.of(thread1, thread2, thread3)
                .stream()
                .peek(Thread::start)
                .collect(Collectors.toList());
        for (Thread thread : threads) {
            thread.join();
        }
        return threads;
    }
}
