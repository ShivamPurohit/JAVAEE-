package com.codesp07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DemoClass {

	public static void main(String args[]) throws Exception {
		
		String url = "jdbc:mysql://localhost:3306/mario";
		String uname = "root";
		String pass = "root";
		int userid = 5;
		String username = "Shivam";
		String query = "insert into student values(?,?);";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,uname,pass);
		PreparedStatement st = con.prepareStatement(query);
		st.setInt(1,userid);
		st.setString(2, username);
		int count = st.executeUpdate();
		System.out.println(count + "row/s affected");
		
		st.close();
		con.close();
			
	}

}
