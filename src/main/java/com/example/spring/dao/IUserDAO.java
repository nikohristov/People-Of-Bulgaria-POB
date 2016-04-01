package com.example.spring.dao;

import java.util.List;

import com.example.spring.model.post.Post;
import com.example.spring.model.user.User;

public interface IUserDAO {
	
	public List<User> list();
	
	public void registerNewUser(User user);
	
	public List<User> getAllUsers();
	
	public void updateUser(User user);
	
	public void followUser(User follower,String usernameOfFollowed);
	
	public void unfollowUser(User follower,String usernameOfFollowed);
	
	public void uploadPost(User user,Post post);
	
	public void deletePost(User user,Post post);
	
	public void likePost(User user,Post post);
	
	public void unlikePost(User user,Post post);
	
	public void addCommentOnPost(User user, Post post);
	
	public void deleteCommentOnPost(User user,Post post);
	
	public User getUser(String username);
	
}
