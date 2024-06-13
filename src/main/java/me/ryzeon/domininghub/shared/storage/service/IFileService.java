package me.ryzeon.domininghub.shared.storage.service;

import me.ryzeon.domininghub.shared.storage.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface IFileService {

    Optional<File> upload(MultipartFile file) throws IOException;

    Optional<File> findById(String id) throws IOException;
}
