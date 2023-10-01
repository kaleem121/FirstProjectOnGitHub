package com.tech.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeDao {
	
	Connection con;
	
	public LikeDao(Connection con){
		this.con = con;
	}
	
	public boolean doLike(int postId, int userId) {

		boolean f = false;
		
		String query = "insert into liked(pid, uid) values(?, ?)";
		try {
			PreparedStatement pstmt = this.con.prepareStatement(query);
			pstmt.setInt(1, postId);
			pstmt.setInt(2,userId);
			pstmt.executeUpdate();
			f = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return f;
	}

	
	public int countLikeOnPost(int pid) {

		int count = 0;
		
		String query = "select count(*) from liked where pid =?";
		
		try {
			PreparedStatement pstmt =this.con.prepareStatement(query);
			pstmt.setInt(1, pid);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
			count = rs.getInt("count(*)");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	public boolean islikedByUser(int pid, int uid) {
		boolean f = false;
		
		String query = "select * from liked where pid = ? and uid = ?";
		
		try {
			
			PreparedStatement pstmt = this.con.prepareStatement(query);
			pstmt.setInt(1, pid);
			pstmt.setInt(2, uid);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) 
			{
				f = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return f;
	}

	public boolean deleteLike(int pid , int uid) {
		
		boolean f =false;
		
		String query = "delete from liked where pid=?uid=?";
		
		try {
			PreparedStatement pstmt = this.con.prepareStatement(query);
			pstmt.setInt(1, pid);
			pstmt.setInt(2, uid);
			pstmt.executeUpdate();
			f=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return f;
	}

}


