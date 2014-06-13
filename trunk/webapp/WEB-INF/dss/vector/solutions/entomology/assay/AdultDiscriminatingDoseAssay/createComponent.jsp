<%@page import="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseIntervalViewDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.AdultAgeRangeDTO"%>
<%@page import="dss.vector.solutions.util.yui.DataGrid"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO"%>

<%@page import="com.runwaysdk.transport.metadata.AttributeMdDTO"%>

<%

DataGrid grid = (DataGrid) request.getAttribute("grid");
AdultDiscriminatingDoseAssayDTO assay = (AdultDiscriminatingDoseAssayDTO) request.getAttribute("item");

%>

<c:set var="page_title" value="Enter_new_adult_diagnostic_assay_data" scope="request" />

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

<%=Halp.loadTypes(Arrays.asList(new String[]{MosquitoCollectionViewDTO.CLASS, GeoEntityViewDTO.CLASS, AdultDiscriminatingDoseAssayDTO.CLASS, AdultAgeRangeDTO.CLASS, AdultDiscriminatingDoseIntervalViewDTO.CLASS}))%>
<script type="text/javascript">  

Mojo.Meta.newClass('MDSS.AdultDiscriminatingDoseAssayForm', {
  Instance: {
    initialize : function(grid, isNewInstance, isReplicate) {
      this._attributes = YAHOO.util.Dom.getElementsByClassName("component");

      this._saveButton = new YAHOO.widget.Button("save.button");
      this._deleteButton = new YAHOO.widget.Button("delete.button");
      this._cancelButton = new YAHOO.widget.Button("cancel.button");
      
      this._id = document.getElementById('id');
      this._formEl = document.getElementById("form.id");
      
      this._grid = grid;
      this._isNewInstance = isNewInstance;
      this._isReplicate = isReplicate;      
      
      // SETUP THE DELETE BUTTON
      this._initButtons();     

      this._deleteButton.on("click", this.deleteHandler, this, this);
      this._cancelButton.on("click", this.cancelHandler, this, this);      
      this._saveButton.on('click', this.saveHandler, this, this);  
      
      // Attach the on change listener to the replicate fields
      var replicates = YAHOO.util.Dom.getElementsByClassName("replicate");

      for(var i=0, len=replicates.length; i<len; i++){
        var el = replicates[i];
        YAHOO.util.Event.addListener(el, 'change', this.validate, this, this);
      }
      
      MDSS.GlobalDateListener = (Mojo.Util.bind(this, this.validate));      
    },
    
    validate : function(e) {  
      if(this._isReplicate)
      {
        alert(MDSS.localize('changing_control_numbers'));
      }
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
      this.submitForm("dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.delete.mojo");
    },

    cancelHandler : function(){
      this.submitForm("dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.cancel.mojo");
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
      this.submitForm("dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.view.mojo");
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
        
        Mojo.$.dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.lock(request, this._id.value);
      }
      else
      {
        this.saveComponent(new Mojo.$.dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay());
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
    
    var _form = new MDSS.AdultDiscriminatingDoseAssayForm(intervalsGrid, <%=assay.isNewInstance()%>, <%=assay.hasReplicates()%>);    
    
    MDSS.ValidationBridge.getInstance().setHandler(_form);
  })
})();       
</script>

