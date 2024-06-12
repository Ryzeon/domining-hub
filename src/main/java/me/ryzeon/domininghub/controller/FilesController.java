package me.ryzeon.domininghub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import me.ryzeon.domininghub.shared.storage.model.File;
import me.ryzeon.domininghub.shared.storage.service.IFileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/11/24 @ 18:26
 */
@Controller
@RequestMapping(value = "/api/v1/files", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Files", description = "Files Controller")
@AllArgsConstructor
public class FilesController {

    private final IFileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
        File uploadedFile = fileService.upload(file).orElseThrow(() -> new IllegalArgumentException("Cannot upload file"));
        return ResponseEntity.ok(uploadedFile);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) throws IOException {
        File file = fileService.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find file with id: " + id));
        String encodedFileName = URLEncoder.encode(file.getName(), StandardCharsets.UTF_8);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(file.getContentType()));
        headers.setContentDisposition(ContentDisposition.builder("inline").filename(encodedFileName).build());
        return new ResponseEntity<>(file.getBytes(), headers, HttpStatus.OK);
    }

    @GetMapping("/video/{id}")
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable String id) throws IOException {
        File file = fileService.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find file with id: " + id));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
                .body(new InputStreamResource(new ByteArrayInputStream(file.getBytes())));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String id) throws IOException {
        File file = fileService.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find file with id: " + id));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(new InputStreamResource(new ByteArrayInputStream(file.getBytes())));
    }
}
