package com.example.apiproject.controller;

import com.example.apiproject.entity.Post;
import com.example.apiproject.service.ImageService;
import org.eclipse.angus.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    //Upload
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) {
        try {
            // Tạo tên file duy nhất để tránh trùng lặp
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String imageUrl = imageService.uploadImage(name, file.getBytes(), fileName);
            return ResponseEntity.ok(imageUrl);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Upload failed!");
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<Resource> getImage(@PathVariable("id") Long id) throws IOException {
        Resource resource = imageService.getImage(id);
        if (resource != null) { String contentType = "image/png"; // hoặc "image/jpeg" tùy loại ảnh
            return ResponseEntity.ok()
                    .header("Content-Type", contentType)
                    .body(resource);}
        else return ResponseEntity.notFound().build();
    }


    // Post
    @PostMapping("/post/upload")
    public ResponseEntity<List<String>> uploadPostImages(@RequestParam("postId") Long postId,
                                                         @RequestParam("files") MultipartFile[] files) {
        try {
            List<String> imageUrls = imageService.uploadPostImages(postId, files);
            return ResponseEntity.ok(imageUrls);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Collections.singletonList("Upload failed!"));
        }
    }

    @GetMapping("/post/{fileName:.+}")
    public ResponseEntity<Resource> getPostImage(@PathVariable String fileName) {
        try {
            Resource resource = imageService.getPostImageResource(fileName);
            String contentType = Files.probeContentType(resource.getFile().toPath());
            return ResponseEntity.ok()
                    .header("Content-Type", contentType != null ? contentType : "application/octet-stream")
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
