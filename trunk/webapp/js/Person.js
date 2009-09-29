// Author: Justin Smethie
Mojo.Meta.newClass('MDSS.PersonModal', {
  Instance: {
	initialize : function(element, recipientIdEl, calendarIdEl) {
	  this.element = (Mojo.Util.isString(element) ? document.getElementById(element) : element);
	  this.recipientIdEl = (Mojo.Util.isString(recipientIdEl) ? document.getElementById(recipientIdEl) : recipientIdEl);
	  
	  // IMPORTANT: calendarIdEl may not be in the DOM yet because it should appear on the modal page
	  this.calendarIdEl = calendarIdEl;

	  this.currentModal = null;
	  this._id = new String(Math.random()).substring(2);	  	  
	  this.controller = Mojo.$.dss.vector.solutions.PersonController;
	  
      var updateListener = Mojo.Util.bind(this, this.updateListener);
      var cancelListener = Mojo.Util.bind(this, this.cancelListener);

  	  this.controller.setUpdateRecipientListener(updateListener);
  	  this.controller.setViewAllListener(cancelListener);
	  
	  // Setup the element events
	  YAHOO.util.Event.on(this.element, 'click', this.handleClick, null, this);	  
    },
    
    updateListener : function(params) {
        var request = new MDSS.Request({
        	that : this,
            onSuccess: function() {
        	  this.that.currentModal.destroy();
            }
        });

        return request;
    },
    
    cancelListener : function(params) {
        var request = new MDSS.Request({
        	that : this,
            onSuccess: function() {
        	  this.that.currentModal.destroy();
            }
        });

        return request;
     },
    
    handleClick : function (e) {
        var request = new MDSS.Request({
        	that : this,
            onSuccess: function(html) {        	
        	  var executable = MDSS.util.extractScripts(html);
        	  
        	  html = MDSS.util.removeScripts(html);
        	
        	  this.that.currentModal = this.that.createModal(html);
        	
        	  var calendar = document.getElementById(this.that.calendarIdEl);

        	  MDSS.Calendar.addCalendarListeners(calendar);
        	  
        	  eval(executable);
        	}
        });
        
        var id = this.recipientIdEl.value;

        this.controller.editRecipient(request, id);
    },
    
    createModal : function (html) {
        var panel = new YAHOO.widget.Panel(this._id,  {
            width: '400px',
            height: '550px',
            fixedcenter:true,
            close:false,
            draggable:false,
            zindex:4,
            modal:true,
            visible:true
          });

        panel.setBody(html);
        panel.render(document.body);
        panel.bringToTop();
        
        return panel;
    }
  },
  
  Static : {
	 setUpPersonModal : function(searchConfig, modalConfig, formConfig) {
	   searchConfig.searchEl = (Mojo.Util.isString(searchConfig.searchEl) ? document.getElementById(searchConfig.searchEl) : searchConfig.searchEl);
	   searchConfig.idEl = (Mojo.Util.isString(searchConfig.idEl) ? document.getElementById(searchConfig.idEl) : searchConfig.idEl);

	   modalConfig.createLink = (Mojo.Util.isString(modalConfig.createLink) ? document.getElementById(modalConfig.createLink) : modalConfig.createLink);
	   modalConfig.editLink = (Mojo.Util.isString(modalConfig.editLink) ? document.getElementById(modalConfig.editLink) : modalConfig.editLink);

	   formConfig.button = (Mojo.Util.isString(formConfig.button) ? document.getElementById(formConfig.button) : formConfig.button);

	   var listFunction = function(valueObject) {
		    var firstName = Mojo.$.dss.vector.solutions.PersonView.FIRSTNAME;
		    var lastName = Mojo.$.dss.vector.solutions.PersonView.LASTNAME;
		    var dateOfBirth = Mojo.$.dss.vector.solutions.PersonView.DATEOFBIRTH;
		    var location = Mojo.$.dss.vector.solutions.PersonView.RESIDENTIALGEOID;
	    	var sex = Mojo.$.dss.vector.solutions.PersonView.SEX;

	        var formattedDateOfBirth = MDSS.Calendar.getLocalizedString(valueObject.getValue(dateOfBirth));

		    return valueObject.getValue(firstName) + ' ' + valueObject.getValue(lastName) + ' (' + valueObject.getValue(sex) + '), DOB: ' + formattedDateOfBirth;
	    };

	    var idFunction = function(valueObject) {
	    	var id = Mojo.$.dss.vector.solutions.PersonView.ID;

		    return valueObject.getValue(id);
	    };

	    var displayFunction = function(valueObject) {
		    var firstName = Mojo.$.dss.vector.solutions.PersonView.FIRSTNAME;
		    var lastName = Mojo.$.dss.vector.solutions.PersonView.LASTNAME;

		    return valueObject.getValue(firstName) + ' ' + valueObject.getValue(lastName);
	    };

	    var searchFunction = Mojo.$.dss.vector.solutions.Person.searchForPerson;

	    var selectEventHandler = function() {
		    MDSS.ElementHandler.hideElement(modalConfig.createLink);
		    MDSS.ElementHandler.showElement(modalConfig.editLink);
		    formConfig.button.disabled=false;
	    };

	    var showCreatePatient = function() {
	    	MDSS.ElementHandler.hideElement(modalConfig.editLink);
	    	MDSS.ElementHandler.showElement(modalConfig.createLink);
	    	formConfig.button.disabled=true;
	    }

	 
	    var search = new MDSS.GenericSearch(searchConfig.searchEl, searchConfig.idEl, listFunction, displayFunction, idFunction, searchFunction, selectEventHandler);

	    YAHOO.util.Event.on(searchConfig.searchEl, 'keyup', search.performSearch, search, search);
	    YAHOO.util.Event.on(searchConfig.searchEl, 'keyup', showCreatePatient, null, null);

	    var modal = new MDSS.PersonModal(modalConfig.modalEl, searchConfig.idEl, modalConfig.calendarEl);

	    showCreatePatient();	  
      }
  }
});