<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.io.PrintWriter"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
try
{
  Boolean printStacktrace = true;
%>

<jsp:include page="/WEB-INF/templates/header.jsp" />
<div class="pageTitle"><mdss:localize key="welcome"/></div>
<%
  //if someone has bookmarked something they should not have, we will redirect them to the index
  if (exception.getClass().getName().contains("IllegalURIMethodException"))
  {
    printStacktrace = false;
%>    
    <div class="alert alertbox">
    <p>
    <%=exception.getLocalizedMessage()%>    
    </p>
    </div>
<%
  }

  if (exception.getClass().getName().contains("PermissionExceptionDTO"))
  {
    printStacktrace = false;
%>
<div class="pageContent">
<div class="pageTitle"><mdss:localize key="Not_Authorized" /></div>
<br>
<br>
<mdss:localize key="Not_Authorized_Long" /> <br>
<img alt="Padlock" src="imgs/icons/Padlock.png" width="128" height="128">
<%
  }
  
  String stackTrace = Halp.renderJspToString(request, response, "/WEB-INF/templates/stackTrace.jsp");

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