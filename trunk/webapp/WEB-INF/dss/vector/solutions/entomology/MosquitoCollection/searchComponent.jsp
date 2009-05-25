<%@ include file="/WEB-INF/templates/jsp_includes.jsp"%>
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

<c:set var="page_title" value="Search_Mosquito_Collections"  scope="request"/>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
request.setAttribute("SentinelSiteClass", SentinelSiteDTO.CLASS);
  request.setAttribute("NonSentinelSiteClass", NonSentinelSiteDTO.CLASS);
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
  MosquitoCollectionDTO item = new MosquitoCollectionDTO(clientRequest);
  request.setAttribute("item",item);
%>

<mjl:form name="dss.vector.solutions.entomology.MosquitoCollection.search" method="POST" id ="searchMosquitoCollections">
  <dl>
    <dt><fmt:message key="Filter"/></dt>
    <dd>
      <input type="radio" name="filterType" value="" checked="checked" />&nbsp;<fmt:message key="All"/>  &nbsp;&nbsp;&nbsp;
      <input type="radio" name="filterType" value="${SentinelSiteClass}" class="filterType"/>&nbsp;<fmt:message key="Sentinel_Site"/> &nbsp;&nbsp;&nbsp;
      <input type="radio" name="filterType" value="${NonSentinelSiteClass}" class="filterType" />&nbsp;<fmt:message key="Non_Sentinel_Site"/>
    </dd>
    <dt> <label> ${item.geoEntityMd.displayLabel}</label></dt>
    <dd> <mjl:input id="geoIdEl" param="geoId" type="text" maxlength="16" classes="geoInput"/></dd>
    <dt> <label> ${item.dateCollectedMd.displayLabel}</label></dt>
    <dd> <mjl:input param="collectionDate" type="text" classes="DatePick NoFuture" id="collectionDate"/></dd>
    <dt> <label> ${item.collectionMethodMd.displayLabel}</label> </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${MosquitoCollection_collectionMethod}" param="collectionMethod.componentId" >
        <mjl:option>
          ${current.displayLabel}
        </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command classes="submitButton" action="dss.vector.solutions.entomology.MosquitoCollectionController.searchByGeoIdAndDate.mojo" name="search.button" value="Search"/>
  </dl>
</mjl:form>


<mjl:messages>
  <mjl:message />
</mjl:messages>

<br />
<br />
<br />
<fmt:message key="Recently_Created_Collections"/>

<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.entomology.MosquitoCollectionController.search.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="collectionMethod">
      <mjl:header>
         ${item.collectionMethodMd.displayLabel}
      </mjl:header>
      <mjl:row>
        ${item.collectionMethod.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="dateCollected">
      <mjl:header>
        ${item.dateCollectedMd.displayLabel}
      </mjl:header>
      <mjl:row>
        <fmt:formatDate value="${item.dateCollected}" dateStyle="SHORT" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="geoEntity">
      <mjl:header>
         ${item.geoEntityMd.displayLabel}
      </mjl:header>
      <mjl:row>
        ${item.geoEntity.entityName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>

      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.entomology.MosquitoCollectionController.view.mojo" name="view.link">
          <mjl:property value="${item.id}" name="id" />
        </mjl:commandLink>
        <mjl:commandLink display="ViewAssays" action="dss.vector.solutions.entomology.MosquitoCollectionController.viewAssays.mojo" name="viewAssays.link">
          <mjl:property value="${item.id}" name="id" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
</mjl:table>
<br />

<mjl:commandLink display="View Mosquito Collection Report" action="dss.vector.solutions.report.ReportController.report.mojo" name="MosquitoCollection.viewReport">
  <mjl:property name="reportName" value="collection.rptdesign" />
</mjl:commandLink>