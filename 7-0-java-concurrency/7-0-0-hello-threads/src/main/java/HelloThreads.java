public class HelloThreads {
    /**
     * Receives a {@link Runnable} parameter, and returns a {@link Thread}.
     * @param runnable the code you want to tun in new thread
     * @return a new thread
     */
    public static Thread runningThread(Runnable runnable) {
        throw new RuntimeException();
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
        throw new RuntimeException();
    }

    /**
     * Receives a {@link Thread} parameter, and return a {@link Thread.State}
     * @param thread the code you want start thread and return state
     * @return the thread state
     */
    public static Thread.State runningThreadGetStateThread(Thread thread) {
        throw new RuntimeException();
    }

    /**
     * Receives a {@link Runnable} parameter, create new {@link Thread}, start and return this {@link Thread}
     * @param runnable the code you want to tun in new thread and start
     * @return this thread
     */
    public static Thread getSomeLogicRunningTreadAndReturnThisThread(Runnable runnable) {
        throw new RuntimeException();
    }
}
