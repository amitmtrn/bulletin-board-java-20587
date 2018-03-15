package com.openu.forum.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue
	long id;
	
	String username;
	String password;
	Boolean enabled = true;
	String role = "USER";
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public long getId() {
		return this.id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	public boolean equals(User user) {
		return this.id == user.id;
	}
}
