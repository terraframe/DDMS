<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.util.yui.DataGrid"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>

<%@page import="dss.vector.solutions.intervention.monitor.ControlInterventionController"%>
<%@page import="dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO"%>


<%@page import="dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodViewDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodViewDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonViewDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.PersonInterventionViewDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.PersonInterventionMethodViewDTO"%><c:set var="page_title" value="Control_intervention"  scope="request"/>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<c:set var="entity" scope="request" value="${item.geoEntity}" />

<dl>
  <mjl:form name="form.name" id="ControlIntervention.form" method="POST">
    <mjl:component item="${item}" param="view">
      <mjl:input type="hidden" param="concreteId" id="concreteId" value="${item.concreteId}" classes="component mutable" />
       <mjl:dt attribute="geoEntity">
         <mdss:geo param="geoEntity" id="geoEntity" universals="${entityUniversals}" value="${entity}" concreteClass="component immutable"/>
       </mjl:dt>
      <mjl:dt attribute="startDate">
        <mjl:input param="startDate" id="startDate" type="text" classes="DatePick NoFuture component immutable"/>
       </mjl:dt>
      <mjl:dt attribute="endDate">
        <mjl:input param="endDate" id="endDate" type="text" classes="DatePick NoFuture component immutable"/>
       </mjl:dt>

      <button id="button.go"><fmt:message key="Go" /></button>

      <hr />
      
       <mjl:dt attribute="comments">
         <mjl:textarea param="comments" rows="5" cols="15" id="comments" classes="component mutable">${item.notes}</mjl:textarea>
       </mjl:dt>
       
       <mjl:dt attribute="individulPremiseUniversal">
         <mjl:select param="individulPremiseUniversal" id="individulPremiseUniversal" classes="universal component" valueAttribute="geoHierarchyId" var="current" items="${universals}" includeBlank="true">
           <mjl:option selected="${(individulPremiseUniversal != null && individulPremiseUniversal.geoHierarchyId == current.geoHierarchyId) ? 'selected' : 'false'}">
             ${current.displayLabel}
           </mjl:option>
         </mjl:select>
         
         <button type="button" id="individulPremiseUniversal.button"> <fmt:message key="Edit"/> </button>         
       </mjl:dt>
       
       <mjl:dt attribute="aggregatedPremiseUniversal">
         <mjl:select param="aggregatedPremiseUniversal" id="aggregatedPremiseUniversal" classes="universal component" valueAttribute="geoHierarchyId" var="current" items="${universals}" includeBlank="true">
           <mjl:option selected="${(aggregatedPremiseUniversal != null && aggregatedPremiseUniversal.geoHierarchyId == current.geoHierarchyId) ? 'selected' : 'false'}">
             ${current.displayLabel}
           </mjl:option>
         </mjl:select>
         
         <button type="button" id="aggregatedPremiseUniversal.button"> <fmt:message key="Edit"/> </button>         
       </mjl:dt>
             
       <mjl:dt attribute="personIntervention">
         <button type="button" id="personIntervention.button" class="editButtons"> <fmt:message key="Edit"/> </button>         
       </mjl:dt>
             
    </mjl:component>    
    
    <hr />
    
    <div id="details">
    </div>   
  </mjl:form>
</dl>

<%=Halp.loadTypes(Arrays.asList(new String[]{ControlInterventionController.CLASS, ControlInterventionViewDTO.CLASS, IndividualPremiseVisitViewDTO.CLASS, IndividualPremiseVisitMethodViewDTO.CLASS, AggregatedPremiseVisitViewDTO.CLASS, AggregatedPremiseReasonViewDTO.CLASS, AggregatedPremiseMethodViewDTO.CLASS, PersonInterventionViewDTO.CLASS, PersonInterventionMethodViewDTO.CLASS}))%>

