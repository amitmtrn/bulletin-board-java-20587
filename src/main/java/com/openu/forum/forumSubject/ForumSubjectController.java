package com.openu.forum.forumSubject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class for interactions with the MySQL database using the ForumSubject class.
 * 
 * @author amit and nir
 *
 */
@RestController
public class ForumSubjectController {

  @Autowired
	ForumSubjectJpaRepository forumSubjectRepository;

  	/**
  	 * Finds all the subjects
  	 * @return a list with all the subjects 
  	 */
	@GetMapping("/api/subjects")
	public List<ForumSubject> getAll() {
		List<ForumSubject> list = new ArrayList<ForumSubject>();
		
		forumSubjectRepository.findAll().forEach(list::add);
		return list;
	}
	
	/**
	 * Adds a new subject
	 * @param subject - the subject to add
	 * @return the saved entity
	 */
	@PostMapping("/api/subjects")
	public ForumSubject addSubject(@RequestBody ForumSubject subject) {
		return forumSubjectRepository.save(subject);
	}

}
