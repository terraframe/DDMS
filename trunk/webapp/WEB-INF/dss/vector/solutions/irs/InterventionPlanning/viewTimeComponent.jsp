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


<%@page import="dss.vector.solutions.irs.InterventionPlanningViewDTO"%><c:set var="page_title" value="Time_Intervention_Planning"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

TimeInterventionPlanningViewDTO view = new TimeInterventionPlanningViewDTO(clientRequest);
TimeInterventionPlanningViewDTO[] rows = (TimeInterventionPlanningViewDTO[]) request.getAttribute(InterventionPlanningController.VIEWS);

String[] attributes = {"Id", "GeoEntity", "EntityLabel", "Season", "Targets", "Operators", "RequiredDays"};

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
  <dt></dt>
  <dd>
    <mjl:form name="InterventionPlanning.setUnits.mojo" method="POST">
      <fmt:message key="Units_Per_Day"/>
      <mjl:input type="text" param="unitsPerDay" size="5" maxlength="5" id="unitsPerDay" value="${unitsPerDay}"/> <input type="button" id="units.button" value=""/>    
    </mjl:form>
  </dd>
</dl>
<div id="InterventionPlanning"></div>

<div style="display:none;">
  <form method="POST" action="dss.vector.solutions.irs.InterventionPlanningController.exportTimePlanning.mojo" name="planning.export" id="planning.export" >
  </form>
</div>

<br />

<span class="noprint">
  <mjl:commandLink  action="dss.vector.solutions.irs.InterventionPlanningController.search.mojo" name="search.link" >
    <fmt:message key="Back_To_Search"/>
  </mjl:commandLink>
</span>

<%=Halp.loadTypes(Arrays.asList(new String[]{InterventionPlanningViewDTO.CLASS}))%>
<%=Halp.loadTypes(Arrays.asList(new String[]{TimeInterventionPlanningViewDTO.CLASS, InterventionPlanningController.CLASS}))%>
<%
Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
map.put("Id", new ColumnSetup(true, false));
map.put("GeoEntity", new ColumnSetup(true, false));
map.put("EntityLabel", new ColumnSetup(false, false));
map.put("Season", new ColumnSetup(true, false));
map.put("SeasonLabel", new ColumnSetup(true, false));
map.put("RequiredDays", new ColumnSetup(false, false));
%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    <%=Halp.getDropdownSetup(view, attributes, deleteColumn, clientRequest)%>
    
	  var unitsPerDay = document.getElementById('unitsPerDay');    
	  var button = document.getElementById('units.button');    

	  button.value = MDSS.localize('Update_Default');
	  
    var saveUnits = function() {
        var units = parseInt(unitsPerDay.value, 10);

        
        if(Mojo.Util.isNumber(units)) {
          var request = new MDSS.Request({});
          Mojo.$.dss.vector.solutions.irs.InterventionPlanningController.setSprayedUnitsPerDay(request, units);
        }
    };

    var saveHandler = function(request, view_array) {
      // Invoke the save method
      var units = parseInt(unitsPerDay.value, 10);
        
      if(Mojo.Util.isNumber(units)) {
        Mojo.$.<%=TimeInterventionPlanningViewDTO.CLASS%>.calculate(request, view_array, units);          
      }
      else {
        Mojo.$.<%=TimeInterventionPlanningViewDTO.CLASS%>.calculateDefault(request, view_array);          
      }
    };

    var validateUnits = function () {
        var units = parseInt(unitsPerDay.value, 10);
        
        if(!(Mojo.Util.isNumber(units) && units > 0 && units < 100)) {
        	unitsPerDay.value = 1;

        	var message = MDSS.localize('Spray_Units_Must_Be_Integer');

        	alert(message);
        }
    };
    
    var data = {
      rows:<%=Halp.getDataMap(rows, attributes, view)%>,
      columnDefs:<%=Halp.getColumnSetup(view, attributes, deleteColumn, true, map)%>,
      defaults:<%=Halp.getDefaultValues(view, attributes)%>,
      div_id: "InterventionPlanning",
      data_type: "Mojo.$.<%=TimeInterventionPlanningViewDTO.CLASS%>",
      saveFunction:"calculateDefault",
      excelButtons:false,
      addButton:false,
      reloadKeys : ["RequiredDays"],
      saveLabelKey : 'Calculate',
      saveHandler : saveHandler,
      cleanDisable : false,
      customButtons : [{
          id : 'Export',
          label : MDSS.localize('Export'),
          action : function() {
            var objects = grid.createObjectRepresentation();
            var form = document.getElementById('planning.export');
            var innerHTML = '';

            if(Mojo.Util.isArray(objects)) {
                for(var i = 0; i < objects.length; i++) {
                    // Decompose the objects for the action parameters
                    var object = objects[i];
                                        
                    innerHTML += '<input type="hidden" name="views_' + i + '.componentId" value="' + object.getId() + '" />\n';
                    innerHTML += '<input type="hidden" name="views_' + i + '.isNew" value="true" />\n';
                    innerHTML += '<input type="hidden" name="#views_' + i + '.actualType" value="dss.vector.solutions.irs.TimeInterventionPlanningView" />\n';
                    innerHTML += '<input type="hidden" name="views_' + i + '.entityLabel" value="' + object.getEntityLabel() + '"/>\n';
                    innerHTML += '<input type="hidden" name="views_' + i + '.targets" value="' + object.getTargets() + '"/>\n';
                    innerHTML += '<input type="hidden" name="views_' + i + '.operators" value="' + object.getOperators() + '"/>\n';
                    innerHTML += '<input type="hidden" name="views_' + i + '.requiredDays" value="' + object.getRequiredDays() + '"/>\n'                    
                }                     
            }

            form.innerHTML = innerHTML;
            form.submit();
          }
      }]
    };        

    var grid = MojoGrid.createDataTable(data);

    YAHOO.util.Event.on(button, 'click', saveUnits);    
    YAHOO.util.Event.on(unitsPerDay, 'blur', validateUnits);    
  });
})();
        
</script>
