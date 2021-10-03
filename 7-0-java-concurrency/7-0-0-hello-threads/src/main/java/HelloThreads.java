import lombok.SneakyThrows;

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

    // accept a list of tasks (Runnable) and return a list of threads that are in progress
    @SneakyThrows
    public static void runningMultipleThreadsWithOneTask(Runnable runnable) {
        var thread1 = new Thread(runnable);
        var thread2 = new Thread(runnable);
        var thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }

    // accept a thread and make it sleep
    @SneakyThrows
    public static void runningThreadAndMakeItSleep(Thread thread) {

//        thread.start();
//        Thread.sleep(10000);
//        thread.join();
    }
}
