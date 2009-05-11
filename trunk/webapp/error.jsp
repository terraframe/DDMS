<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.io.PrintWriter"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%
try
{
%>
<jsp:include page="/WEB-INF/templates/header.jsp" />
<%
//if someone has bookmarked something they should not have, we will redirect them to the index
if (exception.getClass().getName().contains("IllegalURIMethodException"))
{
%>
<script type="text/javascript">
window.location = "index.jsp ";
</script>
<%
}
else
{
if (exception.getClass().getName().contains("PermissionExceptionDTO"))
{
%>
<div class="pageContent">
<div class="pageTitle"><fmt:message key="Not_Authorized" /></div>
<br>
<br>
<fmt:message key="Not_Authorized_Long" /> <br>
<img alt="Padlock" src="imgs/icons/Padlock.png" width="128" height="128"> <%
}
else
{
%> <jsp:include page="/WEB-INF/templates/errorMail.jsp" /> <%
}
}
}
catch(Exception e)
{

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