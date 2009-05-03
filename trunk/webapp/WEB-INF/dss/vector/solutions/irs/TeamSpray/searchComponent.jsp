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

<mjl:form name="search" method="POST" id ="searchTeamSpray">
  <dl>
    <dt> Geo Id </dt>
    <dd> <mjl:input id="geoIdEl" param="geoId" type="text" value="${geoId}" maxlength="16"/><a href="#" id="searchOpener">
    <img src="./imgs/icons/world.png"/></a>
    <br/>(<span id ="entityName"></span>)</dd>
    <dt> Insecticide Brand </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${brands}" param="brand.componentId" >
       <mjl:option selected="${brand != null && current.id == brand.id ? 'selected' : 'false'}">
          ${current.displayLabel}
       </mjl:option>
      </mjl:select>
    </dd>
    <dt> Spray Date </dt>
    <dd> <mjl:input param="date" type="text" classes="DatePick" id="sprayDate" value="${date}"/></dd>    
    <dt> Spray Method </dt>
    <dd>
      <mjl:radioGroup var="current" varStatus="status" valueAttribute="enumName" items="${methods}" param="method">
        <mjl:radioOption checked="${current.enumName == method ? 'checked' : 'false'}">
            ${current.displayLabel}
        </mjl:radioOption>
      </mjl:radioGroup>
    </dd>
    <dt> Spray Team </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${teams}" param="team.componentId" >
       <mjl:option selected="${team != null && current.id == team.id ? 'selected' : 'false'}">
          ${current.teamCode}
       </mjl:option>
      </mjl:select>
    </dd>
  </dl>
  <br>
  <mjl:command classes="submitButton" action="dss.vector.solutions.irs.TeamSprayController.searchByParameters.mojo" name="search.button" value="Search" />
</mjl:form>

<div id="cal1Container" class="yui-skin-sam"></div>
<%//out.flush();%>