package com.example.spring.model.comment;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.example.spring.model.post.Post;
import com.example.spring.model.user.User;

@Entity
@Table(name="comments")
public class Comment {
	
	//Define hibernate table and states
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comment_id")
	private int id;
	
	@Column(name="by_user",columnDefinition="VARCHAR(50)",unique=false,nullable=false)
    private String username;
	
	@Column(name="description",columnDefinition="VARCHAR(50)",unique=false,nullable=false)
	@NotEmpty(message="Please enter")
    private String description;
	
	@Column(name="date_upload",columnDefinition="DATE",unique=false,nullable=false)
	private Date dateOfUpload;
	
	 @ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name="post_id",nullable=false)
	 private Post post;

	
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateOfUpload() {
		return dateOfUpload;
	}
	public void setDateOfUpload(Date dateOfUpload) {
		this.dateOfUpload = dateOfUpload;
	}
	
	//HashCode and Equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfUpload == null) ? 0 : dateOfUpload.hashCode());
		result = prime * result + id;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (dateOfUpload == null) {
			if (other.dateOfUpload != null)
				return false;
		} else if (!dateOfUpload.equals(other.dateOfUpload))
			return false;
		if (id != other.id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}
