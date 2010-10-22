package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = 1873023767)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidFemaleQuantityProblem.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class InvalidFemaleQuantityProblemBase extends dss.vector.solutions.NotificationProblem implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.InvalidFemaleQuantityProblem";
  public static java.lang.String QUANTITY = "quantity";
  public static java.lang.String QUANTITYFEMALE = "quantityFemale";
  private static final long serialVersionUID = 1873023767;
  
  public InvalidFemaleQuantityProblemBase()
  {
    super();
  }
  
  public InvalidFemaleQuantityProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public Integer getQuantity()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITY));
  }
  
  public void validateQuantity()
  {
    this.validateAttribute(QUANTITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getQuantityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InvalidFemaleQuantityProblem.CLASS);
    return mdClassIF.definesAttribute(QUANTITY);
  }
  
  public void setQuantity(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITY, "");
    }
    else
    {
      setValue(QUANTITY, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getQuantityFemale()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYFEMALE));
  }
  
  public void validateQuantityFemale()
  {
    this.validateAttribute(QUANTITYFEMALE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getQuantityFemaleMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InvalidFemaleQuantityProblem.CLASS);
    return mdClassIF.definesAttribute(QUANTITYFEMALE);
  }
  
  public void setQuantityFemale(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYFEMALE, "");
    }
    else
    {
      setValue(QUANTITYFEMALE, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{quantity}", this.getQuantity());
    message = replace(message, "{quantityFemale}", this.getQuantityFemale());
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
