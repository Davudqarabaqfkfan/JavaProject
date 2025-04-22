package www.com.Project.controller;

import java.util.List;



import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
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

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import www.com.Project.entity.CompanyEntity;
import www.com.Project.entity.TeacherEntity;
import www.com.Project.exception.MyException;
import www.com.Project.response.TeacherResponse;
import www.com.Project.service.TeacherService;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/teachers")
public class TeacherController {
@Autowired
TeacherService teacherService;

@GetMapping("/get-all")
@Operation(summary = "Get All Teachers")
public List<TeacherResponse> getAllTeacherss(){
	return teacherService.getAllTeachers();
}
@GetMapping("/{id}")
@Operation(summary = "Get Teacher By ID")
public Optional<TeacherEntity> getOneTeacherr(@PathVariable Long id){
	return teacherService.GetOneTeacher(id);
}
@PostMapping("create")
@PreAuthorize("hasRole('ROLE_USER')")
@Operation(summary = "Create new teacher")
public void createe(@Valid @RequestBody TeacherEntity teacherEntity) {
	teacherService.create(teacherEntity);
}
@DeleteMapping("/delete/{id}")
@PreAuthorize("hasRole('ROLE_USER')")
@Operation(summary = "Delete teacher by ID")
public void deleete(@PathVariable Long id) {
	teacherService.delete(id);
}
@PutMapping("update/{id}")
@Operation(summary = "Update teacher")
public Optional<TeacherEntity> updateeTeacherEntity(@PathVariable Long id, @RequestBody TeacherEntity teacherEntity) {
	return Optional.ofNullable(teacherService.GetOneTeacher(id).orElseThrow(()-> new MyException("error")));
}
@GetMapping("/search")
@Operation(summary = "Search")
public List<TeacherEntity> searchh(@RequestParam String keyword){
	return teacherService.search(keyword);
}
@GetMapping("/{id}/company")
@Operation(summary = "Get teacher's company")
public Optional<CompanyEntity> getTeacherCompany(@PathVariable Long id) {
	return teacherService.getCompanyById(id);
}
}
