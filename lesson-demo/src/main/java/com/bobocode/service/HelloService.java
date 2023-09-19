package com.bobocode.service;

import com.bobocode.annotation.Autowired;
import com.bobocode.annotation.Component;

@Component
public class HelloService {
    @Autowired
    private MessageService messageService;
    public void hello() {
        System.out.println(messageService.message());
    }
}
