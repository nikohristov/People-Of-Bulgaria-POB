package com.example.spring.controller;


import com.example.spring.model.post.Post;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsInputChannel;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CloudController {
	
	public static final boolean SERVE_USING_BLOBSTORE_API = false;

	  /**
	   * This is where backoff parameters are configured. Here it is aggressively retrying with
	   * backoff, up to 10 times but taking no more that 15 seconds total to do so.
	   */
	  private final GcsService gcsService = GcsServiceFactory.createGcsService(new RetryParams.Builder()
	      .initialRetryDelayMillis(10)
	      .retryMaxAttempts(10)
	      .totalRetryPeriodMillis(15000)
	      .build());

	  /**Used below to determine the size of chucks to read in. Should be > 1kb and < 10MB */
	  private static final int BUFFER_SIZE = 2 * 1024 * 1024;

	  @RequestMapping(value="/postCloud")
		public String post(HttpServletRequest req,@RequestParam("title") String title) {
		  
			 return "redirect:gcs/western-figure-126418/"+title;
			
		}
	  
	  
	@RequestMapping(value="/gcs/western-figure-126418/",method=RequestMethod.POST)
	public String upload(@ModelAttribute("SpringWeb")Post post, ModelMap model,@RequestParam("title") String title,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		 System.out.println(req.getRequestURI());
		  model.addAttribute("title", post.getTitle());
	      model.addAttribute("description", post.getDescription());
	      model.addAttribute("category",post.getCategory());
	      
		GcsOutputChannel outputChannel = gcsService.createOrReplace(getFileName(req,title), GcsFileOptions.getDefaultInstance());
	    copy(req.getInputStream(), Channels.newOutputStream(outputChannel));
	    
	    model.addAttribute("message","You successfully uploaded file=" + title);
	    return "clouddemo";
	  }

	  private GcsFilename getFileName(HttpServletRequest req, String title) {
	    String[] splits = req.getRequestURI().split("/", 4);
	    if (!splits[0].equals("") || !splits[2].equals("gcs")) {
	      throw new IllegalArgumentException("The URL is not formed as expected. " +
	          "Expecting /gcs/<bucket>/<object>");
	    }
	    System.out.println(title + " " + splits[3]);
	    return new GcsFilename(splits[3], title);
	  }

	  /**
	   * Transfer the data from the inputStream to the outputStream. Then close both streams.
	   */
	  private void copy(InputStream input, OutputStream output) throws IOException {
	    try {
	      byte[] buffer = new byte[BUFFER_SIZE];
	      int bytesRead = input.read(buffer);
	      while (bytesRead != -1) {
	        output.write(buffer, 0, bytesRead);
	        bytesRead = input.read(buffer);
	      }
	    } finally {
	      input.close();
	      output.close();
	    }
	  }
	  
	  
	  
	  @RequestMapping(value="/getFromCloud",method=RequestMethod.GET)
	  public void getFilesFromCloud(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		    GcsFilename fileName = getFileName(req,"");
		    if (SERVE_USING_BLOBSTORE_API) {
		      BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		      BlobKey blobKey = blobstoreService.createGsBlobKey(
		          "/gs/" + fileName.getBucketName() + "/" + fileName.getObjectName());
		      blobstoreService.serve(blobKey, resp);
		    } else {
		      GcsInputChannel readChannel = gcsService.openPrefetchingReadChannel(fileName, 0, BUFFER_SIZE);
		      copy(Channels.newInputStream(readChannel), resp.getOutputStream());
		    }
		  }
	}

