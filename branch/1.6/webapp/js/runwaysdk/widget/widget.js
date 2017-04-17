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