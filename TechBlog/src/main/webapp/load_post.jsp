<%@page import="com.tech.blog.entities.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.tech.blog.entities.Post"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tech.blog.dao.PostDao" %>
<%@page import="com.tech.blog.dao.LikeDao"%>
<%@ page import="com.tech.blog.helper.ConnectionProvider" %>

<div class="row">

<%
Thread.sleep(1000);
PostDao dao=new PostDao(ConnectionProvider.getConnection());
int cid=Integer.parseInt(request.getParameter("cid"));
User usr = (User) session.getAttribute("currentUser");
List<Post> list=null;
if(cid==0)
{
	 list =dao.getAllPosts();
	
}
else
{
	list=dao.getPostByCatId(cid);
}
if(list.size()==0)
{
	out.println(" <h3 class='display-3 text-center'>No any post available for this category </h3>");
			return;
}

for(Post post:list)
{
%>

<div class="col-md-6 mt-2">

 <div class="card">
 
     <img class="card-img-top" src="pics/<%=post.getpPic() %>" alt="Card image cap">
     <div class="card-body">
        <b><%=post.getpTitle() %></b>
        <p><%=post.getpContent() %></p>
       
     </div>
 
     <div class="card-footer primary-background text-center">
     <%
      LikeDao ldao = new LikeDao(ConnectionProvider.getConnection());
     %>
        <a href="#!" onClick = "doLike(<%= post.getPid()%>, <%= usr.getId()%>)" class="btn btn-outline-light btn-sm"><i class="fa fa-thumbs-o-up"></i><span class = "like-counter"><%=ldao.countLikeOnPost(post.getPid()) %></span></a>   
        <a href="show_blog_page.jsp?post_id=<%=post.getPid() %>" class="btn btn-outline-light btn-sm">Read More...</a>
        <a href="#!" class="btn btn-outline-light btn-sm"><i class="fa fa-commenting-o">7</i></a>
     </div>
 
 </div>
</div>

<%
}
%>
</div>