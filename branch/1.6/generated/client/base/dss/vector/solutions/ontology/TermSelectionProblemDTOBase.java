package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = -918435235)
public abstract class TermSelectionProblemDTOBase extends com.runwaysdk.business.ProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.TermSelectionProblem";
  private static final long serialVersionUID = -918435235;
  
  public TermSelectionProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public TermSelectionProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String TERMID = "termId";
  public String getTermId()
  {
    return getValue(TERMID);
  }
  
  public void setTermId(String value)
  {
    if(value == null)
    {
      setValue(TERMID, "");
    }
    else
    {
      setValue(TERMID, value);
    }
  }
  
  public boolean isTermIdWritable()
  {
    return isWritable(TERMID);
  }
  
  public boolean isTermIdReadable()
  {
    return isReadable(TERMID);
  }
  
  public boolean isTermIdModified()
  {
    return isModified(TERMID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTermIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TERMID).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{termId}", this.getTermId().toString());
    
    return template;
  }
  
}
