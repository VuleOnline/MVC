<% 
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		if(session.getAttribute("admin")==null)
		{
			response.sendRedirect(request.getContextPath() + "/form/login.jsp");
		}
%>