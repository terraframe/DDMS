package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -1098394422)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoEntityExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class GeoEntityExcelViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.GeoEntityExcelView";
  public static java.lang.String ACTIVATED = "activated";
  public static java.lang.String ENTITYNAME = "entityName";
  public static java.lang.String GEOID = "geoId";
  public static java.lang.String GEOTYPE = "geoType";
  public static java.lang.String GEOMETRYWKT = "geometryWKT";
  public static java.lang.String ID = "id";
  public static java.lang.String SUBTYPE = "subType";
  private static final long serialVersionUID = -1098394422;
  
  public GeoEntityExcelViewBase()
  {
    super();
  }
  
  public Boolean getActivated()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ACTIVATED));
  }
  
  public void validateActivated()
  {
    this.validateAttribute(ACTIVATED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getActivatedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.GeoEntityExcelView.CLASS);
    return mdClassIF.definesAttribute(ACTIVATED);
  }
  
  public void setActivated(Boolean value)
  {
    if(value == null)
    {
      setValue(ACTIVATED, "");
    }
    else
    {
      setValue(ACTIVATED, java.lang.Boolean.toString(value));
    }
  }
  
  public String getEntityName()
  {
    return getValue(ENTITYNAME);
  }
  
  public void validateEntityName()
  {
    this.validateAttribute(ENTITYNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getEntityNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.GeoEntityExcelView.CLASS);
    return mdClassIF.definesAttribute(ENTITYNAME);
  }
  
  public void setEntityName(String value)
  {
    if(value == null)
    {
      setValue(ENTITYNAME, "");
    }
    else
    {
      setValue(ENTITYNAME, value);
    }
  }
  
  public String getGeoId()
  {
    return getValue(GEOID);
  }
  
  public void validateGeoId()
  {
    this.validateAttribute(GEOID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getGeoIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.GeoEntityExcelView.CLASS);
    return mdClassIF.definesAttribute(GEOID);
  }
  
  public void setGeoId(String value)
  {
    if(value == null)
    {
      setValue(GEOID, "");
    }
    else
    {
      setValue(GEOID, value);
    }
  }
  
  public String getGeoType()
  {
    return getValue(GEOTYPE);
  }
  
  public void validateGeoType()
  {
    this.validateAttribute(GEOTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getGeoTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.GeoEntityExcelView.CLASS);
    return mdClassIF.definesAttribute(GEOTYPE);
  }
  
  public void setGeoType(String value)
  {
    if(value == null)
    {
      setValue(GEOTYPE, "");
    }
    else
    {
      setValue(GEOTYPE, value);
    }
  }
  
  public String getGeometryWKT()
  {
    return getValue(GEOMETRYWKT);
  }
  
  public void validateGeometryWKT()
  {
    this.validateAttribute(GEOMETRYWKT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getGeometryWKTMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.GeoEntityExcelView.CLASS);
    return mdClassIF.definesAttribute(GEOMETRYWKT);
  }
  
  public void setGeometryWKT(String value)
  {
    if(value == null)
    {
      setValue(GEOMETRYWKT, "");
    }
    else
    {
      setValue(GEOMETRYWKT, value);
    }
  }
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.GeoEntityExcelView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getSubType()
  {
    return getValue(SUBTYPE);
  }
  
  public void validateSubType()
  {
    this.validateAttribute(SUBTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSubTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.GeoEntityExcelView.CLASS);
    return mdClassIF.definesAttribute(SUBTYPE);
  }
  
  public void setSubType(String value)
  {
    if(value == null)
    {
      setValue(SUBTYPE, "");
    }
    else
    {
      setValue(SUBTYPE, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static GeoEntityExcelView get(String id)
  {
    return (GeoEntityExcelView) com.runwaysdk.business.View.get(id);
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
