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
  <mjl:dt attribute="insecticideIntervention">
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
    var cache = {};
    
    var gridListener = function(event) {
      if(event.getType() == MDSS.Event.AFTER_SET_DATA) {
        var value = event.getValue();
        var col = value.col;

        if(col == 'Insecticide') {
          loadInsecticideLabels(value.row, value.value);
        }
      }
    };

    var setLabels = function(index, obj) {
      grid.setData(index, 'ActiveIngredient', obj.activeIngredient);
      grid.setData(index, 'Amount', obj.amount);

      grid.focus();
    };

    var loadInsecticideLabels = function(index, id) {
      if(cache[id] != null) {
        var obj = cache[id];

        setLabels(index, obj);
      }
      else {
        var request = new MDSS.Request({
          cache:cache,
          index:index,
          id:id,
          onSuccess : function(view) {
            var obj = {activeIngredient:view.getActiveIngredient(), amount:view.getConcentration()};

            this.cache[this.id] = obj;

            setLabels(this.index, obj);   
          }
        });

        Mojo.$.dss.vector.solutions.irs.InsecticideBrandLabel.getLabel(request, id);
      }
    };

  
    // THE SAVE HANDLER FOR THE SUB COLLECTIONS DATA GRID
    var saveHandler = function(request, parameters) {
      var component = _form.populateComponent();

      var oldOnSuccess = request.onSuccess;
      var newOnSuccess = function(savedRows, returnedComponent) {
        oldOnSuccess.apply(request, [savedRows]);

        _form.populateForm(returnedComponent);
      }

      request.onSuccess = newOnSuccess;

      var views = parameters[0];

      component.applyWithInsecticideInterventionViews(request, views);
    };

    <%=grid.getDropDownMap()%>

    // SETUP THE SUB COLLECTIONS DATA GRID
    var data = {
      columnDefs:<%=grid.getColumns()%>,
      defaults:<%=grid.getDefaultValues()%>,
      div_id: "premises",
      excelButtons:false,
      addButton:false,
      saveButton:false,
      doNotReload:['InterventionMethod']
    };
    
    var rows = <%=grid.getData()%>;
    var metadata = new MDSS.ModelMetadata.init(<%=grid.getMetadata()%>);
        
    var model = new MDSS.DataGridModel(metadata, rows, saveHandler);

    var grid = new MDSS.DataGrid(model, data);
    grid.addListener(gridListener);
          
    if(!_form.hasConcreteId()) {
      grid.enableSaveButton();
    }

    _form.setGrid(grid);
  });
})();
        
</script>

