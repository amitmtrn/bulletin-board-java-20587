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

/**
 * A class for interactions with the MySQL database using the Topic and Comment classes.
 * 
 * @author amit and nir
 *
 */
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
	
	/**
	 * Gets all the topics that have opened so far
	 * @return a list contains all the topics that have opened so far
	 */
	@GetMapping("/api/topics")
	public List<Topic> getAll() {
		List<Topic> list = new ArrayList<Topic>();
		
		topicRepository.findAll().forEach(list::add);
		return list;
	}
	
	/**
	 * Gets the list of topics related to a given subject
	 * @param subjectId - the id of a given subject
	 * @return the list of topics related to the given subject
	 */
	@GetMapping("/api/topics/{subjectId}")
	public List<Topic> getBySubject(@PathVariable(value = "subjectId") Long subjectId) {
		List<Topic> list = new ArrayList<Topic>();
		
		topicRepository.findTopicsBySubject(subjectId).forEach(list::add);
		return list;
	}
	
	/**
	 * Adds a new topic entry to the DB or updates one, after getting the user details from a given authenticated principal 
	 * @param subjectId - a given subject
	 * @param topic - a given topic to add or update
	 * @param authentication - the authenticated principal
	 * @return the saved entity
	 * @throws Exception
	 */
	@PostMapping("/api/topics/{subjectId}")
	public Topic addTopic(
		@PathVariable(value = "subjectId") Long subjectId, 
		@RequestBody Topic topic, 
		Authentication authentication
		) throws Exception {
		
		// Retrieves the user details
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		// Finds the corresponding user entity to the user details
		User user = userRepository.findByUsername(userDetails.getUsername());
		
		// Gets the subject
		ForumSubject subject = subjectRepository.findById(subjectId).get();
		
		/*
		 * Prevents *editing* (topic id != 0) if this topic wasn't created by this user and he isn't ADMIN;
		 * If so - throws an exception.
		 */
		if(topic.getId() != 0 && !(topic.getUser().equals(user.getUsername()) || user.isAdmin())) {
			throw new Exception("Permission denied!");
		}

		// otherwise allows adding/editing:
		topic.setUser(user);
		topic.setSubject(subject);

		return topicRepository.save(topic);

	}
	
	/**
	 * Deletes a topic entry from the DB 
	 * @param id - the given topic id
	 * @param authentication - the authenticated principal 
	 */
	@DeleteMapping("/api/topics/{id}")
	public void deleteTopic(@PathVariable(value = "id") Long id, Authentication authentication) {
		
		// Retrieves the user details
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		// Finds the corresponding user entity to the user details
		User user = userRepository.findByUsername(userDetails.getUsername());
		
		// Finds the corresponding topic entity to the given id
		Topic topic = topicRepository.findById(id).get();

		/*
		 * Checks if the active user is the one who opened this topic or is an admin:
		 * if so - deletes this topic entry; else - does nothing 
		 */
		if(topic.haveUser(user) || user.isAdmin()) {
			topicRepository.deleteById(id);
		}

	}

	
	/**
	 * Adds a new comment to a given topic or updates one
	 * @param id - the given topic id
	 * @param c - the given comment
	 * @param authentication- the given topic id
	 * @throws Exception
	 */
	@PostMapping("/api/topics/{id}/comments")
	public void addComment(
		@PathVariable(value = "id") Long id, 
		@RequestBody Comment c, 
		Authentication authentication) throws Exception {
		
		// Finds the corresponding topic entity to the given id
		Topic topic = topicRepository.findById(id).get();
		
		// Retrieves the user details
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		// Finds the corresponding user entity to the user details
		User user = userRepository.findByUsername(userDetails.getUsername());
		
		long commentId = c.getId();

		c.setUser(user);
		Comment comment = commentRepository.save(c);

		/*
		 * Prevents *editing* (comment id != 0) if this comment wasn't created by this user and he isn't ADMIN;
		 * If so - throws an exception.
		 */
		if(commentId != 0 && !(comment.getUser().equals(user.getUsername()) || user.isAdmin())) {
			throw new Exception("Permission denied!");
		}

		// If it is a new comment - adds the new comment to the list of comments of the topic
		if(commentId == 0) {
			topic.addComment(comment);
			topicRepository.save(topic);
		}
	}
	
	/**
	 * Deletes a comment entry from the DB 
	 * @param commentId  - the given comment id
	 */
	@DeleteMapping("/api/comments/{commentId}")
	public void deleteComment(@PathVariable(value = "commentId") Long commentId) {
		commentRepository.deleteById(commentId);
	}

}
