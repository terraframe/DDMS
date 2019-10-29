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

//define(["../runway"], function(){
(function(){

  var UI = Mojo.Meta.alias(Mojo.UI_PACKAGE + "*");
  var RW = Mojo.Meta.alias(Mojo.RW_PACKAGE + "*");
  
  if (Mojo.Meta.findClass(Mojo.RW_PACKAGE + "Widget") != null) {
    return;
  }
  
  var Widget = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'Widget', {
  
    IsAbstract : true,
    
    Extends : RW.HTMLElement,
    
    Instance : {
      initialize: function(el, attributes, styles, id) {
        el = el || "div";
        this.$initialize(el, attributes, styles, id);
        
        this.addClassNames( this.getInheritedCSS() );
      },
      getImpl : function() {
        return this;
      },
      getInheritedCSS : function() {
        var retArr = [];
        var el = this.getEl();
        var meta = this.getMetaClass();
        var widgetMeta = Widget.getMetaClass();
        
        // Prime the loop by doing the first one
        var qName = meta.getQualifiedName();
        qName = qName.replace(/\./g, "-");
        retArr.push(qName);
        
        for (var supMeta = meta; widgetMeta.isSuperClassOf(supMeta); supMeta = supMeta.getSuperClass().getMetaClass())
        {
          qName = supMeta.getQualifiedName();
          
          if (qName != null) {
            qName = qName.replace(/\./g, "-");
            retArr.push(qName);
          }
        }
        
        return retArr;
      },
      toString : function() {
        return "Widget: [" + this.getMetaClass().getQualifiedName() + "] [" + this.getId() + "]";
      }
    }
    
  });
  
  return Widget;

})();