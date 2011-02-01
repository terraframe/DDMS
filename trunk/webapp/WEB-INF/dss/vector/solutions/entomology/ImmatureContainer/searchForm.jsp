<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<c:set var="entity" scope="request" value="${item.geoEntity}" />

<mjl:component item="${item}" param="dto">
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
  <mjl:input type="hidden" param="premiseId" value="${item.premiseId}"/>
  <mjl:input type="hidden" param="taxonId" value="${item.taxonId}"/>
  
  <mjl:dt attribute="geoEntity">
    <mdss:geo param="geoEntity" political="false" populated="false" spray="false" urban="true" value="${entity}" />
  </mjl:dt>
  <mjl:dt attribute="startDate">
    <mjl:input param="startDate" type="text" classes="DatePick NoFuture" id="startDate"/>
  </mjl:dt>
  <mjl:dt attribute="endDate">
    <mjl:input param="endDate" type="text" classes="DatePick NoFuture" id="endDate"/>
  </mjl:dt>
  <mjl:dt attribute="premiseType">
    <mdss:mo param="premiseType" value="${premiseType}"/>
  </mjl:dt>
  <mjl:dt attribute="taxon">
    <mdss:mo param="taxon" value="${taxon}"/>
  </mjl:dt>
  <mjl:dt attribute="collectionId">
    <mjl:input type="text" param="collectionId"/>
  </mjl:dt>
</mjl:component>