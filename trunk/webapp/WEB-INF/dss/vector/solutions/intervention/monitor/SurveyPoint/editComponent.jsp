<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

         if(selected != null)
         {
           geoId.value = selected.getGeoId();
         }
         else
         {
           geoId.value = '';
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
  <dl>
    <%@ include file="form.jsp"%>
    <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.SurveyPointController.update.mojo" name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.SurveyPointController.delete.mojo" name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.SurveyPointController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.cancel.button" />
  </dl>
</mjl:form>
