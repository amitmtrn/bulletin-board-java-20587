package com.openu.forum;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/topics")
	public Topic addTopic(@RequestBody Topic t) {
		if(t.getId() != 0)
			return null;
		
		return repository.save(t);
	}
	
	@PutMapping("/topics")
	public Topic editTopic(@RequestBody Topic t) {
		if(t.getId() == 0)
			return null;
		
		return repository.save(t);
	}
	
	@DeleteMapping("/topics/{id}")
	public void deleteTopic(@PathVariable(value = "id") Long id) {
		
		repository.deleteById(id);
	}
}
