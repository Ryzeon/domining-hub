package me.ryzeon.domininghub.shared.storage.dto;

import me.ryzeon.domininghub.shared.storage.entity.File;
import me.ryzeon.domininghub.utils.ArchiveUtils;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 12/06/24 @ 17:40
 */
public record FileResponse(
        String name,
        String id,
        String downloadUrl,
        String viewUrl,
        String niceFileSize
) {

    public FileResponse(File file) {
        this(
                file.getName(),
                file.getId(),
                file.getDownloadUrl(),
                file.getViewUrl(),
                ArchiveUtils.getNiceFileSize(file.getSize())
        );
    }
}
