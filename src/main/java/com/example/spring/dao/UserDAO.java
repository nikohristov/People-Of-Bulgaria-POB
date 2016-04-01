package com.example.spring.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.model.post.Post;
import com.example.spring.model.user.User;


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
		session.get(User.class, "nikso");
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

}
