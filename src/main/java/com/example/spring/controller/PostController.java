package com.example.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.spring.model.user.User;
import com.example.spring.dao.IPostDAO;
import com.example.spring.dao.IUserDAO;
import com.example.spring.model.post.Post;
import com.example.spring.model.user.UserManager;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;


import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;



@Controller
public class PostController {
	
	@Autowired
	private IPostDAO postDAO;


	@RequestMapping(value="/getPost")
	public String getPost(){
		
		return "post";
	}
	
	@RequestMapping(value="/upload")
	public ModelAndView post() {
		 return new ModelAndView("upload", "command", new Post());
		
	}
	
	 @RequestMapping(value = "/post", method = RequestMethod.POST)
	   public String addStudent(@ModelAttribute("SpringWeb")Post post, 
	   ModelMap model,@RequestParam("title") String title,
		@RequestParam("file") MultipartFile file, HttpServletRequest req) {
		 
	      model.addAttribute("title", post.getTitle());
	      model.addAttribute("description", post.getDescription());
	      model.addAttribute("category",post.getCategory());
	      
	      //save file
	      if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();

					// Creating the directory to store file
					String path = req.getSession().getServletContext().getRealPath("/resources/");
					File f = new File(path+File.separator+title+".png");
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(f));
					stream.write(bytes);
					stream.close();
					configureAndAddPost(req,post,f);
				
					System.out.println(post.getPath());
					model.addAttribute("message","You successfully uploaded file=" + title);
					model.addAttribute("path",post.getPath());
					
				} catch (Exception e) {
					model.addAttribute("message","You failed to upload " + title + " => " + e.getMessage());
					System.out.println(e.getMessage());
				}
			} else {
				model.addAttribute("message","You failed to upload " + title
						+ " because the file was empty.");
			}
		

	      return "post";
	   }

	 
	private void configureAndAddPost(HttpServletRequest req, Post post,File f) {
		post.setDateOfUpload(new Date());
		post.setPath(f.getAbsolutePath());
		UserManager man=(UserManager) req.getSession().getAttribute("loggedUser");
		User loggedUser=man.getLoggedUser();
		post.setUser(loggedUser);
		loggedUser.getPostsOfUser().add(post);
		this.postDAO.addPost(post);
		
	}
}
