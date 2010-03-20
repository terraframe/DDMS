package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = 1555281983)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoEntityView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class GeoEntityViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.GeoEntityView";
  public static java.lang.String ACTIVATED = "activated";
  public static java.lang.String ENTITYNAME = "entityName";
  public static java.lang.String ENTITYTYPE = "entityType";
  public static java.lang.String GEOENTITYID = "geoEntityId";
  public static java.lang.String GEOID = "geoId";
  public static java.lang.String ID = "id";
  public static java.lang.String MOSUBTYPE = "moSubType";
  public static java.lang.String TYPEDISPLAYLABEL = "typeDisplayLabel";
  private static final long serialVersionUID = 1555281983;
  
  public GeoEntityViewBase()
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoEntityView.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoEntityView.CLASS);
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
  
  public String getEntityType()
  {
    return getValue(ENTITYTYPE);
  }
  
  public void validateEntityType()
  {
    this.validateAttribute(ENTITYTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getEntityTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoEntityView.CLASS);
    return mdClassIF.definesAttribute(ENTITYTYPE);
  }
  
  public void setEntityType(String value)
  {
    if(value == null)
    {
      setValue(ENTITYTYPE, "");
    }
    else
    {
      setValue(ENTITYTYPE, value);
    }
  }
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITYID);
  }
  
  public void validateGeoEntityId()
  {
    this.validateAttribute(GEOENTITYID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getGeoEntityIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoEntityView.CLASS);
    return mdClassIF.definesAttribute(GEOENTITYID);
  }
  
  public void setGeoEntityId(String value)
  {
    if(value == null)
    {
      setValue(GEOENTITYID, "");
    }
    else
    {
      setValue(GEOENTITYID, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoEntityView.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoEntityView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getMoSubType()
  {
    return getValue(MOSUBTYPE);
  }
  
  public void validateMoSubType()
  {
    this.validateAttribute(MOSUBTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getMoSubTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoEntityView.CLASS);
    return mdClassIF.definesAttribute(MOSUBTYPE);
  }
  
  public void setMoSubType(String value)
  {
    if(value == null)
    {
      setValue(MOSUBTYPE, "");
    }
    else
    {
      setValue(MOSUBTYPE, value);
    }
  }
  
  public String getTypeDisplayLabel()
  {
    return getValue(TYPEDISPLAYLABEL);
  }
  
  public void validateTypeDisplayLabel()
  {
    this.validateAttribute(TYPEDISPLAYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTypeDisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoEntityView.CLASS);
    return mdClassIF.definesAttribute(TYPEDISPLAYLABEL);
  }
  
  public void setTypeDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(TYPEDISPLAYLABEL, "");
    }
    else
    {
      setValue(TYPEDISPLAYLABEL, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static GeoEntityView get(String id)
  {
    return (GeoEntityView) com.runwaysdk.business.View.get(id);
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
