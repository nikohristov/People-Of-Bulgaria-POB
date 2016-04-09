package com.example.spring.model.user;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.spring.dao.IPostDAO;
import com.example.spring.dao.IUserDAO;
import com.example.spring.dao.UserDAO;
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
	
	public User getLoggedUser(){
		return this.user;
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
	
	public void likePost(Post post, IUserDAO userDAO2){
		userDAO2.likePost(this.user,post);
	}
	
	public void unlikePost(Post post, IUserDAO userDAO2){
		userDAO2.unlikePost(this.user,post);
	}
	
	public User followUser(int following_id){
		return this.userDao.followUser(this.user, following_id);
	}
	
	public User unfollowUser(int following_id){
		return this.userDao.unfollowUser(this.user, following_id);
	}
	
	public void commentOnPost(Post post,Comment comment,IPostDAO dao){
		dao.addCommentOnPost(post, comment);
		
	}
	
	public void deleteCommentOnPost(Post post,Comment comment, IPostDAO dao){
		dao.deleteCommentOnPost(post, comment);
	}
	
	public IUserDAO getUserDao(){
		return this.userDao;
	}
	
}
