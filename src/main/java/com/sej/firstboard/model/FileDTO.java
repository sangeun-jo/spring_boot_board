package com.sej.firstboard.model;

public class FileDTO {

    private int fno;
    private int bno;
    private String fileName; 
    private String fileOriName;
    private String fileUrl; 

    public int getFno() {
        return fno; 
    }

    public void setFno(int fno) {
        this.fno = fno; 
    }

    public void setBno(int bno) {
        this.bno = bno; 
    }

    public int getBno() {
        return bno; 
    }

    public void setFileName(String fileName) {
        this.fileName = fileName; 
    }
    public String getFileName() {
        return fileName; 
    }

    public void setFileOriName(String fileOriName) {
        this.fileOriName = fileOriName; 
    }

    public String getFileOriName() {
        return fileOriName; 
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl; 
    }

    public String getFileUrl() {
        return fileUrl; 
    }
    
}