<script type="text/javascript">
Mojo.Meta.newClass('MDSS.ControlInterventionForm', {
  Instance: {
    initialize : function() {
      this._attributes = YAHOO.util.Dom.getElementsByClassName("component");
      this._mutables = YAHOO.util.Dom.getElementsByClassName("mutable");
      this._immutables = YAHOO.util.Dom.getElementsByClassName("immutable");
      this._universals = YAHOO.util.Dom.getElementsByClassName("universal");
      this._editButtons = YAHOO.util.Dom.getElementsByClassName("editButtons");
      
      this._concreteId = document.getElementById('concreteId');
      this._formEl = document.getElementById("ControlIntervention.form");
      this._disabled = false;
      
      this._dirtyIntervention = false;
      this._grid = null;
      
      for each (el in this._attributes) {
        el.key = el.id.split("_")[0];
      }

      for each (el in this._immutables) {
        YAHOO.util.Event.on(el, 'focus', this.focusHandler, this, this);                    
      }

      for each (el in this._universals) {
        el.original = el.value;
        el.button = document.getElementById(el.key + '.button');

        if(el.key == 'individulPremiseUniversal') {
          el.button.action = Mojo.$.dss.vector.solutions.intervention.monitor.ControlInterventionController.getIndividualPremiseMap;
        }
        else if(el.key == 'aggregatedPremiseUniversal') {
          el.button.action = Mojo.$.dss.vector.solutions.intervention.monitor.ControlInterventionController.getAggregatedPremiseMap;
        }

        YAHOO.util.Event.on(el.button, 'click', this.editHandler, this, this); 
        YAHOO.util.Event.on(el, 'change', this.buttonHandler, this, this);          
      }      

      for each (el in this._editButtons) {
        if(el.id == 'personIntervention.button') {
          el.action = Mojo.$.dss.vector.solutions.intervention.monitor.ControlInterventionController.getPersonInterventionMap;
        }

        YAHOO.util.Event.on(el, 'click', this.editHandler, this, this); 
      }      

      YAHOO.util.Event.on('button.go', 'click', this.onGoHandler, this, this);

      for each (el in this._mutables) {
        YAHOO.util.Event.on(el, 'change', this.interventionChanged, this, this);
      }

      this.buttonHandler();
    },

    focusHandler : function() {
      if(!this._disabled) {

        if(this.hasChanges()) {
          this.confirmSave();
        }

        this.disable();        
      }      
    },

    disable : function() {
      this._disabled = true;

      this.buttonHandler();
      this.toggleDelete();

      if(this._grid != null) {
        this._grid.disable();
      }
    },    
   
    populateComponent : function() {
      var component = new Mojo.$.dss.vector.solutions.intervention.monitor.ControlInterventionView();

      for each (el in this._attributes) {
        var key = el.key;
        var value = el.value;
        
        this.setValue(component, key, value);
      }

      return component;  
    },

    setValue : function(component, attributeName, value) {
      var attributeDTO = component.getAttributeDTO(attributeName);

      if(attributeDTO instanceof com.runwaysdk.transport.attributes.AttributeDateDTO) {
        var date = MDSS.Calendar.parseDate(value);

        attributeDTO.setValue(date);        
      }
      else {
        attributeDTO.setValue(value);
      }
    },

    populateForm : function(component) {
      for each (el in this._mutables) {   
        var key = el.key;
        var value = component.getAttributeDTO(key).getValue();

        el.value = value;
      }

      this.toggleDelete();
      this.resetChanges();
    },

    resetChanges : function () {
      this._dirtyIntervention = false;
    },

    interventionChanged : function () {
      this._dirtyIntervention = true;
    },

    hasChanges : function() {
      return (this._dirtyIntervention || (this._grid != null && this._grid.hasChanges()));
    },

    setGrid : function(grid) {
      this._grid = grid;
    },

    initializeDelete : function() {
      this._deleteButton = new YAHOO.widget.Button("delete.button");

      this.toggleDelete();     

      this._deleteButton.on("click", this.deleteConcrete, this, this);
    },

    deleteConcrete : function(){
      var hasConcrete = this.hasConcreteId();

      if(hasConcrete) { 
        this._formEl.action = "dss.vector.solutions.intervention.monitor.ControlInterventionController.delete.mojo";
        this._formEl.submit();
      }      
    },

    hasConcreteId : function() {
      return (this._concreteId.value != '');
    },

    toggleDelete : function() {
      if(this._deleteButton != null) {
        var disabled = (this._disabled || !this.hasConcreteId());
        this._deleteButton.set("disabled", disabled);
      }
    },

    editHandler : function(e) {
      if(this._grid != null && this._grid.hasChanges()) {
        this.confirmSave();
      }
      this.editDetails(e);
    },

    confirmSave : function() {
      var x=window.confirm(MDSS.localize("Save_Changes"));

      if (x && this._grid != null) {
        this._grid.save();
      }        
    },

    editDetails : function(e) {
      var request = new MDSS.Request({
        that : this,
        onSuccess: function(innerHTML) {
          var executable = MDSS.util.extractScripts(innerHTML);
          var html = MDSS.util.removeScripts(innerHTML);
            
          var details = document.getElementById("details");

          details.innerHTML = html;

          eval(executable);

          this.that.initializeDelete();
        }
      });

      var action = e.originalTarget.action;
      var params = Mojo.Util.collectFormValues('ControlIntervention.form');

      for each (el in this._universals) {
        this._unshiftUniversal(params, el.name);
      }

      action(request, params);
    },

    _unshiftUniversal : function(params, key) {
      var universal = params[key][0];

      params[key] = universal;
    },
      
    buttonHandler : function() {
      for each (el in this._universals) {            
        if(el.original != '' && el.original != el.value) {
          var x=window.confirm(MDSS.localize("Change_Aggregation"));

          if (!x) {
            this.selectOption(el, el.original);              
          }
          else {
            el.original = el.value;
            
            this.interventionChanged();
          }
        }
        else {
          el.original = el.value;            
        }

        el.button.disabled = (this._disabled || el.value == '');        
      }
    },

    selectOption : function(obj, value) {
      for (var i=0; i < obj.options.length; i++) {
        if (obj.options[i].value == value) {
          obj.options[i].selected = true;
        }
        else {
          obj.options[i].selected = false;
        }
      }        
    },

    onGoHandler : function(e) {
      if(this.hasChanges()) {
        var x=window.confirm(MDSS.localize("Unsaved_Data"));

        if (x) {
          this.go();
        }
        else {
          YAHOO.util.Event.preventDefault(e);
        }        
      }    
      else {
        this.go();          
      }
    },

    go : function() {
      this._formEl.action = "dss.vector.solutions.intervention.monitor.ControlInterventionController.forward.mojo";
      this._formEl.submit();        
    }    
  }
});

var _form = null;
  
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    _form = new MDSS.ControlInterventionForm();
  });
})();
</script> 