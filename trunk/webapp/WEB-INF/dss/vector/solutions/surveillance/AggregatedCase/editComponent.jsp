<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.util.yui.DataGrid"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedCaseViewDTO"%>


<%@page import="dss.vector.solutions.surveillance.CaseTreatmentStockViewDTO"%>
<%@page import="dss.vector.solutions.surveillance.CaseTreatmentViewDTO"%>
<%@page import="dss.vector.solutions.surveillance.CaseTreatmentMethodViewDTO"%>
<%@page import="dss.vector.solutions.surveillance.CaseDiagnosticViewDTO"%>
<%@page import="dss.vector.solutions.surveillance.CaseReferralViewDTO"%><mjl:messages>
  <mjl:message />
</mjl:messages>

<c:set var="page_title" value="Edit_Aggregated_Case"  scope="request"/>

<%@ include file="form.jsp"%>

<div class="pageTitle"> <fmt:message key="Enter_data_for_ages"/> ${item.ageGroup.displayLabel} </div> 

<dl>
  <mjl:form name="form.name" id="form.id" method="POST">
    <mjl:input type="hidden" param="id" id="caseId" value="${item.concreteId}" />
  
    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="concreteId" id="concreteId" classes="component" value="${item.concreteId}" />
      <mjl:input type="hidden" param="geoEntity" id="geoEntity" classes="component" value="${item.geoEntity.id}" />
      <mjl:input type="hidden" param="ageGroup" id="ageGroup" classes="component" value="${item.ageGroup.id}" />
      <mjl:input type="hidden" param="startDate" id="startDate" classes="component DatePick" value="${item.startDate}"/>
      <mjl:input type="hidden" param="endDate" id="endDate" classes="component DatePick" value="${item.endDate}"/>     

      <mjl:dt attribute="geoEntity">
        ${item.geoEntity.displayString}
      </mjl:dt>
      <mjl:dt attribute="startDate">
        <span class="formatDate">${item.startDate}</span>
      </mjl:dt>
      <mjl:dt attribute="endDate">
        <span class="formatDate">${item.endDate}</span>      
      </mjl:dt>      
      <mjl:dt attribute="ageGroup">
        ${item.ageGroup.displayLabel}
      </mjl:dt>          
           
      <mjl:dt attribute="cases" >
        <mjl:input param="cases" classes="component" id="cases" type="text"/>
      </mjl:dt>

      <mjl:dt attribute="positiveCases">
        <mjl:input param="positiveCases" classes="component" id="positiveCases" type="text"/>
      </mjl:dt>
      
      <mjl:dt attribute="negativeCases">
        <mjl:input param="negativeCases" classes="component" id="negativeCases" type="text"/>
      </mjl:dt>
      
      <mjl:dt attribute="deaths">
        <mjl:input param="deaths" classes="component" id="deaths" type="text"/>
      </mjl:dt>
      
    </mjl:component>

    <div id="treatment"></div>
    <div id="method"></div>
    <div id="stock"></div>
    <div id="referral"></div>
    <div id="diagnostic"></div>
    
    <hr />

    <button type="button" id="save.button"> <fmt:message key="save"/> </button>
    <button type="button" id="delete.button"> <fmt:message key="Delete"/> </button>
    <button type="button" id="cancel.button"> <fmt:message key="Cancel"/> </button>
  </mjl:form>
</dl>

<%
  DataGrid treatment = (DataGrid) request.getAttribute("treatment");
  DataGrid method = (DataGrid) request.getAttribute("method");
  DataGrid stock = (DataGrid) request.getAttribute("stock");
  DataGrid referral = (DataGrid) request.getAttribute("referral");
  DataGrid diagnostic = (DataGrid) request.getAttribute("diagnostic");
%>

<%=Halp.loadTypes(new String[]{AggregatedCaseViewDTO.CLASS, CaseTreatmentViewDTO.CLASS, CaseTreatmentMethodViewDTO.CLASS, CaseTreatmentStockViewDTO.CLASS, CaseDiagnosticViewDTO.CLASS, CaseReferralViewDTO.CLASS}) %>

