<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>

<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.geo.generated.SentinelSiteDTO"%>
<%@page import="dss.vector.solutions.geo.generated.NonSentinelSiteDTO"%>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">

YAHOO.util.Event.onDOMReady(function(){

    function selectHandler(selected)
    {
      var geoId = document.getElementById('geoIdEl');
      var geoEntityName = document.getElementById('entityName');

      if(selected != null)
      {
        geoId.value = selected.getGeoId();
        geoEntityName.innerHTML = selected.getEntityName();
      }
      else
      {
        geoId.value = '';
        geoEntityName.innerHTML = '';
      }
    }

    var selectSearch = new MDSS.SingleSelectSearch();
    selectSearch.setSelectHandler(selectHandler);
    selectSearch.setTreeSelectHandler(selectHandler);
    selectSearch.setFilter('');



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

<mjl:form name="search" method="POST" id ="searchAggregatedCase">
  <mjl:input type="hidden" param="ageGroup.componentId" value="${ageGroup.id}"/>
  <dl>
    <dt> Geo Id </dt>
    <dd> <mjl:input id="geoIdEl" param="geoId" type="text" value="${geoId}" maxlength="16"/><a href="#" id="searchOpener">
    <img src="./imgs/icons/world.png"/></a>
    <br/>(<span id ="entityName"></span>)</dd>
    <dt> Period Type </dt>
    <dd>
      <mjl:radioGroup var="current" varStatus="status" valueAttribute="enumName" items="${periodType}" param="periodType">
        <mjl:radioOption checked="${current.enumName == checkedType ? 'checked' : 'false'}">
            ${current.displayLabel}
        </mjl:radioOption>
      </mjl:radioGroup>
    </dd>
    <dt> Period </dt>
    <dd> <mjl:input param="period" type="text" size="2" value="${period}"/></dd>
    <dt> Year </dt>
    <dd> <mjl:input param="year" type="text" size="4" value="${year}"/></dd>
  </dl>
  <br>
  <mjl:command classes="submitButton" action="dss.vector.solutions.surveillance.AggregatedCaseController.searchByGeoIdAndEpiWeek.mojo" name="search.button" value="Search" />
</mjl:form>

<div id="cal1Container" class="yui-skin-sam"></div>
<%//out.flush();%>