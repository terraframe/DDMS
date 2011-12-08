/**
 * Custom form object generator for the hard-coded SurveyForm domain.
 */
(function(){

Mojo.Meta.newClass('dss.vector.solutions.FormSearch', {
  IsAbstract : true,
  Instance : {
    initialize : function(id, errorContainerId){
      var dF = Mojo.Util.bind(this, this._displayFunction);
      var sF = Mojo.Util.bind(this, this._searchFunction);
      var sEH = Mojo.Util.bind(this, this._selectEventHandler);

      this._element = document.getElementById(id);
      this._errorSpan = document.getElementById(errorContainerId);
      this._genericSearch = new MDSS.GenericSearch(id, null, dF, dF, dF, sF, sEH);
      this._genericSearch.addEventListener(MDSS.EnterResults, this._enterResultsHandler, null, this);
      this._genericSearch.addEventListener(MDSS.ExitResults, this._exitResultsHandler, null, this);
      
      YAHOO.util.Event.on(this._element, 'blur', this._blurEventHandler, null, this);
      this._withinResults = false; // toggled when the user is entering, existing the results panel
    },
    _enterResultsHandler : function(e){
      this._withinResults = true;
      console.log(e);
    },
    _exitResultsHandler : function(e){
      this._withinResults = false;
      console.log(e);
    },
    showErrorMessage : function(message)
    {
      if(this._errorSpan != null)
      {  
        this._errorSpan.innerHTML = message
        this._errorSpan.style.display = 'inline-block';
        
        var contentNode = this._errorSpan.parentNode.children.item(0);
        contentNode.style.cssFloat = 'left';
      }
      else
      {
        new MDSS.ErrorModal(message);
      }    	
    },
    clearErrors : function()
    {
      if(this._errorSpan != null)
      {  
        this._errorSpan.innerHTML = "";
        this._errorSpan.style.display = 'none';
        
        var contentNode = this._errorSpan.parentNode.children.item(0);
        contentNode.style.cssFloat = 'none';
      }      
    },    
    _searchFunction : function(request, value)
    {
      IsAbstract : true
    },        
    _validate : function(request, value)
    {
      IsAbstract : true
    },
    _displayFunction : function(valueObj)
    {
      return valueObj.getValue('oid');
    },
    _selectEventHandler : function(selected)
    {
      this._element.value = selected.id;
      this.clearErrors();
    },
    _blurEventHandler : function(e)
    {
      // don't perform validation if we're selecting from the search results
      if(this._withinResults){
        return;
      }
    
      var request = new MDSS.Request({
      that : this,
        onSend: function(){},
        onComplete: function(){},      
        onSuccess : function()
        {
          this.that.clearErrors();
        },
        onFailure : function(e)
        {
          this.that.showErrorMessage(e.getLocalizedMessage());
        }
      });

      this._validate(request, this._element.value);
    }
  }
});

Mojo.Meta.newClass('dss.vector.solutions.FormHouseholdSearch', {
  Extends : Mojo.$.dss.vector.solutions.FormSearch,
  Instance : {
    initialize : function(id, errorContainerId){
      this.$initialize(id, errorContainerId);
    },
    _searchFunction : function(request, value)
    {
      Mojo.$.dss.vector.solutions.form.business.FormHousehold.getHouseIds(request, value);
    },
    _validate : function(request, value)
    {
      Mojo.$.dss.vector.solutions.form.business.FormHousehold.validateHouseholdId(request, value);
    }
  }
});

Mojo.Meta.newClass('dss.vector.solutions.FormBedNetSearch', {
  Extends : Mojo.$.dss.vector.solutions.FormSearch,
  Instance : {
    initialize : function(id, errorContainerId){
      this.$initialize(id, errorContainerId);
    },
    _searchFunction : function(request, value)
    {
      Mojo.$.dss.vector.solutions.form.business.FormBedNet.getNetIds(request, value);
    },
    _validate : function(request, value)
    {
      Mojo.$.dss.vector.solutions.form.business.FormBedNet.validateNetId(request, value);
    }    
  }
});

Mojo.Meta.newClass('dss.vector.solutions.FormPersonSearch', {
  Extends : Mojo.$.dss.vector.solutions.FormSearch,
  Instance : {
    initialize : function(id, errorContainerId){
      this.$initialize(id, errorContainerId);
    },
    _searchFunction : function(request, value)
    {
      Mojo.$.dss.vector.solutions.form.business.FormPerson.getPersonIds(request, value);
    },
    _validate : function(request, value)
    {
      Mojo.$.dss.vector.solutions.form.business.FormPerson.validatePersonId(request, value);
    }    
  }
});

Mojo.Meta.newClass('dss.vector.solutions.SurveyFormGenerator', {
  Instance : {
    initialize : function(params){
      this.$initialize();
      
      this._survey = new dss.vector.solutions.FormObjectGenerator('survey', params.surveyFormId, params.surveyClassType);
      this._survey.addEventListener(dss.vector.solutions.RenderViewEvent, Mojo.Util.bind(this, this.surveyRenderViewEventHandler));
      this._survey.addEventListener(dss.vector.solutions.ViewAllEvent, Mojo.Util.bind(this, this.surveyRenderFormEvent));
      this._survey.addEventListener(dss.vector.solutions.RenderFormEvent, Mojo.Util.bind(this, this.surveyRenderFormEvent));
      
      this._household = new dss.vector.solutions.FormObjectGenerator('household', params.householdFormId, params.householdClassType);
      this._household.addEventListener(dss.vector.solutions.NewInstanceEvent, Mojo.Util.bind(this, this.householdNewInstanceEventHandler));  
      this._household.addEventListener(dss.vector.solutions.RenderViewEvent, Mojo.Util.bind(this, this.householdRenderViewEventHandler));  
      this._household.addEventListener(dss.vector.solutions.ViewParentEvent, Mojo.Util.bind(this, this.householdViewParentEventHandler));
      this._household.addEventListener(dss.vector.solutions.CreateEvent, Mojo.Util.bind(this, this.householdApplyEventHandler));  
      this._household.addEventListener(dss.vector.solutions.UpdateEvent, Mojo.Util.bind(this, this.householdApplyEventHandler));  
      this._household.addEventListener(dss.vector.solutions.BeforeQueryEvent, Mojo.Util.bind(this, this.householdBeforeQueryEventHandler));  
      this._household.addEventListener(dss.vector.solutions.DeleteEvent, Mojo.Util.bind(this, this.householdViewParentEventHandler));
      this._household.addEventListener(dss.vector.solutions.CancelEvent, Mojo.Util.bind(this, this.householdCancelEventHandler));
      this._household.addEventListener(dss.vector.solutions.RenderEditFieldEvent, Mojo.Util.bind(this, this.householdRenderEditFieldEventHandler));    
      this._household.addEventListener(dss.vector.solutions.ViewAllEvent, Mojo.Util.bind(this, this.householdRenderFormEventHandler));
      this._household.addEventListener(dss.vector.solutions.RenderFormEvent, Mojo.Util.bind(this, this.householdRenderFormEventHandler));  
      this._household.addEventListener(dss.vector.solutions.PostRenderEditFieldEvent, Mojo.Util.bind(this, this.householdPostRenderEditFieldEventHandler));  
      
      this._bedNet = new dss.vector.solutions.FormObjectGenerator('bedNet', params.bedNetFormId, params.bedNetClassType);
      this._bedNet.addEventListener(dss.vector.solutions.NewInstanceEvent, Mojo.Util.bind(this, this.bedNetRenderFormEventHandler));  
      this._bedNet.addEventListener(dss.vector.solutions.RenderViewEvent, Mojo.Util.bind(this, this.bedNetRenderFormEventHandler));  
      this._bedNet.addEventListener(dss.vector.solutions.ViewParentEvent, Mojo.Util.bind(this, this.bedNetViewParentEventHandler));
      this._bedNet.addEventListener(dss.vector.solutions.CreateEvent, Mojo.Util.bind(this, this.bedNetApplyEventHandler));  
      this._bedNet.addEventListener(dss.vector.solutions.UpdateEvent, Mojo.Util.bind(this, this.bedNetApplyEventHandler));  
      this._bedNet.addEventListener(dss.vector.solutions.BeforeQueryEvent, Mojo.Util.bind(this, this.bedNetBeforeQueryEventHandler));  
      this._bedNet.addEventListener(dss.vector.solutions.DeleteEvent, Mojo.Util.bind(this, this.bedNetViewParentEventHandler));
      this._bedNet.addEventListener(dss.vector.solutions.CancelEvent, Mojo.Util.bind(this, this.bedNetCancelEventHandler));
      this._bedNet.addEventListener(dss.vector.solutions.RenderEditFieldEvent, Mojo.Util.bind(this, this.bedNetRenderEditFieldEventHandler));
      this._bedNet.addEventListener(dss.vector.solutions.RenderViewFieldEvent, Mojo.Util.bind(this, this.bedNetRenderViewFieldEventHandler));
      this._bedNet.addEventListener(dss.vector.solutions.PostRenderEditFieldEvent, Mojo.Util.bind(this, this.bedNetPostRenderEditFieldEventHandler));  
      
      this._person = new dss.vector.solutions.FormObjectGenerator('person', params.personFormId, params.personClassType);
      this._person.addEventListener(dss.vector.solutions.NewInstanceEvent, Mojo.Util.bind(this, this.personRenderViewEventHandler));  
      this._person.addEventListener(dss.vector.solutions.RenderViewEvent, Mojo.Util.bind(this, this.personRenderViewEventHandler));  
      this._person.addEventListener(dss.vector.solutions.ViewParentEvent, Mojo.Util.bind(this, this.personViewParentEventHandler));
      this._person.addEventListener(dss.vector.solutions.CreateEvent, Mojo.Util.bind(this, this.personApplyEventHandler));  
      this._person.addEventListener(dss.vector.solutions.UpdateEvent, Mojo.Util.bind(this, this.personApplyEventHandler));  
      this._person.addEventListener(dss.vector.solutions.BeforeQueryEvent, Mojo.Util.bind(this, this.personBeforeQueryEventHandler));  
      this._person.addEventListener(dss.vector.solutions.DeleteEvent, Mojo.Util.bind(this, this.personViewParentEventHandler));
      this._person.addEventListener(dss.vector.solutions.CancelEvent, Mojo.Util.bind(this, this.personCancelEventHandler));
      this._person.addEventListener(dss.vector.solutions.RenderEditFieldEvent, Mojo.Util.bind(this, this.personRenderEditFieldEventHandler));           
      this._person.addEventListener(dss.vector.solutions.RenderViewFieldEvent, Mojo.Util.bind(this, this.personRenderViewFieldEventHandler));
      this._person.addEventListener(dss.vector.solutions.PostRenderEditFieldEvent, Mojo.Util.bind(this, this.personPostRenderEditFieldEventHandler));       
    },
    render : function()
    {
      this._bedNet.hide();
      this._person.hide();
      this._household.hide();      
        
      this._survey.render();    
    },    
    surveyRenderViewEventHandler : function(e)
    {
       this._household.viewAllInstance();
    },
    surveyRenderFormEvent : function(e)
    {
      this._household.hide();
    },
    householdNewInstanceEventHandler : function(e)
    {
      this._survey.hide();
    },
    householdRenderEditFieldEventHandler : function(e)
    {
      if(e.getField().getFieldName() == 'survey')
      {
        e.preventDefault();
      }      
    },
    householdPostRenderEditFieldEventHandler : function(e)
    {
      var fieldComponent = e.getFieldComponent();
      var field = fieldComponent.getField();
      
      if(field.getFieldName() == 'householdId')
      {
        var inputId = fieldComponent.getInputId();
        var errorContainerId = fieldComponent.getErrorContainerId();
      
        new dss.vector.solutions.FormHouseholdSearch(inputId, errorContainerId);
      }
    },
    householdViewParentEventHandler : function(e)
    {
      this._survey.renderView()
    },
    householdRenderFormEventHandler : function(e)
    {
      this._bedNet.hide();
      this._person.hide();
    },
    householdRenderViewEventHandler : function(e)
    {
      this._survey.hide();
      this._bedNet.viewAllInstance();
      this._person.viewAllInstance();    
    },
    householdApplyEventHandler : function(e)
    {
      var form = e.getFormObject();
      var fieldMap = form.getFieldMap();
      var field = fieldMap.get('survey');

      field.setValue(this._survey.getFormObject().getDataId());
    },
    householdBeforeQueryEventHandler : function(e)
    {
      if(this._survey.getFormObject() != null )
      {
        var surveyId = this._survey.getFormObject().getDataId();
        var query = e.getQuery();

        // Reset the existing conditions
        query.clearConditions();
      
        query.addCondition('survey', 'EQ', surveyId);
      }
    },
    householdCancelEventHandler : function(e)
    {
      if(e.getFormObject().isNewInstance())
      {
        e.preventDefault();
        this._survey.renderView();
      }
    },
    bedNetApplyEventHandler : function(e)
    {
      var form = e.getFormObject();
      var fieldMap = form.getFieldMap();
      var householdField = fieldMap.get('household');
      var surveyField = fieldMap.get('survey');

      householdField.setValue(this._household.getFormObject().getDataId());
      surveyField.setValue(this._survey.getFormObject().getDataId());
    },
    bedNetBeforeQueryEventHandler : function(e)
    {
      if(this._household.getFormObject() != null)
      {
        var householdId = this._household.getFormObject().getDataId();
        var query = e.getQuery();

        // Reset the existing conditions
        query.clearConditions();
        
        query.addCondition('household', 'EQ', householdId);
      }
    },
    bedNetCancelEventHandler : function(e)
    {
      if(e.getFormObject().isNewInstance())
      {
        e.preventDefault();
        this._household.renderView();
      }
    },
    bedNetRenderFormEventHandler : function(e)
    {
      this._household.hide();
      this._person.hide();
    },
    bedNetViewParentEventHandler : function(e)
    {
      this._household.renderView();
    },
    bedNetRenderEditFieldEventHandler : function(e)
    {
      var fieldName = e.getField().getFieldName();
        
      if(fieldName == 'household' || fieldName == 'survey')
      {
        e.preventDefault();
      }
    },
    bedNetPostRenderEditFieldEventHandler : function(e)
    {
      var fieldComponent = e.getFieldComponent();
      var field = fieldComponent.getField();
      
      if(field.getFieldName() == 'netId')
      {
        // add generic ajax search
        new dss.vector.solutions.FormBedNetSearch(fieldComponent.getInputId(), fieldComponent.getErrorContainerId());
      }
    },    
    bedNetRenderViewFieldEventHandler : function(e)
    {
      var fieldName = e.getField().getFieldName();
    
      if(fieldName == 'survey')
      {
        e.preventDefault();
      }
    },
    personApplyEventHandler : function(e)
    {
      var form = e.getFormObject();
      var fieldMap = form.getFieldMap();
      var householdField = fieldMap.get('household');
      var surveyField = fieldMap.get('survey');

      householdField.setValue(this._household.getFormObject().getDataId());
      surveyField.setValue(this._survey.getFormObject().getDataId());
    },
    personBeforeQueryEventHandler : function(e)
    {
      if(this._household.getFormObject() != null)
      {
        var householdId = this._household.getFormObject().getDataId();
        var query = e.getQuery();

        // Reset the existing conditions
        query.clearConditions();
          
        query.addCondition('household', 'EQ', householdId);
      }
    },
    personCancelEventHandler : function(e)
    {
      if(e.getFormObject().isNewInstance()){
        e.preventDefault();
        this._household.renderView();
      }
    },
    personRenderEditFieldEventHandler : function(e)
    {
      var fieldName = e.getField().getFieldName();
      
      if(fieldName == 'household' || fieldName == 'survey')
      {
        e.preventDefault();
      }
      else if(fieldName == 'net')
      {
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
              
            for(var i = 0; i < bednets.length; i++)
            {
              var label = bednets[i].getOid();
              var value = bednets[i].getId();

              if(value == fieldValue) 
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

        var dataId = this._household.getFormObject().getDataId();
        
        if(dataId != null)
        {
          dss.vector.solutions.form.business.FormHousehold.getBedNets(request, dataId);
        }
      }
    },
    personPostRenderEditFieldEventHandler : function(e)
    {
      var fieldComponent = e.getFieldComponent();
      var field = fieldComponent.getField();
      
      if(field.getFieldName() == 'personId')
      {
        // add generic ajax search
        new dss.vector.solutions.FormPersonSearch(fieldComponent.getInputId(), fieldComponent.getErrorContainerId());
      }
    },    
    personRenderViewFieldEventHandler : function(e)
    {
      var fieldName = e.getField().getFieldName();
    
      if(fieldName == 'survey')
      {
        e.preventDefault();
      }
    },    
    personRenderViewEventHandler : function(e)
    { 
      this._household.hide();
      this._bedNet.hide();
    },
    personViewParentEventHandler : function(e)
    {
      this._household.renderView();
    }    
  }
});
})();