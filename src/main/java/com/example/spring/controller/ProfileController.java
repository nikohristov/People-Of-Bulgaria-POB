package com.example.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.spring.dao.IUserDAO;
import com.example.spring.model.user.User;
import com.example.spring.model.user.UserManager;

@Controller
public class ProfileController {
	
	@Autowired
	private IUserDAO userDao;

	@RequestMapping(value="/changeProfile",method=RequestMethod.GET)
	public String changeProfile(){
		return "changeprofile";
	}
	
	@RequestMapping(value="/viewProfile", method=RequestMethod.GET)
	public String viewProfile(HttpServletRequest req,Model model){
		User user = ((UserManager)req.getSession().getAttribute("loggedUser")).getLoggedUser();
		int id = 0;
		try{
			 id = Integer.parseInt(req.getParameter("Id"));
		}catch(NumberFormatException e){
			 id = user.getId();
		}
		
		if(id != user.getId()){
			User userToView = this.userDao.getUser(id);
			if(user.getUsersWhoFollowed().contains(userToView)){
				req.setAttribute("isFollow", true);
			}else{
				req.setAttribute("isFollow", false);
			}
			req.setAttribute("userProfileToView", userToView);
		}else{
			req.setAttribute("userProfileToView", user);
		}
		
		return "viewprofile";
	}
	
	
	@RequestMapping(value="/myposts", method=RequestMethod.GET)
	public String showMyPosts(){
		return "myposts";
	}
	
	@RequestMapping(value="/doChangeProfile")
	public String doChangeProfile(HttpServletRequest request) {
		
		 UserManager manager=(UserManager)request.getSession().getAttribute("loggedUser");
	     User updatedUser=new User();
			updatedUser.setFirstName(request.getParameter("firstname"));
			updatedUser.setLastName(request.getParameter("lastname"));
			updatedUser.setPassword(request.getParameter("password"));
			updatedUser.setEmail(request.getParameter("email"));
			updatedUser.setBiography(request.getParameter("biography"));
			
		  manager.updateProfile(updatedUser);
		
		 return "viewprofile";
		
	}
	
	@RequestMapping(value="/follow",method=RequestMethod.GET)
	public String followUser(HttpServletRequest req){
		UserManager manager = (UserManager) req.getSession().getAttribute("loggedUser");
		int following_id = Integer.parseInt(req.getParameter("Id"));
		User userFollowing = this.userDao.followUser(manager.getLoggedUser(),following_id);
		System.out.println(userFollowing.getId());
		System.out.println(userFollowing.getUsername());
		System.out.println(userFollowing.getPassword());
		req.setAttribute("userProfileToView", userFollowing);
		req.setAttribute("isFollow", true);
		return "viewprofile";
	}
	
	@RequestMapping(value="/unfollow",method=RequestMethod.GET)
	public String unfollowUser(HttpServletRequest req){
		UserManager manager = (UserManager) req.getSession().getAttribute("loggedUser");
		int following_id = Integer.parseInt(req.getParameter("Id"));
		User userUnfollowing = this.userDao.unfollowUser(manager.getLoggedUser(),following_id);
		req.setAttribute("userProfileToView", userUnfollowing);
		req.setAttribute("isFollow", false);
		return "viewprofile";
	}
}
