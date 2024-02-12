<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage = "/error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/sessCheck/sessEqAdmin.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/questionform.css">
</head>
<body>
<div class="main"></div>
<jsp:include page="../common/header.jsp" />

 	<c:if test="${test.testid != null}">
        <form action="<%=request.getContextPath()%>/edittest" method="post">
    </c:if>
    <c:if test="${test.testid == null}">
        <form action="<%=request.getContextPath()%>/insertquestion" method="post">
    </c:if>
<c:choose>
				<c:when test="${test.testid != null}">
				<h3>Edit question</h3>
				</c:when>
				<c:otherwise>
                    <h3>Add question</h3>
                </c:otherwise>
</c:choose>
<h5>${INSERTMSG}</h5>
<table>
<tr>
<td><input type="hidden" name="testid" value="${test.testid}"></td>
</tr>
<tr>
<td><label for="tehnologija">Tehnologija<label></td>
<td><input type="text" name="tehnologija" id="tehnologija" value="${test.tehnologija}" required></td>
</tr>
<tr>
<td><label for="pitanje">Pitanje</label></td>
<td><textarea name="pitanje" id="pitanje" rows="4" cols="40" required>${test.pitanje}</textarea></td>
</tr>
<tr>
<td><label for="odgovori">Odgovori<br><span style="font-size:12px">(razdvojite odgovore " , " simbolom)<span></label></td>
<td><textarea name="odgovori" id="odgovori" rows="6" cols="40" required>${test.odgovori}</textarea></td>
</tr>
<tr>
<td><label for="tacan">Tacan odgovor</label></td>
<td><textarea name="tacan" id="tacan" rows="4" cols="40" required>${test.tacan}</textarea></td>
</tr>
<tr>
<td colspan="2" class="td_btn"><button type="submit" class="btn-success">Save</button></td>
</tr>
</table>
</form>
<jsp:include page="../common/footer.jsp" />
</div>
</body>
</html>