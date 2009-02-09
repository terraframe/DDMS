<%@page isErrorPage="true" %>
<%@page import="java.io.PrintWriter"%>
<h2>Error in MDSS/trunk</h2>
<%
  out.print(exception.getClass().getName() + "<br/>");
  out.print(exception.getLocalizedMessage());
  
  out.println("<pre>");
  PrintWriter pw = new PrintWriter(out);
  exception.printStackTrace(pw);
  out.println("</pre>");
%>
