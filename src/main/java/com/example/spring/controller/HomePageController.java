package com.example.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePageController {
	
	@RequestMapping(value="/homepage",method=RequestMethod.GET)
	public String goToHomePage(){
		return "homepage";
	}
	
	@RequestMapping(value="/category",method=RequestMethod.GET)
	public String goToCategoryAndSearch(){
		return "categoryAndSearch";
	}
	
	
	
}
