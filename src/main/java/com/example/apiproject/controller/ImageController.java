package com.example.apiproject.controller;

import com.example.apiproject.service.ImageService;
import org.eclipse.angus.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

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

}
