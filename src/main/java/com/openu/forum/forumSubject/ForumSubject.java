package com.openu.forum.forumSubject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * An entity class that contains the information of a single subject entry.
 * 
 * @author amit and nir
 *
 */
@Entity
@Table(name = "subjects")
public class ForumSubject  {

	@Id
	@GeneratedValue
	long id;

	@Column(unique=true)		// This column is a unique key
	String title;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param id - the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param title - the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
