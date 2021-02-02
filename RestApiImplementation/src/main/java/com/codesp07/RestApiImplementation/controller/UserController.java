package com.codesp07.RestApiImplementation.controller;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codesp07.RestApiImplementation.model.Posts;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import com.codesp07.RestApiImplementation.model.Posts;
import com.codesp07.RestApiImplementation.model.User;

import io.swagger.annotations.ApiOperation;

import com.codesp07.RestApiImplementation.ProfileHelper.ProfileUploadHelper;
import com.codesp07.RestApiImplementation.Service.UserService;
import com.codesp07.RestApiImplementation.Service.userPosts.UserPostService;
//import com.codesp07.RestApiImplementation.dto.PostsDone;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userservice;
	
	@Autowired
	private ProfileUploadHelper fileUploadHelper;

	@Autowired
    UserPostService userpostservice;
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return this.userservice.getUsers();
	}
	
	@PostMapping("/register")
	public String addUser(@RequestBody User user)
{
		userservice.addUser(user);
		
		return "User registered Successfully!";
}
	
	@GetMapping("/login")
	public User getValidUser(@RequestParam String email,@RequestParam String pass)
	{
		return this.userservice.getValidUser(email,pass);
	}
	
	@GetMapping("/forgotPassword")
	public String forgotPassword(@RequestParam String email) {
		String checkUserExist = this.userservice.getValidatedUserEmail(email);
		return checkUserExist;
	}
	
	
	 @ApiOperation(value = "Reset Password - Generate OTP ", tags = "Reset Password")
	   @PostMapping("/resetPasswrod")
	    public ResponseEntity<String> resetPasswd(@RequestParam String email)
	    { 
		  
		    User userObj=userservice.fetchByUserEmailId(email);
	        
		   if(userObj!=null)
		   {
				int myotp=(int) (Math.random()*9000)+1000;
				userObj.setOtp(myotp);
				  LocalTime time=LocalTime.now(); 
		          LocalDate date=LocalDate.now();
		   
	 	          userObj.setLocalDate(date);
	 	          userObj.setLocalTime(time);
		      
				userservice.addUser(userObj); 
			    boolean status = userservice.sendEmail(myotp,email);
			    if(!status) 
				{
			    return new ResponseEntity<>("Otp is not sending",HttpStatus.BAD_REQUEST);
				}
				
		          
				return new ResponseEntity<>("Otp is sending ",HttpStatus.OK);				
		   }

				return new ResponseEntity<>("Email is invaild",HttpStatus.BAD_REQUEST);
			  
	    }
	
	  @ApiOperation(value = "Confirm OTP ", tags = "Reset Password")
		   
	   @PostMapping("/resetPassword/verify") 
	    public ResponseEntity<String> resetPasswdWithVerify(@RequestParam String email,@RequestParam int otp, @RequestParam String passwd)
	    {
		  
		   User userObj=userservice.fetchByUserEmailId(email);
		   if (userObj==null)
			   return new ResponseEntity<>("Incorrect User",HttpStatus.BAD_REQUEST); 
		       int myotp=userObj.getOtp();
			  int t=LocalTime.now().minusMinutes(1).compareTo(userObj.getLocalTime());
		      int d=userObj.getLocalDate().compareTo(LocalDate.now());
		      
		      if(d!=0 || t>=0)
			  return new ResponseEntity<>("Otp is expire",HttpStatus.BAD_REQUEST);
            		   
		     if(myotp!=otp)
		    return new ResponseEntity<>("Incorrect otp",HttpStatus.BAD_REQUEST);
		   
		   // reset pass
		    userservice.updatePasswd(userObj,passwd);
          
		   return new ResponseEntity<>("Successfull reset Password ",HttpStatus.OK);
	   }
	
