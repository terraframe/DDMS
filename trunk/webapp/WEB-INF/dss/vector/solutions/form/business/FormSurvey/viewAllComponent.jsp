<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>
<%@page import="dss.vector.solutions.query.SavedSearchDTO"%>
<%@page import="dss.vector.solutions.query.AttributeGeoHierarchyDTO"%>
<%@page import="dss.vector.solutions.form.FormObjectController"%>
<%@page import="com.runwaysdk.system.metadata.MdWebInteger"%>
<%@page import="com.runwaysdk.system.metadata.MdWebLongDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebIntegerDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebDecimalDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebDoubleDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebFloatDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebDateDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebTextDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebBooleanDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebCharacterDTO"%>
<%@page import="dss.vector.solutions.form.MdFormAdminController"%>
<%@page import="com.runwaysdk.system.metadata.MdWebBreakDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebCommentDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebHeaderDTO"%>


<%@page import="com.runwaysdk.system.metadata.MdWebReferenceDTO"%><c:set var="page_title" value="Form_Generator"  scope="request"/>

<jwr:script src="/bundles/yui3Bundle.js" useRandomParam="false"/>

<script type="text/javascript">


<%
ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

String[] types = new String[]{
request.getAttribute("surveyClassType").toString(),
request.getAttribute("householdClassType").toString(),
request.getAttribute("bedNetClassType").toString(),
request.getAttribute("personClassType").toString(),
FormObjectController.CLASS,
    
// WebNumber (excluding float)
MdWebIntegerDTO.CLASS,
MdWebLongDTO.CLASS,
MdWebDecimalDTO.CLASS,
MdWebDoubleDTO.CLASS,
MdWebFloatDTO.CLASS,

// WebMoment (excluding DateTime, Time)
MdWebDateDTO.CLASS,
// Character, Text, Boolean
MdWebCharacterDTO.CLASS,
MdWebTextDTO.CLASS,
MdWebBooleanDTO.CLASS,
MdWebReferenceDTO.CLASS,

MdWebBreakDTO.CLASS,
MdWebHeaderDTO.CLASS,
MdWebCommentDTO.CLASS
};

String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
out.print(js);
%>

