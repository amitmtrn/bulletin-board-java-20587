package com.openu.forum.topics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.openu.forum.users.User;

@Entity
@Table(name = "topics")
public class Topic  {

	@Id
	@GeneratedValue
	long id;
	
	@Column(nullable=false)
	String title;
	
	@Column(nullable=false)
	String body;
	
	@ManyToOne(targetEntity=User.class)
	long user;
	
	public long getId() {
		return this.id;
	}
}
