<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.entomology.AssayController"%>
<%@page import="java.util.Map"%>
<%@page import="dss.vector.solutions.util.yui.ColumnSetup"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.DiagnosticAssayViewDTO"%>
<%@page import="dss.vector.solutions.entomology.TimeResponseAssayViewDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionController"%>
<%@page import="dss.vector.solutions.geo.GeoEntityViewDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionViewDTO"%>


<%@page import="dss.vector.solutions.util.yui.DataGrid"%><c:set var="page_title" value="Enter_bioassays"  scope="request"/>

<mjl:form name="form.name" id="form.id" method="GET">
  <dl>
    <dt> 
      <label>${diagnostic.collectionMd.displayLabel}</label>
    </dt>
    <dd>
      <mjl:input id="collectionInput" param="collectionInput" type="text" />
      <mjl:input id="collectionId" param="collectionId" type="hidden" />       
      <button type="button" id="button.go"><mdss:localize key="Go" /></button>
    </dd>
  </dl>  
</mjl:form>


<dl>
  <mjl:component item="${item}" param="dto">
    <mjl:dt attribute="collectionId">
      ${item.collectionId}
    </mjl:dt>    
    <mjl:dt attribute="collectionMethod">
      ${collectionMethod.displayLabel}
    </mjl:dt>
    <mjl:dt attribute="collectionDate">
      <span class="formatDate">${item.collectionDate}</span>
    </mjl:dt>
    <mjl:dt attribute="geoEntity">
      ${entity.displayString}
    </mjl:dt>
    <mjl:dt attribute="lifeStage">
      <ul>
        <c:forEach items="${item.lifeStageEnumNames}" var="enumName">
          <li>
            ${item.lifeStageMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </mjl:dt> 
    <mjl:dt attribute="abundance">
      ${item.abundance ? item.abundanceMd.positiveDisplayLabel :  item.abundanceMd.negativeDisplayLabel}
    </mjl:dt>
  </mjl:component>
</dl>

<dt>
  <label>${diagnostic.md.displayLabel} </label>
</dt>
<dd>
  <div id="DiagnosticAssay">
  </div>
</dd>
<dt>
  <label>${timeResponse.md.displayLabel} </label>
</dt>
<dd>
  <div id="TimeResponseAssay">
  </div>
</dd>

<mdss:localize key="Save_Comments" var="Save_Comments" />

<mjl:form id="collection.update" name="collection.update" method="POST">
  <mjl:component item="${item}" param="dto">
    <mjl:dt attribute="resistanceAssayComments">
      <input name="collectionId" type="hidden" value="${item.concreteId}" />
      <textarea id="comments" name="comments" cols="30" rows="5">${item.resistanceAssayComments}</textarea>
      <input type="button" name="submit" id="setComments.button" value="${Save_Comments}"  />
    </mjl:dt>
  </mjl:component>  
</mjl:form>



<%
DataGrid diagnostic = (DataGrid) request.getAttribute(AssayController.DIAGNOSTIC_GRID);
DataGrid timeresponse = (DataGrid) request.getAttribute(AssayController.TIME_RESPONSE_GRID);
%>

<%=Halp.loadTypes(new String[]{MosquitoCollectionViewDTO.CLASS, DiagnosticAssayViewDTO.CLASS, TimeResponseAssayViewDTO.CLASS, MosquitoCollectionController.CLASS, GeoEntityViewDTO.CLASS})%>

<script type="text/javascript">
Mojo.Meta.newClass('MDSS.Form', {
  Instance: {
    initialize : function(grids) {
      this._grids = grids;
      this._changes = false;
      this._formEl = document.getElementById("form.id");

      YAHOO.util.Event.on('setComments.button', 'click', this.updateComments, this, this);   
      YAHOO.util.Event.on('button.go', 'click', this.onGoHandler, this, this);     
      YAHOO.util.Event.on('comments', 'change', this.changed, this, this);     
    },

    changed : function() {
      this.setChanges(true);
    },

    setChanges : function(changes) {
      this._changes = changes;
    },
    
    updateComments : function()  {
      var request = new MDSS.Request({
        that : this,
        onSuccess : function() {
          this.that.setChanges(false);
        }
      });
          
      var params = Mojo.Util.collectFormValues('collection.update');
      Mojo.$.dss.vector.solutions.entomology.MosquitoCollectionController.setResistanceAssayCommentMap(request, params);
    },

    submitForm : function() {
      this._formEl.action = "dss.vector.solutions.entomology.AssayController.getResistanceAssays.mojo";
      this._formEl.submit();        
    },

    hasChanges : function() {
      for(var i in this._grids) {
        if(this._grids[i].hasChanges()) {
          return true;
        }
      }

      return this._changes;
    },

    onGoHandler : function(e) {
      if(this.hasChanges()) {
        var x=window.confirm(MDSS.localize("Unsaved_Data"));

        if (x) {
          this.submitForm();
        }
        else {
          YAHOO.util.Event.preventDefault(e);
        }        
      }    
      else {
        this.submitForm();          
      }
    }    
  }
});


(function(){
  YAHOO.util.Event.onDOMReady(function(){
    MDSS.collectionSearch({search:'collectionInput', concrete:'collectionId', type:'<%=MosquitoCollectionDTO.CLASS%>'});
  
       
    // SETUP THE INFECTION DATA GRID
    var biochemicalData = {
      rows:<%=diagnostic.getData()%>,
      columnDefs:<%=diagnostic.getColumnSetupWithDelete()%>,
      defaults:<%=diagnostic.getDefaultValues()%>,
      div_id: "DiagnosticAssay",
      data_type: "Mojo.$.<%=DiagnosticAssayViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false,
      addButton:true
    };        
 
    var biochemicalGrid = MojoGrid.createDataTable(biochemicalData);

    // SETUP THE POOLED DATA GRID
    var molecularData = {
      rows:<%=timeresponse.getData()%>,
      columnDefs:<%=timeresponse.getColumnSetupWithDelete()%>,
      defaults:<%=timeresponse.getDefaultValues()%>,
      div_id: "TimeResponseAssay",
      data_type: "Mojo.$.<%=TimeResponseAssayViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false,
      addButton:true
    };        
 
    var molecularGrid = MojoGrid.createDataTable(molecularData);

    new MDSS.Form([biochemicalGrid, molecularGrid]);
  });
})();
        
</script>
