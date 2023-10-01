package com.tech.blog.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tech.blog.entities.Category;
import com.tech.blog.entities.Post;
public class PostDao {
	Connection con;
	
	public PostDao(Connection con){
		this.con=con;
	}
	
	public ArrayList<Category> getAllCategories(){
		ArrayList<Category> list =new ArrayList<>();
		try {
			String q="select * from categories";
			Statement st= con.createStatement();
			ResultSet set = st.executeQuery(q);
			while(set.next())
			{
				int cid=set.getInt("cid");
				String name= set.getString("name");
				String description =set.getString("description");
				Category c= new Category(cid ,name,description);
				list.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
    
	public boolean savePost(Post p)
	{
		boolean f=false;
		try {
			
		String q = "insert into posts(pTitle,pContent, pCode, pPic,cid,userId) values(?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(q);
		pstmt.setString(1, p.getpTitle());
		pstmt.setString(2, p.getpContent());
		pstmt.setString(3, p.getpCode());
		pstmt.setString(4, p.getpPic());
		pstmt.setInt(5, p.getCid());
		pstmt.setInt(6, p.getUserId());
		pstmt.executeUpdate();
		f=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public List<Post> getAllPosts()
	{
		List<Post> list =new ArrayList<>();
		//fetching all the post
		String q= "Select * from posts order by pid desc";
		try {
			PreparedStatement pstmt=con.prepareStatement(q);
			ResultSet rs =pstmt.executeQuery();
			while(rs.next())
			{
				int pid=rs.getInt("pId");
				String pTitle=rs.getString("pTitle");
				String pContent = rs.getString("pContent");
				String pCode =rs.getString("pCode");
				String pPic=rs.getString("pPic");
				Timestamp pDate = rs.getTimestamp("pDate");
				int cid = rs.getInt("cid");
				int userId = rs.getInt("userId");
				
				Post post =new Post(pid,pTitle,pContent,pCode,pPic,pDate,cid,userId);
				list.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}

	public List<Post> getPostByCatId(int catId)
	{
		List<Post>list=new ArrayList<>();
		//fetch all post by Id
		
		String q= "select * from posts where cid=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(q);
			pstmt.setInt(1, catId);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				int pid=rs.getInt("pId");
				String pTitle=rs.getString("pTitle");
				String pContent = rs.getString("pContent");
				String pCode =rs.getString("pCode");
				String pPic=rs.getString("pPic");
				Timestamp pDate = rs.getTimestamp("pDate");
				int userId = rs.getInt("userId");
				Post post =new Post(pid,pTitle,pContent,pCode,pPic,pDate,catId,userId);
				list.add(post);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public Post getPostByPostId(int PostId) {
		Post p=null;
		
		String query = "select * from posts where pid =?";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, PostId);
			ResultSet set = pstmt.executeQuery();
			if(set.next()) {
				
				int pid=set.getInt("pId");
				String pTitle=set.getString("pTitle");
				String pContent = set.getString("pContent");
				String pCode =set.getString("pCode");
				String pPic=set.getString("pPic");
				Timestamp pDate = set.getTimestamp("pDate");
				int userId = set.getInt("userId");
				int cid = set.getInt("cid");
				p=new Post(pid,pTitle,pContent,pCode,pPic,pDate,cid,userId);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}

}
