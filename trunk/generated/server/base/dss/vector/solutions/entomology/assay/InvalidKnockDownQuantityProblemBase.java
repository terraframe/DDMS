package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = 1350552493)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidKnockDownQuantityProblem.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class InvalidKnockDownQuantityProblemBase extends dss.vector.solutions.NotificationProblem implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.InvalidKnockDownQuantityProblem";
  public static java.lang.String QUANTITYKNOCKDOWN = "quantityKnockDown";
  public static java.lang.String QUANTITYTESTED = "quantityTested";
  private static final long serialVersionUID = 1350552493;
  
  public InvalidKnockDownQuantityProblemBase()
  {
    super();
  }
  
  public InvalidKnockDownQuantityProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public Integer getQuantityKnockDown()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYKNOCKDOWN));
  }
  
  public void validateQuantityKnockDown()
  {
    this.validateAttribute(QUANTITYKNOCKDOWN);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getQuantityKnockDownMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.InvalidKnockDownQuantityProblem.CLASS);
    return mdClassIF.definesAttribute(QUANTITYKNOCKDOWN);
  }
  
  public void setQuantityKnockDown(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYKNOCKDOWN, "");
    }
    else
    {
      setValue(QUANTITYKNOCKDOWN, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getQuantityTested()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYTESTED));
  }
  
  public void validateQuantityTested()
  {
    this.validateAttribute(QUANTITYTESTED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getQuantityTestedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.InvalidKnockDownQuantityProblem.CLASS);
    return mdClassIF.definesAttribute(QUANTITYTESTED);
  }
  
  public void setQuantityTested(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYTESTED, "");
    }
    else
    {
      setValue(QUANTITYTESTED, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{quantityKnockDown}", this.getQuantityKnockDown());
    message = replace(message, "{quantityTested}", this.getQuantityTested());
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
