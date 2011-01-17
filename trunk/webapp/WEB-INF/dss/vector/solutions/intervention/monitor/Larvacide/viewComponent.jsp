<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO"%>
<%@page import="dss.vector.solutions.util.yui.ColumnSetup"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.intervention.monitor.LarvacideDTO"%>
<%@page import="dss.vector.solutions.util.yui.DataGrid"%><c:set var="page_title" value="View_Larvacide" scope="request" />

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
        ${item.geoEntity.displayString}
      </mjl:dt>
      <mjl:dt attribute="geoDescription">
        ${item.geoDescription}
      </mjl:dt>
      <mjl:dt attribute="natureOfControl">
        <c:choose>
          <c:when test="${item.natureOfControl == null}">-</c:when>
          <c:otherwise>
            ${item.natureOfControl ? item.natureOfControlMd.positiveDisplayLabel : item.natureOfControlMd.negativeDisplayLabel}
          </c:otherwise>
        </c:choose>        
      </mjl:dt>      
      <mjl:dt attribute="personCount">
        ${item.personCount}
      </mjl:dt>
      <mjl:dt attribute="teamLeader">
        ${leader.label}
      </mjl:dt>
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.LarvacideController.edit.mojo" name="dss.vector.solutions.intervention.monitor.Larvacide.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.intervention.monitor.LarvacideController.viewAll.mojo" name="dss.vector.solutions.intervention.monitor.Larvacide.viewAll.link">
  <mdss:localize key="View_All" />
</mjl:commandLink>

<div id="LaravacideInstance"></div>
<br />


<%=Halp.loadTypes(Arrays.asList(new String[]{LarvacideInstanceViewDTO.CLASS}))%>

<%
DataGrid grid = (DataGrid) request.getAttribute("grid");
%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    <%=grid.getDropDownMap()%>
        
    var data = {
      rows:<%=grid.getData()%>,
      columnDefs:<%=grid.getColumnSetupWithDelete()%>,
      defaults:<%=grid.getDefaultValues()%>,
      div_id: "LaravacideInstance",
      data_type: "Mojo.$.<%=LarvacideInstanceViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false
    };        

    var grid = MojoGrid.createDataTable(data);
  });
})();
        
</script>
