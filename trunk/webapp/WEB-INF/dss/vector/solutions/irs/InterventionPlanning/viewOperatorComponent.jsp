<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.util.Halp"%>

<%@page import="dss.vector.solutions.general.MalariaSeasonDTO"%>
<%@page import="dss.vector.solutions.irs.TimeInterventionPlanningViewDTO"%>
<%@page import="dss.vector.solutions.irs.InterventionPlanningController"%>
<%@page import="java.util.Map"%>
<%@page import="dss.vector.solutions.util.ColumnSetup"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO"%>


<%@page import="dss.vector.solutions.irs.InterventionPlanningViewDTO"%><c:set var="page_title" value="Operator_Intervention_Planning"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

OperatorInterventionPlanningViewDTO view = (OperatorInterventionPlanningViewDTO) request.getAttribute(InterventionPlanningController.ITEM);
OperatorInterventionPlanningViewDTO[] rows = (OperatorInterventionPlanningViewDTO[]) request.getAttribute(InterventionPlanningController.VIEWS);

String[] attributes = {"Id", "GeoEntity", "EntityLabel", "Season", "Targets", "NumberofDays", "UnitsPerDay", "RequiredOperators"};

String deleteColumn = "";
%>

<dl>
  <dt>
    <label>
      ${item.geoEntityMd.displayLabel}
    </label>
  </dt>
  <dd>
     ${item.geoEntity.displayString} 
  </dd>
  <dt>
    <label>
      ${item.seasonMd.displayLabel}
      <span class="formatDate"> ${item.season.startDate} </span>
      -
      <span class="formatDate"> ${item.season.endDate} </span>
    </label>
  </dt>
  <dd>
    ${item.season.seasonName}
  </dd>
</dl>
<div id="InterventionPlanning"></div>

<br />
<span class="noprint">
  <mjl:commandLink  action="dss.vector.solutions.irs.InterventionPlanningController.search.mojo" name="search.link" >
    <fmt:message key="Back_To_Search"/>
  </mjl:commandLink>
</span>

<%=Halp.loadTypes(Arrays.asList(new String[]{InterventionPlanningViewDTO.CLASS}))%>
<%=Halp.loadTypes(Arrays.asList(new String[]{OperatorInterventionPlanningViewDTO.CLASS, InterventionPlanningController.CLASS}))%>
<%
Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
map.put("Id", new ColumnSetup(true, false));
map.put("GeoEntity", new ColumnSetup(true, false));
map.put("EntityLabel", new ColumnSetup(false, false));
map.put("Season", new ColumnSetup(true, false));
map.put("SeasonLabel", new ColumnSetup(true, false));
map.put("RequiredOperators", new ColumnSetup(false, false));
map.put("NumberofDays",  new ColumnSetup(false, true, "validateNumberofDays", null, null));    
map.put("UnitsPerDay",  new ColumnSetup(false, true, "validateUnitsPerDay", null, null));    
%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    <%=Halp.getDropdownSetup(view, attributes, deleteColumn, clientRequest)%>

    var validateNumberofDays = function (oData) {
        var days = parseInt(oData, 10);
        
        if(!(Mojo.Util.isNumber(days) && days > 0 && days < 366)) {
          alert(MDSS.localize("Days_Cannot_Exceed_365"));
          return undefined;
        }

        return oData;
    };
    
    var validateUnitsPerDay = function (oData) {
        var units = parseInt(oData, 10);
        
        if(!(Mojo.Util.isNumber(units) && units > 0 && units < 100)) {
          alert(MDSS.localize('Spray_Units_Must_Be_Integer'));

          return undefined;
        }

        return oData;
    };
    
    var data = {
      rows:<%=Halp.getDataMap(rows, attributes, view)%>,
      columnDefs:<%=Halp.getColumnSetup(view, attributes, deleteColumn, true, map)%>,
      defaults: {},
      div_id: "InterventionPlanning",
      data_type: "Mojo.$.<%=OperatorInterventionPlanningViewDTO.CLASS%>",
      saveFunction:"calculate",
      excelButtons:false,
      addButton:false,
      reloadKeys : ["RequiredOperators"],
      saveLabelKey : 'Calculate',
      cleanDisable : false
    };        

    document.addEventListener('load', MojoGrid.createDataTable(data), false);
  });
})();
        
</script>
