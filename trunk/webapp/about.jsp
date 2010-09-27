<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<%@page import="dss.vector.solutions.global.CredentialsSingleton"%>
<c:set var="page_title" value="About"  scope="request"/>
<jsp:include page="/WEB-INF/templates/header.jsp" />

<div class="pageContent">
<div class="pageTitle"><fmt:message key="About"/></div>

<dl>
  <dt><fmt:message key="About_System_Name"/>:</dt>
  <dd><fmt:message key="About_Version"/></dd>
  <dt><fmt:message key="developed_by"/>:</dt>
  <dd>
    <ul>
      <li> Colorado State University (<a href="http://www.colostate.edu">www.colostate.edu</a>) </li>
      <li> TerraFrame, Inc. (<a href="http://www.terraframe.com/pages/">www.terraframe.com/pages/</a>) </li>
      <li> Medical Research Council of South Africa (<a href="http://www.mrc.ac.za">www.mrc.ac.za</a>) </li>
    </ul>
  </dd>
  <dt><fmt:message key="funding_by"/>:</dt>
  <dd>
    <ul>
      <li> Innovative Vector Control Consortium (<a href="http://www.ivcc.com">www.ivcc.com</a>) </li>
    </ul>
  </dd>
</dl>

</div>

<br/><br/>

<jsp:include page="/revision.html" />




<jsp:include page="/WEB-INF/templates/footer.jsp" />