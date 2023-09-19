package com.bobocode;

import com.bobocode.context.ApplicationContext;
import com.bobocode.service.HelloService;

public class ContextDemoApp {
    
    public static void main(String[] args) {
        var context = new ApplicationContext("com.bobocode.service");
        var helloService = context.getBean(HelloService.class);
        helloService.hello();
    }
    
}
