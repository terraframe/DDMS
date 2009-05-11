<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.io.PrintWriter"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%
try
{
  Boolean mailStacktrace = ! Pattern.matches("http[s]?://(localhost|127\\.0\\.0\\.1).*", request.getRequestURL().toString());
  Boolean printStacktrace = true;
%>

<jsp:include page="/WEB-INF/templates/header.jsp" />
<div class="pageTitle">Internal Error</div>
<%
//if someone has bookmarked something they should not have, we will redirect them to the index
if (exception.getClass().getName().contains("IllegalURIMethodException"))
{
  printStacktrace = false;
  mailStacktrace = false;
%>
<script type="text/javascript">
window.location = "index.jsp ";
</script>
<%
}

if (exception.getClass().getName().contains("PermissionExceptionDTO"))
{
  printStacktrace = false;
  mailStacktrace = false;
%>
<div class="pageContent">
<div class="pageTitle"><fmt:message key="Not_Authorized" /></div>
<br>
<br>
<fmt:message key="Not_Authorized_Long" /> <br>
<img alt="Padlock" src="imgs/icons/Padlock.png" width="128" height="128">
<%
}
String stackTrace = Halp.renderJspToString(request, response, "/WEB-INF/templates/stackTrace.jsp");

if(mailStacktrace)
{
%>
  <div class="alert alertbox">
  <p>
  A developer has been sent an email regarding this error.
  </p>
  </div>
<%
  Halp.sendErrorMail(exception, request, stackTrace);
}

if(printStacktrace)
{
  out.println(stackTrace);
}

}
catch(Exception e)
{
  out.println("error In error page = " + e);
  out.println("<pre>");
  out.print(exception.getLocalizedMessage());
  out.println("</pre>");
  out.println("<pre>");
  PrintWriter pw = new PrintWriter(out);
  exception.printStackTrace(pw);
  out.println("</pre>");
}
%>
</div>