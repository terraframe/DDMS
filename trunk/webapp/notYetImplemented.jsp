<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<%@page import="dss.vector.solutions.global.CredentialsSingleton"%>
<c:set var="page_title" value="Not_Yet_Implemented"  scope="request"/>
<jsp:include page="/WEB-INF/templates/header.jsp" />

<div class="pageContent">
<div class="pageTitle"><mdss:localize key="Not_Yet_Implemented"/></div>
<dl>
The menu item you followed has not been implemented yet!
</dl>

</div>

<br/><br/>

<jsp:include page="/revision.html" />




<jsp:include page="/WEB-INF/templates/footer.jsp" />