package com.openu.forum.privateMessages;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController	
public class PrivateMessageController {

	@Autowired
	PrivateMessageJpaRepository repository;
	
	@PostMapping("/new-message")
	public PrivateMessage addPrivateMessage(@RequestBody PrivateMessage p) {
		return repository.save(p);
	}

	@DeleteMapping("/new-message/{id}/{userId}")
	public PrivateMessage removePrivateMessage(@PathVariable("id") long id, @PathVariable("userId") long userId) {
		Optional<PrivateMessage> om = repository.findById(id);
		
		if(!om.isPresent())
			return null;
		
		PrivateMessage m = om.get();
		
		if(m.getTo() == userId) {
			m.setToVisable(false);
		}

		else if(m.getFrom() == userId) {
			m.setFromVisable(false);
		}
		
		return repository.save(m);
	}

	@GetMapping("/private-messages/inbox/{userId}")
	public List<PrivateMessage> getInbox(@PathVariable("userId") long userId) {
		return repository.getUserReceivedMessages(userId);
	}

	@GetMapping("/private-messages/outbox/{userId}")
	public List<PrivateMessage> getoutbox(@PathVariable("userId") long userId) {
		return repository.getUserSentMessages(userId);
	}

}
