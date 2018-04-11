package com.openu.forum.users;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class for interactions with the MySQL database using the User class.
 * 
 * @author amit and nir
 *
 */
@RestController	
public class UserController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	UserJpaRepository repository;
	
	/**
	 * Adds a new user entry to the DB, after encoding the user's password  
	 * @param user - the user to add to the DB
	 * @return the saved entity 
	 */
	@PostMapping("/api/users")
	public User addUser(@RequestBody User user) {
		
		//encoding the user's password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

	/**
	 * Gets the user from a given authenticated principal
	 * @param authentication - the authenticated principal 
	 * @return the corresponding user
	 */
	@GetMapping("/api/users/me")
	public User getUser(Authentication authentication) {
		
		// Retrieves the user details
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		// Finds the corresponding user entity to the user details
		User user = repository.findByUsername(userDetails.getUsername());
		
		// Resets the user's password 
		user.setPassword("");

		return user;
	}

}
