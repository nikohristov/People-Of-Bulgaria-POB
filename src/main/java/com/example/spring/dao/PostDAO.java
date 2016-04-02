package com.example.spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.example.spring.model.post.Post;


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
	
}
