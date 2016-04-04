package com.example.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring.model.user.User;
import com.example.spring.model.user.UserManager;


public class ProfileController {
	
	@RequestMapping(value="/viewProfile")
	public String viewProfile(){
		return "viewprofile";
	}

	@RequestMapping(value="/changeProfile")
	public String changeProfile(){
		return "changeprofile";
	}
	
	@RequestMapping(value="/doChangeProfile")
	public String doChangeProfile(HttpServletRequest request) {
		
		User loggedUser=(User) request.getSession().getAttribute("loggedUser");
		UserManager man= new UserManager(loggedUser);
		User updatedUser= new User();
		updatedUser.setFirstName(request.getParameter("firstname"));
		updatedUser.setLastName(request.getParameter("lastname"));
		updatedUser.setPassword(request.getParameter("password"));
		updatedUser.setEmail(request.getParameter("email"));
		updatedUser.setBiography(request.getParameter("biography"));
		
		man.updateProfile(updatedUser);
		
		 return "viewprofile";
		
	}
}
