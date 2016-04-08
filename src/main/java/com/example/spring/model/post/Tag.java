package com.example.spring.model.post;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.spring.model.user.User;

@Entity
@Table(name="tags")
public class Tag {
	
	//Define hibernate table and states
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tag_id")
	private int id;	
	
	@Column(name="title",columnDefinition="VARCHAR(50)",unique=true,nullable=false)
    private String title;
	
	@ManyToMany(mappedBy="tagsOfPost",cascade = CascadeType.ALL)
	private Set<Post> postsOfTag = new HashSet<Post>();

	//Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Post> getPostsOfTag() {
		return postsOfTag;
	}

	public void setPostsOfTag(Set<Post> postsOfTag) {
		this.postsOfTag = postsOfTag;
	}
	
	//HashCode and Equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Tag other = (Tag) obj;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
}
