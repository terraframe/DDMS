<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>

<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>

<%@page import="dss.vector.solutions.geo.generated.PermanentWaterBodyDTO"%>
<%@page import="dss.vector.solutions.geo.generated.TrapDTO"%>


<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">

MDSS.currentSearchModal = null;

  YAHOO.util.Event.onDOMReady(function(){

    var opener = new YAHOO.util.Element("searchOpener");
    opener.on("click", function(){

      if(MDSS.SelectSearch.isInitialized())
      {
        MDSS.SelectSearch.show();
      }
      else
      {
        var radios = YAHOO.util.Selector.query('input[type="radio"]', 'searchMosquitoCollections');
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

<%
  request.setAttribute("TrapClass", TrapDTO.CLASS);
  request.setAttribute("PermanentWaterBodyClass", PermanentWaterBodyDTO.CLASS);
%>

<mjl:form name="dss.vector.solutions.entomology.MosquitoCollectionPoint.search" method="POST" id ="searchMosquitoCollections">
  <dl>
    <dt> Filter </dt>
    <dd>
        <input type="radio" name="filterType" value="" checked="checked" />All &nbsp;&nbsp;&nbsp;
        <input type="radio" name="filterType" value="${TrapClass}" />Trap &nbsp;&nbsp;&nbsp;
        <input type="radio" name="filterType" value="${PermanentWaterBodyClass}" />Permanent Water Body
    </dd>
    <dt> Geo Id </dt>
    <dd> <mjl:input id="geoIdEl" param="geoId" type="text" /><a id="searchOpener" href="#"><img src="./imgs/icons/world.png"/></a></dd>
    <dt>Start Date </dt>
    <dd> <mjl:input param="startDate" type="text" classes="DatePick" id="startDate"/></dd>
    <dt>End Date </dt>
    <dd> <mjl:input param="endDate" type="text" classes="DatePick" id="endDate"/></dd>

  </dl>
  <br>
  <br>
  <mjl:command classes="submitButton" action="dss.vector.solutions.entomology.MosquitoCollectionPointController.searchByGeoIdAndDate.mojo" name="search.button"
  value="Search"
  />
</mjl:form>


