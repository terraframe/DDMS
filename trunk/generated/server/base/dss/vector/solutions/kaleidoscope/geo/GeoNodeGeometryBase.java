package dss.vector.solutions.kaleidoscope.geo;

@com.runwaysdk.business.ClassSignature(hash = 2016780539)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoNodeGeometry.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class GeoNodeGeometryBase extends dss.vector.solutions.kaleidoscope.geo.GeoNode implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.geo.GeoNodeGeometry";
  public static java.lang.String DISPLAYLABELATTRIBUTE = "displayLabelAttribute";
  public static java.lang.String GEOMETRYATTRIBUTE = "geometryAttribute";
  public static java.lang.String IDENTIFIERATTRIBUTE = "identifierAttribute";
  public static java.lang.String MULTIPOLYGONATTRIBUTE = "multiPolygonAttribute";
  public static java.lang.String POINTATTRIBUTE = "pointAttribute";
  private static final long serialVersionUID = 2016780539;
  
  public GeoNodeGeometryBase()
  {
    super();
  }
  
  public com.runwaysdk.system.metadata.MdAttribute getDisplayLabelAttribute()
  {
    if (getValue(DISPLAYLABELATTRIBUTE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdAttribute.get(getValue(DISPLAYLABELATTRIBUTE));
    }
  }
  
  public String getDisplayLabelAttributeId()
  {
    return getValue(DISPLAYLABELATTRIBUTE);
  }
  
  public void validateDisplayLabelAttribute()
  {
    this.validateAttribute(DISPLAYLABELATTRIBUTE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getDisplayLabelAttributeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.geo.GeoNodeGeometry.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(DISPLAYLABELATTRIBUTE);
  }
  
  public void setDisplayLabelAttribute(com.runwaysdk.system.metadata.MdAttribute value)
  {
    if(value == null)
    {
      setValue(DISPLAYLABELATTRIBUTE, "");
    }
    else
    {
      setValue(DISPLAYLABELATTRIBUTE, value.getId());
    }
  }
  
  public com.runwaysdk.system.metadata.MdAttribute getGeometryAttribute()
  {
    if (getValue(GEOMETRYATTRIBUTE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdAttribute.get(getValue(GEOMETRYATTRIBUTE));
    }
  }
  
  public String getGeometryAttributeId()
  {
    return getValue(GEOMETRYATTRIBUTE);
  }
  
  public void validateGeometryAttribute()
  {
    this.validateAttribute(GEOMETRYATTRIBUTE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getGeometryAttributeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.geo.GeoNodeGeometry.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(GEOMETRYATTRIBUTE);
  }
  
  public void setGeometryAttribute(com.runwaysdk.system.metadata.MdAttribute value)
  {
    if(value == null)
    {
      setValue(GEOMETRYATTRIBUTE, "");
    }
    else
    {
      setValue(GEOMETRYATTRIBUTE, value.getId());
    }
  }
  
  public com.runwaysdk.system.metadata.MdAttribute getIdentifierAttribute()
  {
    if (getValue(IDENTIFIERATTRIBUTE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdAttribute.get(getValue(IDENTIFIERATTRIBUTE));
    }
  }
  
  public String getIdentifierAttributeId()
  {
    return getValue(IDENTIFIERATTRIBUTE);
  }
  
  public void validateIdentifierAttribute()
  {
    this.validateAttribute(IDENTIFIERATTRIBUTE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getIdentifierAttributeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.geo.GeoNodeGeometry.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(IDENTIFIERATTRIBUTE);
  }
  
  public void setIdentifierAttribute(com.runwaysdk.system.metadata.MdAttribute value)
  {
    if(value == null)
    {
      setValue(IDENTIFIERATTRIBUTE, "");
    }
    else
    {
      setValue(IDENTIFIERATTRIBUTE, value.getId());
    }
  }
  
  public com.runwaysdk.system.gis.metadata.MdAttributeMultiPolygon getMultiPolygonAttribute()
  {
    if (getValue(MULTIPOLYGONATTRIBUTE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.gis.metadata.MdAttributeMultiPolygon.get(getValue(MULTIPOLYGONATTRIBUTE));
    }
  }
  
  public String getMultiPolygonAttributeId()
  {
    return getValue(MULTIPOLYGONATTRIBUTE);
  }
  
  public void validateMultiPolygonAttribute()
  {
    this.validateAttribute(MULTIPOLYGONATTRIBUTE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getMultiPolygonAttributeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.geo.GeoNodeGeometry.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(MULTIPOLYGONATTRIBUTE);
  }
  
  public void setMultiPolygonAttribute(com.runwaysdk.system.gis.metadata.MdAttributeMultiPolygon value)
  {
    if(value == null)
    {
      setValue(MULTIPOLYGONATTRIBUTE, "");
    }
    else
    {
      setValue(MULTIPOLYGONATTRIBUTE, value.getId());
    }
  }
  
  public com.runwaysdk.system.gis.metadata.MdAttributePoint getPointAttribute()
  {
    if (getValue(POINTATTRIBUTE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.gis.metadata.MdAttributePoint.get(getValue(POINTATTRIBUTE));
    }
  }
  
  public String getPointAttributeId()
  {
    return getValue(POINTATTRIBUTE);
  }
  
  public void validatePointAttribute()
  {
    this.validateAttribute(POINTATTRIBUTE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getPointAttributeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.geo.GeoNodeGeometry.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(POINTATTRIBUTE);
  }
  
  public void setPointAttribute(com.runwaysdk.system.gis.metadata.MdAttributePoint value)
  {
    if(value == null)
    {
      setValue(POINTATTRIBUTE, "");
    }
    else
    {
      setValue(POINTATTRIBUTE, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static GeoNodeGeometryQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    GeoNodeGeometryQuery query = new GeoNodeGeometryQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static GeoNodeGeometry get(String id)
  {
    return (GeoNodeGeometry) com.runwaysdk.business.Business.get(id);
  }
  
  public static GeoNodeGeometry getByKey(String key)
  {
    return (GeoNodeGeometry) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static GeoNodeGeometry lock(java.lang.String id)
  {
    GeoNodeGeometry _instance = GeoNodeGeometry.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static GeoNodeGeometry unlock(java.lang.String id)
  {
    GeoNodeGeometry _instance = GeoNodeGeometry.get(id);
    _instance.unlock();
    
    return _instance;
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
