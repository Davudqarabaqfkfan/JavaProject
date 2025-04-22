package www.com.Project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
Long id;
String username;
String password;
Boolean enabled;
public String getUsername() {
	return username;
}
public Users() {

}
public Users(long id, String username, String password, Boolean enabled) {
	this.id = id;
	this.username = username;
	this.password = password;
	this.enabled = enabled;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Boolean getEnabled() {
	return enabled;
}
public void setEnabled(Boolean enabled) {
	this.enabled = enabled;
}
}
