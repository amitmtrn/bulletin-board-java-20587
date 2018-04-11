package com.openu.forum.privateMessages;

/**
 * A class for interactions with the MySQL database using the PrivateMessage class.
 * 
 * @author amit and nir
 *
 */
import java.util.List;
import java.util.Optional;

import com.openu.forum.users.User;
import com.openu.forum.users.UserJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

@RestController	
public class PrivateMessageController {

	@Autowired
	PrivateMessageJpaRepository repository;

	@Autowired
	UserJpaRepository userRepository;
	
	/**
	 * Adds a new private message entry to the DB, after encoding the user's password  
	 * @param p - the given private message
	 * @param authentication - the authenticated principal
	 * @return the saved entity
	 */
	@PostMapping("/api/private-messages")
	public PrivateMessage addPrivateMessage(@RequestBody PrivateMessage p, Authentication authentication) {
		
		// Retrieves the user details
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		// Finds the corresponding user entity to the user detail
		User from = userRepository.findByUsername(userDetails.getUsername());
		
		// Finds the receiver of the given private message
		User to = userRepository.findByUsername(p.getToStr());

		p.setFrom(from);
		p.setTo(to);

		return repository.save(p);
	}
	
	/**
	 * Deletes a topic entry from the DB 
	 * @param id - the given private message id
	 * @param authentication - the authenticated principal
	 * @return the saved entity
	 */
	@DeleteMapping("/api/private-messages/{id}")
	public PrivateMessage removePrivateMessage(@PathVariable("id") long id, Authentication authentication) {
		
		// Retrieves the user details
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		// Finds the corresponding user entity to the user detail
		User user = userRepository.findByUsername(userDetails.getUsername());
		
		Optional<PrivateMessage> om = repository.findById(id);
		
		// If the the given private message is not present:
		if(!om.isPresent())
			return null;
		
		// Else:
		PrivateMessage m = om.get();
		
		/* 
		 * If the user is the receiver - this message won't appear to him anymore.
		 * On the other hand, it will still appear to the one who sent it. 
		 */
		if(m.getTo().equals(user.getUsername())) {
			m.setToVisable(false);
		}

		/* 
		 * If the user is the sender - this message won't appear to him anymore.
		 * On the other hand, it will still appear to the one who received it. 
		 */
		if(m.getFrom().equals(user.getUsername())) {
			m.setFromVisable(false);
		}
		
		return repository.save(m);
	}

	/**
	 * Get all the private messages sent to a given user
	 * @param authentication - the authenticated principal
	 * @return a list of all the private messages sent to the given user
	 */
	@GetMapping("/api/private-messages/inbox")
	public List<PrivateMessage> getInbox(Authentication authentication) {
		
		// Retrieves the user details
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		// Finds the corresponding user entity to the user detail
		User user = userRepository.findByUsername(userDetails.getUsername());

		return repository.getUserReceivedMessages(user.getId());
	}
	
	/**
	 * Get all the private messages sent by a given user
	 * @param authentication - the authenticated principal
	 * @return a list of all the private messages sent by the given user
	 */
	@GetMapping("/api/private-messages/outbox")
	public List<PrivateMessage> getoutbox(Authentication authentication) {
		
		// Retrieves the user details
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		// Finds the corresponding user entity to the user detail
		User user = userRepository.findByUsername(userDetails.getUsername());

		return repository.getUserSentMessages(user.getId());
	}

}
