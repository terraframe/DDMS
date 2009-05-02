<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.io.*" %>
<%@ page import="dss.vector.solutions.util.RedirectingServletResponse" %>
<%@page import="dss.vector.solutions.util.Halp"%>

<%//set the defualt header and footer, this can be overidden in the component jsp %>
<c:set var="header_jsp" value="/WEB-INF/templates/header.jsp"  scope="request"/>
<c:set var="footer_jsp" value="/WEB-INF/templates/footer.jsp"  scope="request"/>

<%// render the component jsp to a string, using the current request context%>
<% String jsp_as_string = Halp.renderJspToString(request, response, (String) request.getAttribute("jsp")); %>

<%// render the component inside a pageContent div%>
<jsp:include page="${header_jsp}"  flush="false"  />
<div class="pageContent">
<div class="pageTitle"><fmt:message key="${page_title}"/></div>

<jsp:include page="/WEB-INF/inlineError.jsp" flush="false"  />
<%= jsp_as_string %>
</div>
<jsp:include page="${footer_jsp}"  flush="false" />