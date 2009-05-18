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
      var geoEntityName = document.getElementById('entityName');

      if(selected != null)
      {
        geoId.value = selected.getGeoId();
        geoEntityName.innerHTML = selected.getEntityName();// +':'+ selected.getType() ;
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

<mjl:messages>
  <mjl:message />
</mjl:messages>

<%
  request.setAttribute("SentinelSiteClass", SentinelSiteDTO.CLASS);
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
%>

<mjl:form name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.name" id="surveyPointForm" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
  
    <mjl:command value="Create" action="dss.vector.solutions.intervention.monitor.SurveyPointController.create.mojo" name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.create.button" />
  </dl>
</mjl:form>

<div id="cal1Container" class="yui-skin-sam"></div>
<%//out.flush();%>
