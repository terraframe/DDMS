package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = 1894168053)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoEntityNotUniqueException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class GeoEntityNotUniqueExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.GeoEntityNotUniqueException";
  public static java.lang.String ENTITYLABEL = "entityLabel";
  public static java.lang.String ENTITYTYPE = "entityType";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 1894168053;
  
  public GeoEntityNotUniqueExceptionBase()
  {
    super();
  }
  
  public GeoEntityNotUniqueExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public GeoEntityNotUniqueExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public GeoEntityNotUniqueExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getEntityLabel()
  {
    return getValue(ENTITYLABEL);
  }
  
  public void validateEntityLabel()
  {
    this.validateAttribute(ENTITYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getEntityLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.GeoEntityNotUniqueException.CLASS);
    return mdClassIF.definesAttribute(ENTITYLABEL);
  }
  
  public void setEntityLabel(String value)
  {
    if(value == null)
    {
      setValue(ENTITYLABEL, "");
    }
    else
    {
      setValue(ENTITYLABEL, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.GeoEntityNotUniqueException.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.GeoEntityNotUniqueException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{entityLabel}", this.getEntityLabel());
    message = replace(message, "{entityType}", this.getEntityType());
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
