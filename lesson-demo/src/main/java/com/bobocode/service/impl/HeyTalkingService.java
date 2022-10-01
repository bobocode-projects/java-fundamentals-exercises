package com.bobocode.service.impl;

import com.bobocode.service.TalkingService;
import org.springframework.stereotype.Service;

@Service
public class HeyTalkingService implements TalkingService {
    @Override
    public void talk() {
        System.out.println("Hey");
    }
}
