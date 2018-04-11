package com.openu.forum.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * An entity class that contains the information of a single user entry.
 * 
 * @author amit and nir
 *
 */
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue
	long id;

	@Column(unique=true)	// This column is a unique key		
	String username;

	String password;
	Boolean enabled = true;
	String role = "USER";

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param username - the user name to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password - the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Checks if this user's role is ADMIN
	 * @return true - if this user's role is ADMIN; false - otherwise
	 */
	public boolean isAdmin() {
		return this.role == "ADMIN";
	}

	/**
	 * @return the user name
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Checks if a given user is identical to this user
	 * @param user - the given user to compare to
	 * @return true -  if the given user is identical to this user; false - otherwise
	 */
	public boolean equals(User user) {
		return this.id == user.id;
	}
}
