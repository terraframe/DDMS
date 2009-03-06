<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

Hi guys, check the title.


<%
request.setAttribute("title","This title came from inside out rendering!");

%>

 <%
   System.out.println("in the inner jsp");
   // uncomment the following line to see this example work
  // out.flush();
 %>
 
