package com.openu.forum.privateMessages;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.openu.forum.users.User;

@Entity
@Table(name="private_messages")
public class PrivateMessage {
	@Id
	@GeneratedValue
	long id;
	
	@ManyToOne
	User from;
	
	@ManyToOne
	User to;

	@Column(columnDefinition="boolean default true")
	boolean fromVisable;

	@Column(columnDefinition="boolean default true")
	boolean toVisable;

	public User getTo() {
		return this.to;
	}

	public void setToVisable(boolean b) {
		this.toVisable = b;
	}

	public User getFrom() {
		return this.from;
	}

	public void setFromVisable(boolean b) {
		this.fromVisable = b;
	}
}
