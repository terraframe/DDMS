<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodViewDTO"%>


<%@page import="dss.vector.solutions.util.yui.DataGrid"%><c:set var="page_title" value="premises_units_visit"  scope="request"/>

<div id="premises"></div>

<%
  DataGrid grid = (DataGrid) request.getAttribute("grid");
%>

<%=Halp.loadTypes(Arrays.asList(new String[]{IndividualPremiseVisitViewDTO.CLASS, IndividualPremiseVisitMethodViewDTO.CLASS}))%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    // SETUP THE SUB COLLECTIONS DATA GRID
    var data = {
      columnDefs:<%=grid.getColumns()%>,
      defaults:<%=grid.getDefaultValues()%>,
      div_id: "premises",
      excelButtons:false,
      addButton:false
    };
    
    var rows = <%=grid.getData()%>;
    var metadata = new MDSS.ModelMetadata.init(<%=grid.getMetadata()%>);
    var saveHandler = MDSS.DataGridModel.getDefaultSaveHandler("applyAll", "dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView");
        
    var model = new MDSS.DataGridModel(metadata, rows, saveHandler);

    new MDSS.DataGrid(model, data);
  });
})();
        
</script>

