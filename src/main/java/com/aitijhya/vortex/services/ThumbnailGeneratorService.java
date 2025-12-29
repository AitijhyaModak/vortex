package com.aitijhya.vortex.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.aitijhya.vortex.entities.MediaAsset;
import com.aitijhya.vortex.events.VideoUploadedEvent;
import com.aitijhya.vortex.repositories.MediaRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ThumbnailGeneratorService {
    private final MediaRepository mediaRepository;

    @Async
    @EventListener
    public void generateThumbnail(VideoUploadedEvent event) {
        log.info("Generating thumbnail for uploaded video: " + event.getMediaAsset().getOriginalFileName());
        try {
            Path path = Path.of("vortex-data/videos/" + event.getMediaAsset().getSystemFileName());
            Path thumbnailDir = Path.of("vortex-data/thumbnails");

            Files.createDirectories(thumbnailDir);
            Path thumbnailImagePath = thumbnailDir.resolve(event.getMediaAsset().getSystemFileName() + ".png");

            String[] command = {
                "ffmpeg", "-y", "-i", path.toAbsolutePath().toString(), 
                "-ss", "00:00:01.000", "-vframes", "1", 
                "-q:v", "2", thumbnailImagePath.toAbsolutePath().toString()
            };
            
            Process process = new ProcessBuilder(command).start();
            int exitCode = process.waitFor();

            MediaAsset mediaAsset = event.getMediaAsset();
            mediaAsset.setThumbnailName(event.getMediaAsset().getSystemFileName() + ".png");

            mediaRepository.save(mediaAsset);

            if (exitCode == 0) log.info("Video thumbnail successfully saved");
            else log.error("Failed to create video thumbnail");
        }

        catch (IOException e) {
            log.info(e.getMessage());
        }

        catch (InterruptedException e) {
            log.info(e.getMessage());
        }
    }
}
