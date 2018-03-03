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
	Boolean enabled;
	String role;
}
