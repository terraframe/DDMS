<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
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
 
