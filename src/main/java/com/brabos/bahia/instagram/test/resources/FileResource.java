package com.brabos.bahia.instagram.test.resources;


import com.brabos.bahia.instagram.test.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping(path = "/file")
public class FileResource {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(path = "/upload_file/{path}")
    public ResponseEntity<?> uploadFileFtp(@RequestParam("file")MultipartFile file, @PathVariable("path") String path)
    {
        String url = fileStorageService.storageFile(file, path);
        return ResponseEntity.created(URI.create(url)).build();
    }

}
