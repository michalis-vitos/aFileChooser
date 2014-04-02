package com.ipaulpro.afilechooser;

public class FileInfo {
    private String displayName;
    private String path;
    private long size;
    private boolean external;

    public FileInfo(String path, String displayName, long size, boolean external) {
        this.path = path;
        this.displayName = displayName;
        this.size = size;
        this.external = external;
    }

    public String getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isExternal() {
        return external;
    }
}
