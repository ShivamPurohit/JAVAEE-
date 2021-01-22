package com.codesp07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcDaoDemo {
	public static void main(String args[]) {
		SchoolDao dao = new SchoolDao();
		School s1 = dao.getStudent(2);
		System.out.println(s1.sname);
	}
}

class SchoolDao{
	public School getStudent(int rollno) {
		
		try {
			String query = "select sname from school where rollno="+rollno;
			School s = new School();
			s.rollno = rollno;
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mario", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			String name = rs.getString(1);
			s.sname = name;
			return s;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	
	}
}

class School{
	int rollno;
	String sname;
	
}
