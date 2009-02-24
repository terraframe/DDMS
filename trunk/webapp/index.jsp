<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" >
 <jsp:include page="/WEB-INF/templates/yuiIncludes.jsp" />
<title>MDSS</title>
</head>
<body class="yui-skin-sam">
<h1><f:message key="welcome"/></h1>
<jsp:include page="/WEB-INF/templates/navMenu.jsp" /> 
<br><br>
  <% 
   Locale locale = request.getLocale();
   out.println("Your Detected Locale is "+locale.toString());
   %>
</body>
</html>
