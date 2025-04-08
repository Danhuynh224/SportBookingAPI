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
        List<String> imageUrls = new ArrayList<>();
        List<String> fileNames = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE);

            imageUrls.add("http://localhost:8080/images/" + fileName);
            fileNames.add(fileName);
        }

        // Lưu danh sách tên file vào trường img trong bảng Post
        Post post = postRepository.findByIdPost(postId);
        String existingImg = post.getImg(); // Có thể null nếu chưa có
        String newImg = String.join(",", fileNames);

        if (existingImg != null && !existingImg.isEmpty()) {
            post.setImg(existingImg + "," + newImg);
        } else {
            post.setImg(newImg);
        }

        postRepository.save(post);
        return imageUrls;
    }

    // Phương thức lấy danh sách ảnh của post
    public List<String> getPostImageUrls(Long postId) {
        Post post = postRepository.findByIdPost(postId);
        if (post == null || post.getImg() == null || post.getImg().isEmpty()) {
            return Collections.emptyList();
        }

        String[] fileNames = post.getImg().split(",");
        return Arrays.stream(fileNames)
                .map(name -> "http://localhost:8080/images/" + name)
                .collect(Collectors.toList());
    }

}
