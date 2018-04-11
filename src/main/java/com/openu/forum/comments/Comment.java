package com.openu.forum.comments;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.openu.forum.users.User;

/**
 * An entity class that contains the information of a single comment entry.
 * @author amit and nir
 *
 */
@Entity
@Table(name="comments")
public class Comment {

	@Id
	@GeneratedValue
	long id;
	
	@ManyToOne
	User user;
	
	@Column(nullable=false)			//  This column is NOT nullable.
	String title;
	
	String body;

	/**
	 * @return the id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * @param user - the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @param body - the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @param title - the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the name of the user
	 */
	public String getUser() {
		return this.user.getUsername();
	}
}
