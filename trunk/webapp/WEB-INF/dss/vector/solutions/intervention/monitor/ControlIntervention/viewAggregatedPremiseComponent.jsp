<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="dss.vector.solutions.util.yui.DataGrid"%>

<mjl:component item="${view}" param="dto">
  <mjl:dt attribute="aggregatedPremiseUniversal">
    <div id="premises"></div>
  </mjl:dt>
</mjl:component>

<hr />

<%
  DataGrid grid = (DataGrid) request.getAttribute("grid");
%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){
	// VALIDATION METHODS

    // THE SAVE HANDLER FOR THE SUB COLLECTIONS DATA GRID
    var saveHandler = function(request, parameters) {
      var component = _form.populateComponent();

      var oldOnSuccess = request.onSuccess;
      var newOnSuccess = function(savedRows, returnedComponent) {
        oldOnSuccess.apply(request, [savedRows]);

        _form.populateForm(returnedComponent);
      }

      request.onSuccess = newOnSuccess;

      var premises = parameters[0];
      var reasons = parameters[1];
      var methods = parameters[2];

      component.applyWithAggregatedPremiseViews(request, premises, reasons, methods);
    };

    // SETUP THE SUB COLLECTIONS DATA GRID
    var data = {
      columnDefs:<%=grid.getColumns()%>,
      defaults:<%=grid.getDefaultValues()%>,
      div_id: "premises",
      excelButtons:false,
      addButton:false,
      saveButton:false
    };
    
    var rows = <%=grid.getData()%>;
    var metadata = new MDSS.ModelMetadata.init(<%=grid.getMetadata()%>);
        
    var model = new MDSS.DataGridModel(metadata, rows, saveHandler);

    var grid = new MDSS.DataGrid(model, data);
      
    if(!_form.hasConcreteId()) {
      grid.enableSaveButton();
    }

    _form.setGrid(grid);
  });
})();
        
</script>