YAHOO.util.Event.onDOMReady(function(){
  var UI = Mojo.Meta.alias(Mojo.UI_PACKAGE+'*');
  UI.Manager.setFactory("YUI3");
  
  var prePersonApply = function(e) {
    var form = e.getFormObject();
    var fieldMap = form.getFieldMap();
    var field = fieldMap.get('household');

    field.setValue(household.getFormObject().getDataId());
  };

  var beforePersonQuery = function(e) {
    if(household.getFormObject() != null )
    {
      var householdId = household.getFormObject().getDataId();
      var query = e.getQuery();

      // Reset the existing conditions
      query.clearConditions();
      
      query.addCondition('household', 'EQ', householdId);
    }
  };

  var personCancel = function(e) {
    if(e.getFormObject().isNewInstance()){
      e.preventDefault();
      household.renderView();
    }
  };

  var personRenderViewField = function(e) {
    if(e.getField().getFieldName() == 'household') {
      e.preventDefault();
    }
  };  

  var personRenderEditField = function(e) {
    if(e.getField().getFieldName() == 'household') {
      e.preventDefault();
    }
    if(e.getField().getFieldName() == 'net') {
      e.getDDFragment().destroy();
      
      var factory = UI.Manager.getFactory();
            
      var div = factory.newElement('div');
      
      e.setDDFragment(div);      
                        
      var request = new MDSS.Request({
        value : e.getField().getValue(),
        factory : factory,
        div : div,
        onSuccess : function(bednets){
          var select = factory.newElement('select', {
           'name':e.getField().getFieldName(),
          });                 


          // Add the blank option
          var option = factory.newElement('option', {
              'value' : '',
          });
          option.setInnerHTML('');
          select.appendChild(option);                   
          

          // Add an option for every bed net that is in the household
          var fieldValue = e.getField().getValue();
          
          for(var i = 0; i < bednets.length; i++) {
            var label = bednets[i].getNetId();            
            var value = bednets[i].getId();

            if(label == fieldValue) 
            {
              var option = factory.newElement('option', {
                'label' : label,
                'value' : value,
                'selected' : 'selected'
              });
              option.setInnerHTML(label);
            
              select.appendChild(option);
            }
            else
            {
              var option = factory.newElement('option', {
               'label' : label,
               'value' : value,
              });
              option.setInnerHTML(label);
                
              select.appendChild(option);                   
            }
                
          }

          div.appendChild(select);
        }        
      });

      var dataId = household.getFormObject().getDataId()
      if(dataId != null)
      {
        dss.vector.solutions.form.business.FormHousehold.getBedNets(request, dataId);
      }
    }
  };  

  var person = new dss.vector.solutions.FormObjectGenerator('person', '${personFormId}', '${personClassType}');
  person.addEventListener(dss.vector.solutions.NewInstanceEvent, function(e){ household.hide(); bedNet.hide();});  
  person.addEventListener(dss.vector.solutions.RenderViewEvent, function(e){ household.hide(); bedNet.hide();});  
  person.addEventListener(dss.vector.solutions.ViewParentEvent, function(e){ household.renderView();});
  person.addEventListener(dss.vector.solutions.CreateEvent, prePersonApply);  
  person.addEventListener(dss.vector.solutions.UpdateEvent, prePersonApply);  
  person.addEventListener(dss.vector.solutions.BeforeQueryEvent, beforePersonQuery);  
  person.addEventListener(dss.vector.solutions.DeleteEvent, function(e){household.renderView();});
  person.addEventListener(dss.vector.solutions.CancelEvent, personCancel);
  person.addEventListener(dss.vector.solutions.RenderViewFieldEvent, personRenderViewField);  
  person.addEventListener(dss.vector.solutions.RenderEditFieldEvent, personRenderEditField);     
  person.hide();
  
  var preBedNetApply = function(e) {
    var form = e.getFormObject();
    var fieldMap = form.getFieldMap();
    var field = fieldMap.get('household');

    field.setValue(household.getFormObject().getDataId());
  };

  var beforeBedNetQuery = function(e) {
    if(household.getFormObject() != null )
    {
      var householdId = household.getFormObject().getDataId();
      var query = e.getQuery();

      // Reset the existing conditions
      query.clearConditions();
      
      query.addCondition('household', 'EQ', householdId);
    }
  };

  var bedNetCancel = function(e) {
    if(e.getFormObject().isNewInstance()){
      e.preventDefault();
      household.renderView();
    }
  };

  var bedNet = new dss.vector.solutions.FormObjectGenerator('bedNet', '${bedNetFormId}', '${bedNetClassType}');
  bedNet.addEventListener(dss.vector.solutions.NewInstanceEvent, function(e){ household.hide(); person.hide();});  
  bedNet.addEventListener(dss.vector.solutions.RenderViewEvent, function(e){ household.hide(); person.hide();});  
  bedNet.addEventListener(dss.vector.solutions.ViewParentEvent, function(e){ household.renderView();});
  bedNet.addEventListener(dss.vector.solutions.CreateEvent, preBedNetApply);  
  bedNet.addEventListener(dss.vector.solutions.UpdateEvent, preBedNetApply);  
  bedNet.addEventListener(dss.vector.solutions.BeforeQueryEvent, beforeBedNetQuery);  
  bedNet.addEventListener(dss.vector.solutions.DeleteEvent, function(e){household.renderView();});
  bedNet.addEventListener(dss.vector.solutions.CancelEvent, bedNetCancel);
  bedNet.addEventListener(dss.vector.solutions.RenderViewFieldEvent, function(e){
      if(e.getField().getFieldName() == 'household') {
        e.preventDefault();
      }
    });  
  bedNet.addEventListener(dss.vector.solutions.RenderEditFieldEvent, function(e){
    if(e.getField().getFieldName() == 'household') {
      e.preventDefault();
    }
  });     
  bedNet.hide();

  var preHouseholdApply = function(e) {
    var form = e.getFormObject();
    var fieldMap = form.getFieldMap();
    var field = fieldMap.get('survey');

    field.setValue(survey.getFormObject().getDataId());
  };

  var beforeHouseholdQuery = function(e) {
    if(survey.getFormObject() != null )
    {
      var surveyId = survey.getFormObject().getDataId();
      var query = e.getQuery();

      // Reset the existing conditions
      query.clearConditions();
      
      query.addCondition('survey', 'EQ', surveyId);
    }
  };

  var householdCancel = function(e) {
    if(e.getFormObject().isNewInstance()){
      e.preventDefault();
      survey.renderView();
    }
  };

  var household = new dss.vector.solutions.FormObjectGenerator('household', '${householdFormId}', '${householdClassType}');
  household.addEventListener(dss.vector.solutions.NewInstanceEvent, function(e){ survey.hide();});  
  household.addEventListener(dss.vector.solutions.RenderViewEvent, function(e){
    survey.hide();
    bedNet.viewAllInstance();
    person.viewAllInstance();
  });  
  household.addEventListener(dss.vector.solutions.ViewParentEvent, function(e){ survey.renderView();});
  household.addEventListener(dss.vector.solutions.CreateEvent, preHouseholdApply);  
  household.addEventListener(dss.vector.solutions.UpdateEvent, preHouseholdApply);  
  household.addEventListener(dss.vector.solutions.BeforeQueryEvent, beforeHouseholdQuery);  
  household.addEventListener(dss.vector.solutions.DeleteEvent, function(e){survey.renderView();});
  household.addEventListener(dss.vector.solutions.CancelEvent, householdCancel);
  household.addEventListener(dss.vector.solutions.RenderViewFieldEvent, function(e){
    if(e.getField().getFieldName() == 'survey') {
      e.preventDefault();
    }
  });  
  household.addEventListener(dss.vector.solutions.RenderEditFieldEvent, function(e){
    if(e.getField().getFieldName() == 'survey') {
        e.preventDefault();
    }
  });    
  household.addEventListener(dss.vector.solutions.ViewAllEvent, function(e){ 
    bedNet.hide();
    person.hide();
  });
  household.addEventListener(dss.vector.solutions.RenderFormEvent, function(e){
    bedNet.hide();
    person.hide();
  });  
  household.hide();

  var survey = new dss.vector.solutions.FormObjectGenerator('survey', '${surveyFormId}', '${surveyClassType}');
  survey.addEventListener(dss.vector.solutions.RenderViewEvent, function(e){ household.viewAllInstance();});
  survey.addEventListener(dss.vector.solutions.ViewAllEvent, function(e){ household.hide();});
  survey.addEventListener(dss.vector.solutions.RenderFormEvent, function(e){ household.hide();});
  survey.render();
});
</script>
<div class="generatorContent" id="generatorContent">
  <a href="#" id="surveyNewInstanceCommand">
    <mdss:localize key="New_Form_Survey" />
  </a>
  <br id="surveyOptionBreak" />
  <a href="#" id="surveyViewAllCommand">
    <mdss:localize key="View_All_Form_Surveys" />
  </a>
  <div id="surveyTableContainer" class="yui3-skin-sam">
  </div>
  <div id="surveyFormContainer">
  </div>
  
  <a href="#" id="householdNewInstanceCommand">
    <mdss:localize key="New_Form_Household" />
  </a>
  <br id="householdParentBreak" />
  <a href="#" id="householdViewParentCommand">
    <mdss:localize key="View_Form_Survey" />
  </a>
  <div id="householdTableContainer" class="yui3-skin-sam">
  </div>
  <div id="householdFormContainer">
  </div>
  
  <a href="#" id="bedNetNewInstanceCommand">
    <mdss:localize key="New_Form_Bed_Net" />
  </a>
  <br id="bedNetParentBreak" />
  <a href="#" id="bedNetViewParentCommand">
    <mdss:localize key="View_Form_Household" />
  </a>
  <div id="bedNetTableContainer" class="yui3-skin-sam">
  </div>
  <div id="bedNetFormContainer">
  </div>
  <a href="#" id="personNewInstanceCommand">
    <mdss:localize key="New_Form_Person" />
  </a>
  <br id="personParentBreak" />
  <a href="#" id="personViewParentCommand">
    <mdss:localize key="View_Form_Household" />
  </a>
  <div id="personTableContainer" class="yui3-skin-sam">
  </div>
  <div id="personFormContainer">
  </div>
  
  
</div>