package kr.co.miroirs.smoking.dto;

import java.time.LocalDateTime;

public class FileInfo {

    private int id;
    private String fileName;
    private String filePath;
    private String fileType;
    private int deleteFlag;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public int getDeleteFlag() {
        return deleteFlag;
    }
    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    public LocalDateTime getModifyDate() {
        return modifyDate;
    }
    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }
    @Override
    public String toString() {
        return "FileInfo [id=" + id + ", fileName=" + fileName + ", filePath=" + filePath + ", fileType=" + fileType
                + ", deleteFlag=" + deleteFlag + "]";
    }
    
    
}
