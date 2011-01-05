// geometry
Mojo.Meta.newClass('com.runwaysdk.gis.transport.attributes.AttributeGeometryDTO', {

  Extends : Mojo.$.com.runwaysdk.transport.attributes.AttributeDTO,

  IsAbstract : true,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});


Mojo.Meta.newClass('com.runwaysdk.gis.transport.metadata.AttributeGeometryMdDTO', {

  Extends : Mojo.$.com.runwaysdk.transport.metadata.AttributeMdDTO,

  IsAbstract : true,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

// lineString
Mojo.Meta.newClass('com.runwaysdk.gis.transport.attributes.AttributeLineStringDTO', {

  Extends: Mojo.$.com.runwaysdk.gis.transport.attributes.AttributeGeometryDTO,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass('com.runwaysdk.gis.transport.metadata.AttributeLineStringMdDTO', {

  Extends: Mojo.$.com.runwaysdk.gis.transport.metadata.AttributeGeometryMdDTO,

  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

// point
Mojo.Meta.newClass('com.runwaysdk.gis.transport.attributes.AttributePointDTO', {

  Extends: Mojo.$.com.runwaysdk.gis.transport.attributes.AttributeGeometryDTO,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass('com.runwaysdk.gis.transport.metadata.AttributePointMdDTO', {

  Extends: Mojo.$.com.runwaysdk.gis.transport.metadata.AttributeGeometryMdDTO,

  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

// polygon
Mojo.Meta.newClass('com.runwaysdk.gis.transport.attributes.AttributePolygonDTO', {

  Extends: Mojo.$.com.runwaysdk.gis.transport.attributes.AttributeGeometryDTO,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass('com.runwaysdk.gis.transport.metadata.AttributePolygonMdDTO', {

  Extends: Mojo.$.com.runwaysdk.gis.transport.metadata.AttributeGeometryMdDTO,

  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});


// multiLine
Mojo.Meta.newClass('com.runwaysdk.gis.transport.attributes.AttributeMultiLineStringDTO', {

  Extends: Mojo.$.com.runwaysdk.gis.transport.attributes.AttributeGeometryDTO,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass('com.runwaysdk.gis.transport.metadata.AttributeMultiLineStringMdDTO', {

  Extends: Mojo.$.com.runwaysdk.gis.transport.metadata.AttributeGeometryMdDTO,

  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

// multiPoint
Mojo.Meta.newClass('com.runwaysdk.gis.transport.attributes.AttributeMultiPointDTO', {

  Extends: Mojo.$.com.runwaysdk.gis.transport.attributes.AttributeGeometryDTO,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass('com.runwaysdk.gis.transport.metadata.AttributeMultiPointMdDTO', {

  Extends: Mojo.$.com.runwaysdk.gis.transport.metadata.AttributeGeometryMdDTO,

  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

// multiPolygon
Mojo.Meta.newClass('com.runwaysdk.gis.transport.attributes.AttributeMultiPolygonDTO', {

  Extends: Mojo.$.com.runwaysdk.gis.transport.attributes.AttributeGeometryDTO,
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass('com.runwaysdk.gis.transport.metadata.AttributeMultiPolygonMdDTO', {

  Extends: Mojo.$.com.runwaysdk.gis.transport.metadata.AttributeGeometryMdDTO,

  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});