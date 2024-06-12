package me.ryzeon.domininghub.shared.storage.model;

import lombok.Getter;
import lombok.Setter;
import me.ryzeon.domininghub.utils.ArchiveUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
}
