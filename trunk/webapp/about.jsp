<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<%@page import="dss.vector.solutions.global.CredentialsSingleton"%>
<jsp:include page="/WEB-INF/originalVersion.jsp" />
<c:set var="page_title" value="About"  scope="request"/>
<c:set var="version" value="1.02.0013" scope="request" />
<jsp:include page="/WEB-INF/templates/header.jsp" />

<div class="pageContent">
<div class="pageTitle"><mdss:localize key="About"/></div>

<dl>
  <dt><mdss:localize key="About_System_Name"/>:</dt>
  <dd><mdss:localize key="About_Version"/> ${version} (<mdss:localize key="About_Original_Version"/> ${original_version})</dd>
  <dt><mdss:localize key="developed_by"/>:</dt>
  <dd>
    <ul>
      <li> Colorado State University (<a href="http://www.colostate.edu">www.colostate.edu</a>) </li>
      <li> TerraFrame, Inc. (<a href="http://www.terraframe.com/pages/">www.terraframe.com/pages/</a>) </li>
    </ul>
  </dd>
  <dt><mdss:localize key="funding_by"/>:</dt>
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