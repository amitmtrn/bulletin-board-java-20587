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

		return topicRepository.save(topic);
	}
	
	@DeleteMapping("/api/topics/{id}")
	public void deleteTopic(@PathVariable(value = "id") Long id, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userRepository.findByUsername(userDetails.getUsername());
		Topic topic = topicRepository.findById(id).get();

		if(topic.haveUser(user)) {
			topicRepository.deleteById(id);
		}

	}

	@PostMapping("/api/topics/{id}/comments")
	public void addComment(@PathVariable(value = "id") Long id, @RequestBody Comment c, Authentication authentication) {
		Topic topic = topicRepository.findById(id).get();		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userRepository.findByUsername(userDetails.getUsername());
		long commentId = c.getId();

		c.setUser(user);
		Comment comment = commentRepository.save(c);

		if(commentId == 0) {
			topic.addComment(comment);
			topicRepository.save(topic);
		}
	}

	@DeleteMapping("/api/comments/{commentId}")
	public void deleteComment(@PathVariable(value = "commentId") Long commentId) {
		commentRepository.deleteById(commentId);
	}

}
