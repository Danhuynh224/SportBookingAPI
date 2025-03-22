package com.example.apiproject.service;

import com.example.apiproject.entity.SportsFacility;
import com.example.apiproject.repository.SportsFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.nio.file.*;


@Service
public class ImageService {

    @Autowired
    SportsFacilityRepository sportsFacilityRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

    public String uploadImage(Long id, byte[] bytes, String fileName) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, bytes, StandardOpenOption.CREATE);

        String imageUrl = "http://localhost:8080/images/" + fileName;
        SportsFacility sportsFacility = sportsFacilityRepository.findBySportsFacilityId(id);
        sportsFacility.setImg(fileName);
        sportsFacilityRepository.save(sportsFacility);
        return imageUrl;
    }

//    public Resource getImage(String id) {
//    }
}
