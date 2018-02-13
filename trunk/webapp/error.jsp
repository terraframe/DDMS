<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
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