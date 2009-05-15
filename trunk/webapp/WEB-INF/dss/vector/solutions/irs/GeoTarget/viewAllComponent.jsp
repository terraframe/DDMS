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

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">
  YAHOO.util.Event.onDOMReady(function(){

    function selectHandler(selected)
    {
      var geoId = document.getElementById('geoIdEl')
       var geoEntityId = document.getElementById('geoEntityId');;
      var geoEntityName = document.getElementById('entityName');

      if(selected != null)
      {
        if(selected.getEntityType() == "${ProvinceClass}" || selected.getEntityType() == "${ProvinceClass}" || selected.getEntityType() == "${ProvinceClass}")
        {
          geoId.value = selected.getGeoId();
          geoEntityId.value = selected.getGeoEntityId();
          geoEntityName.innerHTML = selected.getEntityName();
        }
        else
        {
        	geoEntityName.innerHTML = "Error, Must Select a Province or District or SprayZone";
        }
      }
      else
      {
        geoId.value = '';
        geoEntityName.innerHTML = '';
        geoEntityId.value = '';
      }
    }

    var selectSearch = new MDSS.SingleSelectSearch();
    selectSearch.setSelectHandler(selectHandler);
    selectSearch.setTreeSelectHandler(selectHandler);
    selectSearch.setFilter('');

    var radios = YAHOO.util.Selector.query('input[type="radio"]', 'searchMosquitoCollections');
    for(var i=0; i<radios.length; i++)
    {
      var radio = radios[i];
      YAHOO.util.Event.on(radio, 'click', function(e, obj){

        var radio = e.target;
        if(radio.checked)
        {
          var filter = e.target.value;
          this.setFilter(filter);
        }

      }, null, selectSearch);
    }

    var opener = new YAHOO.util.Element("searchOpener");
    opener.on("click", function(){

      if(selectSearch.isInitialized())
      {
        selectSearch.show();
      }
      else
      {
        selectSearch.render();
      }
    });
  }, null, true);

</script>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<c:set var="now" value="<%=new java.util.Date()%>" scope="request"/>


<mjl:form name="dss.vector.solutions.irs.GeoTargetController.view.mojo" method="POST" id ="searchMosquitoCollections">
  <dl>
    <dt><fmt:message key="Filter"/></dt>
    <dd>
      <input type="radio" name="filterType" value="${ProvinceClass}" checked="checked" />&nbsp;<fmt:message key="Province"/> &nbsp;&nbsp;&nbsp;
      <input type="radio" name="filterType" value="${DistrictClass}" />&nbsp;<fmt:message key="District"/> &nbsp;&nbsp;&nbsp;
      <input type="radio" name="filterType" value="${SprayZoneClass}" />&nbsp;<fmt:message key="SprayZone"/>
    </dd>
    <dt> <label> ${item.geoEntityMd.displayLabel}</label></dt>
    <dd> <mjl:input id="geoIdEl" param="geoId" type="text" maxlength="16"/>
    <a href="#" id="searchOpener"><img src="./imgs/icons/world.png"/></a>
    <mjl:input id="geoEntityId" param="geoEntity.componentId" type="hidden" />
    <br/>(<span id ="entityName"></span>)</dd>
    <dd>
      <input type="radio" name="showChildren" value="true" checked="checked" />&nbsp;<fmt:message key="Edit_All_Children"/>  &nbsp;&nbsp;&nbsp;
      <input type="radio" name="showChildren" value="false" />&nbsp;<fmt:message key="Edit_Only_Selected"/> &nbsp;&nbsp;&nbsp;
    </dd>

    <dt> <label> <fmt:message key="Season"/></label></dt>
    <dd>
    <input id="year" type="text" name="targetYear" value="<fmt:formatDate pattern="yyyy" value="${now}"/>" maxlength="4" />
    </dd>
  </dl>
  <br>
  <br>
  <mjl:command classes="submitButton" action="dss.vector.solutions.irs.GeoTargetController.view.mojo" name="search.button"
  value="Search"
  />
</mjl:form>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>