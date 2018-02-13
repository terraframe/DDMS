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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="dss.vector.solutions.ontology.TermSynonymViewDTO"%>
<%@page import="dss.vector.solutions.ontology.TermSynonymArrayViewDTO"%>
<%@page import="java.util.List"%>
<%@page import="dss.vector.solutions.util.yui.DataGrid"%>

<c:set var="page_title" value="Create_TermSynonym"  scope="request"/>
<mjl:messages>
<mjl:message />
</mjl:messages>

<%

DataGrid grid = (DataGrid) request.getAttribute("grid");

%>

<%=Halp.loadTypes(Arrays.asList(new String[]{TermSynonymArrayViewDTO.CLASS, TermSynonymViewDTO.CLASS}))%>

<script type="text/javascript">
var viewTermSynonym = new Mojo.$.dss.vector.solutions.ontology.TermSynonymArrayView();
viewTermSynonym.setTerm("${item.term.id}");
</script>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<c:set var="termvar" scope="request" value="${item.term}" />

<dl>
  <mjl:form name="form.name" id="TermSynonym.form" method="POST">
    <mjl:component item="${item}" param="view">
      <mjl:dt attribute="term">
<%-- 		    <mdss:geo param="term" value="${termvar}" /> --%>
         <mdss:mo param="term" value="${termvar}"/>
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
Mojo.Meta.newClass('MDSS.TermSynonymViewForm', {
  Instance: {
    initialize : function(grid, isNewInstance) {
      this._attributes = YAHOO.util.Dom.getElementsByClassName("component");

      this._saveButton = new YAHOO.widget.Button("save.button");
      this._deleteButton = new YAHOO.widget.Button("delete.button");
      this._cancelButton = new YAHOO.widget.Button("cancel.button");
      
      this._id = document.getElementById('id');
      this._formEl = document.getElementById("TermSynonym.form");
      
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
      var termId = document.getElementById("term").value;
      
      if (termId != null && termId != "")
      {
        component.setTerm("");
        component.setTermName("TERMID=" + termId);
      }

      return component;  
    },
    
    deleteHandler : function(){
      this.submitForm("dss.vector.solutions.ontology.TermSynonymController.delete.mojo");
    },

    cancelHandler : function(){
      this.submitForm("dss.vector.solutions.ontology.TermSynonymController.cancel.mojo");
    },
    
    saveHandler : function() {
      this.saveComponent(viewTermSynonym);
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
      this.submitForm("dss.vector.solutions.ontology.TermSynonymController.view.mojo");
    },

    submitForm : function(action) {
      this._formEl.action = action;
      this._formEl.submit();
    },

    saveComponent : function (component) {
    	console.log("saving...")
      var request = new MDSS.Request({
        that:this,
        onSuccess:function(returnValue, returnComponent) {
        	console.log("success");
          this.that.submitForm("dss.vector.solutions.ontology.TermSynonymController.searchByDTO.mojo");
        }
      });

      this.populateComponent(component);

      this._grid.myDataTable.saveCellEditor(); // Save any open editor forms (DDMS ticket 3428)
      var synonyms = this._grid != null ? this._grid.getParameters()[0] : [];
      
      component.applyWithSynonyms(request, synonyms);
    }
  }
});

(function(){
  YAHOO.util.Event.onDOMReady(function(){   

    // This sets the synonymNamesGrid js variable.
    <%=grid.getJavascript()%>        
    
    var _form = new MDSS.TermSynonymViewForm(synonymNamesGrid, ${newInstance});
  })
})();   
</script>
