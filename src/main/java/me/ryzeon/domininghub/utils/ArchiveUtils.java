package me.ryzeon.domininghub.utils;

import lombok.experimental.UtilityClass;
import me.ryzeon.domininghub.shared.storage.entity.FileType;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 11/06/24 @ 22:42
 */
@UtilityClass
public class ArchiveUtils {

    public String getNiceFileSize(long size) {
        if (size < 1024) {
            return size + " B";
        }
        int z = (63 - Long.numberOfLeadingZeros(size)) / 10;
        return String.format("%.1f %sB", (double) size / (1L << (z * 10)), " KMGTPE".charAt(z));
    }

    public static FileType getFileType(String name) {
        String extension = name.substring(name.lastIndexOf(".") + 1);
        return FileType.fromExtension(extension);
    }
}
