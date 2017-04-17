package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -242595665)
public abstract class PrevSprayedHouseholdValueProblemDTOBase extends dss.vector.solutions.irs.ValueProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.PrevSprayedHouseholdValueProblem";
  private static final long serialVersionUID = -242595665;
  
  public PrevSprayedHouseholdValueProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public PrevSprayedHouseholdValueProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
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
