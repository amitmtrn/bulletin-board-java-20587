package com.openu.forum.users;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController	
public class UserController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	UserJpaRepository repository;
	
	@PostMapping("/api/users")
	public User addUser(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

	@GetMapping("/api/users/me")
	public User getUser(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = repository.findByUsername(userDetails.getUsername());

		user.setPassword("");

		return user;
	}

}
