<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<jsp:include page="/WEB-INF/templates/header.jsp" />

<div class="pageTitle"><f:message key="welcome"/></div>
<br><br>
  <% 
   Locale locale = request.getLocale();
   out.println("Your Detected Locale is "+locale.toString());
   %>
<jsp:include page="/WEB-INF/templates/footer.jsp" />