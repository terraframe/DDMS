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
<%@page import="dss.vector.solutions.util.yui.DataGrid"%>

<style type="text/css">
.yui-skin-sam .yui-dt th, .yui-skin-sam .yui-dt th a
{
  vertical-align:bottom;
  background-color:#DDDDDD;
  background:none;
}

.yui-dt-first .yui-dt-label {
-moz-transform: none !important;
position: static;
height: auto;
width: auto;
display: block;
text-align: center;
}

.yui-dt-label
{
  /*writing-mode: tb-rl;*/
  -moz-transform: rotate(-90deg);
  width:10px;
  height:190px;
  display:block;
  position:relative;
  top:88px;
  left:88px;
}
</style>

<mjl:component item="${view}" param="dto">
  <mjl:dt attribute="individulPremiseUniversal">
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
      var methods = parameters[1];

      component.applyWithIndividualPremiseViews(request, premises, methods);
    };

    var validateMethod = function(oData) {
      var record = this.getCellEditor().getRecord();
      var index = this.getRecordIndex(record);

      var visited = (grid.getData(index, 'Visited') == 'true');
      var treated = (grid.getData(index, 'Treated') == 'true');

      if(visited && treated) {
        return oData;
      }

      return undefined;
    };   

    var validateTreated = function(oData) {
      var record = this.getCellEditor().getRecord();
      var index = this.getRecordIndex(record);

      var visited = (grid.getData(index, 'Visited') == 'true');

      if(visited) {
        return oData;
      }

      return undefined;
    };   

    var validateNotTreated = function(oData) {
      var editor = this.getCellEditor();

      if(editor != null) {
        var record = editor.getRecord();
        var index = this.getRecordIndex(record);

        var visited = (grid.getData(index, 'Visited') == 'true');
        var treated = (grid.getData(index, 'Treated') == 'true');

        if(visited && !treated) {
          return oData;
        }
      }

      return undefined;
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
      
    var addDataHandler = function(event) {
      if(event.getType() == MDSS.Event.AFTER_SET_DATA) {
        var data = event.getValue()

        if(data.col == 'Visited' ) {
          var row = data.row;
          var value = data.value;

          if(value == 'false') {
            var columns = grid.getColumnDefinitions();

            clearColumns(row, columns, ['Visited']);
          }
          
          grid.refresh();
        }
        else if(data.col == 'Treated') {
          var row = data.row;
          var value = data.value;

          if(value == 'false') {
            var columns = grid.getColumnDefinitions();

            clearColumns(row, columns, ['Visited', 'Treated', 'ReasonsForNotTreated']);
          }
          else if(value == 'true') {
            grid.setData(row, 'ReasonsForNotTreated', '');
          }
          
          grid.refresh();
        }
      }
    };

    var clearColumns = function(row, columns, ignore) {
      for (var i = 0; i < columns.length; i++) {
        var column = columns[i];
                          
        if(column.hidden == null || !column.hidden) {
          if(column.children != null) {
            clearColumns(row, column.children, ignore);
          }
          else {
            var editor = column.editor;
            var key = column.key;

            if(ignore.indexOf(key) == -1) {
              if(editor instanceof YAHOO.widget.DropdownCellEditor) {
                grid.setData(row, key, 'false');
              }
              else if(editor instanceof YAHOO.widget.OntologyTermEditor) {
                grid.setData(row, key, '');                    
              }
            }
          }
        }
      }    
    }

    grid.addListener(addDataHandler);
      

    if(!_form.hasConcreteId()) {
      grid.enableSaveButton();
    }

    _form.setGrid(grid);
  });
})();
        
</script>

