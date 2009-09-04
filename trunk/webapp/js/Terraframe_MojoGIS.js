// geometry
Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.attributes.AttributeGeometryDTO', {

  Extends : Mojo.$.com.terraframe.mojo.transport.attributes.AttributeDTO,

  Abstract : true,
  
  Alias: Mojo.$,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});


Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.metadata.AttributeGeometryMdDTO', {

  Extends : Mojo.$.com.terraframe.mojo.transport.metadata.AttributeMdDTO,

  Abstract : true,
  
  Alias: Mojo.$,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

// lineString
Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.attributes.AttributeLineStringDTO', {

  Extends: Mojo.$.com.terraframe.mojo.gis.transport.attributes.AttributeGeometryDTO,
  
  Alias: Mojo.$,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.metadata.AttributeLineStringMdDTO', {

  Extends: Mojo.$.com.terraframe.mojo.gis.transport.metadata.AttributeGeometryMdDTO,

  Alias: Mojo.$,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

// point
Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.attributes.AttributePointDTO', {

  Extends: Mojo.$.com.terraframe.mojo.gis.transport.attributes.AttributeGeometryDTO,
  
  Alias: Mojo.$,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.metadata.AttributePointMdDTO', {

  Extends: Mojo.$.com.terraframe.mojo.gis.transport.metadata.AttributeGeometryMdDTO,

  Alias: Mojo.$,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

// polygon
Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.attributes.AttributePolygonDTO', {

  Extends: Mojo.$.com.terraframe.mojo.gis.transport.attributes.AttributeGeometryDTO,
  
  Alias: Mojo.$,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.metadata.AttributePolygonMdDTO', {

  Extends: Mojo.$.com.terraframe.mojo.gis.transport.metadata.AttributeGeometryMdDTO,

  Alias: Mojo.$,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});


// multiLine
Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.attributes.AttributeMultiLineStringDTO', {

  Extends: Mojo.$.com.terraframe.mojo.gis.transport.attributes.AttributeGeometryDTO,
  
  Alias: Mojo.$,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.metadata.AttributeMultiLineStringMdDTO', {

  Extends: Mojo.$.com.terraframe.mojo.gis.transport.metadata.AttributeGeometryMdDTO,

  Alias: Mojo.$,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

// multiPoint
Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.attributes.AttributeMultiPointDTO', {

  Extends: Mojo.$.com.terraframe.mojo.gis.transport.attributes.AttributeGeometryDTO,
  
  Alias: Mojo.$,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.metadata.AttributeMultiPointMdDTO', {

  Extends: Mojo.$.com.terraframe.mojo.gis.transport.metadata.AttributeGeometryMdDTO,

  Alias: Mojo.$,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

// multiPolygon
Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.attributes.AttributeMultiPolygonDTO', {

  Extends: Mojo.$.com.terraframe.mojo.gis.transport.attributes.AttributeGeometryDTO,
  
  Alias: Mojo.$,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.metadata.AttributeMultiPolygonMdDTO', {

  Extends: Mojo.$.com.terraframe.mojo.gis.transport.metadata.AttributeGeometryMdDTO,

  Alias: Mojo.$,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});