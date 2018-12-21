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
(function(){

  var ClassFramework = Mojo.Meta;
  var Util = Mojo.Util;
  var Widget = com.runwaysdk.ui.factory.runway.Widget;
  
  // In miliseconds
  var POLLING_INTERVAL = 10000;

  var sessionInfoPollerName = 'dss.vector.solutions.SessionInfoPoller';

  /**
   * LANGUAGE
   */
  com.runwaysdk.Localize.defineLanguage(sessionInfoPollerName, {
	"warningDialogTitle"   : "Session Expiring Soon",
	"warningDialogOk"      : "Ok",
	"warningDialogBody"    : "Your session is expiring soon. Press 'Ok' to refresh your session and continue working.",
    "expiredDialogTitle"   : "Session Expired",
    "expiredDialogLogIn"   : "Log In",
    "expiredDialogDismiss" : "Dismiss",
    "expiredDialogBody"    : "Your session has expired. Press 'Log In' to log in again and continue working."
  });
  
  var sessionInfoPoller = ClassFramework.newClass(sessionInfoPollerName, {
	
    Extends : Widget,
    
    Instance : {
      
      initialize : function(config) {
    	this._warningDialog = this.getFactory().newDialog(this.localize("warningDialogTitle"), {modal: true, destroyOnExit: false});
    	
        this.$initialize();
        
        this._config = config || {};
        
        var that = this;
        
        this._warningDialog.appendContent(this.localize("warningDialogBody"));
        this._warningDialog.addButton(this.localize("warningDialogOk"), Mojo.Util.bind(this, this.refreshSession));
        
        this._expiredDialog = this.getFactory().newDialog(this.localize("expiredDialogTitle"), {modal: true});
        this._expiredDialog.appendContent(this.localize("expiredDialogBody"));
        this._expiredDialog.addButton(this.localize("expiredDialogLogIn"), function(){ window.location.href = com.runwaysdk.__applicationContextPath + "/"; });
        this._expiredDialog.addButton(this.localize("expiredDialogDismiss"), function(){that._expiredDialog.close();});
        
        this._hasDisplayedWarning = false;
      },
      
      refreshSession : function() {
    	this._warningDialog.hide();
    	dss.vector.solutions.util.SessionInfoUtil.refreshSession(new MDSS.Request({
          onSuccess : function(data) {
            
          },
          onFailure: function(ex) {
	          
          }
    	}));
      },
      
      render : function(parent) {
        this.$render(parent);
        
        this._warningDialog.render(parent);
        this._warningDialog.hide();
        
        this._expiredDialog.render(parent);
        this._expiredDialog.hide();
        
        var that = this;
        this._pollingRequest = new com.runwaysdk.ui.PollingRequest({
          callback: {
	          onSuccess: function(strJson) {
	        	var data = Mojo.Util.getObject(strJson);
	        	
	        	console.log("Seconds left on user's session: " + data.sessionTimeLeft);
	        	
	            if (data.sessionTimeLength >= 60*10 && data.sessionTimeLeft <= 60*5 && data.sessionTimeLeft >= 0)
	            {
	              that._warningDialog.show();
	            }
	            else if (data.sessionTimeLength > 60 && data.sessionTimeLeft <= 60 && data.sessionTimeLeft >= 0)
	            {
	              that._warningDialog.show();
	            }
	            else if (data.sessionTimeLeft <= 0)
            	{
	              that._warningDialog.hide();
	              that._expiredDialog.show();
	              that._pollingRequest.disable();
            	}
	            else
	            {
	              that._warningDialog.hide();
		          that._expiredDialog.hide();
	            }
	          },
	          onFailure: function(ex) {
	            console.log("SessionInfoFailure");
	            console.log(ex);
	          }
	      },
          performRequest : function(callback) {
            // dss.vector.solutions.util.SessionInfoUtil.getSessionTimeLeft(new Mojo.ClientRequest(callback));
            dss.vector.solutions.util.SessionInfoController.getSessionTimeLeft(new Mojo.ClientRequest(callback));
          },
          pollingInterval : POLLING_INTERVAL
	    });
	    
        this._pollingRequest.enable();
      }
      
    }
  });
  
//  $( document ).ready(function() {
//	com.runwaysdk.ui.Manager.setFactory("JQuery");
//    new dss.vector.solutions.SessionInfoPoller().render();
//  });
  
  setTimeout(function () {
	  var oldFac = com.runwaysdk.ui.Manager.getFactoryName();
	  
	  if (oldFac == null)
	  {
	    com.runwaysdk.ui.Manager.setFactory("YUI3");
	  }
	  
	  new dss.vector.solutions.SessionInfoPoller().render();
  }, 5000);
})();
  