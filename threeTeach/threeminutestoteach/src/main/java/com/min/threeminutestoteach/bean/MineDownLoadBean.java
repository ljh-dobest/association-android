package com.min.threeminutestoteach.bean;

/**
 * Created by Min on 2017/6/19.
 */

public class MineDownLoadBean {
    private String fileName;
    private String fileSize;
    private String filePath;

    public MineDownLoadBean(String fileName, String fileSize, String filePath) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
