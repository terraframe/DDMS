package dss.vector.solutions.etl.dhis2.response;

@com.runwaysdk.business.ClassSignature(hash = -1475650050)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DHIS2DuplicateAttributeException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DHIS2DuplicateAttributeExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.etl.dhis2.response.DHIS2DuplicateAttributeException";
  public static java.lang.String DHIS2ATTRS = "dhis2Attrs";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -1475650050;
  
  public DHIS2DuplicateAttributeExceptionBase()
  {
    super();
  }
  
  public DHIS2DuplicateAttributeExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DHIS2DuplicateAttributeExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DHIS2DuplicateAttributeExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getDhis2Attrs()
  {
    return getValue(DHIS2ATTRS);
  }
  
  public void validateDhis2Attrs()
  {
    this.validateAttribute(DHIS2ATTRS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getDhis2AttrsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.etl.dhis2.response.DHIS2DuplicateAttributeException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(DHIS2ATTRS);
  }
  
  public void setDhis2Attrs(String value)
  {
    if(value == null)
    {
      setValue(DHIS2ATTRS, "");
    }
    else
    {
      setValue(DHIS2ATTRS, value);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.etl.dhis2.response.DHIS2DuplicateAttributeException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{dhis2Attrs}", this.getDhis2Attrs());
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
