<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<c:set var="page_title" value="welcome"  scope="request"/>
<jsp:include page="/WEB-INF/templates/header.jsp" />
<div class="pageContent">
<div class="pageTitle"><mdss:localize key="welcome"/></div>
<br><br>
<a herf="dss.vector.solutions.util.ReadableAttributeController.getUniversal.mojo?actor=entomologist"><mdss:localize key="Edit_Entomologist"/></a>
<pre>
<jsp:include page="/revision.html" />
</pre>
</div>
<jsp:include page="/WEB-INF/templates/footer.jsp" />
