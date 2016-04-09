package com.example.spring.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.model.post.Post;
import com.example.spring.model.user.User;

@Transactional
public class UserDAO implements IUserDAO {
	private SessionFactory sessionFactory;

	public UserDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> list() {
		
		Post test = new Post();
		test.setTitle("testerch1e");
		test.setDescription("testing1");
		test.setCategory("jajaja1");
		test.setDateOfUpload(new Date());
		
		User u = new User();
		test.setUser(u);
		u.setEmail("lalalala1");
		u.setUsername("nikso1");
		u.setBiography("jajaja1");
		u.setFirstName("niksooo1");
		u.setLastName("joooo1");
		u.setPassword("testtt1");
		u.getPostsOfUser().add(test);
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(test);
		session.save(u);
		
		//Post opit = (Post) session.get(Post.class, 1);
//		User opiten = (User) session.get(User.class, 1);
//		User opiten1 = (User) session.get(User.class, 2);
		//opiten.likedPosts.add(opit);
		//opiten.usersWhoFollowed.add(opiten1);
		//session.update(opiten);
		//session.get(User.class, "nikso");
		session.getTransaction().commit();
		System.out.println("save user?");
		
		for(Post p : u.getPostsOfUser()){
			System.out.println(p.getDateOfUpload());
		}
		
	
		Query query = session.createQuery("from com.example.spring.model.user.User");
		List<User> listUser = query.list();
		session.close();
		return listUser;
	}


	@Override
	public void registerNewUser(User user) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}


	@Override
	public List<User> getAllUsers() {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from com.example.spring.model.user.User");
		@SuppressWarnings("unchecked")
		List<User> allUsers = query.list();
		session.close();
		return allUsers;
	}


	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public User unfollowUser(User follower, int following_id) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		User following = (User) session.get(User.class, following_id);
		follower.getUsersWhoFollowed().remove(following);
		following.getUsersWhoFollower().remove(follower);
		session.clear();
		String SQL = "DELETE FROM user_follower WHERE user_id = (?)"; 
		Query query = session.createSQLQuery(SQL); 
		query.setInteger(0, follower.getId());
		query.executeUpdate();
		session.getTransaction().commit();
		session.clear();
		session.close();
		return following;
	}


	@Override
	public void uploadPost(User user, Post post) {
		user.getPostsOfUser().add(post);
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.save(post);
		System.out.println("saved post");
		session.update(user);
	    session.getTransaction().commit();
		session.close();
	}


	@Override
	public void deletePost(User user, Post post) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void likePost(User user, Post post) {		
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		user=(User) session.get(User.class,user.getId());
		post=(Post) session.get(Post.class, post.getId());
		post.setCountsOfLikes(post.getCountsOfLikes()+1);
		post.getUsersWhoLike().add(user);
		user.getLikedPosts().add(post);
		session.update(post);
		session.update(user);
		transaction.commit();
		session.close();
		
	}


	@Override
	public void unlikePost(User user, Post post) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		user=(User) session.get(User.class,user.getId());
		post=(Post) session.get(Post.class, post.getId());
		post.setCountsOfLikes(post.getCountsOfLikes()-1);
		post.getUsersWhoLike().remove(user);
		user.getLikedPosts().remove(post);
		session.update(post);
		session.update(user);
		transaction.commit();
		session.close();
		
	}


	@Override
	public void addCommentOnPost(User user, Post post) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteCommentOnPost(User user, Post post) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public User getUser(String username) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from User where username=?");
		User user=(User)query.setString(0,username);
		session.close();
		return user;
	}


	@Override
	public boolean checkIfUsernameExists(String username) {
		Session session = this.sessionFactory.openSession();
		String hql = "FROM com.example.spring.model.user.User WHERE username = :name";
		Query query = session.createQuery(hql);
		query.setParameter("name",username);
		@SuppressWarnings("rawtypes")
		List results = query.list();
		if(results.size() == 0){
			session.close();
			return false;
		}
		session.close();
		return true; 
	}


	@Override
	public boolean checkIfEmailAddressExists(String email) {
		for(User user : this.getAllUsers()){
			if(user.getEmail().equals(email)){
				return true;
			}
		}
		return false;
	}


	@Override
	public User getUser(int id) {
		Session session = this.sessionFactory.openSession();
		User user = (User) session.get(User.class, id);
		session.close();
		return user;
	}


	@Override
	public User followUser(User follower,int following_id) {
		
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		User following = (User) session.get(User.class, following_id);
		follower.getUsersWhoFollowed().add(following);
		following.getUsersWhoFollowed().add(follower);
		session.clear();
		String SQL = "INSERT INTO user_follower VALUES (?,?)"; 
		Query query = session.createSQLQuery(SQL); 
		query.setInteger(0, follower.getId());
		query.setInteger(1, following_id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		return following;
	}

	
	
}
