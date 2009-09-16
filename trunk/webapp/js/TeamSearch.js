// Author: Justin Smethie
Mojo.Meta.newClass('MDSS.TeamSearch', {
  Instance: {

    initialize: function(geoId, teamSelect, operatorSelect, leaderSelect) {
      // Constructor code  
      this.geoId = geoId;
      this.teamSelect = teamSelect;
      this.operatorSelect = operatorSelect;
      this.leaderSelect = leaderSelect;
      this._pollingId = null;     
      
      YAHOO.util.Event.addListener(this.teamSelect, "click", this.populateTeamMembers, this, true); 
    },
  
    // Public getter functions
    getGeoId : function () {
      return this.geoId;
    },
    
    getTeamSelect : function () {
      return this.teamSelect;
    },

    getOperatorSelect : function () {
      return this.operatorSelect;
    },
    
    getLeaderSelect : function () {
      return this.leaderSelect;
    },
    
    getTeamId : function () {
      return this.teamId;
    },
    
    // Clear a select list of all options and disables it
    clearSelect : function(select) {
      if(select) {
    	Selectbox.removeAllOptions(select);                
        select.disabled=true;   
      }      
    },
    
    clearTeamMembers : function() {
      this.clearSelect(this.getOperatorSelect());
      this.clearSelect(this.getLeaderSelect());    	
    },
    
    clearAll : function() {
      this.clearSelect(this.getTeamSelect());    	
      this.clearSelect(this.getOperatorSelect());
      this.clearSelect(this.getLeaderSelect());    	
    },
        
    // Private methods
    _populateOperatorList : function (select, operators) {
      if(select) {
        // Remove all of the current options in the select list
        Selectbox.removeAllOptions(select);

        // Add the new options retrieved from the AJAX call
        for(var i=0; i< operators.length; i++) {
          var label = operators[i].getOperatorId() + " - " + operators[i].getLastName() + ", " + operators[i].getFirstName();

          Selectbox.addOption(select, label, operators[i].getActorId(), false);
        }          

        // Enable the select list
        select.disabled=false;
      }
    },
    
    populateTeamMembers : function (){
      this.clearTeamMembers();
    	
      if(this.getTeamSelect().value != '')
      {
        var request = new MDSS.Request({
          obj : this,
          onSend: function(){},
          onComplete: function(){},
          onFailure : function(){
        	this.obj.clearTeamMembers();
          },
          onProblemExceptionDTO : function(){
        	this.obj.clearTeamMembers();
          },          
          onSuccess : function(operators){
        	this.obj._populateOperatorList(this.obj.getOperatorSelect(), operators);
        	this.obj._populateOperatorList(this.obj.getLeaderSelect(), operators);
          }
        });

        Mojo.$.dss.vector.solutions.irs.SprayTeam.getTeamMemberViews(request, this.getTeamSelect().value);
      }  
    },
    
    populateSprayTeams : function(){ 
      this.clearAll();
    	
      if(this.getGeoId().value != '')
      {
    	var request = new MDSS.Request({
          obj : this,    		
          onSend: function(){},
          onComplete: function(){},
          onFailure : function(){
        	this.obj.clearAll();
          },
          onProblemExceptionDTO : function(){
        	this.obj.clearAll();
          },          
          onSuccess : function(teams){
            // Remove all of the current options in the select list
            Selectbox.removeAllOptions(this.obj.getTeamSelect());

            Selectbox.addOption(this.obj.getTeamSelect(), 'Select Team', '', false);
              
            // Add the new options retrieved from the AJAX call
            for(var i=0; i< teams.length; i++) {
              Selectbox.addOption(this.obj.getTeamSelect(), teams[i].getTeamId(), teams[i].getId(), false);
            }          

            // Enable the select list
            this.obj.getTeamSelect().disabled = false;
          }
        });

        Mojo.$.dss.vector.solutions.irs.SprayTeam.findByLocation(request, this.getGeoId().value);
      }
    }      
  }   
});

Mojo.Meta.newClass('MDSS.ElementCondition', {
  IsAbstract : true,

  Instance: {
    initialize: function(option, condition) {
	  this.option = option;
	  this.condition = condition;
    },

    getOption : function () {
    	return this.option;
    },

    getCondition : function () {
    	return this.condition;
    },	

    evaluate : function () { //AbstractMethod
    	throw new Error('Unsupported operationon an abstract class.');    	
    } 
  }
});

