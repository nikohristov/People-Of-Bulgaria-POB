package com.example.spring.model.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.example.spring.model.post.Post;





@Entity
@Table(name="users")
public class User {
	
	//Define hibernate table and states
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private int id;
	@Column(name="username",columnDefinition="VARCHAR(50)",unique=true,nullable=false)
	private String username;
	@Column(name="password",columnDefinition="VARCHAR(50)",unique=false,nullable=false)
	private String password;
	@Column(name="email",columnDefinition="VARCHAR(50)",unique=true,nullable=false)
	private String email;
	@Column(name="first_name",columnDefinition="VARCHAR(50)",unique=false,nullable=false)
	private String firstName;
	@Column(name="last_name",columnDefinition="VARCHAR(50)",unique=false,nullable=false)
	private String lastName;
	@Column(name="biography",columnDefinition="VARCHAR(50)",unique=false,nullable=true)
	private String biography;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable( name="user_post", 
                joinColumns=@JoinColumn(name="user_id"), 
                inverseJoinColumns=@JoinColumn(name="post_id"))
	private Set<Post> postsOfUser = new HashSet<Post>();

	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="user_likedpost",
    joinColumns={@JoinColumn(name="user_id")},
    inverseJoinColumns={@JoinColumn(name="post_id")})
	private Set<Post> likedPosts = new HashSet<Post>();
	
	
	@ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="user_follower",
        joinColumns={@JoinColumn(name="user_id")},
        inverseJoinColumns={@JoinColumn(name="follower_id")})
    private Set<User> usersWhoFollowed = new HashSet<User>();

	
	//Getters and Setters

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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getBiography() {
		return biography;
	}


	public void setBiography(String biography) {
		this.biography = biography;
	}


	public Set<Post> getPostsOfUser() {
		return postsOfUser;
	}


	public void setPostsOfUser(Set<Post> postsOfUser) {
		this.postsOfUser = postsOfUser;
	}


	public Set<Post> getLikedPosts() {
		return likedPosts;
	}


	public void setLikedPosts(Set<Post> likedPosts) {
		this.likedPosts = likedPosts;
	}


	public Set<User> getUsersWhoFollowed() {
		return usersWhoFollowed;
	}


	public void setUsersWhoFollowed(Set<User> usersWhoFollowed) {
		this.usersWhoFollowed = usersWhoFollowed;
	}

	//HashCode and Equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
