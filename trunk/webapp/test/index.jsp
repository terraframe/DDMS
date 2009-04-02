<%@ page import="java.io.*" %>
<%@ page import="dss.vector.solutions.util.RedirectingServletResponse" %>
<%@ page contentType="text/html;charset=UTF8" %>
 
<%!
public String getEmailText(HttpServletRequest request, HttpServletResponse response, String emailURL) {
	try{
	  // create an output stream - to file, to memory...
	  ByteArrayOutputStream out = new ByteArrayOutputStream();
	  // create the "dummy" response object
	  RedirectingServletResponse dummyResponse;
	  dummyResponse = new RedirectingServletResponse(response, out);
	 
	  // get a request dispatcher for the email template to load  
	  RequestDispatcher rd = request.getRequestDispatcher(emailURL);
	  // execute that JSP
	  rd.include(request, dummyResponse);
	 
	  // at this point the ByteArrayOutputStream has had the response written into it.
	  dummyResponse.flushBuffer();
	  
	  byte[] result = out.toByteArray();
	  System.out.println("result = " + result.length);
	  // now you can do with it what you will.
	  String emailText = new String(result);
	  return emailText;
	}
	catch (Exception e){
		e.printStackTrace(System.out);
		return e.getMessage();
	}
	
}
%>
<%
  String result = getEmailText(request, response, "/test/render_me.jsp");
 // System.out.println(result);
%>
 
<html>
<head>
    <title>
      <%=request.getAttribute("title") %>
    </title>
</head>

<body>
<h2> Server Info Virtual!!</h2>
Server info = <%= application.getServerInfo() %> <br>
Servlet engine version = <%=  application.getMajorVersion() %>.<%= application.getMinorVersion() %><br>
Java version = <%= System.getProperty("java.vm.version") %><br>
<hr>
<%= result %>
</body>
</html>
