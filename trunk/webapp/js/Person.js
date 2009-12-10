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
  
  // the SingleSelectSearch used by all geo input fields
  this._selectSearch = new MDSS.SingleSelectSearch();
      
      this._residentialGeoSearch = null;
      this._workGeoSearch = null;
    },
    
    updateListener : function(params) {
        var request = new MDSS.Request({
        that : this,
            onSuccess: function() {
          this.that.destroyModal();
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
        
        var id = this.recipientIdEl.value;

        this.controller.editRecipient(request, id);
    },
    
    createModal : function (html) {
    
      var executable = MDSS.util.extractScripts(html);
          
      html = MDSS.util.removeScripts(html);    
    
      this.currentModal = new YAHOO.widget.Panel(this._id,  {
         width: '400px',
         height: '550px',
         fixedcenter:true,
         close:false,
         draggable:false,
         zindex:4,
         modal:true,
         visible:true
       });

      this.currentModal.setBody(html);
      this.currentModal.render(document.body);
      this.currentModal.bringToTop();
      
      var calendar = document.getElementById(this.calendarIdEl);
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
      this.currentModal._removeFocusHandlers();
      
      // now attach geo searching to each geo input
      this._residentialGeoSearch = new MDSS.GeoSearch('residentialGeoId', this._selectSearch);
      this._workGeoSearch = new MDSS.GeoSearch('workGeoId', this._selectSearch);
    },
    
    destroyModal : function()
    {
      this.currentModal.destroy();
      this.currentModal = null;
      
      this._residentialGeoSearch.destroy();
      this._workGeoSearch.destroy();
      
      this._residentialGeoSearch = null;
      this._workGeoSearch = null;
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
       modalConfig.createLink.style.display = "none";      
       modalConfig.editLink.style.display = "inline";
       formConfig.button.disabled=false;
     };

     var showCreatePatient = function() {
       modalConfig.editLink.style.display = "none";
       modalConfig.createLink.style.display = "inline";
       formConfig.button.disabled=true;
     }
 
     var search = new MDSS.GenericSearch(searchConfig.searchEl, searchConfig.idEl, listFunction, displayFunction, idFunction, searchFunction, selectEventHandler);
     search.addListener(showCreatePatient);

     var modal = new MDSS.PersonModal(modalConfig.modalEl, searchConfig.idEl, modalConfig.calendarEl);

     if(searchConfig.idEl.value != '') {
       selectEventHandler();
     }
     else {
       showCreatePatient();  
     }
   }
  }
});