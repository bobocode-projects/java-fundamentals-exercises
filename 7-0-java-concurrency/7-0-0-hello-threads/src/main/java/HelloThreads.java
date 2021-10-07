import com.bobocode.util.ExerciseNotCompletedException;
import lombok.SneakyThrows;

import java.util.List;

public class HelloThreads {
    /**
     * Receives a {@link Runnable} parameter, and returns a {@link Thread}.
     * @param runnable the code you want to run in new thread
     * @return a new thread
     */
    public static Thread runningThread(Runnable runnable) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Receive a {@link Thread} parameter and start it
     * @param thread the code you want to start thread
     */
    public static void runningThreadViaStart(Thread thread) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Receives a {@link Thread} parameter, and return a {@link String}
     * @param thread the code you want start thread and return tread name
     * @return the name thread
     */
    public static String runningThreadGetNameThread(Thread thread) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Receives a {@link Thread} parameter, and return a {@link Thread.State}
     * @param thread the code you want start thread and return state
     * @return the thread state
     */
    public static Thread.State runningThreadGetStateThread(Thread thread) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Receives a {@link Runnable} parameter, create new {@link Thread}, start and return this {@link Thread}
     * @param runnable the code you want to run in new thread and start
     * @return this thread
     */
    public static Thread getSomeLogicRunningTreadAndReturnThisThread(Runnable runnable) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Receives a {@link Runnable} parameter, create new {@link Thread}, start and wait when it has completed
     * @param runnable - the code you want to run in new thread and start
     */
    @SneakyThrows
    public static void runningThreadAndWhenJoinCompleted(Runnable runnable) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Receives a {@link Runnable} parameter, create three new {@link Thread} start it,
     * and return list of threads that are in progress
     * @param runnable the code you want to run in new thread and start
     * @return list these threads
     */
    @SneakyThrows
    public static List<Thread> runningMultipleThreadsWithOneTask(Runnable runnable) {
        throw new ExerciseNotCompletedException();
    }
}
