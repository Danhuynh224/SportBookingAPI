package com.example.apiproject.service;

import com.example.apiproject.entity.Post;
import com.example.apiproject.entity.SportsFacility;
import com.example.apiproject.repository.PostRepository;
import com.example.apiproject.repository.SportsFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    @Autowired
    SportsFacilityRepository sportsFacilityRepository;

    @Autowired
    PostRepository postRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

    private static final String POST_IMAGE_DIR = "src/main/resources/static/images/post/";

    public String uploadImage(String name, byte[] bytes, String fileName) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, bytes, StandardOpenOption.CREATE);

        String imageUrl = "http://localhost:8080/images/" + fileName;
        SportsFacility sportsFacility = sportsFacilityRepository.findByName(name);
        sportsFacility.setImg(fileName);
        sportsFacilityRepository.save(sportsFacility);
        return imageUrl;
    }

    public Resource getImage(Long id) throws IOException {
        SportsFacility sportsFacility = sportsFacilityRepository.findBySportsFacilityId(id);
        String fileName = sportsFacility.getImg();
        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() || resource.isReadable()) {
            System.out.println(filePath.toString());
            return resource;
        } else {
            throw new IOException("Image not found: " + fileName);
        }
    }

    // Phương thức upload nhiều ảnh
    public List<String> uploadPostImages(Long postId, MultipartFile[] files) throws IOException {
        List<String> fileNames = new ArrayList<>();
        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(POST_IMAGE_DIR + fileName);
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE);

                fileNames.add(fileName);
                imageUrls.add("http://localhost:8080/images/post/" + fileName);
            }
        }

        Post post = postRepository.findByIdPost(postId);
        if (post != null) {
            String existingImages = post.getImg();
            String newImages = String.join(",", fileNames);
            if (existingImages != null && !existingImages.isEmpty()) {
                post.setImg(existingImages + "," + newImages);
            } else {
                post.setImg(newImages);
            }
            postRepository.save(post);
        }

        return imageUrls;
    }

    public Resource getPostImageResource(String fileName) throws IOException {
        Path filePath = Paths.get(POST_IMAGE_DIR).resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new IOException("Image not found: " + fileName);
        }
    }

}
