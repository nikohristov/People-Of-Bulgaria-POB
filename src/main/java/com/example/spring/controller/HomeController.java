package com.example.spring.controller;



import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring.dao.IUserDAO;
import com.example.spring.model.post.Post;
import com.example.spring.model.user.User;
import com.example.spring.model.user.UserManager;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private IUserDAO userDao;
	
	@RequestMapping(value="/home")
	public String getHomepage(){
		return "homepage";
	}

	@RequestMapping(value = "/gg")
	public ModelAndView home() {
		// List<User> listUsers = userDao.list();
		ModelAndView model = new ModelAndView("logIn");
		// model.addObject("userList", listUsers);

		return model;
	}
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String getPojoForRegister(Model model) {
		User user = new User();
		model.addAttribute("userForm", user);
		return "register";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String doLogin(@Valid @ModelAttribute("userForm") User userForm, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "register";
		}

		if (this.userDao.checkIfUsernameExists(userForm.getUsername())) {
			model.addAttribute("ErrorRegMessage", "This username already exists !");
			System.out.println("exists");
			return "register";
		} else if (this.userDao.checkIfEmailAddressExists(userForm.getEmail())) {
			model.addAttribute("ErrorRegMessage", "The email address is already exists !");
			return "register";
		}

		this.userDao.registerNewUser(userForm);
		model.addAttribute("ErrorMessage", "Congrats you were registered succesfully !");
		
		return "logIn";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String logIn(Model model, HttpServletRequest request) {

		User user = this.validateIfUserExists(request);

		if (user == null) {
			model.addAttribute("ErrorMessage", "Wrong username or password !");
			System.out.println("false");
			return "logIn";
		} else {
			// send to main
			request.getSession().setAttribute("loggedUser", new UserManager(user));
			System.out.println("true");
		}
		return "logIn";
	}
	private User validateIfUserExists(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		for (User user : this.userDao.getAllUsers()) {
			if (user.getUsername().equals(username)) {
				if (password.equals(user.getPassword())) {
					return user;
				} else {
					return null;
				}
			}
		}
		return null;
	}

}
