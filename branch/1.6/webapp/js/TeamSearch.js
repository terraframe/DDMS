// Author: Justin Smethie
Mojo.Meta.newClass('MDSS.TeamSearch', {
  Instance: {

    initialize: function(geoId, teamSelect, operatorSelect, leaderSelect) {
      // Constructor code  
      this._geoId = Mojo.Util.isString(geoId) ? document.getElementById(geoId) : geoId;
      this._teamSelect = Mojo.Util.isString(teamSelect) ? document.getElementById(teamSelect) : teamSelect;
      this._operatorSelect = Mojo.Util.isString(operatorSelect) ? document.getElementById(operatorSelect) : operatorSelect;
      this._leaderSelect = Mojo.Util.isString(leaderSelect) ? document.getElementById(leaderSelect) : leaderSelect;
      this._pollingId = null;     
      
      YAHOO.util.Event.addListener(this._teamSelect, "change", this.populateFields, this, true);
    },
  
    // Public getter functions
    getGeoId : function () {
      return this._geoId;
    },
    
    getTeamSelect : function () {
      return this._teamSelect;
    },

    getOperatorSelect : function () {
      return this._operatorSelect;
    },
    
    getLeaderSelect : function () {
      return this._leaderSelect;
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
    
    populateFields : function() {
      this.clearTeamMembers();  
        
      if(this.getOperatorSelect() != null) {
        this._populateOperators();
      }
        
      if(this.getLeaderSelect() != null) {
        this._populateLeaders();
      }
    },   
        
    // Private methods
    _populateOperatorList : function (select, operators, blank) {
      if(select) {
        // Remove all of the current options in the select list
        Selectbox.removeAllOptions(select);

        // Add blank option
        if(blank != null && blank == true) {
         Selectbox.addOption(select, "", "", false);
        }
        
        // Add the new options retrieved from the AJAX call
        for(var i=0; i< operators.length; i++) {
          var label = operators[i].getMemberId() + " - " + operators[i].getLastName() + ", " + operators[i].getFirstName();

          Selectbox.addOption(select, label, operators[i].getActorId(), false);
        }          

        // Enable the select list
        select.disabled=false;
      }
    },
    
    _populateOperators : function (){
      if(this.getTeamSelect().value != '')
      {
        var request = new MDSS.Request({
          that : this,
          onFailure : function(){
            this.that.clearTeamMembers();
          },
          onProblemExceptionDTO : function(){
            this.that.clearTeamMembers();
          },          
          onSuccess : function(operators){
            this.that._populateOperatorList(this.that.getOperatorSelect(), operators, true);
          }
        });

        Mojo.$.dss.vector.solutions.irs.SprayTeam.getOperatorViews(request, this.getTeamSelect().value);
      }  
    },
    
    _populateLeaders : function (){      
      if(this.getTeamSelect().value != '')
      {
        var request = new MDSS.Request({
          that : this,
          onFailure : function(){
            this.that.clearTeamMembers();
          },
          onProblemExceptionDTO : function(){
            this.that.clearTeamMembers();
          },          
          onSuccess : function(operators){
            this.that._populateOperatorList(this.that.getLeaderSelect(), operators, true);
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

    evaluate : {
      IsAbstract : true
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
    initialize : function(prop) {
      this._visible = true;
      this._clearValue = Mojo.Util.isBoolean(prop.clearValue) ? prop.clearValue : true;
      this._elements = YAHOO.util.Selector.query('.' + prop.element);      
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

    getClearValue : function() {
      return this._clearValue;
    },

    getElements : function() {
      return this._elements;
    },          

    hideElement : function() {
      if(this._visible == true) {
        this.updateValues();
      
        var clear = this.getClearValue();
    
        if(clear === true) {
          this.clearValues();
        }

        var elements = this.getElements();
      
        for(var i = 0; i < elements.length; i++) {
          elements[i].style.display = "none";      
        }
        
        this._visible = false;
      }
    },
  
    showElement : function() {
      if(this._visible == false) {
        this.resetValues();
      
        var elements = this.getElements();
        
        for(var i = 0; i < elements.length; i++) {
          var element = elements[i];
        
          if(element.tagName && element.tagName == 'div') {
            element.style.display = "block";
          }
        
          element.style.display = "inline";      
        }
        
        this._visible = true;
      } 
    }
  }
});

Mojo.Meta.newClass('MDSS.HiddenBooleanElement', {
  Extends : MDSS.AbstractHiddenElement,  
  Instance: {
    initialize: function(prop) {
      this.$initialize(prop);

      this._positiveElement = document.getElementById(prop.element + '.positive');
      this._negativeElement = document.getElementById(prop.element + '.negative');
    
      this.updateValues();
    },
    
    updateValues : function() {
      if(this._positiveElement != null) {
        this._postivieChecked = this._positiveElement.checked;
      }
      
      if(this._negativeElement != null) {
        this._negativeChecked = this._negativeElement.checked;      
      }
    },
    
    clearValues : function() {
      if(this._positiveElement != null) {
        this._positiveElement.checked = false;
      }
      if(this._negativeElement != null) {
        this._negativeElement.checked = false;            
      }
    },
    
    resetValues : function() {
      if(this._positiveElement != null) {   
        this._positiveElement.checked = this._postivieChecked;
      }
      if(this._negativeElement != null) {
        this._negativeElement.checked = this._negativeChecked;
      }
    }    
  }
});


Mojo.Meta.newClass('MDSS.HiddenRadioElement', {
  Extends : MDSS.AbstractHiddenElement,  
  Instance: {
    initialize: function(prop) {
      this.$initialize(prop);

      this._options = YAHOO.util.Selector.query('.' + prop.option);
      this._values = [];
    
      this.updateValues();
    },
    
    updateValues : function() {
      for(var i = 0; i < this._options.length; i++) {
        this._values[i] = this._options[i].checked;
      }
    },
    
    clearValues : function() {
      for(var i = 0; i < this._options.length; i++) {
        this._options[i].checked = false;
      }   
    },
    
    resetValues : function() {
      for(var i = 0; i < this._options.length; i++) {
        this._options[i].checked = this._values[i];
      }   
    }    
  }
});


Mojo.Meta.newClass('MDSS.HiddenSelectElement', {
  Extends : MDSS.AbstractHiddenElement,  
  Instance: {
    initialize: function(prop) {
      this.$initialize(prop);

      this._inputElement = document.getElementById(prop.element);
      this._clearValue = Mojo.Util.isBoolean(prop.clearValue) ? prop.clearValue : true;
    
      this.updateValues();
    },
    
    updateValues : function() {
      if(this._inputElement != null) {      
        this._selectedIndex = this._inputElement.selectedIndex;
      }
    },
      
    clearValues : function() {
      if(this._inputElement != null && this._inputElement.value != '') {
        //Add a blank option with a null value
        var blankOption = document.createElement('option');
        blankOption.text = '';
        blankOption.value = '';

        //Set the input element to the blank option
        this._inputElement.add(blankOption, null);
        this._inputElement.selectedIndex = this._inputElement.length - 1;
      }
    },
      
    resetValues : function() {
      if(this._inputElement != null) {          
        this._inputElement.selectedIndex = this._selectedIndex;
      
        this._inputElement.remove(this._inputElement.length - 1);
      }
    }    
  }
});



Mojo.Meta.newClass('MDSS.HiddenInputElement', {
  Extends : MDSS.AbstractHiddenElement,  
  Instance: {
    initialize: function(prop) {
      this.$initialize(prop);
    
      this._inputElement = document.getElementById(prop.element);
      this._clearValue = Mojo.Util.isBoolean(prop.clearValue) ? prop.clearValue : true;      
      
      this.updateValues();
    },

    updateValues : function() {
      if(this._inputElement != null) {
        this._inputValue = this._inputElement.value;
      }
    },
    
    clearValues : function() {
      if(this._inputElement != null) {
        this._inputElement.value = '';
      }
    },
    
    resetValues : function() {
      if(this._inputElement != null) {    
        this._inputElement.value = this._inputValue;
      }
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
      this.$initialize(prop);

      this._resultList = document.getElementById(prop.element + 'ResultList');      
      this._inputElement = document.getElementById(prop.element);
      this._clearValue = Mojo.Util.isBoolean(prop.clearValue) ? prop.clearValue : true;      
      
      this.updateValues();
    },

    updateValues : function() {
      if(this._inputElement != null) {
        this._inputValue = this._inputElement.value;
      }
      
      if(this._resultList != null) {
        this._results = this._resultList.innerHTML;
      }
    },
    
    clearValues : function() {
      if(this._inputElement != null) {   
        this._inputElement.value = '';
      }
      
      if(this._resultList != null) {
        this._resultList.innerHTML = '';
      }
    },
    
    resetValues : function() {
      if(this._inputElement != null) {
        this._inputElement.value = this._inputValue;
      }
      
      if(this._resultList != null) {
        this._resultList.innerHTML = this._results;
      }
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
      
      this._listeners = [];

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
        var evaluate = this.getCondition().evaluate();
        
        if(evaluate)
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
        
        for(var i = 0; i < this._listeners.length; i++) {
          this._listeners[i].optionHandler();
        }
      }
    },
    
    addListener : function (listener) {
      this._listeners.push(listener);
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

Mojo.Meta.newClass('MDSS.DataSource', {
  Instance : {
    initialize : function(callback, searchFunction) {
      this.callback = callback;
      this.searchFunction = searchFunction;
      this.requestCount = 0;
      this.currentRequest = 0;
      this.cache = {};
      this._enabled = true;
    },
    
    nextNumber : function() {
      return ++this.requestCount;
    },
    
    retrievedResults : function(value, requestNumber, results) {
      // IMPORTANT : This check then act has a race condition, however I do not if semaphores exit in javascript 
      this.cache[value] = results;
      
      if(requestNumber > this.currentRequest) {
        this.currentRequest = requestNumber;
        
        this.callback.displayResults(results);
      }
    },
    
    getResultsFromServer : function(value, parameters) {
      var requestNumber = this.nextNumber();

      // Create the MDSS request
      var request = new MDSS.Request({
        that : this,
        value : value,
        requestNumber : requestNumber,
        onSend: function(){},
        onComplete: function(){},
        onSuccess: function(query) {
          var results = query.getResultSet();
          
          this.that.retrievedResults(this.value, this.requestNumber, results);
        }
      });
          
      if(parameters) {
        if(Mojo.Util.isArray(parameters)) {
          var args = [request,value];
          args = args.concat(parameters);
           
          this.searchFunction.apply(this,args);
        }
        else {
          this.searchFunction(request, value, parameters);
        }
      }
      else {
        this.searchFunction(request, value);  
      }        
    },
    
    enable : function() {
      this._enabled = true;
    },
    
    disable : function() {
      this._enabled = false;
    },
    
    getResults : function(value, parameters) {
      if(this.cache[value] && this._enabled) {
        var requestNumber = this.nextNumber();
        this.retrievedResults(value, requestNumber, this.cache[value]);
      }
      else {
        this.getResultsFromServer(value, parameters);
      }
    },
    
    resetCache : function() {
      this.cache = {};    
    }
  }
});

Mojo.Meta.newClass('MDSS.ResultPanel', {
  Instance: {
    initialize: function(autocomplete, element) {
      this._autocomplete = autocomplete; 
      
      this.ul = document.createElement('ul');
      this.ul.id = element.id + '_results_ul';
      this.index = 0;

      this._resultsDiv = document.createElement('div');
      this._resultsDiv.id = element.id + '_results';
      this._resultsDiv.className = "yui-panel-container show-scrollbars shadow";
    
      YAHOO.util.Dom.insertAfter(this._resultsDiv,element);

      this.panel = new YAHOO.widget.Panel(this._resultsDiv, {
        zindex:15,
        draggable: false,
        close: false,
        visible: false
      });    
      
      var listener = new YAHOO.util.KeyListener(document, {keys:27}, {fn:this.panel.hide, scope:this.panel, correctScope:true }, "keyup" );

      this.panel.cfg.queueProperty("keylisteners", listener);
    
      var outer = document.createElement('div');
      var inner = document.createElement('div');

      outer.appendChild(inner);      
      inner.appendChild(this.ul);

      this.panel.setBody(outer);
      outer.appendChild(inner);

      YAHOO.util.Dom.addClass(this.ul, 'selectableList');

      YAHOO.util.Event.on(this.ul, 'mouseover', function(e, obj){
        this._autocomplete.dispatchEvent(new MDSS.EnterResults());
        var li = e.target; 
        var ul = e.currentTarget;
        
        if(li.nodeName === 'SPAN') {
          li = li.parentNode;
        }

        if(li.nodeName !== 'LI') {
          return;
        }
        
        this.selectOption(li.index);

      }, this, this);

      YAHOO.util.Event.on(this.ul, 'click', function(e, obj){
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

        this._autocomplete.selectHandler(li);

      }, this, this);
    },
    
    getResultsEl : function(){
      return this._resultsDiv;
    },
    
    _getOption : function(i) {
      return this.ul.children[i];
    },
    
    _unselectOption : function(i) {
      var option = this._getOption(i);
      YAHOO.util.Dom.removeClass(option, 'currentSelection');    
    },
    
    _selectOption : function(i) {
      var option = this._getOption(i);
      YAHOO.util.Dom.addClass(option, 'currentSelection');    
    },

    setOptions : function(options) {
      this.index = null;
      this.ul.innerHTML = '';
      
      this.ul.appendChild(options);

      if(this.ul.children.length > 0) {
        this.panel.render();
        this.show();
        this.panel.bringToTop();
      }
      else {
        this.hide();
      }
    },
    
    show : function() {
      this.panel.show();
    },
    
    hide : function() {
      this.panel.hide();
    },

    isVisible : function() {
      return this.panel.cfg.getProperty("visible");    
    },
    
    selectOption : function(i) {
      if(this.index != null) {
        this._unselectOption(this.index);
      }      

      if(i < this.ul.children.length && i >= 0) {
        this.index = i;
        this._selectOption(this.index);
      }
    },
    
    selectPrevious : function() {
      if(this.index != null) {
        var i = (this.index - 1 >= 0) ? this.index - 1 : this.ul.children.length - 1;
        
        this.selectOption(i);
      }      
      else {
        this.selectOption(this.ul.children.length - 1);        
      }
    },
    
    selectNext : function() {
      if(this.index != null) {
        // Get the next index
        var i = (this.index + 1 < this.ul.children.length) ? this.index + 1 : 0;

        this.selectOption(i);
      }      
      else {
        this.selectOption(0);
      }
    },   
    
    selectCurrent : function() {
      if(this.index != null) {
        var option = this._getOption(this.index);
        
        this._autocomplete.selectHandler(option);
      }
      else if (this.ul.children.length == 1) {
        var option = this._getOption(0);
      
        this._autocomplete.selectHandler(option);
      }
    },
    
    hasOptions : function() {
      return this.ul.children.length > 0;
    }
  }
});

Mojo.Meta.newClass('MDSS.OptionBuilder', {
  Instance: {
    initialize: function(listFunction, displayFunction, idFunction) {
      this.listFunction = listFunction;              // Function which accepts a valueObject and returns a formatted string for a single result 
      this.displayFunction = displayFunction;        // Function which accepts a valueObject and returns the value for 'displayElement'
      this.idFunction = idFunction;                  // Function which accepts a valueObject and returns the value for 'concreteElement' 
    },
    
    createOption : function(valueObj, searchValue, index) {
      var displayStr = this.getListDisplay(valueObj);
      var matched = displayStr.replace(new RegExp("(.*?)(" + searchValue + ")(.*?)", "gi"), "$1<span class='searchMatch'>$2</span>$3");
            
      var li = document.createElement('li');      
      li.id = this.getId(valueObj);              
      li.label = this.getDisplay(valueObj);
      li.innerHTML = matched;
      li.index = index;
          
      return li;
    },

    getDisplay : function(valueObject) {
      return this.displayFunction(valueObject);
    },
      
    getListDisplay : function(valueObject) {
      return this.listFunction(valueObject);
    },
      
    getId : function(valueObject) {
      return this.idFunction(valueObject);
    }
  }
});

Mojo.Meta.newClass('MDSS.AutoComplete', {
  IsAbstract : true,
  Instance: {
    initialize: function(dataSource, optionBuilder, panel, selectEventHandler, prop) {
      // Optional function which is called after an option has been selected
      this._selectEventHandler = selectEventHandler;
      this._dataSource = dataSource;
      this._optionBuilder = optionBuilder;
      this._panel = panel;
      this._value = this.getValue();
      this._hasChanged = false;
      
      // Flag denoting if a selection has been made without any new search input
      // from the user.
      this._hasSelection = false; 
      
      this.listeners = [];
      this.parameters = null;
      
      // Create the default properties object
      if(prop == null) {
        prop = {minLength:2};
      }
      
      this.minLength = (Mojo.Util.isNumber(prop.minLength * 1) ? prop.minLength * 1 : 2);
    },
    
    disableCache : function() {
      this._dataSource.disable();
    },

    preventFormSubmit : function(e)
    {
      if((e.keyCode || e.charCode) === 13)
      {
        YAHOO.util.Event.preventDefault(e);
      }    
    },
    
    hide : function() {
      this._panel.hide();
    },
    
    show : function() {
      this._panel.show();
    },

    getPanel : function() {
      return this._panel;
    },

    getParameters : function() {
      return this.parameters;
    },
      
    addParameter : function(parameter) {
      this.parameters = parameter;
    },
      
    addListener : function(listener) {
       this.listeners.push(listener);
    },

    displayResults : function(results) {
      // We only want to display new results when a selection has not be made
      // with the current search value.  It is possible that a selection will
      // be made, while there are outstanding ajax request for search results.    
      if(!this._hasSelection) {
        var searchValue = this.getValue();

        var options = document.createDocumentFragment();

        for(var i=0; i<results.length; i++) {
          var result = results[i];
          
          var option = this._optionBuilder.createOption(result, searchValue, i); 
          
          options.appendChild(option);
        }
        
        this._panel.setOptions(options);
      
        this.focus();
      }
    },
                  
    selectHandler : function(selected) {
      if(selected) {
        this.dispatchEvent(new MDSS.EnterResults());

        this._hasSelection = true;
        this.setOption(selected);
          
        if(Mojo.Util.isFunction(this._selectEventHandler)) {
          this._selectEventHandler(selected);
        }
        
        this.fireEvent(new MDSS.Event(MDSS.Event.AFTER_SELECTION, {selected:selected, autoComplete:this})); // old
        this.dispatchEvent(new MDSS.ExitResults());
      }
        
      this.hide();
    },
      
    keyHandler : function(oData) {
      var value = this.getValue();

      // Handle the 'tab', 'left', 'right' key
      if (oData.keyCode == 9 || oData.keyCode == 39 || oData.keyCode == 37) {
        // DO NOTHING
      }
      else
      {
        this._hasSelection = false;
      
        // Handle the 'down' arrow key
        if(oData.keyCode === 40) {    
          var visible = this._panel.isVisible();
          YAHOO.util.Event.preventDefault(oData);      
          
          if(!visible || !this._panel.hasOptions()) {
            if(this._hasChanged) {
              this.performSearch(value);
            }
            else {
              this.performSearch('');
            }
          }
          else {
            this._panel.selectNext();
          }
        }
        // Handle the 'up' arrow key
        else if (oData.keyCode === 38) {
          var visible = this._panel.isVisible();
          YAHOO.util.Event.preventDefault(oData);     
        
          if(!visible || !this._panel.hasOptions()) {
            if(this._hasChanged) {
              this.performSearch(value);
            }
            else {
              this.performSearch('');
            }
          }
          else {
            this._panel.selectPrevious();
          }
        }      
        // Handle the 'esc' key
        else if(oData.keyCode === 27) {
          this.hide();
        }   
        // Handle the 'enter' key
        else if (oData.keyCode === 13) {
          this.dispatchEvent(new MDSS.EnterResults());
          this._panel.selectCurrent();
          this.dispatchEvent(new MDSS.ExitResults());
        }
        else { 
          if(this._isDifferent(value)) {
            this._setCurrentValue(value);
          }
        
          if(value.length >= this.minLength) {        
            this.performSearch(value);
          }
        }
      }
    },
    
    forceSearch : function() {
      var value = this.getValue();
      
      this.performSearch(value);
    },
    
    fireEvent : function(event) {
      for(var i = 0; i < this.listeners.length; i++) {
        this.listeners[i](event);
      }
    },
      
    performSearch : function(value) {              
      var parameters = this.getParameters();
      
      this.fireEvent(new MDSS.Event(MDSS.Event.BEFORE_SEARCH, {value:value, autocomplete:this}));
        
      this._dataSource.getResults(value, parameters);
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
    },
    
    _isDifferent : function (value) {
      return (this._value != value);
    },
    
    _setCurrentValue : function(value) {
      this.resetSelected();
      this.value = value;
      this._hasChanged = true;
    },
    
    resetCache : function() {
      this._dataSource.resetCache();
    },
    
    focus : function () {
      IsAbstract : true
    },
    
    getValue : function () {
      IsAbstract : true
    },
    
    setOption : function (selected) {
      IsAbstract : true
    },
            
    resetSelected : function() {
      IsAbstract : true
    }
  }
});

Mojo.Meta.newClass('MDSS.EnterResults', {
  Extends : Mojo.$.com.runwaysdk.event.CustomEvent,
  Instance : {
    initialize : function(){
      this.$initialize();
    }
  }
});

Mojo.Meta.newClass('MDSS.ExitResults', {
  Extends : Mojo.$.com.runwaysdk.event.CustomEvent,
  Instance : {
    initialize : function(){
      this.$initialize();
    }
  }
});

Mojo.Meta.newClass('MDSS.GenericSearch', { // Implements CallBack
  Extends : MDSS.AutoComplete,
  Instance: {
    initialize: function(displayElement, concreteElement, listFunction, displayFunction, idFunction, searchFunction, selectEventHandler, prop) {
      // DOM element where the search is inputed and the selected result is displayed
      this._displayElement = Mojo.Util.isString(displayElement) ? document.getElementById(displayElement) : displayElement;
    
      // DOM element where the id of the selected result is stored
      this._concreteElement = Mojo.Util.isString(concreteElement) ? document.getElementById(concreteElement) : concreteElement;       
  
      if(this._displayElement != null)
      {
        var dataSource = new MDSS.DataSource(this, searchFunction);
        var optionBuilder = new MDSS.OptionBuilder(listFunction, displayFunction, idFunction);      
        var panel = new MDSS.ResultPanel(this, this._displayElement);

        this.$initialize(dataSource, optionBuilder, panel, selectEventHandler, prop);

        // Disable the browser autocomplete function for the element we provide an auto-complete
        this._displayElement.setAttribute("autocomplete", "off");
      
        YAHOO.util.Event.on(this._displayElement, 'keypress', this.preventFormSubmit, null, this);
        YAHOO.util.Event.on(this._displayElement, 'keyup', this.keyHandler, this, this);
      }
      
      // block all blur events if the display element is being modified through a result panel
      YAHOO.util.Event.on(this._displayElement, 'blur', this._blurHandler, null, this);
    },
    getDisplayElement : function() {
      return this._displayElement;
    },
    
    getConcreteElement : function() {
      return this._concreteElement;
    },
    
    focus : function() {
      // refocus the input field
      this.getDisplayElement().focus();
    },
    
    getValue : function () {
      return this._displayElement.value;
    },
      
    setOption : function (selected) {
      this.setElementValue(this.getDisplayElement(), selected.label);
      this.setElementValue(this.getConcreteElement(), selected.id);
    },
    
    resetSelected : function () {
      this.setElementValue(this.getConcreteElement(), '');
    }
  },
    
  Static: {         
    createYearSearch : function(element) {
      element = Mojo.Util.isString(element) ? document.getElementById(element) : element;
    
      var years = new Array();
      var d = new Date();
      var year = d.getFullYear();

      for(var i = 0; i < 10; i++) {
        years.push(year + '');
        year--;
      } 
        
      var listFunction = function(valueObject) {
        return valueObject;
      };

      var idFunction = function(valueObject) {
      };

      var displayFunction = function(valueObject) {    
        return valueObject;
      };

      var searchFunction = function(request, value) {
        var filtered = [];
        
        for(var i=0; i < years.length; i++) {
          var year = years[i];
              
          if(year.search(value) !== -1 || value === '') {
            filtered.push(year);
          }
        }
        
        var query = {
          resultSet : filtered,
          getResultSet : function(){
            return this.resultSet;
          }
        };        
        request.onSuccess(query);        
      };

      var selectEventHandler = function() {};

      var search = new MDSS.GenericSearch(element, null, listFunction, displayFunction, idFunction, searchFunction, selectEventHandler, {minLength:0});

      YAHOO.util.Event.on(element, 'focus', search.forceSearch, search, search);
      
      return search;
    }  
  }  
});

Mojo.Meta.newClass('MDSS.MultiInputAutoComplete', { // Implements CallBack
  Extends : MDSS.AutoComplete,
  Instance: {
    initialize: function(prop) {
      this._concreteElement = Mojo.Util.isString(prop.concrete) ? document.getElementById(prop.concrete) : prop.concrete;       
      this._elements = [];
      this._focusElement = null;

      if(Mojo.Util.isArray(prop.elements)) {  
        for(var i = 0; i < prop.elements.length; i++) {
          this._initializeElement(prop.elements[i]);
        }
      }
      else {
        this._initializeElement(prop.elements);
      }
    
      if(this._elements.length > 0) {
        var dataSource = new MDSS.DataSource(this, prop.search);
        var optionBuilder = new MDSS.OptionBuilder(prop.list, prop.display, prop.id);
        var panel = new MDSS.ResultPanel(this, this._elements[0]);
      
        this.$initialize(dataSource, optionBuilder, panel, prop.selectEventHandler, prop);
      }
    },
    
    _initializeElement : function(elementId) {
      var element = Mojo.Util.isString(elementId) ? document.getElementById(elementId) : elementId;
    
      this._elements.push(element);      
        
      element.setAttribute("autocomplete", "off");
      YAHOO.util.Event.on(element, 'keypress', this.preventFormSubmit, null, this);
      YAHOO.util.Event.on(element, 'keyup', this.keyHandler, this, this);    
    },
    
    keyHandler : function(oData) {
      var target = oData.originalTarget;    
      
      this._focusElement = target;
      
      this.$keyHandler(oData);
    },
        
    focus : function() {
      if(this._focusElement) {
        // refocus the input field
        this._focusElement.focus();
      }
    },
    
    getValue : function () {
      var value = "";
      
      for(var i = 0; i < this._elements.length; i++) {
        var element = this._elements[i];
        
        value += element.value + " ";
      }
      
      // trim the search value before returning it
      return value.replace(/^\s+|\s+$/g,"");
    },
      
    setOption : function (selected) {
      // IMPORTANT: IT IS EXPECTED THAT selected.label IS A MAP WHERE
      //            MAP[ELEMENT.ID] = LABEL FOR ALL OF THE ELEMENTS
      for(var i = 0; i < this._elements.length; i++) {
        var element = this._elements[i];
        var id = element.id;
        var label = selected.label[id];
      
        this.setElementValue(element, label);
      }
        
      this.setElementValue(this._concreteElement, selected.id);
    },
    
    resetSelected : function () {
      this.setElementValue(this._concreteElement, '');
    }
  }
});
