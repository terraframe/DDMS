/*
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
//define(["../button/Button", "../overlay/Overlay"], function(){
(function(){

var RW = Mojo.Meta.alias(Mojo.RW_PACKAGE + "*");
var UI = Mojo.Meta.alias(Mojo.UI_PACKAGE + "*");

var Checkbox = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'checkbox.CheckBox', {
  
  Extends : RW.Widget,
  
  Instance : {
    
    initialize : function(config)
    {
      config = config || {};
      this._config = config;
      
      var el = this._config.el || "div";
      if (config.data != null) {
        this._data = config.data;
      }
      
      this.$initialize(el, config);
      
      if (config.checked === false || config.checked === true || config.checked === "partial") {
        this.setChecked(config.checked, false);
      }
      
      this.addEventListener("click", Mojo.Util.bind(this, this.onClick));
    },
    
    setData : function(usrData) {
      this._data = usrData;
    },
    
    getData : function() {
      return this._data;
    },
    
    addOnCheckListener : function(fnListener) {
      this.addEventListener(CheckEvent, {handleEvent: fnListener});
    },
    
    isChecked : function() {
      return this.hasClassName("checked");
    },
    
    isPartialChecked : function() {
      return this.hasClassName("partialcheck");
    },
    
    setChecked : function(bool, dispatchEvent) {
      if (bool === "partial") {
        this.removeClassName("checked");
        this.addClassName("partialcheck");
      }
      else {
        this.removeClassName("partialcheck");
        if (bool && !this.isChecked()) {
          this.addClassName("checked");
        }
        else if (!bool && this.isChecked()) {
          this.removeClassName("checked");
        }
      }
      
      if (dispatchEvent !== false) {
        this.dispatchEvent(new CheckEvent(this));
      }
    },
    
    onClick : function() {
      this.setChecked(!this.isChecked());
    },
    
    render : function(parent) {
      this.$render(parent);
    }
  }
  
});

var CheckEvent = Mojo.Meta.newClass(Mojo.JQUERY_PACKAGE+"checkbox.CheckEvent", {
  
  Extends : com.runwaysdk.event.CustomEvent,
  
  Instance : {
    initialize : function(checkBox) {
      this.$initialize();
      this._checkBox = checkBox;
    },
    
    getCheckBox : function() {
      return this._checkBox;
    }
  }
  
});

return Checkbox;

})();
