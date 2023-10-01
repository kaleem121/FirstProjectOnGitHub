package com.tech.blog.helper;
import java.sql.*;
public class ConnectionProvider 
{
    private static Connection con;
    public static Connection getConnection()
    {
    	try
    	{
    		if(con==null)
    		{
    			
    			//loading driver class
        		Class.forName("com.mysql.jdbc.Driver");
        		
        		//Creating connection with database
        		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/techblog", "root", "kaleem121");
    			
    		}
    		
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		
    	return con;
    }
	
	
}
