<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.util.yui.DataGrid"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.PupalContainerViewDTO"%>
<%@page import="dss.vector.solutions.entomology.PupalContainerAmountViewDTO"%>
<%@page import="dss.vector.solutions.entomology.PupalCollectionViewDTO"%>

<c:set var="page_title" value="Pupal_by_individual_container"  scope="request"/>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<c:set var="entity" scope="request" value="${item.geoEntity}" />

<dl>
  <mjl:form name="form.name" id="PupalContainer.form" method="POST">
    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="concreteId" id="concreteId" value="${item.concreteId}" classes="collection mutable" />
      <mjl:input type="hidden" param="premiseId" id="premiseId" value="${item.premiseId}" classes="collection mutable"/>
       <mjl:dt attribute="geoEntity">
         <mdss:geo param="geoEntity" id="geoEntity" universals="${entityUniversals}" value="${entity}" concreteClass="collection immutable"/>
       </mjl:dt>
      <mjl:dt attribute="startDate">
        <mjl:input param="startDate" id="startDate" type="text" classes="DatePick NoFuture collection immutable"/>
       </mjl:dt>
      <mjl:dt attribute="endDate">
        <mjl:input param="endDate" id="endDate" type="text" classes="DatePick NoFuture collection immutable"/>
       </mjl:dt>
      <mjl:dt attribute="premiseType">
        <mdss:mo param="premiseType" value="${premiseType}" classes="collection immutable"/>
      </mjl:dt>
      
      <mjl:command name="Go" action="dss.vector.solutions.entomology.PupalContainerController.forward.mojo" value="Go"/>
         
      <hr />
      
       <mjl:dt attribute="collectionId">
         <mjl:input type="text" param="collectionId" id="collectionId" classes="collection mutable"/>
       </mjl:dt>
       <mjl:dt attribute="notes">
         <mjl:textarea param="notes" rows="5" cols="15" id="notes" classes="collection mutable">${item.notes}</mjl:textarea>
       </mjl:dt>
       
       <hr />
       
       <mjl:dt attribute="numberExamined">
         <mjl:input type="text" param="numberExamined" id="numberExamined" classes="collection mutable"/>
       </mjl:dt>
       <mjl:dt attribute="premiseSize">
         <mjl:input type="text" param="premiseSize" id="premiseSize" classes="collection mutable"/>
       </mjl:dt>
       <mjl:dt attribute="numberInhabitants">
         <mjl:input type="text" param="numberInhabitants" id="numberInhabitants" classes="collection mutable"/>
       </mjl:dt>
       
    </mjl:component>    
    
    <hr />
    
    <div id="containers"></div>   

    <button type="button" id="delete.button"> <fmt:message key="Delete"/> </button>
  </mjl:form>
</dl> 

<%
  DataGrid grid = (DataGrid) request.getAttribute("grid");
%>

<%=Halp.loadTypes(Arrays.asList(new String[]{PupalCollectionViewDTO.CLASS, PupalContainerViewDTO.CLASS, PupalContainerAmountViewDTO.CLASS}))%>

