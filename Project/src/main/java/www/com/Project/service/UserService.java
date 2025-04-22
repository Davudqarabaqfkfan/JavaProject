package www.com.Project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.com.Project.entity.AuthorityE;
import www.com.Project.entity.Users;
import www.com.Project.exception.MyException;
import www.com.Project.repository.AuthorityRepository;
import www.com.Project.repository.UserRepository;

@Service
public class UserService {
@Autowired
UserRepository userRepository;
@Autowired
AuthorityRepository authorityRepository;
public void register(Users user) {
	if (userRepository.existsByUsername(user.getUsername())) {
		throw new MyException("This usename already exists!!!");
	}

user.setPassword("{noop}" + user.getPassword());
	user.setEnabled(true);
	userRepository.save(user);
	
	AuthorityE authorityE = new AuthorityE();
	authorityE.setUsername(user.getUsername());
	authorityE.setAuthority("ROLE_USER");
	authorityRepository.save(authorityE);
}
}
