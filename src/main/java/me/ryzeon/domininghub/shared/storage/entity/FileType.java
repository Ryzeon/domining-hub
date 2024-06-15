package me.ryzeon.domininghub.shared.storage.entity;

public enum FileType {

    IMAGE("png", "jpg", "jpeg", "gif", "bmp"),
    VIDEO("mp4", "avi", "mov", "wmv", "flv"),
    AUDIO("mp3", "wav", "ogg", "flac"),
    DOCUMENT("pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt"),
    ARCHIVE("zip", "rar", "7z", "tar", "gz", "bz2"),
    OTHER()
    ;

    String[] extensions;

    FileType(String... extensions) {
        this.extensions = extensions;
    }

    public String[] getExtensions() {
        return extensions;
    }

    public static FileType fromExtension(String extension) {
        for (FileType fileType : values()) {
            for (String ext : fileType.extensions) {
                if (ext.equalsIgnoreCase(extension)) {
                    return fileType;
                }
            }
        }
        return null;
    }
}
