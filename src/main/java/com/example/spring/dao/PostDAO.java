package com.example.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.example.spring.model.post.*;
import com.example.spring.model.comment.Comment;
import com.example.spring.model.post.Post;
import com.example.spring.model.user.User;


public class PostDAO implements IPostDAO {
	
	private SessionFactory sessionFactory;

	public PostDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	
	@Override
	public List<Post> getAllPicsByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getMostPopularPics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getAllPicsByTags(String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCommentOnPost(String username, Post post, Comment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCommentOnPost(String username, Post post, Comment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePost(Post post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Post getPost(String title, String description) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	 public void addPost(Post p) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
		session.close();
	
	}

}

	