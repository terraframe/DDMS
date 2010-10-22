package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = -1401729110)
public abstract class InvalidRelationshipForOntologyExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.InvalidRelationshipForOntologyException";
  private static final long serialVersionUID = -1401729110;
  
  public InvalidRelationshipForOntologyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected InvalidRelationshipForOntologyExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public InvalidRelationshipForOntologyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public InvalidRelationshipForOntologyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public InvalidRelationshipForOntologyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public InvalidRelationshipForOntologyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public InvalidRelationshipForOntologyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public InvalidRelationshipForOntologyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String ONTOLOGY = "ontology";
  public static java.lang.String RELATIONSHIP = "relationship";
  public String getOntology()
  {
    return getValue(ONTOLOGY);
  }
  
  public void setOntology(String value)
  {
    if(value == null)
    {
      setValue(ONTOLOGY, "");
    }
    else
    {
      setValue(ONTOLOGY, value);
    }
  }
  
  public boolean isOntologyWritable()
  {
    return isWritable(ONTOLOGY);
  }
  
  public boolean isOntologyReadable()
  {
    return isReadable(ONTOLOGY);
  }
  
  public boolean isOntologyModified()
  {
    return isModified(ONTOLOGY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getOntologyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ONTOLOGY).getAttributeMdDTO();
  }
  
  public String getRelationship()
  {
    return getValue(RELATIONSHIP);
  }
  
  public void setRelationship(String value)
  {
    if(value == null)
    {
      setValue(RELATIONSHIP, "");
    }
    else
    {
      setValue(RELATIONSHIP, value);
    }
  }
  
  public boolean isRelationshipWritable()
  {
    return isWritable(RELATIONSHIP);
  }
  
  public boolean isRelationshipReadable()
  {
    return isReadable(RELATIONSHIP);
  }
  
  public boolean isRelationshipModified()
  {
    return isModified(RELATIONSHIP);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getRelationshipMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RELATIONSHIP).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{ontology}", this.getOntology().toString());
    template = template.replace("{relationship}", this.getRelationship().toString());
    
    return template;
  }
  
}
