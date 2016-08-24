<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.geo.GeoSynonymController"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="dss.vector.solutions.geo.GeoSynonymViewDTO"%>
<%@page import="dss.vector.solutions.geo.GeoSynonymArrayViewDTO"%>
<%@page import="java.util.List"%>
<%@page import="dss.vector.solutions.util.yui.DataGrid"%>

<c:set var="page_title" value="Create_GeoSynonym"  scope="request"/>
<mjl:messages>
<mjl:message />
</mjl:messages>

<%

DataGrid grid = (DataGrid) request.getAttribute("grid");

%>

<%=Halp.loadTypes(Arrays.asList(new String[]{GeoSynonymArrayViewDTO.CLASS, GeoSynonymViewDTO.CLASS}))%>

<script type="text/javascript">
var viewGeoSynonym = new Mojo.$.dss.vector.solutions.geo.GeoSynonymArrayView();
viewGeoSynonym.setGeoEntity("${item.geoEntity.id}");
</script>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<c:set var="entity" scope="request" value="${item.geoEntity}" />

<dl>
  <mjl:form name="form.name" id="GeoSynonym.form" method="POST">
    <mjl:component item="${item}" param="view">
      <mjl:dt attribute="geoEntity">
		    <mdss:geo param="geoEntity" value="${entity}" />
		  </mjl:dt>
		  <dd>
	      <div id="synonymNames"></div>
	    </dd>
    </mjl:component>
    
    <dd>
      <button type="button" id="save.button"> <mdss:localize key="save"/> </button>
      <c:if test="${not newInstance}">
        <button type="button" id="delete.button"> <mdss:localize key="Delete"/> </button>
      </c:if>
      <button type="button" id="cancel.button"> <mdss:localize key="Cancel"/> </button>    
    </dd>
  </mjl:form>
</dl>

<script type="text/javascript">  
Mojo.Meta.newClass('MDSS.GeoSynonymViewForm', {
  Instance: {
    initialize : function(grid, isNewInstance) {
      this._attributes = YAHOO.util.Dom.getElementsByClassName("component");

      this._saveButton = new YAHOO.widget.Button("save.button");
      this._deleteButton = new YAHOO.widget.Button("delete.button");
      this._cancelButton = new YAHOO.widget.Button("cancel.button");
      
      this._id = document.getElementById('id');
      this._formEl = document.getElementById("GeoSynonym.form");
      
      this._grid = grid;
      this._isNewInstance = isNewInstance;

      if (!isNewInstance)
      {
        this._deleteButton.on("click", this.deleteHandler, this, this);
      }
      this._cancelButton.on("click", this.cancelHandler, this, this);      
      this._saveButton.on('click', this.saveHandler, this, this);
    },

    populateComponent : function(component) {
      var geoId = document.getElementById("geoEntity").value;
      
      if (geoId != null && geoId != "")
      {
        component.setGeoEntity("");
        component.setGeoEntityName("GEOID=" + geoId);
      }

      return component;  
    },
    
    deleteHandler : function(){
      this.submitForm("dss.vector.solutions.geo.GeoSynonymController.delete.mojo");
    },

    cancelHandler : function(){
      this.submitForm("dss.vector.solutions.geo.GeoSynonymController.cancel.mojo");
    },
    
    saveHandler : function() {
      this.saveComponent(viewGeoSynonym);
    },

    lock : function() {
      this._saveButton.set("disabled", true);
      this._cancelButton.set("disabled", true);      
      if (!this._isNewInstance)
      {
        this._deleteButton.set("disabled", true);
      }
    },

    view : function() {
      this.lock();
      this.submitForm("dss.vector.solutions.geo.GeoSynonymController.view.mojo");
    },

    submitForm : function(action) {
      this._formEl.action = action;
      this._formEl.submit();
    },

    saveComponent : function (component) {
      var request = new MDSS.Request({
        that:this,
        onSuccess:function(returnValue, returnComponent) {
          this.that.submitForm("dss.vector.solutions.geo.GeoSynonymController.searchByDTO.mojo");
        }
      });

      this.populateComponent(component);

      var synonyms = this._grid != null ? this._grid.getParameters()[0] : [];
      
      component.applyWithSynonyms(request, synonyms);
    }
  }
});

(function(){
  YAHOO.util.Event.onDOMReady(function(){   

    // This sets the synonymNamesGrid js variable.
    <%=grid.getJavascript()%>        
    
    var _form = new MDSS.GeoSynonymViewForm(synonymNamesGrid, ${newInstance});
  })
})();   
</script>
