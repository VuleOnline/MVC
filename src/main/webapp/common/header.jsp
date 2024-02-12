<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
header{
		font-family: 'Open Sans', sans-serif;
		background-color: rgba(0, 0, 0, 0.09);
		margin: 0px;
 		position: fixed;
   		top: 0;
    	width:100%;
    	height: 40px;
		
}
nav{
			
            overflow: hidden;
            display: flex;
            justify-content: space-between;
			}
nav > a {
            color: black;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 14px;
            }


</style>

			<%
			String currentPage = request.getRequestURI();//trenutni zahtev
			String loginPage = request.getContextPath()+ "/login/login.jsp";
			String regPage = request.getContextPath()+ "/form/registrationform.jsp";
			String active = currentPage.equals(loginPage) ? "login" : "register";
			
			 System.out.println("currentPage: " + currentPage);
			 System.out.println("loginPage: " + loginPage);
			 System.out.println("regPage: " + regPage);
			 System.out.println("active: " + active);
			 %>
			 
<header class="header">

 <nav>
 		<c:if test="${empty sessionScope.username and not sessionScope.admin}">
        <a href="<%=request.getContextPath()%>/form/login.jsp" style="margin-right: auto; font-size: 20px;"><strong>app</strong></a>
        <a href="<%=request.getContextPath()%>/form/login.jsp" >Login</a>
        <a href="<%=request.getContextPath()%>/form/registrationform.jsp" >SignUp</a>
        </c:if>
        <c:if test="${not empty sessionScope.username and not sessionScope.admin}">
      	<a href="<%=request.getContextPath()%>/home/userhome.jsp" style="margin-right: auto; font-size: 20px;"><strong>app</strong></a>
        <a href="<%=request.getContextPath()%>/testlist" class="tab4">Testovi</a>
        <a href="<%=request.getContextPath()%>/logout">Logout</a>
        </c:if>
        <c:if test="${sessionScope.admin}">
      	<a href="<%=request.getContextPath()%>/home/adminhome.jsp" style="margin-right: auto; font-size: 20px;"><strong>app</strong></a>
    	<a href="<%=request.getContextPath()%>/allusers" class="tab1">Svi registovani</a>
      	<a href="<%=request.getContextPath()%>/userlist" class="tab2">Korisnici</a>
      	<a href="<%=request.getContextPath()%>/adminlist" class="tab3">Administratori</a>
      	<a href="<%=request.getContextPath()%>/testlist" class="tab4">Testovi</a>
      	<a href="<%=request.getContextPath()%>/logout">Logout</a>    
        </c:if>
    </nav>
</header>

			 