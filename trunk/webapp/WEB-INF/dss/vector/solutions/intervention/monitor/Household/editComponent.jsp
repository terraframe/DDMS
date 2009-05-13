<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>

<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.geo.generated.SentinelSiteDTO"%>
<%@page import="dss.vector.solutions.geo.generated.NonSentinelSiteDTO"%>
<c:set var="page_title" value="Edit_Household"  scope="request"/>
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

    var roofSearch = new YAHOO.util.Element("roofSearch");
    var wallSearch = new YAHOO.util.Element("wallSearch");

    roofSearch.on("click", function()
    {
      if(MDSS.SelectSearch.isInitialized())
      {
        MDSS.SelectSearch.show();
      }
      else
      {
        var curried = MDSS.util.curry(selectHandler, "roofGeoId", "roofId");
        MDSS.SelectSearch.initialize(curried, curried, "");
      }
    });

    wallSearch.on("click", function()
    {
      if(MDSS.SelectSearch.isInitialized())
      {
        MDSS.SelectSearch.show();
      }
      else
      {
        var curried = MDSS.util.curry(selectHandler, "wallGeoId", "wallId");
        MDSS.SelectSearch.initialize(curried, curried, "");
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
    
    <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.HouseholdController.update.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.HouseholdController.delete.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.HouseholdController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.cancel.button" />
  </dl>
</mjl:form>