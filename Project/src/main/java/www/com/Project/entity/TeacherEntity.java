package www.com.Project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "teachers")
public class TeacherEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
Long id;
String name;
String surname;
int age;
String subjectString;
Long companyId;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSurname() {
	return surname;
}
public void setSurname(String surname) {
	this.surname = surname;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getSubjectString() {
	return subjectString;
}
public void setSubjectString(String subjectString) {
	this.subjectString = subjectString;
}
public Long getCompanyId() {
	return companyId;
}
public void setCompanyId(Long companyId) {
	this.companyId = companyId;
}

public TeacherEntity(Long id, String name, String surname, int age, String subjectString, Long companyId) {
	this.id = id;
	this.name = name;
	this.surname = surname;
	this.age = age;
	this.subjectString = subjectString;
	this.companyId = companyId;
}
public TeacherEntity() {
	
}
}
