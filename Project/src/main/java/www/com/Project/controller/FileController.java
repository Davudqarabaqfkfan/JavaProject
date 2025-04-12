package www.com.Project.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import www.com.Project.entity.FileEntity;
import www.com.Project.service.FileService;

@RestController
@RequestMapping("/files")
public class FileController {
@Autowired
FileService fileService;
@PostMapping("/upload")
public String upload(@RequestParam("file") MultipartFile multipartFile) {
	try {
		FileEntity savedFileEntity = fileService.uploadFile(multipartFile);
		return "file " + multipartFile.getOriginalFilename() + " succesfully saved";
	} catch (Exception e) {
		return "Error in " + multipartFile.getOriginalFilename() + " saving";
	}
}
@DeleteMapping("/delete/{fileName}")
public String deleteFile(@PathVariable String fileName) {
	try {
		fileService.deleteFile(fileName);
		return "file " + fileName + " succesfully deleted";
	} catch (IOException e) {
		return "Error in " + fileName + " deleting";
	}
}
}
