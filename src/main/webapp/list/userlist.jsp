<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage = "/error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/sessCheck/sessEqAdmin.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/list.css">
</head>
<body>
<jsp:include page="../common/header.jsp" />
<div class="main">
<div class="naslov">
<h3>List of users</h3>
</div>
<div class="userdiv">
<a href="<%=request.getContextPath()%>/user">Add user</a>
</div>
<hr>
<table>
<thead>
<tr>
	<th>id</th>
	<th>First Name</th>
	<th>Last Name</th>
	<th>Username</th>
	<th>Password</th>
	<th>isAdmin</th>
	<th>Action</th>
</tr>
</thead>
<tbody>
<c:forEach var="admins" items="${requestScope.admins}">
<tr>
	<td><c:out value="${admins.userid}" /></td>
	<td><c:out value="${admins.userfname}" /></td>
	<td><c:out value="${admins.userlname}" /></td>
	<td><c:out value="${admins.username}" /></td>
	<td><c:out value="${admins.password}" /></td>
	<td><c:out value="${admins.admin}" /></td>
	<td class="shortdivs">
	<div class="editdiv" style="background-color: lightgreen;">
	<a href="<%=request.getContextPath()%>/usereditform?userid=${admins.userid}">Edit</a>
	</div>
	<div class="deletediv" style="background-color: tomato;">
	<a href="deleteuser?userid=<c:out value ='${admins.userid}'/>">Delete</a>
	</div>
	</td>
</tr>
</c:forEach>
<c:forEach var="user" items="${requestScope.users}">
<tr>
	<td><c:out value="${user.userid}" /></td>
	<td><c:out value="${user.userfname}" /></td>
	<td><c:out value="${user.userlname}" /></td>
	<td><c:out value="${user.username}" /></td>
	<td><c:out value="${user.password}" /></td>
	<td><c:out value="${user.admin}" /></td>
	<td class="shortdivs">
	<div class="editdiv" style="background-color: lightgreen;">
	<a href="<%=request.getContextPath()%>/usereditform?userid=${user.userid}">Edit</a>
	</div>
	<div class="deletediv" style="background-color: tomato;">
	<a href="deleteuser?userid=<c:out value ='${user.userid}'/>">Delete</a>
	</div>
	</td>
</tr>
</c:forEach>
<c:forEach var="allusers" items="${requestScope.allUsers}">
<tr>
	<td><c:out value="${allusers.userid}" /></td>
	<td><c:out value="${allusers.userfname}" /></td>
	<td><c:out value="${allusers.userlname}" /></td>
	<td><c:out value="${allusers.username}" /></td>
	<td><c:out value="${allusers.password}" /></td>
	<td><c:out value="${allusers.admin}" /></td>
	<td class="shortdivs">
	<div class="editdiv" style="background-color: lightgreen;">
	<a href="<%=request.getContextPath()%>/usereditform?userid=${allusers.userid}">Edit</a>
	</div>
	<div class="deletediv" style="background-color: tomato;">
	<a href="<%=request.getContextPath()%>/deleteuser?userid=${allusers.userid}">Delete</a>
	</div>
	</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
<jsp:include page="../common/footer.jsp" />
</body>
</html>