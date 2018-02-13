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