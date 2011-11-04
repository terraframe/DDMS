/**
 * Custom form object generator for the hard-coded SurveyForm domain.
 */
(function(){

Mojo.Meta.newClass('dss.vector.solutions.SurveyFormGenerator', {
  Instance : {
    initialize : function(params){
      this.$initialize();
      

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

  var personRenderEditField = function(e) {
    if(e.getField().getFieldName() == 'household') {
      e.preventDefault();
    }
    if(e.getField().getFieldName() == 'net') {
      
      var UI = Mojo.Meta.alias(Mojo.UI_PACKAGE+'*');
      UI.Manager.setFactory("YUI3");
      var factory = UI.Manager.getFactory();
            
      var div = factory.newElement('div');

          var select = factory.newElement('select', {
           'name':e.getField().getFieldName(),
          });  
      div.appendChild(select);      
      e.getFormComponent().setContentNode(div);      
                        
      var request = new MDSS.Request({
        value : e.getField().getValue(),
        factory : factory,
        onSuccess : function(bednets){
               


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
        }        
      });

      var dataId = household.getFormObject().getDataId()
      if(dataId != null)
      {
        dss.vector.solutions.form.business.FormHousehold.getBedNets(request, dataId);
      }
    }
  };  

  var person = new dss.vector.solutions.FormObjectGenerator('person', params.personFormId, params.personClassType);
  person.addEventListener(dss.vector.solutions.NewInstanceEvent, function(e){ household.hide(); bedNet.hide();});  
  person.addEventListener(dss.vector.solutions.RenderViewEvent, function(e){ household.hide(); bedNet.hide();});  
  person.addEventListener(dss.vector.solutions.ViewParentEvent, function(e){ household.renderView();});
  person.addEventListener(dss.vector.solutions.CreateEvent, prePersonApply);  
  person.addEventListener(dss.vector.solutions.UpdateEvent, prePersonApply);  
  person.addEventListener(dss.vector.solutions.BeforeQueryEvent, beforePersonQuery);  
  person.addEventListener(dss.vector.solutions.DeleteEvent, function(e){household.renderView();});
  person.addEventListener(dss.vector.solutions.CancelEvent, personCancel);
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

  var bedNet = new dss.vector.solutions.FormObjectGenerator('bedNet', params.bedNetFormId, params.bedNetClassType);
  bedNet.addEventListener(dss.vector.solutions.NewInstanceEvent, function(e){ household.hide(); person.hide();});  
  bedNet.addEventListener(dss.vector.solutions.RenderViewEvent, function(e){ household.hide(); person.hide();});  
  bedNet.addEventListener(dss.vector.solutions.ViewParentEvent, function(e){ household.renderView();});
  bedNet.addEventListener(dss.vector.solutions.CreateEvent, preBedNetApply);  
  bedNet.addEventListener(dss.vector.solutions.UpdateEvent, preBedNetApply);  
  bedNet.addEventListener(dss.vector.solutions.BeforeQueryEvent, beforeBedNetQuery);  
  bedNet.addEventListener(dss.vector.solutions.DeleteEvent, function(e){household.renderView();});
  bedNet.addEventListener(dss.vector.solutions.CancelEvent, bedNetCancel);
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

  var household = new dss.vector.solutions.FormObjectGenerator('household', params.householdFormId, params.householdClassType);
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

  var survey = new dss.vector.solutions.FormObjectGenerator('survey', params.surveyFormId, params.surveyClassType);
  survey.addEventListener(dss.vector.solutions.RenderViewEvent, function(e){ household.viewAllInstance();});
  survey.addEventListener(dss.vector.solutions.ViewAllEvent, function(e){ household.hide();});
  survey.addEventListener(dss.vector.solutions.RenderFormEvent, function(e){ household.hide();});
  survey.render();



    }

  }
});






})();