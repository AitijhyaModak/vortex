package com.aitijhya.vortex.services;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.aitijhya.vortex.events.VideoUploadedEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AISummaryService {
    
    @EventListener
    @Async
    public void generateAISummary(VideoUploadedEvent event) {
        try {
            log.info("hello");
            Thread.sleep(7000);
            String mock = "This is a mock summary";
            log.info(mock);
        }
        catch (Exception e) {
            log.error("failed to generate summary");
        }
    }
}
