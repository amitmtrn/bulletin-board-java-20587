package com.openu.forum.privateMessages;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	boolean fromVisable = true;

	@Column(columnDefinition="boolean default true")
	boolean toVisable = true;

	String topic;
	String body;

	@Transient
	private String toStr;
	/**
	 * @param to the to to set
	 */
	public void setTo(User to) {
		this.to = to;
	}

	public void setTo(String to) {
		this.toStr = to;
	}

	/**
	 * @return the toStr
	 */
	public String getToStr() {
		return toStr;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(User from) {
		this.from = from;
	}

	public String getTo() {
		return this.to.getUsername();
	}

	public void setToVisable(boolean b) {
		this.toVisable = b;
	}

	public String getFrom() {
		return this.from.getUsername();
	}

	public void setFromVisable(boolean b) {
		this.fromVisable = b;
	}
}
