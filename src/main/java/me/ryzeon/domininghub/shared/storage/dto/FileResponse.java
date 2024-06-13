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
        String url,
        String niceFileSize
) {

    public FileResponse(File file) {
        this(
                file.getName(),
                file.getId(),
                "/download/%s".formatted(file.getId()),
                ArchiveUtils.getNiceFileSize(file.getSize())
        );
    }
}
