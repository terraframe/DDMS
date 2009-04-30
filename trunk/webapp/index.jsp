<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<%@page import="dss.vector.solutions.global.CredentialsSingleton"%>
<c:set var="page_title" value="welcome"  scope="request"/>
<jsp:include page="/WEB-INF/templates/header.jsp" />
<div class="pageContent">
<div class="pageTitle"><fmt:message key="welcome"/></div>
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
