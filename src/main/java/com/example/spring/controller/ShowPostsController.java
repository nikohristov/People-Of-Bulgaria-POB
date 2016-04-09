package com.example.spring.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring.dao.IPostDAO;
import com.example.spring.model.post.Post;

@Controller
public class ShowPostsController {
	
	private static final int posts_on_page = 9;
	private static final int count_pages = 5;
	
	@Autowired
	private IPostDAO postDao;
	
	@RequestMapping(value="/searchByTags",method = RequestMethod.GET)
	public ModelAndView generateByTags(HttpServletRequest request){
		
		ModelAndView model = new ModelAndView("categoryAndSearch");
		String inputForSearch = request.getParameter("searchTags");
		
		if(inputForSearch.trim().equals("")){
			model.addObject("postForShow", this.postDao.getAllPicsByDate());
			return model;
		}
		
		String[] tags = inputForSearch.split("\\s+");
		
		for(String str : tags){
			System.out.println(str);
		}
		
		List<Post> toShow = this.postDao.getAllPicsByTags(tags);
		this.orderBy("Date", toShow);
		model.addObject("postForShow", toShow);
		model.addObject("tags", tags);
		return model;
	}
	
	@RequestMapping(value="/searchBy",method = RequestMethod.GET)
	public ModelAndView generateByUserRequirements(HttpServletRequest request){
		
		ModelAndView model = new ModelAndView("categoryAndSearch");
		String byCategory = request.getParameter("category");
		String bySort = request.getParameter("sortBy");
		
		System.out.println(byCategory);
		System.out.println(bySort);
		List<Post> postsToView = null;
		if(byCategory.equals("All")){
			postsToView = this.postDao.getAllPics();
		}else{
			postsToView = this.postDao.getAllPicsByCategory(byCategory);
		}
		
		if(postsToView.size() != 0){
			this.orderBy(bySort, postsToView);
			if((postsToView.size()/posts_on_page) > count_pages){
				model.addObject("next", "true");
				model.addObject("end", count_pages);
			}else{
				model.addObject("next", "false");
				model.addObject("end", (postsToView.size()/posts_on_page)+1);
			}
		}
		model.addObject("begin", 1);
		model.addObject("page", 1);
		model.addObject("toShow",postsToView);
		request.getSession().setAttribute("searchingPosts", postsToView);
		return model;
	}
	
	@RequestMapping(value="/searchByPage",method = RequestMethod.GET)
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
		List<Post> allPosts = ((List<Post>)req.getSession().getAttribute("searchingPosts"));
		
		
		if(((allPosts.size())%posts_on_page) != 0){
			numberOfPages = (((allPosts.size())/posts_on_page) + 1);
		}else{
			numberOfPages = (((allPosts.size())/posts_on_page));
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
		return "categoryAndSearch";
	}
	
	private void orderBy(String string,List<Post> toSort){
		switch(string){
		case "Date":
			Collections.sort(toSort);
			break;
		case "Likes":
			Collections.sort(toSort, new Comparator<Post>() {

				@Override
				public int compare(Post o1, Post o2) {
					if(o1.getCountsOfLikes() > o2.getCountsOfLikes())
						return -1;
					else
						return 1;
				}
			});
			break;
		case "Popular":
			Collections.sort(toSort, new Comparator<Post>() {

				@Override
				public int compare(Post o1, Post o2) {
					if(o1.getCountsOfViews() > o2.getCountsOfViews())
						return -1;
					else
						return 1;
				}
			});
			break;
		}
	}
}
