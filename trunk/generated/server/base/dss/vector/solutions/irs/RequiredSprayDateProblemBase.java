package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -293674317)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to RequiredSprayDateProblem.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class RequiredSprayDateProblemBase extends com.runwaysdk.business.Problem implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.RequiredSprayDateProblem";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -293674317;
  
  public RequiredSprayDateProblemBase()
  {
    super();
  }
  
  public RequiredSprayDateProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.RequiredSprayDateProblem.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{id}", this.getId());
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
