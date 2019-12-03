package com.komdab.imagefilter;

public class ImagePath {
    private String pathIn;
    private String pathOut;
    private String fileName;

    public ImagePath(String pathIn, String pathOut, String fileName) {
        this.pathIn = pathIn;
        this.pathOut = pathOut;
        this.fileName = fileName;
    }

    public String getPathIn() {
        return pathIn;
    }

    public void setPathIn(String pathIn) {
        this.pathIn = pathIn;
    }

    public String getPathOut() {
        return pathOut;
    }

    public void setPathOut(String pathOut) {
        this.pathOut = pathOut;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImagePathIn() {
        return this.pathIn + "/" + this.fileName;

    } public String getImagePathOut() {
        return this.pathOut + "/" + this.fileName;
    }
}
