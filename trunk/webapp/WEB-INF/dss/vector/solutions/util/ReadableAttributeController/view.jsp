<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.ontology.BrowserFieldController"%>
<%@page import="dss.vector.solutions.ontology.FieldDefaultViewDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserFieldDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootController"%>

<c:set var="page_title" value="Assign_Attribute_Permissions" scope="request" />
<c:set var="page_title_suffix" value=" - ${component}" scope="request" />

<mjl:form name="dss.vector.solutions.util.ReadableAttributeController.form.name" id="dss.vector.solutions.util.ReadableAttributeController.form.id" method="POST" onsubmit="return checkHiddenMandatoryFields(this);" >
  <mjl:input type="hidden" param="universal" value="${universal}" />
  <mjl:input type="hidden" param="actor" value="${actor}" />
  <mjl:input type="hidden" param="component" value="${component}" />
  
	  <h2>${actorLabel}</h2>
      <table class="displayTable">
        <mjl:components items="${views}" param="attributeViews" var="view" varStatus="status">
          <mjl:input type="hidden" param="attributeName" value="${view.attributeName}"/>
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <td>
            ${view.attributeRequired == true ? "*" : ""}
            </td>
            <td>
              <mjl:input type="text" param="displayLabel" size="70"/>
            </td>
            <td>
              <c:choose >
                <c:when test="${actor == 'mdss.GUIVisibility' && (view.attributeRequired || view.notBlank)}">
                  <mjl:boolean param="readPermission" value="${view.readPermission}" disabled="disabled" />
                </c:when>
                 <c:when test="${view.attributeRequired == true}">
                  <mjl:boolean classes="requiredAttributes" param="readPermission" value="${view.readPermission}" />
                </c:when>
                <c:otherwise>
                  <mjl:boolean param="readPermission" value="${view.readPermission}" />
                </c:otherwise>
              </c:choose>
              <mjl:messages attribute="readPermission">
                <mjl:message/>
              </mjl:messages>
            </td>
            <td>
              <mdss:checkBoolean param="notBlank" disabled="${view.attributeRequired || actor != 'mdss.GUIVisibility'}" value="${view.attributeRequired || view.notBlank}" />
            </td>
            <td>
              <c:if test="${view.fieldId != '' && actor == 'mdss.GUIVisibility'}">
                <button type="button" id="${view.fieldId}" class="root.button"> <mdss:localize key="Roots"/> </button>         
              </c:if>
            </td>
          </tr>
        </mjl:components>
      </table>

  <mdss:localize key="save" var="Localized_save" />

  <mjl:command classes="submitButton"value="${Localized_save}" action="dss.vector.solutions.util.ReadableAttributeController.setAttributes.mojo" name="dss.vector.solutions.util.ReadableAttributeController.form.create.button" />
</mjl:form>

<div id="modal" style="scroll:auto"></div>

<%=Halp.loadTypes(new String[]{BrowserFieldController.CLASS, BrowserRootController.CLASS, BrowserFieldDTO.CLASS, FieldDefaultViewDTO.CLASS}) %>

<script type="text/javascript">
Mojo.Meta.newClass('MDSS.ReadableAttributeForm', {
  Instance: {
    initialize : function() {
      this._buttons = YAHOO.util.Dom.getElementsByClassName("root.button");      

      for (var el in this._buttons) {
        YAHOO.util.Event.on(el, 'click', this.clickHandler, this, this);
      }

      this._modal = new YAHOO.widget.Panel("modal",  {
        width:"100%",
        height: "300px",
        fixedcenter:true,
        close:true,
        draggable:false,
        zindex:4,
        modal:true,
        visible:false
      });

      // hide all panels spawned by the search modal
      this._modal.subscribe('beforeHide', this._beforeHide, this, this);

	 var inputs = document.getElementsByTagName("input");      
	
	 for (var el in inputs) {
	     if (el.type=='checkbox' && !el.disabled) {
	     	YAHOO.util.Event.on(el, 'click', this.checkboxHandler, this, this);
	   	}
	 }

    },    

    clickHandler : function(e) {
      var request = new MDSS.Request({
        that : this,
        onSuccess: function(innerHTML) {
          var executable = MDSS.util.extractScripts(innerHTML);
          var html = MDSS.util.removeScripts(innerHTML);

          this.that._openModal(html);

          eval(executable);          
        }
      });

      var id = e.originalTarget.id;
        
      Mojo.$.dss.vector.solutions.ontology.BrowserFieldController.view(request, id);        
    },

    checkboxHandler : function(e) {
        var fieldName = e.target.name.split('.')[0]  + '.readPermission';
        var radioButtons = document.getElementsByName(fieldName);
    	for (var rb in radioButtons) {
        	if (e.target.checked) {
		    	if (rb.value=='true') {
			    	rb.checked = true;
		    	}
		    	rb.disabled = true;
        	} else {
            	rb.disabled = false;
        	}
    	}
    },

    _beforeHide : function() {
      this._modal.setBody('');
    },

    _openModal : function(html)
    {
      this._modal.setBody(html);
      this._modal.render(document.body);
      this._modal.show();
    },    
  }
});

(function(){
  YAHOO.util.Event.onDOMReady(function(){
    new MDSS.ReadableAttributeForm();
  });
})();

function checkHiddenMandatoryFields(that) {
	var confirmed = true;

<c:if test="${actor != 'mdss.GUIVisibility'}">
	var needToConfirm = false;
	var inputs = document.getElementsByTagName("input");      
	
	for (var el in inputs) {
	    if (el.type=='checkbox' && el.checked) {
	        var fieldName = el.name.split('.')[0]  + '.readPermission';
	        var radioButtons = document.getElementsByName(fieldName);
	    	for (var rb in radioButtons) {
	        	if (rb.value == 'false' && rb.checked) {
			    	needToConfirm = true;
			    	break;
	        	}
	    	}
	  	}
	}

	if (needToConfirm) {
		confirmed = confirm('<mdss:localize key="Hidden_Mandatory_Fields_Warning"/>');
	}
</c:if>    
	
	return confirmed;
}
</script> 