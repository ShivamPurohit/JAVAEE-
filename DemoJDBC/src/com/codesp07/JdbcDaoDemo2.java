package com.codesp07;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcDaoDemo2 {
	public static void main(String args[]) {
		SchoolDao dao = new SchoolDao();
		School s2 = new School();
		s2.rollno = 4;
		s2.sname = "Brain";
		dao.connect();
		dao.addStudent(s2);
	}
}

class SchoolDao{
	Connection con = null;
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mario", "root", "root");

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public School getStudent(int rollno) {
		
		try {
			String query = "select sname from school where rollno="+rollno;
			School s = new School();
			s.rollno = rollno;
			
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
	
	public void addStudent(School s) {
		String query = "insert into school values (?,?)";
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, s.rollno);
			pst.setString(2, s.sname);
			pst.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

class School{
	int rollno;
	String sname;
	
}
