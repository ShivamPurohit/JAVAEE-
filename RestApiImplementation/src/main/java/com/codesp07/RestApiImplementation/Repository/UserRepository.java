package com.codesp07.RestApiImplementation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codesp07.RestApiImplementation.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

//	public User findByUid(int rollno);

//	User findByUid(int rollno);

//	User fingByRollno(int rollno);
	public User findByEmail(String emailId);
	
	public User findByrollno(int rollno);
}
