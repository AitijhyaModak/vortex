package com.aitijhya.vortex.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aitijhya.vortex.exceptions.InvalidFileType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path uploadDirPath;
    private final String MP4_EXTENSION = ".mp4";

    public FileStorageService(@Value("${vortex.upload-dir}") String dirPath) {
        uploadDirPath = Path.of(dirPath);
        init();
    }

    private void init() {
        try {
            Files.createDirectories(uploadDirPath);
        }

        catch (IOException e) {
            throw new RuntimeException("Failed to create file directory: " + e.getMessage());
        }
    }

    private String getFileExtension(String name) {
        return name.substring(name.lastIndexOf("."));
    }

    public String saveFile(MultipartFile file) throws InvalidFileType {
        try {
            String name = file.getOriginalFilename();
            String extension = getFileExtension(name);

            if (!MP4_EXTENSION.equals(extension)) throw new InvalidFileType();

            String uniqueName = UUID.randomUUID() + extension;
            Path targetPath = uploadDirPath.resolve(uniqueName);

            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            return uniqueName;
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to copy file to hard disk: " + e.getMessage());
        }
    }
}
