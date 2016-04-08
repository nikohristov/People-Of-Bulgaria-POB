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
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.spring.dao.IPostDAO;
import com.example.spring.dao.IUserDAO;
import com.example.spring.model.comment.Comment;
import com.example.spring.model.post.Post;
import com.example.spring.model.post.Tag;
import com.example.spring.model.user.UserManager;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;
import java.util.HashSet;



@Controller
public class PostController {
	
	@Autowired
	private IPostDAO postDAO;
	@Autowired
	private IUserDAO userDAO;
	
	private static String bucketName = "bob-final-project";
	private static String folderName = "testfolder";
	private AWSCredentials credentials = new BasicAWSCredentials(
				"AKIAIONDNGRXYQ7CENBQ", 
				"dlwceclkLHj1wylYjABl5oEjmsZjeaLLsj3Yy6UU");
	
    private  AmazonS3 s3client = new AmazonS3Client(credentials);

   //adding a comment to post
	@RequestMapping(value = "getPost/comment/{pic_id}", method = RequestMethod.GET)
	public String addCommentOnPost(@Valid @ModelAttribute("comment") Comment comment,ModelMap model, BindingResult result,@PathVariable("pic_id") Integer pic_id,HttpServletRequest request){
		Post currentPost=this.postDAO.getPost(pic_id);
		if (!result.hasErrors()) {
			comment.setDateOfUpload(new Date());
			UserManager man= (UserManager)request.getSession().getAttribute("loggedUser");
			comment.setUsername(man.getLoggedUser().getUsername());
			comment.setPost(currentPost);
			man.commentOnPost(currentPost, comment, this.postDAO);
			currentPost=this.postDAO.getPost(pic_id);
			request.getSession().setAttribute("uploadId", currentPost.getId());
			
		}
		
		
		return "forward:/getPost";
		
	}
	//getting image by id and adding a comment object
	@RequestMapping(value="/getPost",method = RequestMethod.GET)
	public String getPost(Model model,HttpServletRequest request){
		int id = 0;
		try{
		  id = Integer.parseInt(request.getParameter("picId"));
		}catch(NumberFormatException e){
		  id = (int) request.getSession().getAttribute("uploadId");	
		}
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	   public String post(@ModelAttribute("SpringWeb")Post post, 
	   ModelMap model,@RequestParam("title") String title,
		@RequestParam("file") MultipartFile file, HttpServletRequest req) {
		model.addAttribute("post",post);
		
	      
	      //save file
	      if (!file.isEmpty()) {
				try {
					
					
					
					//setting post characteristics and uploading post
					//setting tags
					String[] tags=req.getParameter("tags").replace(".", " ").replace(",", " ").replace("?", " ").replace("!"," ").split(" ");
					for(String tag: tags){
						Tag t= new Tag();
						t.getPostsOfTag().add(post);
						t.setTitle(tag);
						post.getTagsOfPost().add(t);
					}
					System.out.println("tags filling");
					UserManager man=(UserManager) req.getSession().getAttribute("loggedUser");
					User loggedUser=man.getLoggedUser();
					post.setUser(loggedUser);
					post.setDateOfUpload(new Date());
					this.userDAO.uploadPost(loggedUser, post);
					System.out.println("post uploading");
					
					//saving file to folder and to cloud
					byte[] bytes = file.getBytes();

					String path = req.getSession().getServletContext().getRealPath("/resources/");
					File f = new File(path+File.separator+post.getId()+".jpg");
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(f));
					stream.write(bytes);
					stream.flush();
					stream.close();
				
					saveToCloud(f,post.getId());
					System.out.println("saved to cloud");
					
					
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
		

	      req.getSession().setAttribute("uploadId", post.getId());
	      //((List<Post>)req.getServletContext().getAttribute("allPostsByDate")).add(post);
	      return "redirect:getPost";
	   }

	
	private void saveToCloud(File file, int id) {
		
		String fileName = id+ ".jpg";
	    s3client.putObject(new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
	    
	}
	private void getPicFromCloud(int id,String path) {
		String fileName=id+".jpg";
		s3client.getObject( new GetObjectRequest(bucketName, fileName),new File(path));
		
	}
	private String download(){
		GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, "secret_plans.txt");
		return s3client.generatePresignedUrl(request).toString();
	}
	
	private HashSet<String> generateCategories() {
		HashSet<String> categories=new HashSet<>();
		categories.add("Nature");
		categories.add("People");
		categories.add("Pets");
		return categories;
	}
	
}
