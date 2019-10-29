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
/**
 * RunwaySDK Javascript UI library.
 * 
 * @author Terraframe
 */
//define(["../widget/Widget"], function(){
(function(){

var RW = Mojo.Meta.alias(Mojo.RW_PACKAGE + "*");
var UI = Mojo.Meta.alias(Mojo.UI_PACKAGE + "*");

if (Mojo.Meta.findClass(Mojo.RW_PACKAGE+'Button') != null) {
  return;
}

var Button = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'Button', {
  
  Extends : RW.Widget,
  
  Implements : [UI.ElementProviderIF, UI.ButtonIF],

  Instance : {
    initialize : function(label, handler, config) {
      config = config || {};
      
      this.$initialize("button", {type: "button"}); // FIXME: I don't really think the type is actually working or doing anything here
      this.setInnerHTML(label);
      
      this._config = config;
      
      this.setPrimary(config.primary);
      this.setEnabled(config.enabled);
      
      // Register onclick event listener
      var listener = new com.runwaysdk.event.EventListener({handleEvent : handler});
      this.addEventListener('click', listener);
    },
    
    setPrimary : function(bool)
    {
      this._config.primary = bool;
      if (bool) {
        this.addClassName("btn btn-primary");
      }
      else {
        this.addClassName("btn");
      }
    },
    
    isPrimary : function() {
      return this._config.primary;
    },
    
    setEnabled : function(bool)
    {
      if (bool !== undefined && !bool)
      {
        this.setAttribute("disabled", true);
      }
      else
      {
        this.removeAttribute("disabled");
      }
    },
    
    isEnabled : function()
    {
      return this.hasAttribute("disabled");
    }
  }
});

})();