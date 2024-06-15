package me.ryzeon.domininghub.shared.storage.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record FileUploadRequest(
        @NotNull(message = "Name is required")
        String name,
        @NotNull(message = "File is required")
        MultipartFile file
) {
}
