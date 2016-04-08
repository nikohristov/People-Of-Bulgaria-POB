package com.example.spring.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring.dao.IPostDAO;
import com.example.spring.model.post.Post;

@Controller
public class ShowPostsController {
	
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
		}
		
		model.addObject("postForShow",postsToView);
		
		return model;
	}
	
	private void orderBy(String string,List<Post> toSort){
		switch(string){
		case "Date":
			Collections.sort(toSort, new Comparator<Post>() {

				@Override
				public int compare(Post o1, Post o2) {
					return o1.getDateOfUpload().compareTo(o2.getDateOfUpload());
				}
			});
			break;
		case "Likes":
			Collections.sort(toSort, new Comparator<Post>() {

				@Override
				public int compare(Post o1, Post o2) {
					if(o1.getCountsOfLikes() > o2.getCountsOfLikes())
						return 1;
					else
						return -1;
				}
			});
			break;
		case "Popular":
			Collections.sort(toSort, new Comparator<Post>() {

				@Override
				public int compare(Post o1, Post o2) {
					if(o1.getCountsOfViews() > o2.getCountsOfViews())
						return 1;
					else
						return -1;
				}
			});
			break;
		}
	}
}
