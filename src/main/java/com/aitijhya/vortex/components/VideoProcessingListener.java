package com.aitijhya.vortex.components;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.aitijhya.vortex.events.VideoUploadedEvent;

@Component
public class VideoProcessingListener {
    
    @Async
    @EventListener
    public void processVideo(VideoUploadedEvent event) {
        // create an ai summary for the videos
    }
}
