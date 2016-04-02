package com.example.spring.dao;

import java.util.List;

import com.example.spring.model.comment.Comment;
import com.example.spring.model.post.Post;


public interface IPostDAO {
	
	public List<Post> getAllPicsByCategory(String category);
	
	public List<Post> getMostPopularPics();
	
	public List<Post> getAllPicsByTags(String[] args);
	
	public void addCommentOnPost(String username,Post post,Comment comment);
	
	public void deleteCommentOnPost(String username,Post post,Comment comment);
	
	public void updatePost(Post post);
	
	public Post getPost(String title,String description);
	
}
