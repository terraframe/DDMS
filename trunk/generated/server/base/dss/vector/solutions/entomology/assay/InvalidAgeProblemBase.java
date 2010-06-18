package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = 2022531118)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidAgeProblem.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class InvalidAgeProblemBase extends dss.vector.solutions.NotificationProblem implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.InvalidAgeProblem";
  public static java.lang.String AGE = "age";
  private static final long serialVersionUID = 2022531118;
  
  public InvalidAgeProblemBase()
  {
    super();
  }
  
  public InvalidAgeProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public Integer getAge()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AGE));
  }
  
  public void validateAge()
  {
    this.validateAttribute(AGE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getAgeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.InvalidAgeProblem.CLASS);
    return mdClassIF.definesAttribute(AGE);
  }
  
  public void setAge(Integer value)
  {
    if(value == null)
    {
      setValue(AGE, "");
    }
    else
    {
      setValue(AGE, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{age}", this.getAge());
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
