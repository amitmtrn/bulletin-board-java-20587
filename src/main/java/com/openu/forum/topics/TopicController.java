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

import com.openu.forum.comments.Comment;
import com.openu.forum.comments.CommentJpaRepository;
import com.openu.forum.users.User;
import com.openu.forum.users.UserJpaRepository;

@RestController
public class TopicController {
	
	@Autowired
	TopicJpaRepository topicRepository;
	
	@Autowired
	UserJpaRepository userRepository;
	
	@Autowired
	CommentJpaRepository commentRepository;
	
	@GetMapping("/topics")
	public List<Topic> getAll() {
		List<Topic> list = new ArrayList<Topic>();
		
		topicRepository.findAll().forEach(list::add);
		return list;
	}
	
	@PostMapping("/topics")
	public Topic addTopic(@RequestBody Topic topic, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userRepository.findByUsername(userDetails.getUsername());
		
		topic.setUser(user);

		if(topic.getId() != 0)
			return null;
		
		return topicRepository.save(topic);
	}
	
	@PutMapping("/topics")
	public Topic editTopic(@RequestBody Topic t) {
		if(t.getId() == 0)
			return null;
		
		return topicRepository.save(t);
	}
	
	@DeleteMapping("/topics/{id}")
	public void deleteTopic(@PathVariable(value = "id") Long id) {
		
		topicRepository.deleteById(id);
	}

	@GetMapping("/topics/{id}/comments")
	public List<Comment> getAllComment(@PathVariable(value = "id") Long id) {
		return topicRepository.findComments(id);
	}

	@PostMapping("/topics/{id}/comments")
	public Comment addComment(@PathVariable(value = "id") Long id, @RequestBody Comment c) {
		if(c.getId() != 0)
			return null;
		
		c.setTopic(id);
		
		return commentRepository.save(c);
	}

	@PutMapping("/topics/{id}/comments")
	public Comment editComment(@PathVariable(value = "id") Long id, @RequestBody Comment c) {
		if(c.getId() == 0)
			return null;
		
		c.setTopic(id);
		
		return commentRepository.save(c);
	}


	@DeleteMapping("/topics/{topicId}/comments/{commentId}")
	public void deleteComment(@PathVariable(value = "topicId") Long topicId, @PathVariable(value = "commentId") Long commentId) {
		commentRepository.deleteById(commentId);
	}

}
