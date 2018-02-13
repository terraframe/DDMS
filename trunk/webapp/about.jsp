<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
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
      <li> TerraFrame, Inc. (<a href="http://www.terraframe.com/">www.terraframe.com/</a>) </li>
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