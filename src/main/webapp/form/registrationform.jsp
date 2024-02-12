<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage = "/error.jsp"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page import="vule.controlService.AccessControl" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/register.css">
</head>
<body>
<jsp:include page="../common/header.jsp" />
<div class="main">
			 <c:if test="${user.userid != null}">
				<form action="<%=request.getContextPath()%>/edituser" method="post">
			 </c:if>
			 <c:if test="${user.userid == null}">
			<form action="<%=request.getContextPath()%>/user" method="post">
			</c:if>
			
</div>
<div class="regmsg">
<p>${REGMSG}</p>
</div>
<table>
<thead>
<tr>
<th colspan="2" style="text-align: center;">
 <c:choose>
				<c:when test="${user.userid != null}">
				<h3>Edit</h3>
				</c:when>
				<c:otherwise>
                    <h3>Register</h3>
                </c:otherwise>
</c:choose>
</th>
</tr>
</thead>
<tbody>
<tr><td><input type="hidden" name="userid" value="${user.userid}"></td></tr>
<tr>
<td>First Name</td>
<td><input type="text" value="${user.userfname}" name="userfname" class="userfname" required placeholder="*First Name"></td>
</tr>

<tr>
	<td>Last Name</td>
	<td><input type="text" value="${user.userlname}" name="userlname" class="userlname" required placeholder="*Last Name"></td>
</tr>

<tr>
	<td>Username</td>
	<td><input type="text" value="${user.username}" name="username"class="username" required placeholder="*Username"></td>
</tr>

<tr>
	<td>Password</td>
	<td><input type="text" value="${user.password}" name="password" class="password" required placeholder="*Passsword"></td>
</tr>
<tr>
<c:if test="${sessionScope.admin}">
<td>
<label for="mojCheckbox">Registracija <br>administartora:</label>
<input type="checkbox" name="admin" id="mojCheckbox" value="da">
</td>
</c:if>
</tbody>
<tfoot>
<tr>
<td colspan="2" style="text-align: center;">
<button type="submit" name="sbmt"> Submit </button>
</td>
</tr>
</tfoot>
</table>
</form>
</div>	
</body>
<jsp:include page="../common/footer.jsp" />
</html>