package com.openu.forum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="private_messages")
public class PrivateMessage {
	@Id
	@GeneratedValue
	long id;
	
	@ManyToOne(targetEntity=User.class)
	long from;
	
	@ManyToOne(targetEntity=User.class)
	long to;

	@Column(columnDefinition="boolean default true")
	boolean fromVisable;

	@Column(columnDefinition="boolean default true")
	boolean toVisable;

	public long getTo() {
		return this.to;
	}

	public void setToVisable(boolean b) {
		this.toVisable = b;
	}

	public long getFrom() {
		return this.from;
	}

	public void setFromVisable(boolean b) {
		this.fromVisable = b;
	}
}
