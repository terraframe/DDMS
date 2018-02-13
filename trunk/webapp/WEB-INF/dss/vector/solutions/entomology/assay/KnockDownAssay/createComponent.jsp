<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@page import="dss.vector.solutions.entomology.assay.AdultAgeRangeDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.KnockDownIntervalViewDTO"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.entomology.assay.KnockDownAssayDTO"%>

<c:set var="page_title" value="Enter_data_for_Knockdown_Assay"  scope="request"/>

<%

DataGrid grid = (DataGrid) request.getAttribute("grid");
KnockDownAssayDTO assay = (KnockDownAssayDTO) request.getAttribute("item");

%>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="form.name" id="form.id" method="POST">
    <%@ include file="form.jsp"%>
    
    <dd>
      <button type="button" id="save.button"> <mdss:localize key="save"/> </button>
      <button type="button" id="delete.button"> <mdss:localize key="Delete"/> </button>
      <button type="button" id="cancel.button"> <mdss:localize key="Cancel"/> </button>    
    </dd>
  </mjl:form>
</dl>

<%=Halp.loadTypes(Arrays.asList(new String[]{MosquitoCollectionViewDTO.CLASS, GeoEntityViewDTO.CLASS, KnockDownAssayDTO.CLASS, AdultAgeRangeDTO.CLASS, KnockDownIntervalViewDTO.CLASS}))%>
<script type="text/javascript">  

Mojo.Meta.newClass('MDSS.KnockDownAssayForm', {
  Instance: {
    initialize : function(grid, isNewInstance) {
      this._attributes = YAHOO.util.Dom.getElementsByClassName("component");

      this._saveButton = new YAHOO.widget.Button("save.button");
      this._deleteButton = new YAHOO.widget.Button("delete.button");
      this._cancelButton = new YAHOO.widget.Button("cancel.button");
      
      this._id = document.getElementById('id');
      this._formEl = document.getElementById("form.id");
      this._grid = grid;
      this._isNewInstance = isNewInstance;
      
      // SETUP THE DELETE BUTTON
      this._initButtons();     

      this._deleteButton.on("click", this.deleteHandler, this, this);
      this._cancelButton.on("click", this.cancelHandler, this, this);      
      this._saveButton.on('click', this.saveHandler, this, this);      
    },
    

    populateComponent : function(component) {
      for(var i=0, len=this._attributes.length; i<len; i++){
        var el = this._attributes[i];
        var key = el.id;
        var value = el.value;
        
        this.setValue(component, key, value);
      }
      
      // Set the more custom fields
      var isofemale = document.getElementById('isofemale.positive').checked;
      var startPoint = MDSS.parseNumber(document.getElementById('startPointinput').value, true);
      var endPoint = MDSS.parseNumber(document.getElementById('endPointinput').value, true);
      
      component.setIsofemale(isofemale);      
      component.getAgeRange().setStartPoint(startPoint);
      component.getAgeRange().setEndPoint(endPoint);

      return component;  
    },

    setValue : function(component, attributeName, value) {
      var attributeDTO = component.getAttributeDTO(attributeName);

      if(attributeDTO instanceof com.runwaysdk.transport.attributes.AttributeDateDTO) {
        value = (value == '') ? null : value;
        
        var date = MDSS.Calendar.parseDate(value, true);

        attributeDTO.setValue(date);        
      }
      else if (attributeDTO instanceof com.runwaysdk.transport.attributes.AttributeNumberDTO) {
        value = (value == '') ? null : value;
        
        var number = MDSS.parseNumber(value);

        attributeDTO.setValue(number);                
      }      
      else {
        attributeDTO.setValue(value);
      }
    },
    
    populateForm : function(component) {
      for(var i=0, len=this._attributes.length; i<len; i++){
        var el = this._attributes[i];
        var key = el.id;
        var value = component.getAttributeDTO(key).getValue();

        el.value = value;
      }
      
      this._id.value = component.getId();
      this._isNewInstance = component.isNewInstance();
    },
    
    deleteHandler : function(){
      this.submitForm("dss.vector.solutions.entomology.assay.KnockDownAssayController.delete.mojo");
    },

    cancelHandler : function(){
      this.submitForm("dss.vector.solutions.entomology.assay.KnockDownAssayController.cancel.mojo");
    },

    hasConcreteId : function() {
      return (!this._isNewInstance);
    },

    _initButtons : function() {
      if(this._deleteButton != null) {
        this._deleteButton.set("disabled", !this.hasConcreteId());
      }

      if(this._cancelButton != null) {
        this._cancelButton.set("disabled", !this.hasConcreteId());
      }
    },

    lock : function() {
      this._saveButton.set("disabled", true);
      this._cancelButton.set("disabled", true);      
      this._deleteButton.set("disabled", true);      
    },

    view : function() {
      this.lock();
      this.submitForm("dss.vector.solutions.entomology.assay.KnockDownAssayController.view.mojo");
    },

    submitForm : function(action) {
      if(this.hasConcreteId()) {
        this._formEl.action = action;
        this._formEl.submit();
      }        
    },

    saveHandler : function() {
      if(this.hasConcreteId())
      {
        var request = new MDSS.Request({
          that:this,
          onSuccess:function(component) {
            this.that.saveComponent(component);
          }
        });
        
        Mojo.$.dss.vector.solutions.entomology.assay.KnockDownAssay.lock(request, this._id.value);
      }
      else
      {
        this.saveComponent(new Mojo.$.dss.vector.solutions.entomology.assay.KnockDownAssay());
      }
    },
    
    saveComponent : function (component) {
      var request = new MDSS.Request({
        that:this,
        onSuccess:function(returnValue, returnComponent) {
          this.that.populateForm(returnComponent);
          this.that.view();
        }
      });

      this.populateComponent(component);

      var intervals = this._grid != null ? this._grid.getParameters()[0] : [];

      component.applyAll(request, intervals);
    }
  }
});

(function(){
  YAHOO.util.Event.onDOMReady(function(){   

    // SETUP THE INTERVAL GRID
    <%=grid.getJavascript()%>        
    
    var _form = new MDSS.KnockDownAssayForm(intervalsGrid, <%=assay.isNewInstance()%>);    
  })
})();       
</script>
    
