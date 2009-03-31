<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.*" %>
<%@ page import="dss.vector.solutions.util.RedirectingServletResponse" %>
<%@page import="dss.vector.solutions.util.Halp"%>

<% String jsp_as_string = Halp.renderJspToString(request, response, (String) request.getAttribute("jsp")); %>


<jsp:include page="/WEB-INF/templates/header.jsp"  flush="false"  />

<div class="pageContent">
<div class="pageTitle">${page_title}</div>
<jsp:include page="/WEB-INF/inlineError.jsp" flush="false"  />
<%= jsp_as_string %>
</div>


<jsp:include page="/WEB-INF/templates/footer.jsp"  flush="false" />