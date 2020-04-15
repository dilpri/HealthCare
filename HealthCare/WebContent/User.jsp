<%@page import="com.controller.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%
	//Insert item---------------------------------
	if (request.getParameter("username") != null) {
		User userObj = new User();
		String stsMsg = userObj.insertUser(request.getParameter("username"), request.getParameter("password"),
				request.getParameter("email"), request.getParameter("address"), request.getParameter("phoneNo"),
				request.getParameter("age"), request.getParameter("sex"));
		session.setAttribute("statusMsg", stsMsg);
	}
	//Delete item----------------------------------
	if (request.getParameter("userID") != null) {
		User userObj = new User();
		String stsMsg = userObj.deleteUser(request.getParameter("userID"));
		session.setAttribute("statusMsg", stsMsg);
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
</head>
<body>
	<h1>Users Management</h1>
	<form method="post" action="User.jsp">
		User name : <input name="username" type="text"><br>
		Password : <input name="password" type="text"><br> Email
		: <input name="email" type="text"><br> Address : <input
			name="address" type="text"><br> Phone No : <input
			name="phoneNo" type="text"><br> Age : <input name="age"
			type="text"><br> Sex : <input name="sex" type="text"><br>
		<input class="btn btn-success" name="btnSubmit" type="submit" value="Save">
	</form>
	<%
		out.print(session.getAttribute("statusMsg"));
	%>
	4
	<br>
	<%
		User userObj = new User();
		out.print(userObj.readUsers());
	%>
</body>
</html>