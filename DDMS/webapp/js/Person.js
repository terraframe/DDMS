/*
 * Copyright (C) 2008 IVCC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
// Author: Justin Smethie
Mojo.Meta.newClass('MDSS.AbstractPersonModal', {
  IsAbstract : true,
  Instance: {
    initialize : function(prop) {
      this._concrete = (Mojo.Util.isString(prop.concrete) ? document.getElementById(prop.concrete) : prop.concrete);      
      this._clickable = (Mojo.Util.isString(prop.clickable) ? document.getElementById(prop.clickable) : prop.clickable);
      this._createLink = (Mojo.Util.isString(prop.createLink) ? document.getElementById(prop.createLink) : prop.createLink);
      this._editLink = (Mojo.Util.isString(prop.editLink) ? document.getElementById(prop.editLink) : prop.editLink);
      this._elements = prop.elements;
            
      // Make it so the clickable span is in the tab index
      YAHOO.util.Dom.setAttribute(this._clickable, 'tabindex', '0');
  
      // IMPORTANT: Modal DOM elements do not exist yet
      this._calendarIdEl = prop.calendar;
      this._attributes = [];

      this._currentModal = null;
      this._id = new String(Math.random()).substring(2);    
  
      // Setup the element events
      YAHOO.util.Event.on(this._clickable, 'click', this.handleClick, null, this);
      YAHOO.util.Event.on(this._clickable, 'keyup', this.handleKeyup, null, this);
   
      // the SingleSelectSearch used by all geo input fields
      this._selectSearch = new MDSS.SingleSelectSearch();
      
      this._residentialGeoSearch = null;
      this._workGeoSearch = null;      
    },

    populateModal : {
      IsAbstract : true
    },

    apply :  {
      IsAbstract : true
    },
    
    
    getAction :  {
      IsAbstract : true
    },
    
    populateComponent : function() {
      var component = new Mojo.$.dss.vector.solutions.PersonView();

      for(var i=0; i<this._attributes.length; i++){
        var el = this._attributes[i];
        var key = el.id;
        var value = el.value;
          
        this.setValue(component, key, value);
      }

      return component;  
    },

    setValue : function(component, attributeName, value) {
      var attributeDTO = component.getAttributeDTO(attributeName);

      if(attributeDTO instanceof com.runwaysdk.transport.attributes.AttributeDateDTO) {
        value = (value == '') ? null : value;
        
        var date = MDSS.Calendar.parseDate(value);

        attributeDTO.setValue(date);        
      }
      else {
        attributeDTO.setValue(value);
      }
    },
    
    confirmHandler : function(e) {
      YAHOO.util.Event.preventDefault(e);
      
      this.apply();
    },
   
    cancelHandler : function(e) {
      YAHOO.util.Event.preventDefault(e);

      this.destroyModal();
    },
        
    handleClick : function (e) {
      var request = new MDSS.Request({
        that : this,
        onSuccess: function(html) {                
          this.that.createModal(html);
        }
      });
        
      var id = this._concrete.value;
      
      var action = this.getAction();
      
      action(request, id);
    },
    
    handleKeyup : function (e) {
      if(e.keyCode === 13) {
        var request = new MDSS.Request({
          that : this,
          onSuccess: function(html) {                
            this.that.createModal(html);
          }
        });
    
        var id = this._concrete.value;
    
        var action = this.getAction();
        
        action(request, id);
      }
    },
    
    createModal : function (html) {    
      var executable = MDSS.util.extractScripts(html);
          
      html = MDSS.util.removeScripts(html);    
    
      this._currentModal = new YAHOO.widget.Panel(this._id,  {
         width: '400px',
         height: '530px',
         fixedcenter:true,
         close:false,
         draggable:false,
         zindex:4,
         modal:true,
         visible:true
       });
      
      var div = '<div class="innerContentModalLarge">'+html+'</div>';

      this._currentModal.setBody(div);
      this._currentModal.render(document.body);
      this._currentModal.bringToTop();
      
      var calendar = document.getElementById(this._calendarIdEl);
      MDSS.Calendar.addCalendarListeners(calendar);
          
      eval(executable);
      
      /*
       * BUG FIX: Any focus event on the YUI calendar is transfered
       * to the underlying Panel such that the first element in the Panel
       * is set to focus. This keeps the calendar from working correctly.
       * Regardless if the bug is in MDSS or YUI, removing the focus handlers
       * directly from the panel instance seems to fix the issue.
       * 
       * TODO unregister the focus handlers in a more official way.
       */
      this._currentModal._removeFocusHandlers();
      
      this.populateModal();
      
      this._attributes = YAHOO.util.Dom.getElementsByClassName("component");
      
      // Setup the click handlers for the buttons
      YAHOO.util.Event.on('button.confirm', 'click', this.confirmHandler, this, this);
      YAHOO.util.Event.on('button.cancel', 'click', this.cancelHandler, this, this);      
    },
    
    destroyModal : function()
    {
      this._attributes = [];
      
      this._currentModal.destroy();
      this._currentModal = null;
    },
    
    selectEventHandler : function() {
      this._createLink.style.display = "none";      
      this._editLink.style.display = "inline";
    },
   
    showCreatePatient : function(e) {
      this._editLink.style.display = "none";
      this._createLink.style.display = "inline";
    },
       
    eventHandler : function(e) {
      if(e.getType() == MDSS.Event.BEFORE_SEARCH) {
        this.showCreatePatient();
      }    
    }    
  }  
});

