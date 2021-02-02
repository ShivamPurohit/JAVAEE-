package com.codesp07.RestApiImplementation.Service;

import java.util.List;


import com.codesp07.RestApiImplementation.model.User;


public interface UserService{

//	int oneTimePassword = 0;



	List<User> getUsers();

//	User getRollno(int Rollno);

	User getValidUser(String email, String pass);

	User addUser(User user);

	boolean sendEmail(int otp,String email);

	String getValidatedUserEmail(String email);


	boolean getValidEmail(String email);

	public User fetchByUserEmailId(String emailId);
	public User updatePasswd(User user,String passwd);


	String setPass(String pass, int Rollno);

	public User findByRollno(int Rollno);

//	int getOneTimePass();
//
//	User fetchByRollno(int rollno);

//	User findById(int Rollno);
//
//	void setOneTimePass(int oneTimePass);



}
