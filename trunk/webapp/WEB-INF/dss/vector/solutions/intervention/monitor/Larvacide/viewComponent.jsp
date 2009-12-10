<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO"%>
<%@page import="dss.vector.solutions.util.ColumnSetup"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.intervention.monitor.LarvacideDTO"%><c:set var="page_title" value="View_Larvacide" scope="request" />

<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

LarvacideDTO control = (LarvacideDTO) request.getAttribute("item");
LarvacideInstanceViewDTO view = (LarvacideInstanceViewDTO) request.getAttribute("view");
LarvacideInstanceViewDTO[] rows = (LarvacideInstanceViewDTO[]) request.getAttribute("rows");

String[] attributes = {"ConcreteId", "ControlId", "Target", "Treated", "ControlMethod", "Unit", "UnitsUsed"};

String deleteColumn = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
%>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.intervention.monitor.Larvacide.form.name" id="dss.vector.solutions.intervention.monitor.Larvacide.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="startDate">
        <span class="formatDate">
          ${item.startDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="completionDate">
        <span class="formatDate">
          ${item.completionDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="geoEntity">
        ${item.geoEntity}
      </mjl:dt>
      <mjl:dt attribute="geoDescription">
        ${item.geoDescription}
      </mjl:dt>
      <mjl:dt attribute="natureOfControl">
        ${item.natureOfControl ? item.natureOfControlMd.positiveDisplayLabel : item.natureOfControlMd.negativeDisplayLabel}
      </mjl:dt>      
      <mjl:dt attribute="personCount">
        ${item.personCount}
      </mjl:dt>
      <mjl:dt attribute="teamLeader">
        ${item.teamLeader}
      </mjl:dt>
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.LarvacideController.edit.mojo" name="dss.vector.solutions.intervention.monitor.Larvacide.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.intervention.monitor.LarvacideController.viewAll.mojo" name="dss.vector.solutions.intervention.monitor.Larvacide.viewAll.link">
  <fmt:message key="View_All" />
</mjl:commandLink>

<div id="LaravacideInstance"></div>
<br />


<%=Halp.loadTypes(Arrays.asList(new String[]{LarvacideInstanceViewDTO.CLASS}))%>

<%
Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
map.put("ConcreteId", new ColumnSetup(true, false));
map.put("ControlId", new ColumnSetup(true, false));
map.put("Target", new ColumnSetup(false, true));
map.put("Treated", new ColumnSetup(false, true));
map.put("ControlMethod", new ColumnSetup(false, true));
map.put("Unit", new ColumnSetup(false, true));
map.put("UnitsUsed", new ColumnSetup(false, true));
%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    <%=Halp.getDropdownSetup(view, attributes, deleteColumn, clientRequest)%>
        
    var data = {
      rows:<%=Halp.getDataMap(rows, attributes, view)%>,
      columnDefs:<%=Halp.getColumnSetup(view, attributes, deleteColumn, true, map)%>,
      defaults:<%=Halp.getDefaultValues(view, attributes)%>,
      div_id: "LaravacideInstance",
      data_type: "Mojo.$.<%=LarvacideInstanceViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false
    };        

    var grid = MojoGrid.createDataTable(data);
  });
})();
        
</script>
