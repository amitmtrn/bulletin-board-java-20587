package com.openu.forum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "topics")
@NamedQuery(name="find_all_topics", query="SELECT p FROM Topic p")
public class Topic  {

	@Id
	@GeneratedValue
	long id;
	
	String title;
	String body;
	long user_id;
	
	public Topic() {}
	
	public Topic(long id, String title, String body, long user_id) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.user_id = user_id;
	}
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public long getUser_id() {
		return user_id;
	}


	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

}
