package com.bobocode.lambdas.tutorial;

public class LambdaRunnableExample {
    public static void main(String[] args) {
        sayHelloFromNewThread();
        sayHelloFromNewThreadUsingLambda();
    }


    private static void sayHelloFromNewThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from new Thread!");
            }
        };
        Thread t = new Thread(runnable);
        t.start();
    }

    private static void sayHelloFromNewThreadUsingLambda() {
        Runnable runnable = () -> System.out.println("Hello Lambda!");
        Thread t = new Thread(runnable);
        t.start();
    }
}
