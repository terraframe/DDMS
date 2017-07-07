
(function(){

  Mojo.Meta.newClass('dss.vector.solutions.generator.WebIndicatorPlugin$WebIndicator', {
    Extends : Mojo.FORM_PACKAGE.FIELD + 'WebAttribute',
    Instance : {
      initialize : function(obj){
        this.$initialize(obj);
      },
      accept : function(visitor){
        visitor.visitDouble(this);
      }
    }
  });

  Mojo.Meta.newClass('dss.vector.solutions.generator.WebIndicatorPlugin$WebIndicatorMd', {
    Extends : Mojo.FORM_PACKAGE.METADATA + 'WebAttributeMd',
    Instance : {
      initialize : function(obj){
        this.$initialize(obj);
      }
    }
  });

  //Indicator
  Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeIndicatorDTO', {

    Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',

    Instance : {
      initialize : function(obj) {
        this.$initialize(obj);
      }
    }
  });
  
  Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeIndicatorMdDTO', {

    Extends : Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',
    
    Instance : {
    
      initialize : function(obj)
      {
        this.$initialize(obj);
      }
    }
  });
  

})();
