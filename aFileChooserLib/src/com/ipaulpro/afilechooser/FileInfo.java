package com.ipaulpro.afilechooser;

import android.net.Uri;

public class FileInfo {
    private Uri uri;
    private String displayName;
    private String path;
    private long size;
    private boolean external;

    public FileInfo(Uri uri, String path, String displayName, long size, boolean external) {
        this.uri = uri;
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

    public Uri getUri() {
        return uri;
    }
}
