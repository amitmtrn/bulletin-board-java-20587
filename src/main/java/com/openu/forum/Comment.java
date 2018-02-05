package com.openu.forum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

	@ManyToOne(targetEntity=Topic.class, optional=false)
	long topic;
	
	public void setTopic(Long topic) {
		this.topic = topic;
		
	}

	public long getId() {
		return this.id;
	}
}
