package com.example.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import com.example.spring.model.post.*;
import com.example.spring.model.comment.Comment;
import com.example.spring.model.post.Post;
import com.example.spring.model.user.User;


public class PostDAO implements IPostDAO {
	
	private SessionFactory sessionFactory;

	public PostDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getAllPicsByCategory(String category) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from Post where category=?");
		query.setString(0,category);
		List<Post> postsToShow = query.list();
		session.close();
		return postsToShow;
	}
	
	@Override
	public List<Post> getAllPicsByTags(String[] args) {
		Session session = this.sessionFactory.openSession();
		String hql = "select distinct a from Post a " +
		                "join a.tagsOfPost t " +
		                "where t.title in (:tags)";
		Query query = session.createQuery(hql);
		query.setParameterList("tags", args);
		@SuppressWarnings("unchecked")
		List<Post> toShow = query.list();
		session.close();
		return toShow;
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



	@Override
	public List<Post> getAllPicsByDate() {
		Session session = this.sessionFactory.openSession();
		Criteria cr = session.createCriteria(Post.class);
		cr.addOrder(Order.desc("dateOfUpload"));
		@SuppressWarnings("unchecked")
		List<Post> result = cr.list();
		session.close();
		return result;
	}



	@Override
	public List<Post> getAllPics() {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from com.example.spring.model.post.Post");
		@SuppressWarnings("unchecked")
		List<Post> allPosts = query.list();
		session.close();
		return allPosts;
	}

}

	