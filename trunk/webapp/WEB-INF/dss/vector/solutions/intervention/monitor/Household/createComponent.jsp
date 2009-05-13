<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>

<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.geo.generated.SentinelSiteDTO"%>
<%@page import="dss.vector.solutions.geo.generated.NonSentinelSiteDTO"%>
<c:set var="page_title" value="Create_Household"  scope="request"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">

  YAHOO.util.Event.onDOMReady(function(){

    function selectHandler(geoId, entityId, selected)
    {
      var geoId = document.getElementById(geoId);
      var geoEntityId = document.getElementById(entityId);

      if(selected != null)
      {
        geoId.value = selected.getGeoId();
        geoEntityId.value = selected.getGeoEntityId();
      }
      else
      {
        geoId.value = '';
        geoEntityId.value = '';
      }
    }

    var selectSearch = new MDSS.SingleSelectSearch();
    selectSearch.setFilter('');

    var roofSearch = new YAHOO.util.Element("roofSearch");
    var wallSearch = new YAHOO.util.Element("wallSearch");


    roofSearch.on("click", function()
    {
      var curried = MDSS.util.curry(selectHandler, "roofGeoId", "roofId");
      selectSearch.setSelectHandler(curried);
      selectSearch.setTreeSelectHandler(curried);

      if(selectSearch.isInitialized())
      {
        selectSearch.show();
      }
      else
      {
        selectSearch.render();
      }
    });

    wallSearch.on("click", function()
    {
      var curried = MDSS.util.curry(selectHandler, "wallGeoId", "wallId");
      selectSearch.setSelectHandler(curried);
      selectSearch.setTreeSelectHandler(curried);

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

<mjl:form name="dss.vector.solutions.intervention.monitor.Household.form.name" id="Household.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
      
    <mjl:command value="Create" action="dss.vector.solutions.intervention.monitor.HouseholdController.create.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.create.button" />
  </dl>
</mjl:form>

<div id="cal1Container" class="yui-skin-sam"></div>
<%
    //out.flush();
%>