package com.example.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.spring.model.post.Post;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application file upload requests
 */


@Controller
public class PostController {

	private static final Logger logger = LoggerFactory
			.getLogger(PostController.class);

	/**
	 * Upload single file using Spring Controller
	 */
	
	
	 @RequestMapping(value = "/addPost", method = RequestMethod.POST)
	   public String addStudent(@ModelAttribute("SpringWeb")Post post, 
	   ModelMap model,@RequestParam("title") String title,
		@RequestParam("file") MultipartFile file) {
		 
	      model.addAttribute("title", post.getTitle());
	      model.addAttribute("description", post.getDescription());
	      model.addAttribute("id", post.getId());
	      
	      //save file
	      if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();

					// Creating the directory to store file
					File dir = new File("C:"+File.separator+"Users"+File.separator+"User"+File.separator+"Desktop");
					if (!dir.exists()){
						Boolean result=dir.mkdirs();
						System.out.println(result);
					}

					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath()
							+ File.separator + title);
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					logger.info("Server File Location="
							+ serverFile.getAbsolutePath());
					post.setPath(serverFile.getAbsolutePath());

					model.addAttribute("pic","You successfully uploaded file=" + title);
					model.addAttribute("path",post.getPath());
				} catch (Exception e) {
					model.addAttribute("pic","You failed to upload " + title + " => " + e.getMessage());
				}
			} else {
				model.addAttribute("pic","You failed to upload " + title
						+ " because the file was empty.");
			}
		

	      return "post";
	   }
}
