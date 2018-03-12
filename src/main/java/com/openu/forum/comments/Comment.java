package com.openu.forum.comments;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.openu.forum.topics.Topic;
import com.openu.forum.users.User;

@Entity
@Table(name="comments")
public class Comment {

	@Id
	@GeneratedValue
	long id;
	
	@ManyToOne(targetEntity=User.class)
	long user;
	
	@Column(nullable=false)
	String title;
	
	String body;

	@ManyToOne(optional=false)
	Topic topic;
	
	public void setTopic(Topic topic) {
		this.topic = topic;
		
	}

	public long getId() {
		return this.id;
	}
}