<script type="text/javascript">
Mojo.Meta.newClass('MDSS.AggregatedCaseForm', {
  Instance: {
    initialize : function(grids) {
      this._attributes = YAHOO.util.Dom.getElementsByClassName("component");

      this._saveButton = new YAHOO.widget.Button("save.button");
      this._deleteButton = new YAHOO.widget.Button("delete.button");
      this._cancelButton = new YAHOO.widget.Button("cancel.button");
      
      this._concreteId = document.getElementById('concreteId');
      this._id = document.getElementById('caseId');
      this._formEl = document.getElementById("form.id");
      this._grids = grids;
      
      // SETUP THE DELETE BUTTON
      this._initButtons();     

      this._deleteButton.on("click", this.deleteHandler, this, this);
      this._cancelButton.on("click", this.cancelHandler, this, this);      
      this._saveButton.on('click', this.saveHandler, this, this);      
    },

    populateComponent : function() {
      var component = new Mojo.$.dss.vector.solutions.surveillance.AggregatedCaseView();

      for each (el in this._attributes) {
        var key = el.id;
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
      for each (el in this._attributes) {   
        var key = el.id;
        var value = component.getAttributeDTO(key).getValue();

        el.value = value;
      }
    },
    
    deleteHandler : function(){
      this.submitForm("dss.vector.solutions.surveillance.AggregatedCaseController.delete.mojo");
    },

    cancelHandler : function(){
      this.submitForm("dss.vector.solutions.surveillance.AggregatedCaseController.cancel.mojo");
    },

    hasConcreteId : function() {
      return (this._concreteId.value != '');
    },

    _initButtons : function() {
      if(this._deleteButton != null) {
        this._deleteButton.set("disabled", !this.hasConcreteId());
      }

      if(this._cancelButton != null) {
        this._cancelButton.set("disabled", !this.hasConcreteId());
      }
    },

    view : function() {
      this._saveButton.set("disabled", true);        
      this.submitForm("dss.vector.solutions.surveillance.AggregatedCaseController.view.mojo");
    },

    submitForm : function(action) {
      if(this.hasConcreteId()) {
        this._id.value = this._concreteId.value;        
        this._formEl.action = action;
        this._formEl.submit();
      }        
    },

    saveHandler : function() {
      var request = new MDSS.Request({
        that:this,
        onSuccess:function(returnValue, returnComponent) {
          this.that.populateForm(returnComponent);
          this.that.view();
        }
      });

      var component = this.populateComponent();
      var params = [];

      for(var i in this._grids) {
        var grid = this._grids[i];

        params.push(grid.getParameters()[0]);
      }

      component.applyAll(request, params[0], params[1], params[2], params[3], params[4]);
    }
  }
});

(function(){
  YAHOO.util.Event.onDOMReady(function(){

    // SETUP THE TREATMENT DATA GRID
    var treatmentData = {
      columnDefs:<%=treatment.getColumns()%>,
      defaults:<%=treatment.getDefaultValues()%>,
      div_id: "treatment",
      excelButtons:false,
      addButton:false,
      saveButton:false
    };
        
    var treatmentGrid = new MDSS.DataGrid(new MDSS.DataGridModel(new MDSS.ModelMetadata.init(<%=treatment.getMetadata()%>), <%=treatment.getData()%>, null), treatmentData);

    // SETUP THE method DATA GRID
    var methodData = {
      columnDefs:<%=method.getColumns()%>,
      defaults:<%=method.getDefaultValues()%>,
      div_id: "method",
      excelButtons:false,
      addButton:false,
      saveButton:false
    };
        
    var methodGrid = new MDSS.DataGrid(new MDSS.DataGridModel(new MDSS.ModelMetadata.init(<%=method.getMetadata()%>), <%=method.getData()%>, null), methodData);

    // SETUP THE STOCK DATA GRID
    var stockData = {
      columnDefs:<%=stock.getColumns()%>,
      defaults:<%=stock.getDefaultValues()%>,
      div_id: "stock",
      excelButtons:false,
      addButton:false,
      saveButton:false
    };
        
    var stockGrid = new MDSS.DataGrid(new MDSS.DataGridModel(new MDSS.ModelMetadata.init(<%=stock.getMetadata()%>), <%=stock.getData()%>, null), stockData);

    // SETUP THE referral DATA GRID
    var referralData = {
      columnDefs:<%=referral.getColumns()%>,
      defaults:<%=referral.getDefaultValues()%>,
      div_id: "referral",
      excelButtons:false,
      addButton:false,
      saveButton:false
    };
        
    var referralGrid = new MDSS.DataGrid(new MDSS.DataGridModel(new MDSS.ModelMetadata.init(<%=referral.getMetadata()%>), <%=referral.getData()%>, null), referralData);

    // SETUP THE diagnostic DATA GRID
    var diagnosticData = {
      columnDefs:<%=diagnostic.getColumns()%>,
      defaults:<%=diagnostic.getDefaultValues()%>,
      div_id: "diagnostic",
      excelButtons:false,
      addButton:false,
      saveButton:false
    };
        
    var diagnosticGrid = new MDSS.DataGrid(new MDSS.DataGridModel(new MDSS.ModelMetadata.init(<%=diagnostic.getMetadata()%>), <%=diagnostic.getData()%>, null), diagnosticData);

    var grids = [treatmentGrid, methodGrid, stockGrid, diagnosticGrid, referralGrid];
      
    var _form = new MDSS.AggregatedCaseForm(grids);    
  });
})();
        
</script>

