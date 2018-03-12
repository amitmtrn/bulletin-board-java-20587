package com.openu.forum.topics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openu.forum.comments.*;
import com.openu.forum.users.*;

@RestController
public class TopicController {

	@Autowired
	UserJpaRepository userRepository;

	@Autowired
	TopicJpaRepository topicRepository;
	
	@Autowired
	CommentJpaRepository commentRepository;
	
	@GetMapping("/api/topics")
	public List<Topic> getAll() {
		List<Topic> list = new ArrayList<Topic>();
		
		topicRepository.findAll().forEach(list::add);
		return list;
	}
	
	@PostMapping("/api/topics")
	public Topic addTopic(@RequestBody Topic topic, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userRepository.findByUsername(userDetails.getUsername());
		
		topic.setUser(user);

		if(topic.getId() != 0)
			return null;
		
		return topicRepository.save(topic);
	}
	
	@PutMapping("/api/topics")
	public Topic editTopic(@RequestBody Topic t) {
		if(t.getId() == 0)
			return null;
		
		return topicRepository.save(t);
	}
	
	@DeleteMapping("/api/topics/{id}")
	public void deleteTopic(@PathVariable(value = "id") Long id) {
		
		topicRepository.deleteById(id);
	}

	@GetMapping("/api/topics/{id}/comments")
	public List<Comment> getAllComment(@PathVariable(value = "id") Long id) {
		return topicRepository.findComments(id);
	}

	@PostMapping("/api/topics/{id}/comments")
	public Comment addComment(@PathVariable(value = "id") Long id, @RequestBody Comment c) {
		if(c.getId() != 0)
			return null;
		
		// c.setTopic(id);
		
		return commentRepository.save(c);
	}

	@PutMapping("/api/topics/{id}/comments")
	public Comment editComment(@PathVariable(value = "id") Long id, @RequestBody Comment c) {
		if(c.getId() == 0)
			return null;
		
		// c.setTopic(id);
		
		return commentRepository.save(c);
	}


	@DeleteMapping("/api/topics/{topicId}/comments/{commentId}")
	public void deleteComment(@PathVariable(value = "topicId") Long topicId, @PathVariable(value = "commentId") Long commentId) {
		commentRepository.deleteById(commentId);
	}

}
