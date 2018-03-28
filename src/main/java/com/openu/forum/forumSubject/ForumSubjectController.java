package com.openu.forum.forumSubject;

import java.util.ArrayList;
import java.util.List;

import com.openu.forum.users.User;
import com.openu.forum.users.UserJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForumSubjectController {

  @Autowired
	ForumSubjectJpaRepository forumSubjectRepository;

	@GetMapping("/api/subjects")
	public List<ForumSubject> getAll() {
		List<ForumSubject> list = new ArrayList<ForumSubject>();
		
		forumSubjectRepository.findAll().forEach(list::add);
		return list;
	}

	@PostMapping("/api/subjects")
	public ForumSubject addSubject(@RequestBody ForumSubject subject) {
		return forumSubjectRepository.save(subject);
	}

}
