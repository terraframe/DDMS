package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = -137209839)
public abstract class SleptUnderNetsProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.SleptUnderNetsProblem";
  private static final long serialVersionUID = -137209839;
  
  public SleptUnderNetsProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public SleptUnderNetsProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
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
