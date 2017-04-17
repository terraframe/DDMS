package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = -642627546)
public abstract class RequiredServiceDateProblemDTOBase extends com.runwaysdk.business.ProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.RequiredServiceDateProblem";
  private static final long serialVersionUID = -642627546;
  
  public RequiredServiceDateProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public RequiredServiceDateProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    
    return template;
  }
  
}
