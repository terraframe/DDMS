<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>

<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.geo.generated.SentinalSiteDTO"%>
<%@page import="dss.vector.solutions.geo.generated.NonSentinalSiteDTO"%>
<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">

  YAHOO.util.Event.onDOMReady(function(){

    var opener = new YAHOO.util.Element("searchOpener");
    opener.on("click", function(){

      if(MDSS.SelectSearch.isInitialized())
      {
        MDSS.SelectSearch.show();
      }
      else
      {
        var radios = YAHOO.util.Selector.query('input[type="radio"]', 'SurveyPoint.form.id');
        var filterType = '';
        for(var i=0; i<radios.length; i++)
        {
          var radio = radios[i];
          if(radio.checked)
          {
            filterType = radio.value;
          }
        }

       function selectHandler(selected)
       {
         var geoId = document.getElementById('geoIdEl');
         var geoEntityId = document.getElementById('geoEntityId');

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

       MDSS.SelectSearch.initialize(selectHandler, selectHandler, filterType);
      }
    });
  }, null, true);

</script>


<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.name" id="SurveyPoint.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt> Filter </dt>
      <dd>
        <input type="radio" name="filterType" value="" checked="checked" />All  &nbsp;&nbsp;&nbsp;
        <input type="radio" name="filterType" value="${SentinalSiteClass}" />Sentinal Site &nbsp;&nbsp;&nbsp;
      </dd>
      <mjl:dt attribute="geoEntity">
        <mjl:input id="geoIdEl" param="none" type="text" maxlength="16" value="${item.geoEntity.geoId}" /><a href="#" id="searchOpener"><img src="./imgs/icons/world.png"/></a>
        <mjl:input id="geoEntityId" param="geoEntity" type="hidden" value="${item.geoEntity.id}" />
        
</mjl:dt>
      <mjl:dt attribute="surveyDate">
        <mjl:input param="surveyDate" type="text" classes="DatePick" id="surveyDate" />
        
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.SurveyPointController.update.mojo" name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.SurveyPointController.delete.mojo" name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.SurveyPointController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.cancel.button" />
</mjl:form>
