package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = -735457152)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoTypeMismatchException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class GeoTypeMismatchExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.GeoTypeMismatchException";
  public static java.lang.String GEOID = "geoId";
  public static java.lang.String GEOTYPE = "geoType";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -735457152;
  
  public GeoTypeMismatchExceptionBase()
  {
    super();
  }
  
  public GeoTypeMismatchExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public GeoTypeMismatchExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public GeoTypeMismatchExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoTypeMismatchException.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoTypeMismatchException.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoTypeMismatchException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{geoId}", this.getGeoId());
    message = replace(message, "{geoType}", this.getGeoType());
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}