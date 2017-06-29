<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>

<%-- <jsp:include page="/WEB-INF/selectSearchTerm.jsp"/> --%>

<c:set var="termvar" scope="request" value="${item.term}" />

<mjl:component item="${item}" param="view">
  <mjl:dt attribute="term">
    <mdss:mo param="term" value="${termvar}"/>
  </mjl:dt>
</mjl:component>


