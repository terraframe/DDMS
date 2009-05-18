<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>

<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.geo.generated.SentinelSiteDTO"%>
<%@page import="dss.vector.solutions.geo.generated.NonSentinelSiteDTO"%>


<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>


<%@page import="dss.vector.solutions.geo.generated.DistrictDTO"%>
<%@page import="dss.vector.solutions.geo.generated.ProvinceDTO"%>
<%@page import="dss.vector.solutions.geo.generated.SprayZoneDTO"%>
<%@page import="dss.vector.solutions.geo.generated.GeoEntityDTO"%>

<%@page import="dss.vector.solutions.PropertyDTO"%>
<c:set var="page_title" value="Search_Geo_Targets"  scope="request"/>

<%
  request.setAttribute("DistrictClass", DistrictDTO.CLASS);
  request.setAttribute("ProvinceClass", ProvinceDTO.CLASS);
  request.setAttribute("SprayZoneClass", SprayZoneDTO.CLASS);
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
//set the root to this install's country
  String geoRootId = PropertyDTO.getStr(clientRequest,"dss.vector.solutions.install","countryGeoId");

  GeoEntityDTO country = GeoEntityDTO.searchByGeoId(clientRequest,geoRootId);
  request.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, country.getId());
%>


<mjl:messages>
  <mjl:message />
</mjl:messages>

<c:set var="now" value="<%=new java.util.Date()%>" scope="request"/>


<mjl:form name="dss.vector.solutions.irs.GeoTargetController.view.mojo" method="POST" id ="searchMosquitoCollections">
  <dl>
    <dt><fmt:message key="Filter"/></dt>
    <dd>
      <input type="radio" name="filterType" value="${ProvinceClass}" checked="checked" class="filterType"/>&nbsp;<fmt:message key="Province"/> &nbsp;&nbsp;&nbsp;
      <input type="radio" name="filterType" value="${DistrictClass}" class="filterType"/>&nbsp;<fmt:message key="District"/> &nbsp;&nbsp;&nbsp;
      <input type="radio" name="filterType" value="${SprayZoneClass}" class="filterType"/>&nbsp;<fmt:message key="SprayZone"/>
    </dd>
    <dt> <label><fmt:message key="Geo_Id"/></label></dt>
    <dd> <mjl:input id="geoIdEl" param="geoId" type="text" maxlength="16" classes="geoInput"/></dd>
    <dd>
      <input type="radio" name="showChildren" value="true" checked="checked" />&nbsp;<fmt:message key="Edit_All_Children"/>  &nbsp;&nbsp;&nbsp;
      <input type="radio" name="showChildren" value="false" />&nbsp;<fmt:message key="Edit_Only_Selected"/> &nbsp;&nbsp;&nbsp;
    </dd>

    <dt> <label> <fmt:message key="Season"/></label></dt>
    <dd>
     <mjl:select var="current" valueAttribute="id" items="${seasons}" param="season.componentId" >
       <mjl:option>
          ${current.seasonName}
       </mjl:option>
    </mjl:select>
    </dd>
  </dl>
  <br>
  <br>
  <mjl:command classes="submitButton" action="dss.vector.solutions.irs.GeoTargetController.view.mojo" name="search.button"
  value="Search"
  />
</mjl:form>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>