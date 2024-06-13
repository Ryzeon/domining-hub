package me.ryzeon.domininghub.shared.storage.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.AllArgsConstructor;
import me.ryzeon.domininghub.shared.storage.entity.File;
import me.ryzeon.domininghub.shared.storage.repository.FileRepository;
import me.ryzeon.domininghub.shared.storage.service.IFileService;
import me.ryzeon.domininghub.utils.ImageCompressor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 11/06/24 @ 22:47
 */
@Service
@AllArgsConstructor
public class FileServiceImpl implements IFileService {

    private FileRepository fileRepository;
    private GridFsTemplate gridFsTemplate;
    private GridFsOperations operations;

    @Override
    public Optional<File> upload(MultipartFile file) throws IOException {
        byte[] optimizeImage = ImageCompressor.compress(file.getInputStream());
        InputStream optimizedImageStream = new ByteArrayInputStream(optimizeImage);
        long newFileSize = optimizeImage.length;

        DBObject metaData = new BasicDBObject();
        metaData.put("size", newFileSize);

        Object fileID = gridFsTemplate.store(optimizedImageStream, file.getOriginalFilename(), file.getContentType(), metaData);
        File storedFile = new File(file.getOriginalFilename(), file.getContentType(), newFileSize, fileID.toString());

        return Optional.of(fileRepository.save(storedFile));
    }

    @Override
    public Optional<File> findById(String id) throws IOException {
        Optional<File> file = fileRepository.findById(id);
        if (file.isPresent()) {
            File dbFile = file.get();
            GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
            if (gridFSFile == null) {
                return Optional.empty();
            }
            dbFile.setBytes(IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()));
            return Optional.of(dbFile);
        }
        return Optional.empty();
    }
}