Mojo.Meta.newClass('MDSS.PersonModal', {
  Extends : MDSS.AbstractPersonModal,
  Instance: {
    initialize : function(prop) {
      this.$initialize(prop);
      
      this._identifier = prop.identifier;
      this._firstName = prop.firstName;
      this._lastName = prop.lastName;      
      this._button = (Mojo.Util.isString(prop.button) ? document.getElementById(prop.button) : prop.button);
      
      var autocomplete = {
        elements:prop.elements,
        concrete:prop.concrete, 
        list:Mojo.Util.bind(this, this.listFunction), 
        display:Mojo.Util.bind(this, this.displayFunction),
        id:Mojo.Util.bind(this, this.idFunction), 
        search:Mojo.$.dss.vector.solutions.Person.searchForPerson,
        selectEventHandler:Mojo.Util.bind(this, this.selectEventHandler),
        minLength:2
      };
     
      this.search = new MDSS.MultiInputAutoComplete(autocomplete);
      this.search.addListener(Mojo.Util.bind(this, this.eventHandler));
      
      if(this._concrete.value != '') {
        this.selectEventHandler();
      }
      else {
        this.showCreatePatient();  
      }
    },
    
    apply : function() {
      var request = new MDSS.Request({
        that : this,
        onSuccess: function(returnedValue, returnedObject) {
          var personId = returnedObject.getPersonId();

          this.that.destroyModal();
          this.that._concrete.value = personId;
          
          this.that._button.onclick();
        }
      });
      
      var component = this.populateComponent();

      component.applyNonDelegates(request);
    },
    
    getAction : function() {
      return Mojo.$.dss.vector.solutions.PersonController.editRecipient;
    },
      
    selectEventHandler : function() {
      this.$selectEventHandler();
     
      this._button.disabled = false;
    },
     
    showCreatePatient : function(e) {
      this.$showCreatePatient();
      
      this._button.disabled = true;
    },    
    
    populateModal : function() {
      // Populate the first and last name values
      if(this._concrete != '') {
        document.getElementById(this._identifier).value = document.getElementById(this._elements[0]).value;
        document.getElementById(this._firstName).value = document.getElementById(this._elements[1]).value;
        document.getElementById(this._lastName).value = document.getElementById(this._elements[2]).value;
      }    
    },

    listFunction : function(valueObject) {
      var identifier = valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.IDENTIFIER);    	
      var firstName = valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.FIRSTNAME);
      var lastName = valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.LASTNAME);
      var dateOfBirth = valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.DATEOFBIRTH);
      var sex = valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.SEX);
      var residential = valueObject.getValue('residentialGeoEntity_displayLabel');

      var formattedDateOfBirth = MDSS.Calendar.getLocalizedString(dateOfBirth);
      
      var label = firstName + ' ' + lastName  + ' (' + sex + '), DOB: ' + formattedDateOfBirth;
      
      if(identifier != null && identifier != '') {
        label = identifier + ' ' + label;
      }
          
      if(residential != null && residential != '') {
        label = label + ', ' + residential;
      }

      return label;
    },

    idFunction : function(valueObject) {
      var id = Mojo.$.dss.vector.solutions.PersonView.ID;

      return valueObject.getValue(id);
    },

    displayFunction : function(valueObject) {
      var identifier = Mojo.$.dss.vector.solutions.PersonView.IDENTIFIER;
      var firstName = Mojo.$.dss.vector.solutions.PersonView.FIRSTNAME;
      var lastName = Mojo.$.dss.vector.solutions.PersonView.LASTNAME;
      
      var identifierKey = this._elements[0];
      var firstNameKey = this._elements[1];
      var lastNameKey = this._elements[2];
         
      var map = {};
               
      map[identifierKey] = valueObject.getValue(identifier);
      map[firstNameKey] = valueObject.getValue(firstName);
      map[lastNameKey] = valueObject.getValue(lastName);

      return map;
    }    
  }
});

Mojo.Meta.newClass('MDSS.PhysicianModal', {
  Extends : MDSS.AbstractPersonModal,
  Instance: {
    initialize : function(prop) {
      this.$initialize(prop);

      this._input = (Mojo.Util.isString(prop.input) ? document.getElementById(prop.input) : prop.input);

      var dF = Mojo.Util.bind(this, this.listFunction);
      var iF = Mojo.Util.bind(this, this.idFunction);
      var lF = Mojo.Util.bind(this, this.listFunction);
      var sF = Mojo.Util.bind(this, this.searchFunction);
      var sEH = Mojo.Util.bind(this, this.selectEventHandler);
  
      this.search = new MDSS.GenericSearch(this._input, this._concrete, lF, dF, iF, sF, sEH);
      this.search.addListener(Mojo.Util.bind(this, this.eventHandler));
      
      if(this._concrete.value != '') {
        this.selectEventHandler();
      }
      else {
        this.showCreatePatient();  
      }
    },
    
    apply : function() {
      var request = new MDSS.Request({
        that : this,
        onSuccess: function(view) {
          var id = view.getValue(Mojo.$.dss.vector.solutions.PersonWithDelegatesView.PHYSICIANDELEGATE);
          this.that._concrete.value = id;
          this.that._input.value = view.getFirstName() + ' ' + view.getLastName();

          this.that.destroyModal();

          this.that.selectEventHandler();
        }
      });
      
      var component = this.populateComponent();

      component.applyAsPhysician(request);
    },
    
    populateModal : function() {
    },

    getAction : function() {
      return Mojo.$.dss.vector.solutions.PersonController.editPhysician;
    },
      
    searchFunction : function(request, value) {
      Mojo.$.dss.vector.solutions.Physician.searchForPhysician(request, value);
    },
   
    listFunction : function(valueObject) {
      var firstName = valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.FIRSTNAME);
      var lastName = valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.LASTNAME);

      return firstName + ' ' + lastName;
    },

    idFunction : function(valueObject) {
      var id = valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.ID);

      return id;
    }
  }
});
