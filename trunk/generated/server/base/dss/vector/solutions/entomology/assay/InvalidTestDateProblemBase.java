package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = 1068202661)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidTestDateProblem.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class InvalidTestDateProblemBase extends dss.vector.solutions.NotificationProblem implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.InvalidTestDateProblem";
  public static java.lang.String COLLECTIONDATE = "collectionDate";
  public static java.lang.String TESTDATE = "testDate";
  private static final long serialVersionUID = 1068202661;
  
  public InvalidTestDateProblemBase()
  {
    super();
  }
  
  public InvalidTestDateProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public java.util.Date getCollectionDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(COLLECTIONDATE));
  }
  
  public void validateCollectionDate()
  {
    this.validateAttribute(COLLECTIONDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCollectionDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.InvalidTestDateProblem.CLASS);
    return mdClassIF.definesAttribute(COLLECTIONDATE);
  }
  
  public void setCollectionDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(COLLECTIONDATE, "");
    }
    else
    {
      setValue(COLLECTIONDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public java.util.Date getTestDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(TESTDATE));
  }
  
  public void validateTestDate()
  {
    this.validateAttribute(TESTDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTestDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.InvalidTestDateProblem.CLASS);
    return mdClassIF.definesAttribute(TESTDATE);
  }
  
  public void setTestDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(TESTDATE, "");
    }
    else
    {
      setValue(TESTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{collectionDate}", this.getCollectionDate());
    message = replace(message, "{testDate}", this.getTestDate());
    return message;
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
