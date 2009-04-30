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
<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>
<c:set var="page_title" value="Create_Survey_Point"  scope="request"/>
<script type="text/javascript">

  YAHOO.util.Event.onDOMReady(function(){

    function selectHandler(selected)
    {
      var geoId = document.getElementById('geoIdEl');
      var geoEntityId = document.getElementById('geoEntityId');
      var geoEntityName = document.getElementById('entityName');

      if(selected != null)
      {
        geoId.value = selected.getGeoId();
        geoEntityId.value = selected.getGeoEntityId();
        geoEntityName.innerHTML = selected.getEntityName();// +':'+ selected.getType() ;
      }
      else
      {
        geoId.value = '';
        geoEntityId.value = '';
        geoEntityName.innerHTML = '';
      }
    }

    var selectSearch = new MDSS.SingleSelectSearch();
    selectSearch.setSelectHandler(selectHandler);
    selectSearch.setTreeSelectHandler(selectHandler);
    selectSearch.setFilter('');

    var radios = YAHOO.util.Selector.query('input[type="radio"]', 'surveyPointForm');
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

<%
  request.setAttribute("SentinelSiteClass", SentinelSiteDTO.CLASS);
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
%>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.name" id="surveyPointForm" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt><fmt:message key="Filter"/></dt>
      <dd>
      <input type="radio" name="filterType" value="" checked="checked" />&nbsp;<fmt:message key="All"/>  &nbsp;&nbsp;&nbsp;
      <input type="radio" name="filterType" value="${SentinelSiteClass}" />&nbsp;<fmt:message key="Sentinel_Site"/> &nbsp;&nbsp;&nbsp;
      </dd>
      <mjl:dt attribute="geoEntity">
        <mjl:input id="geoIdEl" param="none" type="text" maxlength="16"/><a href="#" id="searchOpener"><img src="./imgs/icons/world.png"/></a>
        <mjl:input id="geoEntityId" param="geoEntity" type="hidden" />
        <br/>(<span id ="entityName"></span>)

</mjl:dt>
      <mjl:dt attribute="surveyDate">
        <mjl:input param="surveyDate" type="text" classes="DatePick" id="surveyDate"/>

</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.intervention.monitor.SurveyPointController.create.mojo" name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.create.button" />
</mjl:form>

<div id="cal1Container" class="yui-skin-sam"></div>
<%//out.flush();%>
