package com.example.spring.model.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.spring.dao.IPostDAO;
import com.example.spring.dao.IUserDAO;
import com.example.spring.model.comment.Comment;
import com.example.spring.model.post.Post;

public class UserManager {
	
	private User user;
	
	@Autowired
	private IUserDAO userDao;
	
	@Autowired
	private IPostDAO postDao;
	
	public UserManager(User user){
		this.user = user;
	}
	
	//Methods on current user

	public void updateProfile(User user){
		this.user.setPassword(user.getPassword());
		this.user.setFirstName(user.getFirstName());
		this.user.setLastName(user.getLastName());
		this.user.setEmail(user.getEmail());
		this.user.setBiography(user.getBiography());
		this.userDao.updateUser(this.user);
	}
	
	public void uploadPost(Post post){
		this.userDao.uploadPost(this.user, post);
	}
	
	public void deletePost(Post post){
		this.userDao.deletePost(this.user, post);
	}
	
	public void likePost(Post post){
		this.userDao.likePost(this.user,post);
	}
	
	public void unlikePost(Post post){
		this.userDao.unlikePost(this.user,post);
	}
	
	public void followUser(String username){
		this.userDao.followUser(this.user,username);
	}
	
	public void unfollowUser(String username){
		this.userDao.unfollowUser(this.user,username);
	}
	
	public void commentOnPost(Post post,Comment comment){
		this.postDao.addCommentOnPost(this.user.getUsername(), post, comment);
	}
	
	public void deleteCommentOnPost(Post post,Comment comment){
		this.postDao.deleteCommentOnPost(this.user.getUsername(), post, comment);
	}
   
}