<script type="text/javascript">
Mojo.Meta.newClass('MDSS.PupalForm', {
  Instance: {
    initialize : function(grid) {
      // Attach a key to all of the collection elements
      this._attributes = YAHOO.util.Dom.getElementsByClassName("collection");
      this._premiseId = document.getElementById('premiseId');
      this._immutables = YAHOO.util.Dom.getElementsByClassName("immutable");
      this._mutables = YAHOO.util.Dom.getElementsByClassName("mutable");
      this._refresh = [document.getElementById('geoEntity'), document.getElementById('premiseTypeDisplay')];
      this._original = {};
      this._labels = {};
      this._grid = grid;
      this._deleteButton = new YAHOO.widget.Button("delete.button");

      this._deleteButton.on("click", function(){
        var formEl = document.getElementById("PupalContainer.form");
        formEl.action = "dss.vector.solutions.entomology.PupalContainerController.delete.mojo";
        formEl.submit();
      });    
        
      for each (el in this._attributes) {
        var key = el.id.split("_")[0];
      
        el.key = key;
      }
      
      for each (el in this._mutables) {
        YAHOO.util.Event.on(el, 'change', this.enableSave, this, this);   
      }

      this.saveImmutables();      

      // INITIALIZE THE BUTTONS
      if(!this.hasPremise()) {
        this.enableSave();
      }
      
      this.buttonHandler();        
    },

    saveImmutables : function() {
      for each (el in this._immutables) {
        this._populateMap(this._original, el.key, el);
      }
    
      for each (el in this._refresh) {
        this._populateMap(this._labels, el.id, el);
      }
    },

    _populateMap : function(map, key, el) {
      if(el.classList.contains("DatePick")) {
        map[key] = MDSS.Calendar.getLocalizedString(el.value);
      }
      else {
        map[key] = el.value;
      }
    },

    enableSave : function() {
      this._grid.enableSaveButton();      
    },

    hasPremise : function() {
      return (this._premiseId.value != '');
    },

    populateCollection : function() {
      var collection = new Mojo.$.dss.vector.solutions.entomology.PupalCollectionView();

      for each (el in this._attributes) {
        var key = el.key;
        var value = el.value;

        this.setValue(collection, key, value);
      }

      if(this.hasPremise()) {
        for each (el in this._immutables) {
          var key = el.key;
          var value = this._original[key];

          this.setValue(collection, key, value);
        }          
      }

      return collection;
    },

    setValue : function(collection, attributeName, value) {
      var attributeDTO = collection.getAttributeDTO(attributeName);

      if(attributeDTO instanceof com.runwaysdk.transport.attributes.AttributeDateDTO) {
        var date = MDSS.Calendar.parseDate(value);

        attributeDTO.setValue(date);        
      }
      else {
        attributeDTO.setValue(value);
      }
    },

    refresh : function(collection) {
      // Refresh the immutables from the saved map
      if(!this.hasPremise()) {
        this.saveImmutables();
      }
          
      for each (el in this._mutables) {          
        var key = el.key;          
        var value = collection.getAttributeDTO(key).getValue();

        el.value = value;
      }

      // Reload immutables from the saved map
      for each (el in this._immutables) {
        var key = el.key;
        var value = this._original[key];

        el.value = value;
      }
        
      for each (el in this._refresh) {
        var key = el.id;
        var value = this._labels[key];

        el.value = value;
      }
        
      // Recheck the button status on this page
      this.buttonHandler();        
    },
   
    buttonHandler : function() {
      this._deleteButton.set("disabled", !this.hasPremise());
    }
  }
});


(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    // THE SAVE HANDLER FOR THE SUB COLLECTIONS DATA GRID
    var saveCollection = function(request, parameters) {
      var collection = form.populateCollection();

      var oldOnSuccess = request.onSuccess;
      var newOnSuccess = function(savedRows, returnedCollection) {
        oldOnSuccess.apply(request, [savedRows]);
        
        form.refresh(returnedCollection);
      }

      request.onSuccess = newOnSuccess;

      var containers = parameters[0];
      var amounts = parameters[1];

      collection.applyWithContainers(request, containers, amounts);
    };

    var validateType = function(test, oData) {
      var record = this.getCellEditor().getRecord();
      var index = this.getRecordIndex(record);

      var shape = grid.getData(index, 'Shape');
      var valid = test(shape);

      if(valid) {
        return oData;
      }

      return undefined;
    };

    var validateRectangle = Mojo.Util.curry(validateType, function(shape){return shape == 'RECTANGLE'});
    var validateCircle = Mojo.Util.curry(validateType, function(shape){return shape == 'CIRCLE'});
    var validateShape = Mojo.Util.curry(validateType, function(shape){return shape != ''});
      
    // SETUP THE CONTAINER DATA GRID
    var data = {
      columnDefs:<%=grid.getColumnSetupWithDelete()%>,
      defaults:<%=grid.getDefaultValues()%>,
      div_id: "containers",
      excelButtons:false,
      addButton:true,
      saveLabelKey : "Save_Collection",
      
    };
    
    var rows = <%=grid.getData()%>;
    var metadata = new MDSS.ModelMetadata.init(<%=grid.getMetadata()%>);
        
    var model = new MDSS.DataGridModel(metadata, rows, saveCollection);

    var addDataHandler = function(event) {
      if(event.getType() == MDSS.Event.AFTER_SET_DATA) {
        var data = event.getValue()

        if(data.col == 'Shape') {
          var row = data.row;
          var value = data.value;
          var dataTable = grid.getDataTable();

          if(value == '') {              
            grid.setData(row, 'Height', '');
          }

          if(value == '' || value == 'RECTANGLE') {
            grid.setData(row, 'Diameter', '');
            grid.setData(row, 'OpeningDiameter', '');
          }

          if(value == '' || value == 'CIRCLE') {
            grid.setData(row, 'Width', '');
            grid.setData(row, 'ContainerLength', '');
            grid.setData(row, 'OpeningWidth', '');
            grid.setData(row, 'OpeningLength', '');
          }

          dataTable.render();
        }
      }
    };

    var grid = new MDSS.DataGrid(model, data);

    grid.addListener(addDataHandler);

    var form = new MDSS.PupalForm(grid);
  });
})();
        
</script>