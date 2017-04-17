package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -2142938620)
public abstract class HouseholdValueNotApplicableProblemDTOBase extends dss.vector.solutions.irs.ValueNotApplicableProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.HouseholdValueNotApplicableProblem";
  private static final long serialVersionUID = -2142938620;
  
  public HouseholdValueNotApplicableProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public HouseholdValueNotApplicableProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    
    return template;
  }
  
}
