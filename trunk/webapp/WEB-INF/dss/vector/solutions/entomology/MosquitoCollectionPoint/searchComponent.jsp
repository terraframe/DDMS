<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>

<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>

<%@page import="dss.vector.solutions.geo.generated.PermanentWaterBodyDTO"%>
<%@page import="dss.vector.solutions.geo.generated.TrapDTO"%>

<c:set var="page_title" value="Mosquito_Collection_Points"  scope="request"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">

  YAHOO.util.Event.onDOMReady(function(){

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

<%
  request.setAttribute("TrapClass", TrapDTO.CLASS);
  request.setAttribute("PermanentWaterBodyClass", PermanentWaterBodyDTO.CLASS);
%>

<mjl:form name="dss.vector.solutions.entomology.MosquitoCollectionPoint.search" method="GET" id ="searchMosquitoCollections">
  <dl>
    <dt> <fmt:message key="Filter" /> </dt>
    <dd>
        <input type="radio" name="filterType" value="" checked="checked" /><fmt:message key="All" /> &nbsp;&nbsp;&nbsp;
        <input type="radio" name="filterType" value="${TrapClass}" /><fmt:message key="Trap" /> &nbsp;&nbsp;&nbsp;
        <input type="radio" name="filterType" value="${PermanentWaterBodyClass}" /><fmt:message key="Permanent_Water_Body" />
    </dd>
    <dt> <fmt:message key="Geo_Entity" /> </dt>
    <dd> 
      <mjl:input id="geoIdEl" param="geoId" type="text"  maxlength="16" value="${geoId}"/><a id="searchOpener" href="#"><img src="./imgs/icons/world.png"/></a>
      <mjl:messages attribute="geoId">
        <mjl:message/>
      </mjl:messages>
    </dd>
    <dt> <fmt:message key="Start_Date" /> </dt>
    <dd> 
      <mjl:input param="startDate" type="text" classes="DatePick" id="startDate" value="${startDate}"/>
      <mjl:messages attribute="startDate">
        <mjl:message/>
      </mjl:messages>
    </dd>
    <dt> <fmt:message key="End_Date" /> </dt>
    <dd>
      <mjl:input param="endDate" type="text" classes="DatePick" id="endDate" value="${endDate}"/>
      <mjl:messages attribute="endDate">
        <mjl:message/>
      </mjl:messages>
    </dd>

  </dl>
  <br>
  <br>
  <mjl:command classes="submitButton" action="dss.vector.solutions.entomology.MosquitoCollectionPointController.searchByGeoIdAndDate.mojo" name="search.button"
  value="Search"
  />
</mjl:form>


