package com.example.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.spring.dao.IPostDAO;
import com.example.spring.model.post.Post;

@Controller
public class HomePageController {
	
	private static final int posts_on_page = 9;
	private static final int count_pages = 5;
	
	@Autowired
	private IPostDAO postDao;
	
	@RequestMapping(value="/homepage",method=RequestMethod.GET)
	public String goToHomePage(Model model,HttpServletRequest req){
		System.out.println("LAAAAAAAAAAAAAAAAAA");
		List<Post> postsToShow = new ArrayList<Post>();
		List<Post> appPosts = ((List<Post>)req.getServletContext().getAttribute("allPostsByDate"));
		
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
		
		if((appPosts.size()/posts_on_page) > count_pages){
			model.addAttribute("next", "true");
			model.addAttribute("end", count_pages);
		}else{
			model.addAttribute("next", "false");
			model.addAttribute("end", (appPosts.size()/posts_on_page)+1);
		}
		
		
		
		model.addAttribute("toShow", postsToShow);
		model.addAttribute("begin", 1);
		model.addAttribute("page", 1);
		return "homepage";
	}
	
	@RequestMapping(value="/category",method=RequestMethod.GET)
	public String goToCategoryAndSearch(){
		return "categoryAndSearch";
	}
	
	@RequestMapping(value="/viewPage",method=RequestMethod.GET)
	public String goToPage(HttpServletRequest req,Model model){
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
		List<Post> allPosts = (List<Post>) req.getServletContext().getAttribute("allPostsByDate");
		
		
		if(((allPosts.size()%posts_on_page)) != 0){
			numberOfPages = ((allPosts.size()/posts_on_page) + 1);
		}else{
			numberOfPages = ((allPosts.size()/posts_on_page));
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
		if(indexOfFirstPost < allPosts.size() && indexOfLastPost > allPosts.size()){
			for(int i=indexOfFirstPost; i<allPosts.size(); i++){
				toShow.add(allPosts.get(i));
			}
		}else{
			for(int i=indexOfFirstPost; i<indexOfLastPost; i++){
				toShow.add(allPosts.get(i));
			}
		}
	
		model.addAttribute("page", page);
		model.addAttribute("toShow", toShow);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		model.addAttribute("next", next);
		return "homepage";
	}
	
}