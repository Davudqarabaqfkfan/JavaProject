package www.com.Project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "files")
public class FileEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String fileName;
private String fileType;
private String filePath;
public FileEntity(String fileName, String fileType, String filePath) {
	this.fileName = fileName;
	this.fileType = fileType;
	this.filePath = filePath;
}
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public String getFileType() {
	return fileType;
}
public void setFileType(String fileType) {
	this.fileType = fileType;
}
public String getFilePath() {
	return filePath;
}
public void setFilePath(String filePath) {
	this.filePath = filePath;
}
public FileEntity() {
	
}
}
