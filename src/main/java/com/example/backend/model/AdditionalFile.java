package com.example.backend.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public abstract class AdditionalFile {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long addId;

	public long getAddId() {
		return this.addId;
	}

	public void setAddId(long addId) {
		this.addId = addId;
	}

private String fileName;
private String filePath;
    private Date created;

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

@ManyToOne
@JoinColumn(name = "id")
private FileType fileType;

public FileType getFileType() {
    return this.fileType;
}

public void setFileType(FileType fileType) {
    this.fileType = fileType;
}


public String getFileName() {
    return this.fileName;
}

public void setFileName(String fileName) {
    this.fileName = fileName;
}

public String getFilePath() {
    return this.filePath;
}

public void setFilePath(String filePath) {
    this.filePath = filePath;
}

}
