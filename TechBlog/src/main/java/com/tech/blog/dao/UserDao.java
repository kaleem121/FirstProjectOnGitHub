package com.tech.blog.dao;
import java.sql.*;

import com.tech.blog.entities.User;
public class UserDao {

	private Connection con;

	public UserDao(Connection con) {
		super();
		this.con = con;
	}
	
	//method to insert user to database
	
	
	
	public boolean saveUser(User user)
	{
		boolean f=false;
		
		try 
		{
		 //user-->database
			String query="insert into user(name,email,password,gender,about)values(?,?,?,?,?)";
			PreparedStatement pstmt=this.con.prepareStatement(query);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getGender());
			pstmt.setString(5, user.getAbout());
			
			pstmt.executeUpdate();
			f=true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return f;
	}
	
	// get user by userEmail and userPassword
	
	public User getUserByEmailAndPassword(String email, String password)
	{
		User user=null;
		
		
		
		try {
			String query="select*from user where email=? and password=?";
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				user=new User();
				
				//fetching from DB and setting it into user entity
				
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getString("gender"));
				user.setAbout(rs.getString("about"));
				user.setId(rs.getInt("id"));
				user.setDateTime(rs.getTimestamp("rdate"));
				user.setProfile(rs.getString("profile"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	public boolean updateUser(User user)
	{
		boolean f= false;
		try {
			String query = "update user set name=?,email=?,password=?,gender=?,about=?,profile=? where id=?";
			PreparedStatement pstmt =con.prepareStatement(query);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getGender());
			pstmt.setString(5, user.getAbout());
			pstmt.setString(6,user.getProfile());
			pstmt.setInt(7, user.getId());
			pstmt.executeUpdate();
			f=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public User getUserByUserId(int UserId) {
		User user = null;
		
		String query = "select * from user where id =?";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, UserId);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
			user = new User();
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setGender(rs.getString("gender"));
			user.setAbout(rs.getString("about"));
			user.setDateTime(rs.getTimestamp("rdate"));
			user.setProfile(rs.getString("profile"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
}
