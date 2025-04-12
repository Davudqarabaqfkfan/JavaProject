package www.com.Project.controller;

import java.util.List;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import www.com.Project.entity.CompanyEntity;
import www.com.Project.entity.TeacherEntity;
import www.com.Project.response.TeacherResponse;
import www.com.Project.service.TeacherService;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/teachers")
public class TeacherController {
@Autowired
TeacherService teacherService;

@GetMapping("/get-all")
public List<TeacherResponse> getAllTeacherss(){
	return teacherService.getAllTeachers();
}
@GetMapping("/{id}")
public Optional<TeacherEntity> getOneTeacherr(@PathVariable Long id){
	return teacherService.GetOneTeacher(id);
}
@PostMapping("create")
public void createe(@RequestBody TeacherEntity teacherEntity) {
	teacherService.create(teacherEntity);
}
@DeleteMapping("/delete/{id}")
public void deleete(@PathVariable Long id) {
	teacherService.delete(id);
}
@PutMapping("update/{id}")
public TeacherEntity updateeTeacherEntity(@PathVariable Long id, @RequestBody TeacherEntity teacherEntity) {
	return teacherService.update(id, teacherEntity);
}
@GetMapping("/search")
public List<TeacherEntity> searchh(@RequestParam String keyword){
	return teacherService.search(keyword);
}
@GetMapping("/{id}/company")
public Optional<CompanyEntity> getTeacherCompany(@PathVariable Long id) {
	return teacherService.getCompanyById(id);
}
}
