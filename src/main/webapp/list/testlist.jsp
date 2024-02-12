<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage = "/error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/sessCheck/sessNull.jsp" %>
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
<h3>List of Tests</h3>
</div>
<c:if test="${sessionScope.admin}">
<div class="questdiv">
<a href="<%=request.getContextPath()%>/form/questionform.jsp">Add question</a>
</div>
</c:if>
<hr>
<table>
<thead>
<tr>
	<th>Tehnologija</th>
	<th>Akcije</th>
</tr>
</thead>
<tbody>
<c:forEach var="scope" items="${requestScope.scope}">

	<tr>
		<td><c:out value="${scope.tehnologija}"/></td>
		<td class="shortdivs">
		<c:if test="${sessionScope.admin and not sessionScope.username}">
		<div class="editdiv" style="background-color: rgba(112,193,116, 0.8);">
		<a href="<%=request.getContextPath()%>/testform?tehnologija=${scope.tehnologija}">View</a>
		</div>
		<div class="deletediv" style="background-color: tomato;">
		<a href="<%=request.getContextPath()%>/deletetest?tehnologija=${scope.tehnologija}">Delete</a>
		</div>
		</c:if>
		<c:if test="${not empty sessionScope.username and not sessionScope.admin}">
		<div class="opendiv" style="background-color: rgba(112,193,116, 0.8);">
		<a href="<%=request.getContextPath()%>/testform?tehnologija=${scope.tehnologija}">Open</a>
		</div>
		</c:if>
		</td>
	</tr>
</c:forEach>
</tbody>
</table>
</div>
<jsp:include page="../common/footer.jsp" />
</body>
</html>
