package com.ipaulpro.afilechooser;

import java.io.InputStream;

public class FileInfo {
    private String fileName;
    private InputStream inputStream;

    public FileInfo(String fileName, InputStream inputStream) {
        this.fileName = fileName;
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public boolean isFile() {
        return inputStream == null;
    }
}
