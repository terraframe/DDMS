package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 946012005)
public abstract class MalariaSeasonOverlapProblemDTOBase extends com.runwaysdk.business.ProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.MalariaSeasonOverlapProblem";
  private static final long serialVersionUID = 946012005;
  
  public MalariaSeasonOverlapProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public MalariaSeasonOverlapProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String OVERLAP = "overlap";
  public String getOverlap()
  {
    return getValue(OVERLAP);
  }
  
  public void setOverlap(String value)
  {
    if(value == null)
    {
      setValue(OVERLAP, "");
    }
    else
    {
      setValue(OVERLAP, value);
    }
  }
  
  public boolean isOverlapWritable()
  {
    return isWritable(OVERLAP);
  }
  
  public boolean isOverlapReadable()
  {
    return isReadable(OVERLAP);
  }
  
  public boolean isOverlapModified()
  {
    return isModified(OVERLAP);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getOverlapMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(OVERLAP).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{overlap}", this.getOverlap().toString());
    
    return template;
  }
  
}
