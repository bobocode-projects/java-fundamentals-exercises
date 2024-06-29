package com.bobocode.spring;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Order(1)
@Service
public class HelloService implements TalkingService{
    @Override
    public void talk() {
        System.out.println("Hello");
    }
}
