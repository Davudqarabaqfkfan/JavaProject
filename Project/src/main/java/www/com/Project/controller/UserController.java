package www.com.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.com.Project.entity.Users;
import www.com.Project.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
@Autowired
UserService userService;
@PostMapping("/register")
public void reg(@RequestBody Users user) {
	userService.register(user);
}
@PostMapping("/login")
public void login() {
	
}
}
