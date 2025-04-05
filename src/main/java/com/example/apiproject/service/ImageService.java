package com.example.apiproject.service;

import com.example.apiproject.entity.Post;
import com.example.apiproject.entity.SportsFacility;
import com.example.apiproject.repository.PostRepository;
import com.example.apiproject.repository.SportsFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;


@Service
public class ImageService {

    @Autowired
    SportsFacilityRepository sportsFacilityRepository;

    @Autowired
    PostRepository postRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

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

    public String uploadPostImage(Long postId, byte[] bytes, String fileName) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, bytes, StandardOpenOption.CREATE);

        String imageUrl = "http://localhost:8080/images/post/" + fileName;
        Post post = postRepository.findByIdPost(postId);
        post.setImg(fileName);
        postRepository.save(post);
        return imageUrl;
    }

    public Resource getPostImage(Long postId) throws IOException {
        Post post = postRepository.findByIdPost(postId);
        String fileName = post.getImg();
        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new IOException("Image not found for post: " + fileName);
        }
    }

}
