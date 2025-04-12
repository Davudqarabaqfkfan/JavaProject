package www.com.Project.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import www.com.Project.entity.CompanyEntity;
import www.com.Project.entity.TeacherEntity;
import www.com.Project.repository.CompanyRepository;
import www.com.Project.repository.TeacherRepository;
import www.com.Project.response.TeacherResponse;

@Service

public class TeacherService {
	static final Logger log = LoggerFactory.getLogger(TeacherService.class);
@Autowired
TeacherRepository teacherRepository;
@Autowired
CompanyRepository companyRepository;
@Autowired
ModelMapper modelMapper;
@Cacheable("allTeachers")



public List<TeacherResponse> getAllTeachers(){
List<TeacherEntity> teachers = teacherRepository.findAll();
return teachers.stream().map(teacher->modelMapper.map(teacher, TeacherResponse.class)).collect(Collectors.toList());
}

public Optional<TeacherEntity> GetOneTeacher(Long id){
	return teacherRepository.findById(id);
}
public void create(TeacherEntity teacherEntity) {
	log.info("Teacher created succesfully - " + teacherEntity.getName() + " " + teacherEntity.getSurname());
	teacherRepository.save(teacherEntity);
}
public void delete(Long id) {
	if(!teacherRepository.existsById(id)) {
		log.warn("Can't delete teacher succesfully");
	}else {
		log.info("Teacher deleted succesfully");
		teacherRepository.deleteById(id);
	}
}
public TeacherEntity update(Long id, TeacherEntity teacherEntity) {
	Optional<TeacherEntity> excistingOptional = teacherRepository.findById(id);
	if(excistingOptional.isPresent()) {
		TeacherEntity updatedTeacherEntity = excistingOptional.get();
		updatedTeacherEntity.setName(teacherEntity.getName());
		updatedTeacherEntity.setAge(teacherEntity.getAge());
		updatedTeacherEntity.setSurname(teacherEntity.getSurname());
		updatedTeacherEntity.setSubjectString(teacherEntity.getSubjectString());
		return teacherRepository.save(updatedTeacherEntity);
	}
	return null;
}
public List<TeacherEntity> search(String keyword){
	return teacherRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(keyword, keyword);
}
public Optional<CompanyEntity> getCompanyById(Long id) {
	TeacherEntity teacher = teacherRepository.findById(id).orElse(null);
	if(teacher != null) {
		return companyRepository.findById(teacher.getCompanyId());
	}else {
		return Optional.empty();
	}
}
}
