package com.bobocode.spring;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Order(2)
@Service
public class GreetingService implements TalkingService{
    @Override
    public void talk() {
        System.out.println("How are you?");
    }
}