//	 @ApiOperation(value = "Login User but not remember password ", tags = "Forgot Password")
//	   @PostMapping("/resetPassword")
//	    public ResponseEntity<String> resetPasswd(@RequestParam String email,HttpServletRequest request)
//	    { 
//		    HttpSession session = request.getSession();
//	        session.setAttribute("email",email);
//		    User userObj=userservice.fetchByUserEmailId(email);
//	        
//		   if(userObj!=null)
//		   {
//			   session.setAttribute("userObj",userObj);
//	
//				int myotp=(int)(Math.random() * (8017)) + 2083;
//				String myop=""+myotp;
//			    session.setAttribute("myotp",myop);
//			    	  
////			    boolean status=emailservice.SendEmail("Otp is  "+myotp ,email) ; 
//			    boolean status = userservice.sendEmail(myotp,email);
//				if(!status) 
//				{
//			    return new ResponseEntity<>("Otp is not sending",HttpStatus.BAD_REQUEST);
//				}
//				
//				return new ResponseEntity<>("Otp is sending ",HttpStatus.OK);				
//		   }
//
//				return new ResponseEntity<>("Email is invaild",HttpStatus.BAD_REQUEST);
//			  
//	    }
//	
//	  @ApiOperation(value = "Login User but not remember password ", tags = "Forgot Password")
//		   
//	   @PostMapping("/confirmPassword/verify") 
//	    public ResponseEntity<String> resetPasswdWithVerify(@RequestParam int otp, @RequestParam String passwd,HttpServletRequest request)
//	    {
//		   HttpSession session = request.getSession(false);
//		   if(session==null)
//		   {
//			   return new ResponseEntity<>("Not Authenticate",HttpStatus.OK);
//		   }
//		  
//		   int myotp=Integer.parseInt((String)session.getAttribute("myotp"));
//		   User userObj=(User)session.getAttribute("userObj");
//		   
//		   if(myotp!=otp)
//		   return new ResponseEntity<>("Incorrect otp",HttpStatus.BAD_REQUEST);
//		   
//		   // reset pass
//		    userservice.updatePasswd(userObj,passwd);
//		    // session close
//		    session.invalidate();
//		   return new ResponseEntity<>("Successfull reset Password ",HttpStatus.OK);
//	   }
//	
	
	
	
	
	
	
//	
//	@PutMapping("/upload-profile")
//	public ResponseEntity<String> save(@RequestParam("file") MultipartFile file){
////		uploadProfile
//	try {
//		System.out.println(file.getOriginalFilename());
//		System.out.println(file.getSize());
//		System.out.println(file.getContentType());
//		System.out.println(file.getName());
////		
////		byte[] fileContent = FileUtils.readFileToByteArray(new File(file));
////		String encodedString = Base64.getEncoder().encodeToString(fileContent);
////		
//		if(file.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
//		}
//		
//		if(!file.getContentType().equals("image/jpeg")) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only JPEG content type is allowed");
//		}
//		
//		
//		
//		boolean f = fileUploadHelper.uploadFile(file);
//		
//		if(f) {
//		return ResponseEntity.ok("Profile photo is successfully updated");
////		return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
//		}
//	}
//	catch(Exception e) {
//		e.printStackTrace();
//	}
//		
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong! Try again");
//	}
//	
	  
	  
	 @ApiOperation(value = "Create New Post ", tags = "Post")
     @PostMapping("/create_post")
     public ResponseEntity<String> createUserPost(@RequestParam int Rollno,@RequestParam String title,@RequestParam String content)
     {
             
    	 User userObj=userservice.findByRollno(Rollno);
    	 Posts userpost=new Posts(title,content);
    	 userObj.getPosts().add(userpost);
    	 userservice.addUser(userObj);
	         return new ResponseEntity<>("Successfull create post ",HttpStatus.OK);
     }
 
     
 @ApiOperation(value = "Retrive all post ", tags = "Post")
 @GetMapping("/all_post")
 public List<Posts> getAllPost(@RequestParam int Rollno) throws Exception
 {
   	
	User userObj=userservice.findByRollno(Rollno);
	 
	 
   List<Posts> userpost=userObj.getPosts();
   return userpost;  
 }

	
}
