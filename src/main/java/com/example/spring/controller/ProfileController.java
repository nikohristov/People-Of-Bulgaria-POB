package com.example.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.spring.dao.IUserDAO;
import com.example.spring.model.post.Post;
import com.example.spring.model.user.User;
import com.example.spring.model.user.UserManager;

@Controller
public class ProfileController {
	
	private static final int posts_on_page = 8;
	private static final int count_pages = 5;
	
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
	
	
	@RequestMapping(value="/myPosts", method=RequestMethod.GET)
	public String showMyPosts(HttpServletRequest req){
		User user = ((UserManager)req.getSession().getAttribute("loggedUser")).getLoggedUser();
		int id = 0;
		try{
			 id = Integer.parseInt(req.getParameter("Id"));
		}catch(NumberFormatException e){
			 id = user.getId();
		}
		
		String searchCategory = req.getParameter("category");
		System.out.println(searchCategory);
		try{
			searchCategory.length();
		}catch(NullPointerException e){
			searchCategory = "All";
		}
		
		List<Post> postsToView = new ArrayList<Post>();
		if(id != user.getId()){
			User userToView = this.userDao.getUser(id);
			postsToView.addAll(userToView.getPostsOfUser());
			System.out.println(userToView.getPostsOfUser().size());
			req.setAttribute("userProfileToView", userToView);
		}else{
			postsToView.addAll(user.getPostsOfUser());
			System.out.println(user.getPostsOfUser().size());
			req.setAttribute("userProfileToView", user);
		}
		
		System.out.println(postsToView.size());
		
		if(!searchCategory.equals("All")){
			List<Post> postsToViewByCat = new ArrayList<Post>();
			for(int i=0; i<postsToView.size(); i++){
				if(postsToView.get(i).getCategory().equals(searchCategory)){
					System.out.println(postsToView.get(i).getCategory());
					postsToViewByCat.add(postsToView.get(i));
				}
			}
			req.getSession().setAttribute("postsToView", postsToViewByCat);
			if((postsToViewByCat.size()/posts_on_page) > count_pages){
				req.setAttribute("next", "true");
				req.setAttribute("end", count_pages);
			}else{
				req.setAttribute("next", "false");
				req.setAttribute("end", (postsToViewByCat.size()/posts_on_page)+1);
			}
			req.setAttribute("showPartList", postsToViewByCat);
		}else{
			req.getSession().setAttribute("postsToView", postsToView);
			if((postsToView.size()/posts_on_page) > count_pages){
				req.setAttribute("next", "true");
				req.setAttribute("end", count_pages);
			}else{
				req.setAttribute("next", "false");
				if(postsToView.size()%posts_on_page == 0){
					req.setAttribute("end", (postsToView.size()/posts_on_page));
				}else{
				req.setAttribute("end", (postsToView.size()/posts_on_page)+1);
				}
			}
			req.setAttribute("showPartList", postsToView);
		}
		
		
		req.setAttribute("begin", 1);
		req.setAttribute("page", 1);
		req.getSession().setAttribute("categoryToShow", searchCategory);
		return "myposts";
	}
	
