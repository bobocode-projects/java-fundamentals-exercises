package com.bobocode.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceService {
    @Autowired
    private List<TalkingService> talkingServiceList;

    public void run() {
        talkingServiceList.forEach(TalkingService::talk);
    }

    public List<TalkingService> getTalkingServiceList() {
        return talkingServiceList;
    }
}
