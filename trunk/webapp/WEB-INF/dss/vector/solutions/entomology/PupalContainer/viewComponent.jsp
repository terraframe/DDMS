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
            
    <mjl:command name="Delete" id="delete.button" classes="button" action="dss.vector.solutions.entomology.PupalContainerController.delete.mojo" value="Delete"/>    
  </mjl:form>
</dl> 

<%
  DataGrid grid = (DataGrid) request.getAttribute("grid");
%>

<%=Halp.loadTypes(Arrays.asList(new String[]{PupalCollectionViewDTO.CLASS, PupalContainerViewDTO.CLASS, PupalContainerAmountViewDTO.CLASS}))%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    // Attach a key to all of the collection elements
    var attributes = YAHOO.util.Dom.getElementsByClassName("collection");

    for each (el in attributes) {
      var key = el.id.split("_")[0];
      //key = key.substr(0, 1).toUpperCase() + key.substr(1);
      
      el.key = key;
    }
      
    // Load the original immutable values into a map
    var original = {};
    var labels = {};
    var premiseId = document.getElementById('premiseId');
    var immutables = YAHOO.util.Dom.getElementsByClassName("immutable");
    var mutables = YAHOO.util.Dom.getElementsByClassName("mutable");
    var elements = [document.getElementById('geoEntity'), document.getElementById('premiseTypeDisplay')];

    var saveImmutables = function() {
      for each (el in immutables) {
        var key = el.key;

        original[key] = el.value;
      }
      
      for each (el in elements) {
        var key = el.id;

        labels[key] = el.value;
      }
    }

    saveImmutables();

    // SETUP SUBMIT BUTTON HANDLERS
    var enableSave = function() {
      grid.enableSaveButton();      
    }

    for each (el in mutables) {
      YAHOO.util.Event.on(el, 'change', enableSave);   
    }
    

    // BUTTON HANDLER: DISABLES LINK BUTTONS WHEN THE MOSQUITO COLLECTION HAS NOT BEEN APPLIED
    var buttonHandler = function() {
      var buttons = YAHOO.util.Dom.getElementsByClassName("button");
      
      for each (el in buttons) {
        el.disabled = (premiseId.value == '');
      }        
    }

    // FORM SCRAPPER USED TO POPULATE A MOSQUITO COLLECTION VIEW USED IN THE SAVE HANDLER
    var populateCollection = function() {
      var collection = new Mojo.$.dss.vector.solutions.entomology.PupalCollectionView();

      var attributes = YAHOO.util.Dom.getElementsByClassName("collection");

      for each (el in attributes) {
        var key = el.key;
        var value = el.value;

        setValue(collection, key, value);
      }

      if(premiseId.value != '') {
        for each (el in immutables) {
          var key = el.key;
          var value = original[key];

          setValue(collection, key, value);
        }          
      }

      return collection;
    }

    var setValue = function(collection, attributeName, value) {
      var attributeDTO = collection.getAttributeDTO(attributeName);

      if(attributeDTO instanceof com.runwaysdk.transport.attributes.AttributeDateDTO) {
        var date = MDSS.Calendar.parseDate(value);

        attributeDTO.setValue(date);        
      }
      else {
        attributeDTO.setValue(value);
      }
    }

    var populateForm = function(collection) {
      // Refresh the immutables from the saved map
      if(premiseId.value == '') {
        saveImmutables();
      }
        
      var attributes = YAHOO.util.Dom.getElementsByClassName("mutable");

      for each (el in attributes) {          
        var key = el.key;          
        var value = collection.getAttributeDTO(key).getValue();

        el.value = value;
      }

      // Reload immutables from the saved map
      for each (el in immutables) {
        var key = el.key;
        var value = original[key];

        el.value = value;
      }
      
      for each (el in elements) {
        var key = el.id;
        var value = labels[key];

        el.value = value;
      }
      
      // Recheck the button status on this page
      buttonHandler();        
    }

    // THE SAVE HANDLER FOR THE SUB COLLECTIONS DATA GRID
    var saveCollection = function(request, parameters) {
      var collection = populateCollection();

      var oldOnSuccess = request.onSuccess;
      var newOnSuccess = function(savedRows, returnedCollection) {
        oldOnSuccess.apply(request, [savedRows]);
        
        populateForm(returnedCollection);
      }

      request.onSuccess = newOnSuccess;

      var containers = parameters[0];
      var amounts = parameters[1];

      collection.applyWithContainers(request, containers, amounts);
    };

    var validateType = function(test, oData) {
      var record = this.getCellEditor().getRecord();

      var shape = record.getData('Shape');
      var valid = test(shape);

      if(valid) {
        return oData;
      }

      return undefined;
    };

    var validateRectangle = Mojo.Util.curry(validateType, function(shape){return shape == 'Rectangle'});
    var validateCircle = Mojo.Util.curry(validateType, function(shape){return shape == 'Circle'});
    var validateShape = Mojo.Util.curry(validateType, function(shape){return shape != ''});
      
    // SETUP THE CONTAINER DATA GRID
    var data = {
      columnDefs:<%=grid.getColumnSetupWithDelete()%>,
      defaults:<%=grid.getDefaultValues()%>,
      div_id: "containers",
      excelButtons:false,
      addButton:true,
      saveLabelKey : "Save_Collection"
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

    // INITIALIZE THE BUTTONS
    if(premiseId.value == '') {
      enableSave();
    }
    
    buttonHandler();
  });
})();
        
</script>