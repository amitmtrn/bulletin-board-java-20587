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
	
	@ManyToOne
	User user;
	

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public long getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
