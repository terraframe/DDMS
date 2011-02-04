<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.irs.InterventionPlanningController"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO"%>
<%@page import="dss.vector.solutions.irs.InterventionPlanningViewDTO"%>
<%@page import="dss.vector.solutions.util.yui.DataGrid"%>

<c:set var="page_title" value="Insecticide_Intervention_Planning"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<dl>
  <dt>
    <label>
      * ${item.geoEntityMd.displayLabel}
    </label>
  </dt>
  <dd>
     ${item.geoEntity.displayString} 
  </dd>
  <dt>
    <label>
      * ${item.seasonMd.displayLabel}
      <span class="formatDate"> ${item.season.startDate} </span>
      -
      <span class="formatDate"> ${item.season.endDate} </span>
    </label>
  </dt>
  <dd>
    ${item.season.seasonName}
  </dd>
  <dt>
    <label> * <mdss:localize key="Insecticide"/> </label>
  </dt>
  <dd>
    <mjl:form name="InterventionPlanning.setUnits.mojo" method="POST">
      <mjl:select valueAttribute="insecticdeId" param="brand" var="current" items="${brands}" id="brand.id">
        <mjl:option>
          ${current.label}
        </mjl:option>
      </mjl:select>
    </mjl:form>
  </dd>
</dl>
<div id="InterventionPlanning"></div>

<div style="display:none;">
  <form method="POST" action="dss.vector.solutions.irs.InterventionPlanningController.exportInsecticidePlanning.mojo" name="planning.export" id="planning.export" >
  </form>
</div>

<br />
<span class="noprint">
  <mjl:commandLink  action="dss.vector.solutions.irs.InterventionPlanningController.search.mojo" name="search.link" >
    <mdss:localize key="Back_To_Search"/>
  </mjl:commandLink>
</span>

<%=Halp.loadTypes(Arrays.asList(new String[]{InterventionPlanningViewDTO.CLASS, InsecticideInterventionPlanningViewDTO.CLASS, InterventionPlanningController.CLASS}))%>

<%
DataGrid grid = (DataGrid) request.getAttribute("grid");
%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    <%=grid.getDropDownMap()%>
    
    var brand = document.getElementById('brand.id');    

    var saveHandler = function(request, parameters) {
      // Invoke the save method
      var brandId = brand.value;
        
      if(Mojo.Util.isString(brandId)) {
        Mojo.$.<%=InsecticideInterventionPlanningViewDTO.CLASS%>.calculate(request, parameters[0], brandId);          
      }
    };
    
    var data = {
      rows:<%=grid.getData()%>,
      columnDefs:<%=grid.getColumnSetup("")%>,
      defaults:<%=grid.getDefaultValues()%>,
      div_id: "InterventionPlanning",
      data_type: "Mojo.$.<%=InsecticideInterventionPlanningViewDTO.CLASS%>",
      saveFunction:"calculate",
      excelButtons:false,
      addButton:false,
      reloadKeys : ["RequiredInsecticide"],
      saveLabelKey : 'Calculate',
      saveHandler : saveHandler,
      cleanDisable : false,
      customButtons : [{
          id : 'Export',
          label : MDSS.localize('Export'),
          action : function() {
            var objects = grid.getModel().getParameters()[0];
            var form = document.getElementById('planning.export');
            var innerHTML = '';

            if(Mojo.Util.isArray(objects)) {
                for(var i = 0; i < objects.length; i++) {
                    // Decompose the objects for the action parameters
                    var object = objects[i];
                                        
                    innerHTML += '<input type="hidden" name="views_' + i + '.componentId" value="' + object.getId() + '" />\n';
                    innerHTML += '<input type="hidden" name="views_' + i + '.isNew" value="true" />\n';
                    innerHTML += '<input type="hidden" name="#views_' + i + '.actualType" value="dss.vector.solutions.irs.InsecticideInterventionPlanningView" />\n';
                    innerHTML += '<input type="hidden" name="views_' + i + '.entityLabel" value="' + object.getEntityLabel() + '"/>\n';

                    if(object.getTargets() != null) {
                      innerHTML += '<input type="hidden" name="views_' + i + '.targets" value="' + object.getTargets() + '"/>\n';
                    }

                    if(object.getRequiredInsecticide() != null) {
                      innerHTML += '<input type="hidden" name="views_' + i + '.requiredInsecticide" value="' + object.getRequiredInsecticide() + '"/>\n'                    
                    }
                        
                }                     
            }

            form.innerHTML = innerHTML;
            form.submit();
          }
      }]
      
    };        

    var grid = MojoGrid.createDataTable(data);
  });
})();
        
</script>
