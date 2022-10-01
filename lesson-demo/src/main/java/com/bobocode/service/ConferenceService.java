package com.bobocode.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConferenceService {
    private final List<TalkingService> talkingServiceList;

    public void runConference() {
        talkingServiceList.forEach(TalkingService::talk);
    }
}
