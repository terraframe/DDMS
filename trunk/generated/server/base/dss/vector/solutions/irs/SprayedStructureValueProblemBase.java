package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 1849647438)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SprayedStructureValueProblem.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class SprayedStructureValueProblemBase extends dss.vector.solutions.irs.ValueProblem implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.SprayedStructureValueProblem";
  private static final long serialVersionUID = 1849647438;
  
  public SprayedStructureValueProblemBase()
  {
    super();
  }
  
  public SprayedStructureValueProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
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
