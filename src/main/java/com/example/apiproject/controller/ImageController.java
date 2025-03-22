package com.example.apiproject.controller;

import com.example.apiproject.service.ImageService;
import org.eclipse.angus.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    //Upload
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        try {
            // Tạo tên file duy nhất để tránh trùng lặp
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String imageUrl = imageService.uploadImage(id, file.getBytes(), fileName);
            return ResponseEntity.ok(imageUrl);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Upload failed!");
        }
    }
//    @GetMapping()
//    public ResponseEntity<Resource> getImage(@PathVariable("id") String id) {
//        Resource resource = imageService.getImage(id);
//        if (resource != null) {return ResponseEntity.ok(resource);}
//        else return ResponseEntity.notFound().build();
//    }

}
