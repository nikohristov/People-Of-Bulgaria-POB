package com.example.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.spring.dao.IPostDAO;

@Controller
public class ShowPostsController {
	
	@Autowired
	private IPostDAO postDao;
	
	
}
