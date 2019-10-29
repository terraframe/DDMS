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
//define(["jquery-ui", "./Factory", "../runway/widget/Widget",], function(){
(function(){

  var RUNWAY_UI = Mojo.Meta.alias("com.runwaysdk.ui.*");
  var RW = Mojo.Meta.alias(Mojo.RW_PACKAGE + "*");
  
  var Dialog = Mojo.Meta.newClass(Mojo.JQUERY_PACKAGE+'Dialog', {
    
    Extends : RW.Widget,
    
    Implements : [RUNWAY_UI.DialogIF, RUNWAY_UI.ButtonProviderIF],
    
    Instance: {
      initialize : function(title, config) {
        config = config || {};
        config.title = title;
        config.buttons = config.buttons || {};
        config.close = Mojo.Util.bind(this, function(){
          if (config.destroyOnExit && !this._isHide) {
            this.destroy();
          }
        });
        if (!Mojo.Util.isFunction(config.open) && config.closable === false)
        {
          if (config.blackout)
          {
            var that =  this;
            
            config.open = function() {
              that.getNotExist(that.existing.titlebar, $(".ui-dialog-titlebar-close")).hide();
              that.getNotExist(that.existing.overlay, $(".ui-widget-overlay")).css("opacity", "1.0").css("background-color", "black").css("background-image", "none").css("z-index", "99999999999999999999");
              that.getNotExist(that.existing.dialog, $(".ui-dialog.ui-widget")).css("z-index", "999999999999999999999");
            };
          }
          else
          {
            config.open = function() { $(".ui-dialog-titlebar-close").hide(); };
          }
        }
        this._config = config;
        
        this.$initialize(config.el);
        
        this._buttons = new com.runwaysdk.structure.HashSet();
      },
      getNotExist : function(olds, recents)
      {
    	  for (var i = 0; i < recents.length; ++i)
          {
            var recent = recents[i];
            
            var didFind = false;
      	    for (var j = 0; j < olds.length; ++j)
            {
      		  var old = olds[j];
      		  
      	      if (old.id === recent.id)
      	      {
      	        didFind = true;
      	        break;
      	      }
            }
      	    
      	    if (!didFind)
            {
      	      return $(recent);
            }
          }
    	  
    	  return recents;
      },
      getImpl : function() {
        return this._impl;
      },
      getHeader : function() {
        throw new com.runwaysdk.Exception("Not implemented");
      },
      getFooter : function() {
        throw new com.runwaysdk.Exception("Not implemented");
      },
      setTitle : function (title) {
        this.getImpl().dialog("option", "title", title);
      },
      appendContent : function(content) {
        if (Mojo.Util.isString(content)) {
          this.appendInnerHTML(content);
        }
        else {
          this.appendChild(content);
        }
      },
      setContent : function(content) {
        if (Mojo.Util.isString(content)) {
          this.setInnerHTML(content);
        }
        else {
          this.removeAllChildren();
          this.appendChild(content);
        }
      },
      addButton: function(label, handler, context) {
        this._config.buttons[label] = handler;
        
        if (this.isRendered()) {
          var buttons = this.getImpl().dialog("option", "buttons"); // getter
          buttons[label] = handler;
          this.getImpl().dialog("option", "buttons", buttons); // setter
        }
      },
      removeButton : function() {
        
      },
      getButton : function() {
        
      },
      getButtons : function()
      {
        return this.getImpl().dialog("option", "buttons");
      },
      close : function() {
        this.destroy();
      },
      show : function() {
        $(this.getImpl()).dialog('open');
      },
      hide : function() {
        this._isHide = true;
        $(this.getImpl()).dialog("close");
        this._isHide = false;
      },
      render : function(parent) {
        this.$render(parent);
        
        this.existing = {};
        this.existing.titlebar = $(".ui-dialog-titlebar-close");
        this.existing.overlay = $(".ui-widget-overlay");
        this.existing.dialog = $(".ui-dialog.ui-widget");
        
        this._impl = $(this.getRawEl()).dialog(this._config);
      },
      destroy : function() {
        var parent = this.getParent();
        
        this.$destroy();
        parent.destroy();
        
        if (this._config.modal) {
          $(".ui-widget-overlay.ui-front").remove();
        }
      }
    }
  });

})();
