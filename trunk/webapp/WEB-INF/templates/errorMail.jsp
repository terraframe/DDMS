<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.io.PrintWriter"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<div class="pageTitle">Error in MDSS/trunk</div>
<br>
<br>
<%
  if (Pattern.matches("http[s]?://(localhost|127\\.0\\.0\\.1).*", request.getRequestURL().toString()))
  {
    out.print(exception.getClass().getName() + "<br/>");

    if (exception.getClass().getName().contains("RuntimeException"))
    {
      out.println("<h2>Cause:</h2>");
      out.print(exception.getCause().getMessage() + "<br/>");
      out.println("<pre>");
      PrintWriter pw = new PrintWriter(out);
      exception.getCause().printStackTrace(pw);
      out.println("</pre><br/><br/><br/>");
    }

    out.println("<pre>");
    out.print(exception.getLocalizedMessage());
    out.println("</pre>");
    out.println("<pre>");
    PrintWriter pw = new PrintWriter(out);
    exception.printStackTrace(pw);
    out.println("</pre>");

  }
  else
  {

    Halp.sendErrorMail(exception, request);
    out.print(exception.getClass().getName() + "<br/><br/>");
    out.println("A developer has been sent an email regarding this error.");
    out.println("<pre style='color:#000'>");
    out.print(exception.getLocalizedMessage());
    out.println("</pre>");
    out.println("<pre style='color:#000'>");
    PrintWriter pw = new PrintWriter(out);
    exception.printStackTrace(pw);
    out.println("</pre>");

  }
%>
