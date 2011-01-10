(function(){

var geo = {
  GEO_PACKAGE : 'com.runwaysdk.gis.transport.attributes.',
  MD_PACKAGE : 'com.runwaysdk.gis.transport.metadata.'
};

// geometry
var attrGeo = Mojo.Meta.newClass(geo.GEO_PACKAGE+'AttributeGeometryDTO', {

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',

  IsAbstract : true,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});


var geoMd = Mojo.Meta.newClass(geo.MD_PACKAGE+'AttributeGeometryMdDTO', {

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',

  IsAbstract : true,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

// lineString
Mojo.Meta.newClass(geo.GEO_PACKAGE+'AttributeLineStringDTO', {

  Extends: attrGeo,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass(geo.MD_PACKAGE+'AttributeLineStringMdDTO', {

  Extends: geoMd,

  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

// point
Mojo.Meta.newClass(geo.GEO_PACKAGE+'AttributePointDTO', {

  Extends: attrGeo,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass(geo.MD_PACKAGE+'AttributePointMdDTO', {

  Extends: geoMd,

  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

// polygon
Mojo.Meta.newClass(geo.GEO_PACKAGE+'AttributePolygonDTO', {

  Extends: attrGeo,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass(geo.MD_PACKAGE+'AttributePolygonMdDTO', {

  Extends: geoMd,

  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});


// multiLine
Mojo.Meta.newClass(geo.GEO_PACKAGE+'AttributeMultiLineStringDTO', {

  Extends: attrGeo,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass(geo.MD_PACKAGE+'AttributeMultiLineStringMdDTO', {

  Extends: geoMd,

  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

// multiPoint
Mojo.Meta.newClass(geo.GEO_PACKAGE+'AttributeMultiPointDTO', {

  Extends: attrGeo,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass(geo.MD_PACKAGE+'AttributeMultiPointMdDTO', {

  Extends: geoMd,

  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

// multiPolygon
Mojo.Meta.newClass(geo.GEO_PACKAGE+'AttributeMultiPolygonDTO', {

  Extends: attrGeo,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass(geo.MD_PACKAGE+'AttributeMultiPolygonMdDTO', {

  Extends: geoMd,

  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

})();