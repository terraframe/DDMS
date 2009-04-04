// geometry
Mojo.dto.AttributeGeometryDTO = Mojo.Class.create();
Mojo.dto.AttributeGeometryDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeGeometryMdDTO(obj.attributeMdDTO);
    }
  }
});

Mojo.dto.AttributeGeometryMdDTO = Mojo.Class.create();
Mojo.dto.AttributeGeometryMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeMdDTO.prototype.initialize.call(this,obj);
  }
});

// lineString
Mojo.dto.AttributeLineStringDTO = Mojo.Class.create();
Mojo.dto.AttributeLineStringDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeGeometryDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeGeometryDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeLineStringMdDTO(obj.attributeMdDTO);
    }
  }
});

Mojo.dto.AttributeLineStringMdDTO = Mojo.Class.create();
Mojo.dto.AttributeLineStringMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeGeometryMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeGeometryMdDTO.prototype.initialize.call(this,obj);
  }
});

// point
Mojo.dto.AttributePointDTO = Mojo.Class.create();
Mojo.dto.AttributePointDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeGeometryDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeGeometryDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributePointMdDTO(obj.attributeMdDTO);
    }
  }
});

Mojo.dto.AttributePointMdDTO = Mojo.Class.create();
Mojo.dto.AttributePointMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeGeometryMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeGeometryMdDTO.prototype.initialize.call(this,obj);
  }
});

// polygon
Mojo.dto.AttributePolygonDTO = Mojo.Class.create();
Mojo.dto.AttributePolygonDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeGeometryDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeGeometryDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributePolygonMdDTO(obj.attributeMdDTO);
    }
  }
});

Mojo.dto.AttributePolygonMdDTO = Mojo.Class.create();
Mojo.dto.AttributePolygonMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeGeometryMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeGeometryMdDTO.prototype.initialize.call(this,obj);
  }
});


// multiLine
Mojo.dto.AttributeMultiLineDTO = Mojo.Class.create();
Mojo.dto.AttributeMultiLineDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeGeometryDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeGeometryDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeMultiLineMdDTO(obj.attributeMdDTO);
    }
  }
});

Mojo.dto.AttributeMultiLineMdDTO = Mojo.Class.create();
Mojo.dto.AttributeMultiLineMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeGeometryMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeGeometryMdDTO.prototype.initialize.call(this,obj);
  }
});

// multiPoint
Mojo.dto.AttributeMultiPointDTO = Mojo.Class.create();
Mojo.dto.AttributeMultiPointDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeGeometryDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeGeometryDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeMultiPointMdDTO(obj.attributeMdDTO);
    }
  }
});

Mojo.dto.AttributeMultiPointMdDTO = Mojo.Class.create();
Mojo.dto.AttributeMultiPointMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeGeometryMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeGeometryMdDTO.prototype.initialize.call(this,obj);
  }
});

// multiPolygon
Mojo.dto.AttributeMultiPolygonDTO = Mojo.Class.create();
Mojo.dto.AttributeMultiPolygonDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeGeometryDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeGeometryDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeMultiPolygonMdDTO(obj.attributeMdDTO);
    }
  }
});

Mojo.dto.AttributeMultiPolygonMdDTO = Mojo.Class.create();
Mojo.dto.AttributeMultiPolygonMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeGeometryMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeGeometryMdDTO.prototype.initialize.call(this,obj);
  }
});