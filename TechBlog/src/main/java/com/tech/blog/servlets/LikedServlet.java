package com.tech.blog.servlets;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tech.blog.dao.LikeDao;
import com.tech.blog.helper.ConnectionProvider;

/**
 * Servlet implementation class LikedServlet
 */
@WebServlet("/LikedServlet")
public class LikedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		int pid = Integer.parseInt(request.getParameter("pid"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		String operation = request.getParameter("operation");
		/*
		 * out.println("DATA FROM SERVER"); out.println(pid); out.println(uid);
		 * out.println(operation);
		 */
		
		LikeDao dao =new LikeDao(ConnectionProvider.getConnection());
		if(operation.equals("Like")) {
		boolean flag = dao.doLike(pid, uid);
		if(flag) 
		{
			out.println("true");
		}
		else
		{
		out.println("false");
		}
		}
	}

}
