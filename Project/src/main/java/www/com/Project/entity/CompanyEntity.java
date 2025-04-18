package www.com.Project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "companies")
public class CompanyEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@NotBlank(message = "name of the company can't be empty")
private String name;
@NotBlank(message = "direction of this company can't be empty")
private String direction;
private String creator;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDirection() {
	return direction;
}
public void setDirection(String direction) {
	this.direction = direction;
}
public String getCreator() {
	return creator;
}
public void setCreator(String creator) {
	this.creator = creator;
}
public CompanyEntity(String name, String direction, String creator) {
	this.name = name;
	this.direction = direction;
	this.creator = creator;
}
public CompanyEntity() {
	
}
}
