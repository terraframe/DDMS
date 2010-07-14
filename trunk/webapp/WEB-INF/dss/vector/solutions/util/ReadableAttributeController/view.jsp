<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>


<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.ontology.BrowserFieldController"%>
<%@page import="dss.vector.solutions.ontology.FieldDefaultViewDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserFieldDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootController"%><c:set var="page_title" value="Assign_Attribute_Permissions" scope="request" />

<mjl:form name="dss.vector.solutions.util.ReadableAttributeController.form.name" id="dss.vector.solutions.util.ReadableAttributeController.form.id" method="POST" >
  <mjl:input type="hidden" param="universal" value="${universal}" />
  <mjl:input type="hidden" param="actor" value="${actor}" />

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
                <c:when test="${actor == 'mdss.GUIVisibility'}">
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
              <mdss:checkBoolean param="notBlank" disabled="${view.attributeRequired}" value="${view.attributeRequired || view.notBlank}"/>
            </td>
            <td>
              <c:if test="${view.fieldId != ''}">
                <button type="button" id="${view.fieldId}" class="root.button"> <fmt:message key="Roots"/> </button>         
              </c:if>
            </td>
          </tr>
        </mjl:components>
      </table>

  <mjl:command classes="submitButton"value="save" action="dss.vector.solutions.util.ReadableAttributeController.setAttributes.mojo" name="dss.vector.solutions.util.ReadableAttributeController.form.create.button" />
</mjl:form>

<div id="modal" style="scroll:auto"></div>

<%=Halp.loadTypes(new String[]{BrowserFieldController.CLASS, BrowserRootController.CLASS, BrowserFieldDTO.CLASS, FieldDefaultViewDTO.CLASS}) %>

<script type="text/javascript">
Mojo.Meta.newClass('MDSS.ReadableAttributeForm', {
  Instance: {
    initialize : function() {
      this._buttons = YAHOO.util.Dom.getElementsByClassName("root.button");      

      for each (el in this._buttons) {
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

// TODO -- Remove this horrific hack by fixing the mjl:command generated javascript
var oldHandler;
(function(){ 
    var buttons = YAHOO.util.Dom.getElementsByClassName("submitButton");      

    for each (el in buttons) {
      oldHandler = el.onclick;
      el.onclick = function(){
          if (checkHiddenMandatoryFields()) {
              oldHandler();
          }
      }
      break;
    }
})();


function checkHiddenMandatoryFields() {
	var needToConfirm = false;

	var buttons = YAHOO.util.Dom.getElementsByClassName("requiredAttributes");      
    for each (el in buttons) {
      if (el.checked && el.value=='false') {
          needToConfirm = true;
          break;
      }
    }
	
	var confirmed = true;
	if (needToConfirm) {
		confirmed = confirm('<fmt:message key="Hidden_Mandatory_Fields_Warning"/>');
	}
	return confirmed;
}
</script> 