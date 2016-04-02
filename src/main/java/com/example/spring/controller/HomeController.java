package com.example.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring.dao.IUserDAO;
import com.example.spring.model.user.User;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private IUserDAO userDao;
	
	@RequestMapping(value="/")
	public ModelAndView home() {
		//List<User> listUsers = userDao.list();
		ModelAndView model = new ModelAndView("logIn");
		//model.addObject("userList", listUsers);
		
		return model;
	}
	
	@RequestMapping(value="login",method = RequestMethod.POST)
	public String logIn(Model model,HttpServletRequest request){
		
		User user = this.validateIfUserExists(request);
		
		if(user == null){
			model.addAttribute("ErrorMessage","Wrong username or password !");
			System.out.println("false");
			return "logIn";
		}else{
			//send to main 
			System.out.println("true");
		}
		return "logIn";
	}
	
	private User validateIfUserExists(HttpServletRequest request){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		for(User user : this.userDao.getAllUsers()){
			if(user.getUsername().equals(username)){
				if(password.equals(user.getPassword())){
					return user;
				}else{
					return null;
				}
			}
		}
		return null;
	}
	
}
