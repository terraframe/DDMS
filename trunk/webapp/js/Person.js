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
  }
});