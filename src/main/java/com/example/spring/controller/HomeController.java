package com.example.spring.controller;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
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

import com.example.spring.dao.IPostDAO;
import com.example.spring.dao.IUserDAO;
import com.example.spring.model.post.Post;
import com.example.spring.model.user.User;
import com.example.spring.model.user.UserManager;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static int posts_on_page = 9;
	
	@Autowired
	private IUserDAO userDao;
	
	@Autowired
	private IPostDAO postDao;
		
	
	@RequestMapping(value={"", "/", "/index"})
	public ModelAndView home(HttpServletRequest req) {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		ArrayList<Post> postsToShow = new ArrayList<Post>();
		List<Post> appPosts = new ArrayList<Post>();
		HashSet<Post> allInApp = new HashSet<Post>();
		if(req.getServletContext().getAttribute("allPostsByDate") == null){
			allInApp.addAll(this.postDao.getAllPicsByDate());
			req.getServletContext().setAttribute("allPostsByDate", allInApp);
		}
		
		allInApp = (HashSet<Post>) req.getServletContext().getAttribute("allPostsByDate");
		for(Iterator it = allInApp.iterator(); it.hasNext();){
			Post post = (Post) it.next();
			appPosts.add(post);
			System.out.println(post.getDateOfUpload());
		}
		
		Collections.sort(appPosts);
		
		if(appPosts.size() < posts_on_page && appPosts.size() > 0){
			for(int i=0; i<appPosts.size(); i++){
				postsToShow.add(appPosts.get(i));
			}
		}else if(appPosts.size() > 0){
			for(int i=0; i<posts_on_page; i++){
				postsToShow.add(appPosts.get(i));
			}
		}
		
		System.out.println(postsToShow.size());
		model.addObject("toShow", postsToShow);
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
			model.addAttribute("ErrorRegMessage", "The username already exists !");
			System.out.println("exists");
			return "register";
		} else if (this.userDao.checkIfEmailAddressExists(userForm.getEmail())) {
			model.addAttribute("ErrorRegMessage", "The email address is already exists !");
			return "register";
		}

		this.userDao.registerNewUser(userForm);
		model.addAttribute("ErrorMessage", "Congrats you was registered succesfully !");
		
		return "login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView goToLogIn() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String logIn(Model model, HttpServletRequest request) {

		User user = this.validateIfUserExists(request);

		if (user == null) {
			model.addAttribute("ErrorMessage", "Wrong username or password !");
			System.out.println("false");
			return "login";
		} else {
			request.getSession().setMaxInactiveInterval(10*60);
			request.getSession().setAttribute("loggedUser", new UserManager(user));
			return "redirect:homepage";
		}
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
