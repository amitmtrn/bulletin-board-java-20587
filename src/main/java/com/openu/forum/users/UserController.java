package com.openu.forum.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController	
public class UserController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	UserJpaRepository repository;
	
	@PostMapping("/users")
	public User addUser(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

}
