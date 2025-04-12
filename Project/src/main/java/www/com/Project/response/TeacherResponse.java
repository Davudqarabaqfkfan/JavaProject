package www.com.Project.response;

public class TeacherResponse {
private String name;
private String surname;
String subjectString;

public TeacherResponse(String name, String surname, String subjectString) {
	this.name = name;
	this.surname = surname;
	this.subjectString = subjectString;
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

public String getSubjectString() {
	return subjectString;
}

public void setSubjectString(String subjectString) {
	this.subjectString = subjectString;
}

public TeacherResponse() {
	
}
}
