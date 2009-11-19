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
      if(this.getOption() && this.getCondition()) {
        return (this.getOption().checked == this.getCondition());
      }
      return true;
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
      if(this.getOption() && this.getCondition()) {
        return (this.getOption().selected == this.getCondition());
      }
      
      return true;
    }    
  }
});

Mojo.Meta.newClass('MDSS.AbstractHiddenElement', {
  IsAbstract : true,
  Instance : {
    getElements : function() {
      IsAbstract : true
    },
    
    getClearValue : function() {
      IsAbstract : true
    },
  
    updateValues : function() {
      IsAbstract : true
    },
  
    clearValues : function() {
      IsAbstract : true
    },
  
    resetValues : {
      IsAbstract : true
    },

    hideElement : function() {
      this.updateValues();
      
      var clear = this.getClearValue();
    
      if(clear === true) {
        this.clearValues();
      }

      var elements = this.getElements();
      
      for(var i = 0; i < elements.length; i++) {
        elements[i].style.display = "none";      
      }
    },
  
    showElement : function() {
      this.resetValues();
      
      var elements = this.getElements();
        
      for(var i = 0; i < elements.length; i++) {
        var element = elements[i];
        
        if(element.tagName && element.tagName == 'div') {
          element.style.display = "block";
        }
        
        element.style.display = "inline";      
      }      
    }
  }
});

Mojo.Meta.newClass('MDSS.HiddenRadioElement', {
  Extends : MDSS.AbstractHiddenElement,  
  Instance: {
    initialize: function(prop) {
      this._elements = YAHOO.util.Selector.query('.' + prop.element);      
      this._positiveElement = document.getElementById(prop.element + '.positive');
      this._negativeElement = document.getElementById(prop.element + '.negative');
      this._clearValue = Mojo.Util.isBoolean(prop.element) ? prop.clearValue : true;
    
      this.updateValues();
    },
    
    getElements : function() {
      return this._elements;
    },
    
    getClearValue : function() {
      return this._clearValue;
    },
    
    updateValues : function() {
      this._postivieChecked = this._positiveElement.checked;
      this._negativeChecked = this._negativeElement.checked;      
    },
    
    clearValues : function() {
      this._positiveElement.checked = false;
      this._negativeElement.checked = false;            
    },
    
    resetValues : function() {
      this._positiveElement.checked = this._postivieChecked;
      this._negativeElement.checked = this._negativeChecked;            
    }    
  }
});


Mojo.Meta.newClass('MDSS.HiddenSelectElement', {
  Extends : MDSS.AbstractHiddenElement,  
  Instance: {
    initialize: function(prop) {
      this._elements = YAHOO.util.Selector.query('.' + prop.element);      
      this._inputElement = document.getElementById(prop.element);
      this._clearValue = Mojo.Util.isBoolean(prop.element) ? prop.clearValue : true;
    
      this.updateValues();
    },
    
    getElements : function() {
      return this._elements;
    },

    getClearValue : function() {
      return this._clearValue;
    },
      
    updateValues : function() {
      this._selectedIndex = this._inputElement.selectedIndex;
    },
      
    clearValues : function() {
      //Add a blank option with a null value
      var blankOption = document.createElement('option');
      blankOption.text = '';
      blankOption.value = '';

      //Set the input element to the blank option
      this._inputElement.add(blankOption, null);
      this._inputElement.selectedIndex = this._inputElement.length - 1;
    },
      
    resetValues : function() {
      this._inputElement.selectedIndex = this._selectedIndex;
      
      this._inputElement.remove(this._inputElement.length - 1);      
    }    
  }
});



Mojo.Meta.newClass('MDSS.HiddenInputElement', {
  Extends : MDSS.AbstractHiddenElement,  
  Instance: {
    initialize: function(prop) {
      this._elements = YAHOO.util.Selector.query('.' + prop.element);      
      this._inputElement = document.getElementById(prop.element);
      this._clearValue = Mojo.Util.isBoolean(prop.element) ? prop.clearValue : true;      
      
      this.updateValues();
    },

    getElements : function() {
      return this._elements;
    },

    getClearValue : function() {
      return this._clearValue;
    },
    
    updateValues : function() {
      this._inputValue = this._inputElement.value;
    },
    
    clearValues : function() {
      this._inputElement.value = '';
    },
    
    resetValues : function() {
      this._inputElement.value = this._inputValue;
    }    
  },
  Static: {
    toArray : function(elements) {
      var array = new Array();
  
      for(var i = 0; i < elements.length; i++) {
        var element = new MDSS.HiddenInputElement({element:elements[i]});
        
        array.push(element);
      }
  
      return array;
    }
  }
});

