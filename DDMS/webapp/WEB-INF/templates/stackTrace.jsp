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
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>


<%@page import="java.util.Enumeration"%>
<br>
<br>
<%
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

  if (clientRequest != null && clientRequest.isLoggedIn())
  {
    out.println("Logged In As: " + clientRequest.getSessionUser() + "<br/>");
  }

  out.println("Client:" + request.getRemoteAddr() + " " + request.getRemoteHost()+ "<br/>");

  out.println("Server:" + request.getLocalAddr() + " " + request.getLocalName()+ "<br/>");

  out.println("Requested:" + request.getAttribute("javax.servlet.forward.request_uri") + "<br/>");

  out.println("Detected Locale is " + request.getLocale().toString());



  out.println("<h2>Request Headers:</h2>");
  Enumeration headers = request.getHeaderNames();
  while (headers.hasMoreElements())
  {
    String pName = (String) headers.nextElement();
    String pValue = null;
    if (pName != null)
    {
      pValue = request.getHeader(pName);
    }
    out.println(pName + " = " + pValue + "<br/>");
  }


  out.println("<h2>Request Params:</h2>");
  Enumeration params = request.getParameterNames();
  while (params.hasMoreElements())
  {
    String pName = (String) params.nextElement();
    String pValue = null;
    if (pName != null)
    {
      pValue = request.getParameter(pName);
    }
    out.println(pName + " = " + pValue + "<br/>");
  }


  out.print(exception.getClass().getName() + "<br/>");
try
{
  if (exception.getClass().getName().contains("RuntimeException"))
  {
    out.println("<h2>Cause:</h2>");
    out.print(exception.getCause().getMessage() + "<br/>");
    out.println("<pre>");
    PrintWriter pw = new PrintWriter(out);
    exception.getCause().printStackTrace(pw);
    out.println("</pre><br/><br/><br/>");
  }
}
catch(Exception e)
{out.println(e.getStackTrace());}


  out.println("<pre>");
  out.print(exception.getLocalizedMessage());
  out.println("</pre>");
  out.println("<pre>");
  PrintWriter pw = new PrintWriter(out);
  exception.printStackTrace(pw);
  out.println("</pre>");
%>