	@RequestMapping(value="/myPostsPage", method=RequestMethod.GET)
	public String showMyPostsByPage(HttpServletRequest req){
		String page = req.getParameter("pageId");
		System.out.println(req.getParameter("b"));
		System.out.println(req.getParameter("e"));
		int begin = Integer.parseInt(req.getParameter("b"));
		int end = Integer.parseInt(req.getParameter("e"));
		boolean next = Boolean.parseBoolean(req.getParameter("next"));
		int pageToView = Integer.parseInt(page);
		int indexOfFirstPost = pageToView*posts_on_page-posts_on_page;
		int indexOfLastPost = pageToView*posts_on_page;
		int numberOfPages = 0;
		List<Post> myposts = (List<Post>) req.getSession().getAttribute("postsToView");
		
		User user = ((UserManager)req.getSession().getAttribute("loggedUser")).getLoggedUser();
		int id = 0;
		try{
			 id = Integer.parseInt(req.getParameter("Id"));
		}catch(NumberFormatException e){
			 id = user.getId();
		}
		
		if(id != user.getId()){
			User userToView = this.userDao.getUser(id);
			req.setAttribute("userProfileToView", userToView);
		}else{
			req.setAttribute("userProfileToView", user);
		}
		
		
		if(((myposts.size()%posts_on_page)) != 0){
			numberOfPages = ((myposts.size()/posts_on_page) + 1);
		}else{
			numberOfPages = ((myposts.size()/posts_on_page));
		}
		
		if(pageToView > end){
			begin  = pageToView;
			if(numberOfPages > count_pages+(pageToView-1)){
				next = true;
				end = count_pages+(pageToView-1);
			}else{
				next = false;
				end = (numberOfPages);
			}
		}else if(pageToView < begin){
			end = pageToView;
			begin -= count_pages;
			next = true;
		}
		
		System.out.println(page);
		System.out.println(indexOfFirstPost);
		System.out.println(indexOfLastPost);
		
		List<Post> toShow = new ArrayList<Post>();
		if(indexOfFirstPost < myposts.size() && indexOfLastPost > myposts.size()){
			for(int i=indexOfFirstPost; i<myposts.size(); i++){
				toShow.add(myposts.get(i));
			}
		}else{
			for(int i=indexOfFirstPost; i<indexOfLastPost; i++){
				toShow.add(myposts.get(i));
			}
		}
	
		req.setAttribute("page", page);
		req.setAttribute("begin", begin);
		req.setAttribute("end", end);
		req.setAttribute("next", next);
		req.setAttribute("showPartList", toShow);
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
	
	@RequestMapping(value="/followers",method=RequestMethod.GET)
	public String showFollowers(HttpServletRequest req){
		User user = ((UserManager)req.getSession().getAttribute("loggedUser")).getLoggedUser();
		int id = 0;
		try{
			 id = Integer.parseInt(req.getParameter("Id"));
		}catch(NumberFormatException e){
			 id = user.getId();
		}
		
		List<User> followersToView = new ArrayList<User>();
		if(id != user.getId()){
			User userToView = this.userDao.getUser(id);
			req.setAttribute("userProfileToView", userToView);
			followersToView.addAll(userToView.getUsersWhoFollower());
			req.setAttribute("usersToView", followersToView);
		}else{
			
			followersToView.addAll(user.getUsersWhoFollower());
			System.out.println(user.getUsersWhoFollower().size());
			System.out.println(followersToView.size());
			req.setAttribute("usersToView", followersToView);
			req.setAttribute("userProfileToView", user);
		}
		
		req.setAttribute("isFollowers", true);
		return "followersAndfollowing";
	}
	
	@RequestMapping(value="/following",method=RequestMethod.GET)
	public String showFollowing(HttpServletRequest req){
		User user = ((UserManager)req.getSession().getAttribute("loggedUser")).getLoggedUser();
		int id = 0;
		try{
			 id = Integer.parseInt(req.getParameter("Id"));
		}catch(NumberFormatException e){
			 id = user.getId();
		}
		
		List<User> followingToView = new ArrayList<User>();
		if(id != user.getId()){
			User userToView = this.userDao.getUser(id);
			req.setAttribute("userProfileToView", userToView);
			followingToView.addAll(userToView.getUsersWhoFollowed());
			req.setAttribute("usersToView", followingToView);
		}else{
			
			followingToView.addAll(user.getUsersWhoFollowed());
			System.out.println(user.getUsersWhoFollowed().size());
			System.out.println(followingToView.size());
			req.setAttribute("usersToView", followingToView);
			req.setAttribute("userProfileToView", user);
		}
		req.setAttribute("isFollowers", false);
		return "followersAndfollowing";
	}
}
