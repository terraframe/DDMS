<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<c:set var="entity" scope="request" value="${item.geoEntity}" />

<mjl:component item="${item}" param="view">
  <mjl:dt attribute="geoEntity">
    <mdss:geo param="geoEntity" value="${entity}" />
  </mjl:dt>
  <%-- <mjl:dt attribute="synonymNames">
    <mjl:input type="text" param="synonymNames"/>
  </mjl:dt> --%>
</mjl:component>