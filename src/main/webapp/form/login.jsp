<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage = "/error.jsp"%>
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
<form action="<%=request.getContextPath()%>/login" method="post">
<table>
<thead>
<tr>
<th colspan="2" style="text-align: center; style=font-size: 20px;"><h3>Login</h3></th>
</tr>
</thead>
<tbody>
<tr>
<td>Username</td>
<td>
<input type="text" class="input" name="username" placeholder="*Username"></td>
</tr>
<tr>
<td>Password</td>
<td>
<input type="password" class="input" name="password" placeholder="*Password">
</td>
</tr>
</tbody>
<tfoot>
<tr>
<td colspan="2" style="text-align: center;">
<button type="submit" name="logSub">Submit</button>
</td>
</tr>
<tr><th colspan="2" style="font-size: 10px;"><i>${LOGMSG}</i></th></tr>
</tfoot>
</table>
</form>
</div>
<jsp:include page="../common/footer.jsp" />
</body>
</html>