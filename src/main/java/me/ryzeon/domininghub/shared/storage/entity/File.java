package me.ryzeon.domininghub.shared.storage.entity;

import lombok.Getter;
import lombok.Setter;
import me.ryzeon.domininghub.utils.ArchiveUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/6/24 @ 18:54
 */
@Document
@Getter
public class File {

    @Id
    private String id;

    private String name;

    private String contentType;

    private long size;

    private String storeId;

    @Setter
    private byte[] bytes;

    @CreatedDate
    private Date createdAt;

    public File(String name, String contentType, long size, String storeId) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.storeId = storeId;
        this.bytes = null;
    }

    public File() {
        this("", "", 0, "");
    }

    public String getNiceFileSize() {
        return ArchiveUtils.getNiceFileSize(size);
    }

    public String getViewUrl() {
        return switch (getFileType()) {
            case IMAGE -> "/files/image/" + id;
            case VIDEO -> "/files/video/" + id;
            case DOCUMENT -> "/files/document/" + id;
            default -> "/files/download/" + id;
        };
    }

    public String getDownloadUrl() {
        return "/files/download/" + id;
    }

    public FileType getFileType() {
        return ArchiveUtils.getFileType(name);
    }

    public boolean isImage() {
        return getFileType() == FileType.IMAGE;
    }

    public boolean isVideo() {
        return getFileType() == FileType.VIDEO;
    }

    public boolean isAudio() {
        return getFileType() == FileType.AUDIO;
    }

    public boolean isDocument() {
        return getFileType() == FileType.DOCUMENT;
    }

    public boolean isArchive() {
        return getFileType() == FileType.ARCHIVE;
    }
}