Mojo.Meta.newClass('MDSS.HiddenMultiTermElement', {
  Extends : MDSS.AbstractHiddenElement,  
  Instance: {
    initialize: function(prop) {
      this._elements = YAHOO.util.Selector.query('.' + prop.element);      
      this._inputElement = document.getElementById(prop.element);
      this._clearValue = Mojo.Util.isBoolean(prop.element) ? prop.clearValue : true;      
      
      this.updateValues();
    },

    getElements : function() {
      return this._elements;
    },

    getClearValue : function() {
      return this._clearValue;
    },
    
    updateValues : function() {
      this._inputValue = this._inputElement.innerHTML;
    },
    
    clearValues : function() {
      this._inputElement.innerHTML = '';
    },
    
    resetValues : function() {
      this._inputElement.innerHTML = this._inputValue;
    }    
  }
});


Mojo.Meta.newClass('MDSS.ElementHandler', {
  Instance: {
    initialize: function(condition, elements) {
      // Constructor code
      this.condition = condition;
      
      // elements: An array of AbstractElementProperty
      this.elements = elements;       

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
          for(var i = 0; i < this.elements.length; i++) {
            this.elements[i].showElement();
          }
        }
        else
        {
          for(var i = 0; i < this.elements.length; i++) {
            this.elements[i].hideElement();
          }
        }
      }
    }    
  },
  
  Static:
  {    
    setupBooleanHandler : function (conditionElement, trigger, elements) {
      conditionElement = MDSS.ElementHandler.getElement(conditionElement);
      
      var condition = new MDSS.RadioElementCondition(conditionElement, true);
      var handler = new MDSS.ElementHandler(condition, elements);

      MDSS.ElementHandler.addEventListener(conditionElement, handler);
      MDSS.ElementHandler.addEventListener(trigger, handler);
      
      return handler;
    },
    
    setupSelectHandler : function (conditionElement, trigger, elements) {
      conditionElement = MDSS.ElementHandler.getElement(conditionElement);
            
      var condition = new MDSS.SelectElementCondition(conditionElement, true);
      var handler = new MDSS.ElementHandler(condition, elements);
      
      MDSS.ElementHandler.addEventListener(conditionElement, handler);
      MDSS.ElementHandler.addEventListener(trigger, handler);
      
      return handler;
    },
    
    addEventListener : function (obj, handler) {
      if(Mojo.Util.isArray(obj)) {
        for(var key in obj) {
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

Mojo.Meta.newClass('MDSS.GenericSearch', {
  Instance: {
    initialize: function(displayElement, concreteElement, listFunction, displayFunction, idFunction, searchFunction, selectEventHandler, prop) {
  
      // Constructor code
      this.displayElement = displayElement;          // DOM element where the search is inputed and the selected result is displayed
      this.concreteElement = concreteElement;        // DOM element where the id of the selected result is stored
      
      this.listFunction = listFunction;              // Function which accepts a valueObject and returns a formatted string for a single result 
      this.displayFunction = displayFunction;        // Function which accepts a valueObject and returns the value for 'displayElement'
      this.idFunction = idFunction;                  // Function which accepts a valueObject and returns the value for 'concreteElement' 
      this.selectEventHandler = selectEventHandler;  // Optional function which is called after an option has been selected

      this.searchFunction = searchFunction;          // AJAX function which calls a static method on the server
      
      this.parameters = null;
      
      this.panel = MDSS.GenericSearch.initializePanel(displayElement);  // Result panel
      
      // Disable the browser autocomplete function for the element we provide an auto-complete
      this.displayElement.setAttribute("autocomplete", "off");

      // Create the default properties object
      if(prop == null) {
        prop = {minLength:2};
      }
      
      this.minLength = (Mojo.Util.isNumber(prop.minLength * 1) ? prop.minLength * 1 : 2);
    },
    
    hide : function()
    {
      this.panel.hide();
    },
    
    getDisplayElement : function() {
      return this.displayElement;
    },
    
    getConcreteElement : function() {
      return this.concreteElement;
    },
    
    getPanel : function() {
      return this.panel;
    },

    getParameters : function() {
      return this.parameters;
    },
    
    addParameter : function(parameter) {
      this.parameters = parameter;
    },

    getDisplay : function(valueObject) {
      return this.displayFunction(valueObject);
    },
    
    getListDisplay : function(valueObject) {
      return this.listFunction(valueObject);
    },
    
    getId : function(valueObject) {
      return this.idFunction(valueObject);
    },
        
    selectHandler : function(selected) {
      if(selected) {
        MDSS.GenericSearch.setElementValue(this.getDisplayElement(), selected.label);
        MDSS.GenericSearch.setElementValue(this.getConcreteElement(), selected.id);
        
        if(Mojo.Util.isFunction(this.selectEventHandler)) {
          this.selectEventHandler(selected);
        }
      }
    },
    
    performSearch : function() {
      MDSS.GenericSearch.setElementValue(this.getConcreteElement(), '');
      
      var value = this.getDisplayElement().value;
        
      // must have at least 2 characters ready
      if(value.length < this.minLength)
      {
        return;
      }

      var request = MDSS.GenericSearch.createSearchRequest(this);

      if(this.getParameters()) {
      	if(Mojo.Util.isArray(this.getParameters()))
      	{
      		var args = [request,value];
      		args = args.concat(this.getParameters());
      		this.searchFunction.apply(this,args);
      	}
      	else      	
    		{
        		this.searchFunction(request, value, this.getParameters());
      	}
      }
      else {
        this.searchFunction(request, value);  
      }
    }
  },
  
  Static: {
    initializePanel : function(element) {
      var resultsDiv = document.createElement('div');
      resultsDiv.id = element.id + '_results';
      resultsDiv.className = "yui-panel-container show-scrollbars shadow";
    
      YAHOO.util.Dom.insertAfter(resultsDiv,element);

      var panel = new YAHOO.widget.Panel(resultsDiv, {
        width:'400px',
        height:'200px',
        zindex:15,
        draggable: false,
        close: true
      });    
      
      return panel;
    },
    
    createResultList : function (searchObject, outer, inner, ul) {

      //var header = document.createElement('div');
      //header.innerHTML = '<h3>'+MDSS.Localized.Search_Results+'</h3><hr />';
      //outer.appendChild(header);

      YAHOO.util.Dom.addClass(inner, 'entitySearchResults');
      outer.appendChild(inner);

      YAHOO.util.Dom.addClass(ul, 'selectableList')

      YAHOO.util.Event.on(ul, 'mouseover', function(e, obj){
        var li = e.target; 
        var ul = e.currentTarget;
      
        if(li.nodeName === 'SPAN') {
          li = li.parentNode;
        }

        if(li.nodeName !== 'LI') {
          return;
        }

        // clear all lis of their current class
        var lis = YAHOO.util.Selector.query('li.currentSelection', ul);

        for(var i=0; i<lis.length; i++)
        {
          YAHOO.util.Dom.removeClass(lis[i], 'currentSelection');
        }

        YAHOO.util.Dom.addClass(li, 'currentSelection');
      });

      YAHOO.util.Event.on(ul, 'click', function(e, obj){
        var li = e.target;
        var ul = e.currentTarget;
        
        if(li.nodeName === 'SPAN')
        {
          li = li.parentNode;
        }

        if(li.nodeName !== 'LI')
        {
          return;
        }

        this.getPanel().hide();
        this.selectHandler(li);

      }, searchObject, searchObject);
    },
    
    createSearchRequest : function(searchObject) {
        var request = new MDSS.Request({
          searchObject : searchObject,
          
          // don't paint a loading bar. It's too slow for this type of call
          onSend: function(){},
          onComplete: function(){},
          onSuccess: function(query) {
                var resultSet = query.getResultSet();
                
                var outer = document.createElement('div');
                var inner = document.createElement('div');
                var ul = document.createElement('ul');

                MDSS.GenericSearch.createResultList(this.searchObject, outer, inner, ul);
                
                var searchValue = this.searchObject.getDisplayElement().value;

                for(var i=0; i<resultSet.length; i++)
                {
                  var valueObj = resultSet[i];
                  var displayStr = this.searchObject.getListDisplay(valueObj);
                  var matched = displayStr.replace(new RegExp("(.*?)(" + searchValue + ")(.*?)", "gi"), "$1<span class='searchMatch'>$2</span>$3");

                  var li = document.createElement('li');              
                  li.id = this.searchObject.getId(valueObj);              
                  li.label = this.searchObject.getDisplay(valueObj);
                  li.innerHTML = matched;              
                  
                  ul.appendChild(li);
                }

                inner.appendChild(ul);

                var panel = this.searchObject.getPanel();
                
                panel.setBody(outer);
                panel.render();
                panel.show();
                panel.bringToTop();

                // refocus the input field
                this.searchObject.getDisplayElement().focus();
              }
            });
        
        return request;
    },
    
    setElementValue : function(element, value) {
      if(element) {
        if(value) {
          element.value = value;
        }
        else {
           element.value = '';
        }
      }  
    }
  }  
});