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
import com.openu.forum.forumSubject.ForumSubject;
import com.openu.forum.forumSubject.ForumSubjectJpaRepository;
import com.openu.forum.users.*;

@RestController
public class TopicController {

	@Autowired
	UserJpaRepository userRepository;

	@Autowired
	TopicJpaRepository topicRepository;
	
	@Autowired
	CommentJpaRepository commentRepository;
	
	@Autowired
	ForumSubjectJpaRepository subjectRepository;
	
	@GetMapping("/api/topics")
	public List<Topic> getAll() {
		List<Topic> list = new ArrayList<Topic>();
		
		topicRepository.findAll().forEach(list::add);
		return list;
	}
	
	@GetMapping("/api/topics/{subjectId}")
	public List<Topic> getBySubject(@PathVariable(value = "subjectId") Long subjectId) {
		List<Topic> list = new ArrayList<Topic>();
		
		topicRepository.findTopicsBySubject(subjectId).forEach(list::add);
		return list;
	}
	
	@PostMapping("/api/topics/{subjectId}")
	public Topic addTopic(
		@PathVariable(value = "subjectId") Long subjectId, 
		@RequestBody Topic topic, 
		Authentication authentication
		) throws Exception {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userRepository.findByUsername(userDetails.getUsername());
		ForumSubject subject = subjectRepository.findById(subjectId).get();

		if(topic.getId() != 0 && !(topic.getUser().equals(user.getUsername()) || user.isAdmin())) {
			throw new Exception("Permission denied!");
		}

		topic.setUser(user);
		topic.setSubject(subject);

		return topicRepository.save(topic);

	}
	
	@DeleteMapping("/api/topics/{id}")
	public void deleteTopic(@PathVariable(value = "id") Long id, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userRepository.findByUsername(userDetails.getUsername());
		Topic topic = topicRepository.findById(id).get();

		if(topic.haveUser(user) || user.isAdmin()) {
			topicRepository.deleteById(id);
		}

	}

	@PostMapping("/api/topics/{id}/comments")
	public void addComment(
		@PathVariable(value = "id") Long id, 
		@RequestBody Comment c, 
		Authentication authentication) throws Exception {
		Topic topic = topicRepository.findById(id).get();		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userRepository.findByUsername(userDetails.getUsername());
		long commentId = c.getId();

		c.setUser(user);
		Comment comment = commentRepository.save(c);

		if(commentId != 0 && !(comment.getUser().equals(user.getUsername()) || user.isAdmin())) {
			throw new Exception("Permission denied!");
		}

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
