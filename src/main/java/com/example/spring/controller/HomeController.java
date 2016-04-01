package com.example.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
		List<User> listUsers = userDao.list();
		ModelAndView model = new ModelAndView("home");
		model.addObject("userList", listUsers);
		
		return model;
	}
	
}
