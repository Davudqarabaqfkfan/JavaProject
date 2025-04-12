package www.com.Project.service;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import www.com.Project.entity.FileEntity;
import www.com.Project.repository.FileRepository;

@Service
public class FileService {
	@Value("${our.ssilka}")
private String upload;

@Autowired
FileRepository fileRepository;
public FileEntity uploadFile(MultipartFile multipartFile) throws IOException {
	String filePath = upload + "\\" + multipartFile.getOriginalFilename();
	Files.write(Paths.get(filePath), multipartFile.getBytes());
	FileEntity fileEntity = new FileEntity(multipartFile.getOriginalFilename(), multipartFile.getContentType(), filePath);
	return fileRepository.save(fileEntity);
}
public void deleteFile(String fileName) throws IOException {
FileEntity fileEntity = fileRepository.findByFileName(fileName);
Files.deleteIfExists(Paths.get(fileEntity.getFilePath()));
fileRepository.delete(fileEntity);
}
}
