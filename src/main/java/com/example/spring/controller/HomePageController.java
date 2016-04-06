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
	
	private static final int posts_on_page = 12;
	
	@Autowired
	private IPostDAO postDao;
	
	@RequestMapping(value="/homepage",method=RequestMethod.GET)
	public String goToHomePage(Model model){
		System.out.println("LAAAAAAAAAAAAAAAAAA");
		List<Post> postsToShow = new ArrayList<Post>();
		postsToShow = this.postDao.getPicsForIndexPage();
		System.out.println(postsToShow.size());
		model.addAttribute("toShow", postsToShow);
		model.addAttribute("begin", 1);
		model.addAttribute("page", 1);
		return "homepage";
	}
	
	@RequestMapping(value="/category",method=RequestMethod.GET)
	public String goToCategoryAndSearch(){
		return "categoryAndSearch";
	}
	
	@RequestMapping(value="/previous",method=RequestMethod.GET)
	public String goToPageWithPosts(HttpServletRequest req){
		
		List<Post> allPosts = (List<Post>) req.getServletContext().getAttribute("allPostsByDate");
		String begin = req.getParameter("begin");
		int start = Integer.parseInt(begin);
		int page = start - 1;
		start -= 5;
		List<Post> toShow = new ArrayList<Post>();
		
		int indexOfFirstPost = page*posts_on_page-12;
		int indexOfLastPost = page*posts_on_page;
		
		for(int i=indexOfFirstPost; i<indexOfLastPost; i++){
			toShow.add(allPosts.get(i));
		}
		
		req.setAttribute("begin", start);
		req.setAttribute("page", page);
		req.setAttribute("toShow", toShow);
		return "homepage";
	}
	
	@RequestMapping(value="/viewPage",method=RequestMethod.GET)
	public String goToPage(HttpServletRequest req){
		String page = req.getParameter("pageId");
		int pageToView = Integer.parseInt(page);
		int indexOfFirstPost = pageToView*posts_on_page-12;
		int indexOfLastPost = pageToView*posts_on_page;
		
		System.out.println(page);
		System.out.println(indexOfFirstPost);
		System.out.println(indexOfLastPost);
		
		List<Post> allPosts = (List<Post>) req.getServletContext().getAttribute("allPostsByDate");
		List<Post> toShow = new ArrayList<Post>();
		if(indexOfFirstPost < allPosts.size()){
			for(int i=indexOfFirstPost; i<indexOfLastPost; i++){
				toShow.add(allPosts.get(i));
			}
		}
		
		req.setAttribute("page", page);
		req.setAttribute("toShow", toShow);
		return "redirect:homepage";
	}
	
}
