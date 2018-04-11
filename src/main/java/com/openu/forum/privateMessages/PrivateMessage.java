package com.openu.forum.privateMessages;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.openu.forum.users.User;

/**
 * An entity class that contains the information of a single private message entry.
 * @author amit and nir
 *
 */
@Entity
@Table(name="private_messages")
public class PrivateMessage {
	@Id
	@GeneratedValue
	long id;
	
	@ManyToOne
	User from;			// The user who sends this private message
	
	@ManyToOne			// The user who receives this private message
	User to;

	boolean fromVisable = true;			// Indicates if this private message is visible to the user who sends it
	boolean toVisable = true;			// Indicates if this private message is visible to the user who receives it

	String topic;
	String body;

	@Transient
	private String toStr;				// The string representation of the name of the user who receives this private message
	
	/**
	 * @param to - to set the receiver
	 */
	public void setTo(User to) {
		this.to = to;
	}

	/**
	 * @param to - to set the string representation of the receiver
	 */
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
	 * @param from - to set the sender
	 */
	public void setFrom(User from) {
		this.from = from;
	}

	/**
	 * @return the user name of the receiver
	 */
	public String getTo() {
		return this.to.getUsername();
	}
	
	/**
	 * @param b - to set the boolean value of toVisible
	 */
	public void setToVisable(boolean b) {
		this.toVisable = b;
	}

	/**
	 * @return the user name of the sender
	 */
	public String getFrom() {
		return this.from.getUsername();
	}

	/**
	 * @param b - to set the boolean value of fromVisible
	 */
	public void setFromVisable(boolean b) {
		this.fromVisable = b;
	}
}
