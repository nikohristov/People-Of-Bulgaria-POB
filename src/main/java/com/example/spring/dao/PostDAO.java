package com.example.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
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
	public void addCommentOnPost(Post post, Comment comment) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.save(comment);
		Hibernate.initialize(post.getCommentsOfPost().add(comment));
		session.update(post);
		System.out.println(post.getId());
	    session.getTransaction().commit();
		session.close();
		
		
	}
	@Override
	public void deleteCommentOnPost(Post post, Comment comment) {
		
		
	}

	@Override
	public void updatePost(Post post) {
		
		
	}

	@Override
	public Post getPost(Integer id) {
		Session session=this.sessionFactory.openSession();
		Post post=(Post)session.get(Post.class, new Integer(id));
	    session.close();
	    
		return post;
		 
	}



	@Override
	 public void addPost(Post p) {
		
	
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

<<<<<<< HEAD


	

=======
>>>>>>> 39a6d82eef9a790ebbc01941b470365ab1c6e93f
}

	