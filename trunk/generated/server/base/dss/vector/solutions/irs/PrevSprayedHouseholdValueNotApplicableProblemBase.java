package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 2055337855)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PrevSprayedHouseholdValueNotApplicableProblem.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class PrevSprayedHouseholdValueNotApplicableProblemBase extends dss.vector.solutions.irs.ValueNotApplicableProblem implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.PrevSprayedHouseholdValueNotApplicableProblem";
  private static final long serialVersionUID = 2055337855;
  
  public PrevSprayedHouseholdValueNotApplicableProblemBase()
  {
    super();
  }
  
  public PrevSprayedHouseholdValueNotApplicableProblemBase(java.lang.String developerMessage)
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
