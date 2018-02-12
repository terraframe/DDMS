#-------------------------------------------------------------------------------
# Copyright (C) 2018 IVCC
# 
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
#-------------------------------------------------------------------------------
/**
 * RunwaySDK Javascript UI library.
 * 
 * @author Terraframe
 */
(function(){

var UI = Mojo.Meta.alias("com.runwaysdk.ui.*");

var Widget = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'Widget', {

  IsAbstract : true,
  
  Extends : UI.WidgetBase,
  
  Implements : UI.ElementProviderIF,
  
  Instance : {
    initialize: function(){
      this.$initialize();
      
      this.getEl().addClassNames( this.getInheritedCSS() );
    },
    getImpl : function(){
      return this;
    },
    getInheritedCSS : function() { // TODO: can this be optimized?
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
        qName = qName.replace(/\./g, "-");
        retArr.push(qName);
      }
      
      return retArr;
    },
    render : function(parent) {
      this.getEl().render(parent);
      this.$render(parent);
    },
    addClassName : function(cn) {
      return this.getEl().addClassName(cn);
    },
    removeClassName : function(cn) {
      return this.getEl().removeClassName(cn);
    }
  }
  
});

})();
