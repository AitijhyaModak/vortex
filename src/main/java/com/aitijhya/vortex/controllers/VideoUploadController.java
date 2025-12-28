package com.aitijhya.vortex.controllers;

import com.aitijhya.vortex.entities.MediaAsset;
import com.aitijhya.vortex.repositories.MediaRepository;
import com.aitijhya.vortex.services.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoUploadController {

    private final FileStorageService fileStorageService;
    private final MediaRepository mediaRepository;

    @GetMapping("hello")
    private ResponseEntity<String> greet() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello");
    }


    @PostMapping("/upload")
    private ResponseEntity<MediaAsset> uploadVideo(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            String uniqueName = fileStorageService.saveFile(file);

            MediaAsset asset = new MediaAsset();

            asset.setOriginalFileName(file.getOriginalFilename());
            asset.setSystemFileName(uniqueName);

            MediaAsset savedAsset = mediaRepository.save(asset);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedAsset);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
    }
}
