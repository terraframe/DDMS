<%@ include file="/WEB-INF/templates/jsp_includes.jsp"%>
<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.geo.generated.CollectionSiteDTO"%>
<%@page import="dss.vector.solutions.geo.generated.SentinelSiteDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>


<%@page import="dss.vector.solutions.export.entomology.MosquitoCollectionViewDTO"%><c:set var="page_title" value="Search_Mosquito_Collections"  scope="request"/>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>
<jsp:include page="/WEB-INF/MOSearch.jsp" />


<%
  request.setAttribute("SentinelSiteClass", SentinelSiteDTO.CLASS);
%>

<script type="text/javascript">
  MDSS.AbstractSelectSearch.ExtraUniversals.push('<%= SentinelSiteDTO.CLASS %>*');
</script>

<mjl:form name="dss.vector.solutions.entomology.MosquitoCollection.search" method="POST" id ="searchMosquitoCollections">
  <mjl:input type="hidden" param="type" value="<%=MosquitoCollectionViewDTO.CLASS%>"/>
  <dl>
    <dt><fmt:message key="Filter"/></dt>
    <dd>
<%--
      <input type="radio" name="filterType" class="filterType" value="${CollectionSiteClass}" checked="checked" />&nbsp;<fmt:message key="All"/>  &nbsp;&nbsp;&nbsp;
      <input type="radio" name="filterType" value="${NonSentinelSiteClass}" class="filterType" />&nbsp;<fmt:message key="Non_Sentinel_Site"/>
--%>
      <input type="radio" name="filterType" value="${SentinelSiteClass}" class="filterType" checked="checked"/>&nbsp;<fmt:message key="Sentinel_Site"/> &nbsp;&nbsp;&nbsp;
    </dd>
    <dt> <label> <fmt:message key="Geo_Entity"/> </label></dt>
    <dd> <mjl:input id="geoIdEl" param="geoId" type="text" maxlength="16" classes="geoInput"/></dd>
    <dt> <label> <fmt:message key="Date_Collected"/> </label></dt>
    <dd> <mjl:input param="collectionDate" type="text" classes="DatePick NoFuture" id="collectionDate"/></dd>
    <dt> <label> <fmt:message key="Collection_Method"/> </label> </dt>
    <dd>
      <span class="clickable" id="collectionMethodBtn"> <fmt:message key="Browser"/></span>
      <div id="collectionMethodDisplay" class="ontologyDisplay">
        <c:if test="${collectionMethod != null}">
          ${collectionMethod.displayLabel}
        </c:if>
      </div>
      <mjl:input type="hidden" param="collectionMethod.componentId" id="collectionMethod" value="${collectionMethod != null ? collectionMethod.id : ''}" />
    </dd>
    <mjl:command classes="submitButton" action="dss.vector.solutions.entomology.MosquitoCollectionController.searchByGeoIdAndDate.mojo" name="search.button" value="Search"/>
  </dl>
</mjl:form>

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.entomology.MosquitoCollectionView" name="excelType"/>
</jsp:include>


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
      <mjl:row>
        ${item.collectionMethod.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="dateCollected">
      <mjl:row>
        <fmt:formatDate value="${item.dateCollected}" pattern="${dateFormatPattern}"  />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="geoEntity">
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

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    new MDSS.GenericOntologyBrowser("<%=MosquitoCollectionDTO.CLASS%>", [{attributeName:'collectionMethod'}]);
  })
})();
</script>