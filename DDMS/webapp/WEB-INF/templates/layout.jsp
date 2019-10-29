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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="java.io.*" %>
<%@page import="dss.vector.solutions.util.RedirectingServletResponse" %>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="com.runwaysdk.controller.JSPFetcher"%>


<% //set the defualt header and footer, this can be overidden in the component jsp %>

<c:set var="header_jsp" value="/header.jsp"  scope="request"/>
<c:set var="footer_jsp" value="/footer.jsp"  scope="request"/>

<%// render the component jsp to a string, using the current request context%>
<% String jsp_as_string = Halp.renderJspToString(request, response, (String) request.getAttribute(JSPFetcher.INNER_JSP)); %>

<%// render the component inside a pageContent div%>
<jsp:include page="${header_jsp}"  flush="false"  />
<div class="pageContent"> 

<div class="pageTitle">
<c:choose>
  <c:when test='${localized_page_title != null }'>
    ${localized_page_title}${page_title_suffix}
  </c:when>
  <c:otherwise>
   	<mdss:localize key="${page_title}"/>${page_title_suffix}
  </c:otherwise>
</c:choose>
</div>

<jsp:include page="../../WEB-INF/inlineError.jsp" flush="false"  />

<%= jsp_as_string %>
<%out.flush(); %>
</div>
<jsp:include page="${footer_jsp}"  flush="false" />