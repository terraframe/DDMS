package dss.vector.solutions.etl.dhis2;

@com.runwaysdk.business.ClassSignature(hash = -2036566605)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MaxOneGeoColumnException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class MaxOneGeoColumnExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.etl.dhis2.MaxOneGeoColumnException";
  public static java.lang.String DATASET = "dataset";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -2036566605;
  
  public MaxOneGeoColumnExceptionBase()
  {
    super();
  }
  
  public MaxOneGeoColumnExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public MaxOneGeoColumnExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public MaxOneGeoColumnExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getDataset()
  {
    return getValue(DATASET);
  }
  
  public void validateDataset()
  {
    this.validateAttribute(DATASET);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getDatasetMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.etl.dhis2.MaxOneGeoColumnException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(DATASET);
  }
  
  public void setDataset(String value)
  {
    if(value == null)
    {
      setValue(DATASET, "");
    }
    else
    {
      setValue(DATASET, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.etl.dhis2.MaxOneGeoColumnException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{dataset}", this.getDataset());
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
