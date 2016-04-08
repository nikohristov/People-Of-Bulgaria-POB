package com.example.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.spring.model.user.User;
import com.example.spring.dao.IPostDAO;
import com.example.spring.dao.IUserDAO;
import com.example.spring.model.comment.Comment;
import com.example.spring.model.post.Post;
import com.example.spring.model.user.UserManager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;
import java.util.HashSet;
import java.util.List;



@Controller
public class PostController {
	
	@Autowired
	private IPostDAO postDAO;
	@Autowired
	private IUserDAO userDAO;

   //adding a comment to post
	@RequestMapping(value = "getPost/comment/{pic_id}", method = RequestMethod.POST)
	public String addCommentOnPost(@Valid @ModelAttribute("comment") Comment comment,ModelMap model, BindingResult result,@PathVariable("pic_id") Integer pic_id,HttpServletRequest request){
		Post currentPost=this.postDAO.getPost(pic_id);
		if (!result.hasErrors()) {
			comment.setDateOfUpload(new Date());
			UserManager man= (UserManager)request.getSession().getAttribute("loggedUser");
			comment.setUsername(man.getLoggedUser().getUsername());
			comment.setPost(currentPost);
			man.commentOnPost(currentPost, comment, this.postDAO);
			currentPost=this.postDAO.getPost(pic_id);
			
		}
		
		

		return "forward:/getPost/"+pic_id;
		
	}
	//getting image by id and adding a comment object
	@RequestMapping(value="/getPost",method = RequestMethod.GET)
	public String getPost(Model model,HttpServletRequest request){
		 int id = Integer.parseInt(request.getParameter("picId"));	
		 Post currentPost=this.postDAO.getPost(id);
		 request.getSession().setAttribute("post",currentPost);
		 model.addAttribute("post",currentPost);
	     Comment comment= new Comment();
	     User user = this.userDAO.getUser(currentPost.getUser().getId());
	     model.addAttribute("userOfPost", user);
	     model.addAttribute("comment",comment);
	     return "post";
	}
	//go to upload.jsp
	@RequestMapping(value="/upload", method = RequestMethod.GET)
	public ModelAndView post(Model model) {
		HashSet<String> categories=generateCategories();
		model.addAttribute("categories",categories);
		return new ModelAndView("upload", "command", new Post());
		
	}
	
    //post an image 
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	   public String addStudent(@ModelAttribute("SpringWeb")Post post, 
	   ModelMap model,@RequestParam("title") String title,
		@RequestParam("file") MultipartFile file, HttpServletRequest req) {
		model.addAttribute("post",post);
	      
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
					
					//setting post characteristics and uploading post
					UserManager man=(UserManager) req.getSession().getAttribute("loggedUser");
					User loggedUser=man.getLoggedUser();
					post.setUser(loggedUser);
					post.setDateOfUpload(new Date());
					post.setPath(f.getAbsolutePath());
					this.userDAO.uploadPost(loggedUser, post);
				
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
		
	      ((List<Post>)req.getServletContext().getAttribute("allPostsByDate")).add(post);
	      return "forward:/getPost/"+post.getId();
	   }

	private HashSet<String> generateCategories() {
		HashSet<String> categories=new HashSet<>();
		categories.add("Nature");
		categories.add("People");
		categories.add("Pets");
		return categories;
	}

	
}
