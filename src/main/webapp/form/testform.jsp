<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage = "/error.jsp"%>
<%@include file="/sessCheck/sessNull.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/testform.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/rescheck.js">
</script>
</head>
<body>
<jsp:include page="../common/header.jsp" />
    <div class="main">
    
    
    		<c:if test="${not empty sessionScope.admin && empty sessionScope.username}">
				<form action="<%=request.getContextPath()%>/testlist" method="post">
			</c:if>
			<c:if test="${not empty sessionScope.username && empty sessionScope.admin}">
				<form  action="<%=request.getContextPath()%>/finish" method="post">
			</c:if>
			
				
<c:choose>
				<c:when test="${sessionScope.admin}">
				<h3>View</h3>
				</c:when>
				<c:otherwise>
                    <h3>Test</h3>
                </c:otherwise>
</c:choose>

        <section>
        <c:forEach var="tehtest" items="${sessionScope.tehtest}">
        <input type="hidden" name="testid_${tehtest.testid}" value="${tehtest.testid}">
 		<input type="hidden" name="tehnologija" value="${tehtest.tehnologija}">
   		 <textarea name="pitanje" disabled>${tehtest.pitanje}</textarea>
    		<input type="hidden" name="tacan_${tehtest.testid}" value="${tehtest.tacan}">
   			 <c:forEach var="odgovor" items="${tehtest.odgovori}" varStatus="loop">
   			 <div class="ansblock">
       			 <input type="radio" name="odg_${tehtest.testid}" value="${odgovor}">
       				 <textarea name="odgovor_${loop.index}" disabled>${odgovor}</textarea>
       				 
       		 </div>
   		 	</c:forEach>
   		 	<p>${FODG}</p>	
   		 	 <div id="res_${tehtest.testid}"></div>
   		 	 <c:if test="${sessionScope.admin}">
   		 	 			<div class="shortdivs">
   		 	 			<div class="editdiv" style="background-color: rgba(112,193,116, 0.8);">
    						<a href="<%=request.getContextPath()%>/questioneditform?testid=${tehtest.testid}">Edit</a>
    					</div>
    					<div class="deletediv" style="background-color: tomato;">
    						<a href="<%=request.getContextPath()%>/deletequestion?testid=${tehtest.testid}">Delete</a>
    					</div>
    					</div>
			 </c:if>
		</c:forEach>
		</section>
                  <button type="submit" class="btn btn-success">Done</button>

        </form>
    </div>
<jsp:include page="../common/footer.jsp" />
</body>
</html>
