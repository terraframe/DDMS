package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -473892935)
public abstract class KnockDownTimePropertyProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.KnockDownTimePropertyProblem";
  private static final long serialVersionUID = -473892935;
  
  public KnockDownTimePropertyProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public KnockDownTimePropertyProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
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
