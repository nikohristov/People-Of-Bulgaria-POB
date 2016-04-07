package com.example.spring.dao;

import java.util.List;

import com.example.spring.model.comment.Comment;
import com.example.spring.model.post.Post;


public interface IPostDAO {
	
    public  void addPost(Post p);
	
	public List<Post> getAllPicsByCategory(String category);
	
	public List<Post> getAllPicsByTags(String[] args);
	
	public List<Post> getAllPicsByDate();
	
	public List<Post> getAllPics();
	
<<<<<<< HEAD
	
	public void addCommentOnPost(Post post,Comment comment);
=======
	public void addCommentOnPost(String username,Post post,Comment comment);
>>>>>>> 39a6d82eef9a790ebbc01941b470365ab1c6e93f
	
	public void deleteCommentOnPost(Post post,Comment comment);
	
	public void updatePost(Post post);
	
	public Post getPost(Integer id);
	
}