// Extends ElementCondition
Mojo.Meta.newClass('MDSS.RadioElementCondition', {
  Extends : MDSS.ElementCondition,
  Instance: {
    initialize: function(option, condition) {
      this.$initialize(option, condition);
    },

    evaluate : function () {
    	return (this.getOption().checked == this.getCondition());
    }    
  }
});

// Extends ElementCondition
Mojo.Meta.newClass('MDSS.SelectElementCondition', {
  Extends : MDSS.ElementCondition,
  Instance: {
    initialize: function(option, condition) {
	  this.$initialize(option, condition);
    },
  
    evaluate : function () {
	  return (this.getOption().selected == this.getCondition());
    }    
  }
});

Mojo.Meta.newClass('MDSS.ElementHandler', {
  Instance: {
    initialize: function(condition, elements) {
      // Constructor code
	  this.condition = condition;
      this.elements = elements;       // When option set to some condition these elements are set to element.display = '', otherwise it is set to element.display = 'none'
          
      // Finally we need to set the initial state of the elements
      this.optionHandler();
    },
    
    // Public getter functions    
    getElements : function () {
      return this.elements;
    },
      
    getCondition : function () {
      return this.condition;
    },
                      
    optionHandler : function () {
      if(this.getCondition())
      {
        if(this.getCondition().evaluate())
        {
          this.constructor.toggleElements(this.getElements(), this.constructor.showElement);
        }
        else
        {
          this.constructor.toggleElements(this.getElements(), this.constructor.hideElement);
        }
      }
    }    
  },
  
  Static:
  {
	hideElement : function (obj) {
      // When hiding an element clear out the existing value so that when the form
      // submits occurs hidden elements do not have values assigned to them
      if(obj.value) {
    	  obj.value = '';
      }
      
      obj.style.display = "none";
    },
      
    showElement : function (obj) {
      if(obj.tagName && obj.tagName == 'div') {
    	  obj.style.display = "block";
      }
      
      obj.style.display = "inline";
    },
    
    toggleElements : function (list, func) {
      if(list && func)
      {
        for(i in list) {
          func(list[i]);
        }
      }
    },
    
    setupBooleanHandler : function (conditionElement, trigger, elements) {
    	conditionElement = MDSS.ElementHandler.getElement(conditionElement);
    	
    	if(Mojo.Util.isString(elements)) {
    		elements = YAHOO.util.Selector.query('.' + elements);
    	}
    		
    	var handler = new MDSS.ElementHandler(new MDSS.RadioElementCondition(conditionElement, true), elements);

    	MDSS.ElementHandler.addEventListener(conditionElement, handler);
    	MDSS.ElementHandler.addEventListener(trigger, handler);
    	
    	return handler;
    },
    
    setupSelectHandler : function (conditionElement, trigger, elements) {
    	conditionElement = MDSS.ElementHandler.getElement(conditionElement);
    	    	
    	if(Mojo.Util.isString(elements)) {
    		elements = YAHOO.util.Selector.query('.' + elements);
    	}
    	
    	var handler = new MDSS.ElementHandler(new MDSS.SelectElementCondition(conditionElement, true), elements);
    	
    	MDSS.ElementHandler.addEventListener(conditionElement, handler);
    	MDSS.ElementHandler.addEventListener(trigger, handler);
    	
    	return handler;
    },
    
    addEventListener : function (obj, handler) {
    	if(Mojo.Util.isArray(obj)) {
    		for(key in obj) {
    			var element = obj[key];
    			element = MDSS.ElementHandler.getElement(element);
    			
    			YAHOO.util.Event.addListener(element, "change", handler.optionHandler, handler, true);    			
    		}
    	}
    	else {    		
    		var element = MDSS.ElementHandler.getElement(obj);
    		YAHOO.util.Event.addListener(element, "change", handler.optionHandler, handler, true);
    	}    	
    },
    
    getElement : function(obj) {
    	if(Mojo.Util.isString(obj)) {
    		return document.getElementById(obj);
    	}
    	
    	return obj;
    }
  }
});

