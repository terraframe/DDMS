// Author: Justin Smethie
Mojo.Meta.newClass('MDSS.PersonModal', {
  Instance: {
    initialize : function(prop) {
      this._recipientIdEl = (Mojo.Util.isString(prop.concrete) ? document.getElementById(prop.concrete) : prop.concrete);      
      this._clickable = (Mojo.Util.isString(prop.clickable) ? document.getElementById(prop.clickable) : prop.clickable);
      this._button = (Mojo.Util.isString(prop.button) ? document.getElementById(prop.button) : prop.button);
      this._elements = prop.elements;
      
      // Make it so the clickable span is in the tab index
      YAHOO.util.Dom.setAttribute(this._clickable, 'tabindex', '0');
  
      // IMPORTANT: calendarIdEl may not be in the DOM yet because it should appear on the modal page
      this._calendarIdEl = prop.calendar;
      this._firstName = prop.firstName;
      this._lastName = prop.lastName;

      this._currentModal = null;
      this._id = new String(Math.random()).substring(2);    
      this.controller = Mojo.$.dss.vector.solutions.PersonController;
  
      var updateListener = Mojo.Util.bind(this, this.updateListener);
      var cancelListener = Mojo.Util.bind(this, this.cancelListener);

      this.controller.setUpdateRecipientListener(updateListener);
      this.controller.setViewAllListener(cancelListener);
  
      // Setup the element events
      YAHOO.util.Event.on(this._clickable, 'click', this.handleClick, null, this);
      YAHOO.util.Event.on(this._clickable, 'keyup', this.handleKeyup, null, this);
   
      // the SingleSelectSearch used by all geo input fields
      this._selectSearch = new MDSS.SingleSelectSearch();
      
      this._residentialGeoSearch = null;
      this._workGeoSearch = null;
    },
    
    updateListener : function(params) {
      var request = new MDSS.Request({
        that : this,
        onSuccess: function(personId) {
          this.that.destroyModal();
          
          // Set the id of the newly created person
          var id = Mojo.Util.trim(personId);
          this.that._recipientIdEl.value = id;
          
          // Submit the form
          this.that._button.onclick();
        }
      });

      return request;
    },
    
    cancelListener : function(params) {
      var request = new MDSS.Request({
        that : this,
        onSuccess: function() {
          this.that.destroyModal();
        }
      });

      return request;
     },
    
    handleClick : function (e) {
      var request = new MDSS.Request({
        that : this,
        onSuccess: function(html) {                
          this.that.createModal(html);
        }
      });
        
      var id = this._recipientIdEl.value;

      this.controller.editRecipient(request, id);
    },
    
    handleKeyup : function (e) {
      if(e.keyCode === 13) {
        var request = new MDSS.Request({
          that : this,
          onSuccess: function(html) {                
            this.that.createModal(html);
          }
        });
    
        var id = this._recipientIdEl.value;
    
        this.controller.editRecipient(request, id);
      }
    },
    
    createModal : function (html) {    
      var executable = MDSS.util.extractScripts(html);
          
      html = MDSS.util.removeScripts(html);    
    
      this._currentModal = new YAHOO.widget.Panel(this._id,  {
         width: '400px',
         height: '600px',
         fixedcenter:true,
         close:false,
         draggable:false,
         zindex:4,
         modal:true,
         visible:true
       });

      this._currentModal.setBody(html);
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
      
      // Populate the first and last name values
      if(this._recipientIdEl != '') {
        document.getElementById(this._firstName).value = document.getElementById(this._elements[0]).value;
        document.getElementById(this._lastName).value = document.getElementById(this._elements[1]).value;
      }
    },
    
    destroyModal : function()
    {
      this._currentModal.destroy();
      this._currentModal = null;
    }
  },
  
  Static : {
    setUpPersonModal : function(prop) {
      prop.concrete = (Mojo.Util.isString(prop.concrete) ? document.getElementById(prop.concrete) : prop.concrete);
      prop.createLink = (Mojo.Util.isString(prop.createLink) ? document.getElementById(prop.createLink) : prop.createLink);
      prop.editLink = (Mojo.Util.isString(prop.editLink) ? document.getElementById(prop.editLink) : prop.editLink);
      prop.button = (Mojo.Util.isString(prop.button) ? document.getElementById(prop.button) : prop.button);
 
      var listFunction = function(valueObject) {
        var firstName = valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.FIRSTNAME);
        var lastName = valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.LASTNAME);
        var dateOfBirth = valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.DATEOFBIRTH);
        var sex = valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.SEX);
        var residential = valueObject.getValue('residentialGeoEntity_displayLabel');

        var formattedDateOfBirth = MDSS.Calendar.getLocalizedString(dateOfBirth);
        
        if(residential != null && residential != '') {
          return  firstName + ' ' + lastName  + ' (' + sex + '), DOB: ' + formattedDateOfBirth + ', ' + residential;
        }

        return  firstName + ' ' + lastName  + ' (' + sex + '), DOB: ' + formattedDateOfBirth;
     };

     var idFunction = function(valueObject) {
       var id = Mojo.$.dss.vector.solutions.PersonView.ID;

       return valueObject.getValue(id);
     };

     var displayFunction = function(valueObject) {
       var firstName = Mojo.$.dss.vector.solutions.PersonView.FIRSTNAME;
       var lastName = Mojo.$.dss.vector.solutions.PersonView.LASTNAME;
       var firstNameKey = prop.elements[0];
       var lastNameKey = prop.elements[1];
       
       var map = {};
              
       map[firstNameKey] = valueObject.getValue(firstName);
       map[lastNameKey] = valueObject.getValue(lastName);

       return map;
     };

     var searchFunction = Mojo.$.dss.vector.solutions.Person.searchForPerson;

     var selectEventHandler = function() {
       prop.createLink.style.display = "none";      
       prop.editLink.style.display = "inline";
       prop.button.disabled=false;
     };

     var showCreatePatient = function(e) {
       prop.editLink.style.display = "none";
       prop.createLink.style.display = "inline";
       prop.button.disabled=true;
     }
     
     var eventHandler = function(e) {
       if(e.getType() == MDSS.Event.BEFORE_SEARCH) {
         showCreatePatient();
       }    
     }
     
     var autocomplete = {
       elements:prop.elements,
       concrete:prop.concrete, 
       list:listFunction, 
       display:displayFunction, 
       id:idFunction, 
       search:searchFunction,
       selectEventHandler:selectEventHandler,
       minLength:2
     };
 
     var search = new MDSS.MultiInputAutoComplete(autocomplete);
     search.addListener(showCreatePatient);

     
     var modal = new MDSS.PersonModal(prop);

     if(prop.concrete.value != '') {
       selectEventHandler();
     }
     else {
       showCreatePatient();  
     }
   }
  }
});