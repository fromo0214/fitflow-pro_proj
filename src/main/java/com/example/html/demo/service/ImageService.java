package com.example.html.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Service
public class ImageService {

    @Value("${image.directory}")
    private String imageDirectory;

    private final Random random = new Random();

    private final String uploadDir ="uploads/";

    public String getRandomImage() {
        File dir = new File(imageDirectory + "/motivation");
        if (dir.isDirectory()) {
            String[] images = dir.list((d, name) -> name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".jpeg"));
            if (images != null && images.length > 0) {
                int index = random.nextInt(images.length);
                return "motivation/" + images[index]; // Return the path relative to the images directory
            }
        }
        return null; // or return a default image
    }


    public String storeFile(MultipartFile file) throws IOException{
        if (file.isEmpty()){
            throw new IOException("Failed to store empty file");
        }
    

        Path destinationFile = Paths.get(uploadDir).resolve(
            Paths.get(file.getOriginalFilename()))
            .normalize().toAbsolutePath();

          if (!destinationFile.getParent().equals(Paths.get(uploadDir).toAbsolutePath())) {
            throw new IOException("Cannot store file outside current directory.");
        }

        Files.copy(file.getInputStream(), destinationFile);
        return destinationFile.toString();

    }
}

