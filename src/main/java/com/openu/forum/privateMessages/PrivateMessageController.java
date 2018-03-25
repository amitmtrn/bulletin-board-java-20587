package com.openu.forum.privateMessages;

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
	
	@PostMapping("/api/private-messages")
	public PrivateMessage addPrivateMessage(@RequestBody PrivateMessage p, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User from = userRepository.findByUsername(userDetails.getUsername());
		User to = userRepository.findByUsername(p.getToStr());

		p.setFrom(from);
		p.setTo(to);

		return repository.save(p);
	}

	@DeleteMapping("/api/private-messages/{id}")
	public PrivateMessage removePrivateMessage(@PathVariable("id") long id, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userRepository.findByUsername(userDetails.getUsername());
		Optional<PrivateMessage> om = repository.findById(id);
		
		if(!om.isPresent())
			return null;
		
		PrivateMessage m = om.get();
		
		if(m.getTo().equals(user.getUsername())) {
			m.setToVisable(false);
		}

		if(m.getFrom().equals(user.getUsername())) {
			m.setFromVisable(false);
		}
		
		return repository.save(m);
	}

	@GetMapping("/api/private-messages/inbox")
	public List<PrivateMessage> getInbox(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userRepository.findByUsername(userDetails.getUsername());

		return repository.getUserReceivedMessages(user.getId());
	}

	@GetMapping("/api/private-messages/outbox")
	public List<PrivateMessage> getoutbox(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userRepository.findByUsername(userDetails.getUsername());

		return repository.getUserSentMessages(user.getId());
	}

}
