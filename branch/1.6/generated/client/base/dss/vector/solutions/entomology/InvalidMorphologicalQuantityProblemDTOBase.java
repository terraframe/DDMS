package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -871711887)
public abstract class InvalidMorphologicalQuantityProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.InvalidMorphologicalQuantityProblem";
  private static final long serialVersionUID = -871711887;
  
  public InvalidMorphologicalQuantityProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidMorphologicalQuantityProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
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
