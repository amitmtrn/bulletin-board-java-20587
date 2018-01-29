package com.openu.forum;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	
	@Autowired
	TopicJpaRepository repository;
	
	@GetMapping("/topics")
	public List<Topic> getAll() {
		List<Topic> list = new ArrayList<Topic>();
		repository.findAll().forEach(list::add);
		return list;
	}
	
	
}
