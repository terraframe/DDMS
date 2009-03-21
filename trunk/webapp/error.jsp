<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page isErrorPage="true"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.io.PrintWriter"%>

<h2>Error in MDSS/trunk</h2>

<%
	if (!Pattern.matches("http[s]?://(localhost|127\\.0\\.0\\.1)", request.getRequestURL().toString())) 
    {
      Halp.sendErrorMail(exception,request);
      out.print(exception.getClass().getName() + "<br/><br/>");
      out.println("A developer has been sent an email regarding this error.");
      
	}
	else
    {
	out.print(exception.getClass().getName() + "<br/>");
	out.println("<pre>");
	out.print(exception.getLocalizedMessage());
	out.println("</pre>");
	out.println("<pre>");
	PrintWriter pw = new PrintWriter(out);
	exception.printStackTrace(pw);
	out.println("</pre>");
	}
%>
