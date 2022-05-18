package com.bobocode;

public class GreetingService {
    public void hello() {
        System.out.println("Hello");
    }

    @LogInvocation
    public void gloryToUkraine() {
        System.out.println("Slava Ukraini!");
    }
}
