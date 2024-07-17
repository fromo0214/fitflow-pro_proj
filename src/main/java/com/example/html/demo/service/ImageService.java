package com.example.html.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Random;

@Service
public class ImageService {

    @Value("${image.directory}")
    private String imageDirectory;

    private final Random random = new Random();

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
}
