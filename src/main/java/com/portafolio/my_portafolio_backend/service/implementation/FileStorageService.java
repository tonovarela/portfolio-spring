package com.portafolio.my_portafolio_backend.service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileStorageService  {
    @Value("${file.upload.dir}")
    private String upLoadDir;


    public Optional<String> storeFile(MultipartFile file) {
        if (file.isEmpty()) {
            return Optional.empty();
        }
        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            return Optional.empty();
        }
        int extensionIndex = fileName.lastIndexOf('.');
        if (extensionIndex < 0 || extensionIndex == fileName.length() - 1) {
            return Optional.empty();
        }
        String extension = fileName.substring(extensionIndex + 1);
        String newFileName = UUID.randomUUID() + "." + extension;
        Path path = Paths.get(upLoadDir, newFileName).normalize();
        try {
            Files.createDirectories(path.getParent());
            Files.copy(file.getInputStream(), path);
        }
        catch (IOException e) {

            return Optional.empty();
        }
        return  Optional.of("img/projects/" + newFileName);

    }


}
