<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.geo.generated.CollectionSiteDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<c:set var="entity" scope="request" value="${item.geoEntity}" />

<%
  List<String> entityUniversals = Arrays.asList(new String[]{CollectionSiteDTO.CLASS}); 
  request.setAttribute("entityUniversals", entityUniversals);
%>

<mjl:component item="${item}" param="dto">
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
  <mjl:dt attribute="collectionMethod">
    <mdss:mo param="collectionMethod" value="${collectionMethod}"/>
  </mjl:dt>
  <mjl:dt attribute="startDate">
    <mjl:input param="collectionDate" type="text" classes="DatePick NoFuture" id="collectionDate"/>
  </mjl:dt>
  <mjl:dt attribute="endDate">
    <mjl:input param="endDate" type="text" classes="DatePick NoFuture" id="endDate"/>
  </mjl:dt>
  <mjl:dt attribute="geoEntity">
    <mdss:geo param="geoEntity" universals="${entityUniversals}" value="${entity}" />
  </mjl:dt>
  <mjl:dt attribute="collectionId">
    <mjl:input type="text" param="collectionId"/>
  </mjl:dt>
  <mjl:dt attribute="abundance">
    <mdss:selectBoolean param="abundance" id="abundance" includeBlank="true" value="${item.abundance == null ? '' : item.abundance}"/>
  </mjl:dt>
  <mjl:dt attribute="lifeStage">
    <mjl:select param="lifeStage" items="${lifeStage}" var="current" valueAttribute="enumName" includeBlank="true">
      <mjl:option selected="${mjl:contains(item.lifeStageEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.lifeStageMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>  
</mjl:component>