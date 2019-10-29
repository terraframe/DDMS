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
//define(["../button/Button", "../overlay/Overlay"], function(){
(function(){

var RW = Mojo.Meta.alias(Mojo.RW_PACKAGE + "*");
var UI = Mojo.Meta.alias(Mojo.UI_PACKAGE + "*");

if (Mojo.Meta.findClass(Mojo.RW_PACKAGE + "dialog.Dialog") != null) {
  return;
}

var Dialog = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'dialog.Dialog', {
  
  Extends : RW.Overlay,
  
  /**
   * FIXME
   * Implement using buttonIF
   */
  //Implements : UI.DialogIF,
  
  Instance : {
    
    initialize : function(title, config)
    {
      var dialog = this;
      config = config || {};
      
      config.visible = config.visible || false;
      this.setVisible(config.visible);
      
      this.$initialize(null, config.modal, config.blackout === true ? 1 : null);
      
      // Outermost Div
      this._outerDiv = this.getEl();
      
      // Title Bar
      this._divTitle = this.getFactory().newElement("div", null, config.titleStyle);
      this.getFactory().makeDraggable(this._divTitle, {dragHandle: this});
      this._divTitle.setInnerHTML(title || "Dialog"); // Set title label text the easy way
      this._divTitle.addClassName("title");
      this.appendChild(this._divTitle);
      
      // Close Button (X)
      if (config.closable !== false)
      {
        this._bClose = this.getFactory().newButton("X", function() {dialog.close()});
        this._bClose.addClassName("closeButton");
        this._divTitle.appendChild(this._bClose);
      }
      
      // Content
      this._divContent = this.getFactory().newElement("div");
      this._divContent.addClassName("content");
      this.appendChild(this._divContent);
      
      // Div for buttons
      this._divButtons = this.getFactory().newElement("div");
      this._divButtons.addClassName("buttons");
      this.appendChild(this._divButtons);
      
      this._buttons = new com.runwaysdk.structure.HashSet();
      
      this.setWidth(config.width || "400px");
//    this.setHeight(config.height || "300px");
    },
    getTitleDiv : function() {
      return this._divTitle;
    },
    getContentDiv : function() {
      return this._divContent;
    },
    setTitleStyle : function(k,v)
    {
      this._divTitle.setStyle(k,v);
    },
    setTitle : function(html)
    {
      this._divTitle.setInnerHTML(html);
    },
    appendContent : function(HtmlElement_Or_Text)
    {
      if (Mojo.Util.isString(HtmlElement_Or_Text)) {
        this._divContent.appendInnerHTML(HtmlElement_Or_Text);
      }
      else {
        this._divContent.appendChild(HtmlElement_Or_Text);
      }
    },
    addButton : function(label, handler, context)
    {
      var buttonIF;
      if (com.runwaysdk.ui.ButtonIF.getMetaClass().isInstance(label)) {
        buttonIF = label;
      }
      else {
        buttonIF = this.getFactory().newButton(label, handler, context);
      }
      
      this._buttons.add(buttonIF);
      this._divButtons.appendChild(buttonIF.getEl());
      
      UI.DOMFacade.addClassName(buttonIF, "button");
      
      return buttonIF;
    }
  }
  
});

})();
