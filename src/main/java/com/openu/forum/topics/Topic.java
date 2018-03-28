package com.openu.forum.topics;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.openu.forum.comments.Comment;
import com.openu.forum.forumSubject.ForumSubject;
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
	
	@OrderColumn
	@OneToMany
	List<Comment> comments;

	@OneToOne
	ForumSubject subject;

  private Date created;
  private Date updated;

  @PrePersist
  protected void onCreate() {
    created = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    updated = new Date();
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @return the updated
	 */
	public Date getUpdated() {
		return updated;
	}
	
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}
	
	public void addComment(Comment comment) {
		this.comments.add(comment);
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

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(ForumSubject subject) {
		this.subject = subject;
	}

	/**
	 * @return the subject
	 */
	public ForumSubject getSubject() {
		return subject;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return this.user.getUsername();
	}

	public boolean haveUser(User user) {
		return this.user.equals(user);
	}
}
