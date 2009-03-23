<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<jsp:include page="/WEB-INF/templates/header.jsp" />
<div class="pageContent">
<div class="pageTitle"><f:message key="welcome"/></div>
<br><br>
  <% 
   Locale locale = request.getLocale();
   out.println("Your Detected Locale is "+locale.toString());
   %>
   <br>
   <br>
<pre>   
<jsp:include page="/revision.html" />   
</pre>
</div>
<jsp:include page="/WEB-INF/templates/footer.jsp" />
