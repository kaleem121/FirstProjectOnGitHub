package com.tech.blog.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.Message;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		try {
			PrintWriter out=response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<head>");
			out.println("<title>Login Servlet</title>");
			out.println("</head>");
			out.println("<body>");
			
			//fetch username and password from request
			String userEmail=request.getParameter("user_email");
			String userPassword=request.getParameter("user_password");
			UserDao dao=new UserDao(ConnectionProvider.getConnection());
			User user=dao.getUserByEmailAndPassword(userEmail, userPassword);
			if(user==null)
			{
				//login error
				//out.println("Invalid Email or Password ..Try again");
				Message msg =new Message("Invalid Email or Password..Try again", "error","alert-danger");
				HttpSession s= request.getSession();
				s.setAttribute("msg", msg);
				response.sendRedirect("login_page.jsp");
			}
			else 
			{
				//login Success
				HttpSession s=request.getSession();
				s.setAttribute("currentUser", user);
				response.sendRedirect("profile.jsp");
				
			}
			out.println("</body>");
			out.println("</html>");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
