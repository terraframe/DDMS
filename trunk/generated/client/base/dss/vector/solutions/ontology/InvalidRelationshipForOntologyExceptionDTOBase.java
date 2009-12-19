package dss.vector.solutions.ontology;

@com.terraframe.mojo.business.ClassSignature(hash = -1243704692)
public abstract class InvalidRelationshipForOntologyExceptionDTOBase extends com.terraframe.mojo.business.SmartExceptionDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.InvalidRelationshipForOntologyException";
  private static final long serialVersionUID = -1243704692;
  
  public InvalidRelationshipForOntologyExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected InvalidRelationshipForOntologyExceptionDTOBase(com.terraframe.mojo.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public InvalidRelationshipForOntologyExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public InvalidRelationshipForOntologyExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public InvalidRelationshipForOntologyExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public InvalidRelationshipForOntologyExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public InvalidRelationshipForOntologyExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public InvalidRelationshipForOntologyExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getOntologyMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ONTOLOGY).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getRelationshipMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RELATIONSHIP).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    if (this.getLocale() != null)
    {
      return this.localize(this.getLocale());
    }
    else
    {
      return this.getExceptionDTO().getLocalizedMessage();
    }
  }
  private java.lang.String localize(java.util.Locale locale)
  {
    try
    {
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.ontology.InvalidRelationshipForOntologyException", locale);
      
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{ontology}", this.getOntology().toString());
      message = message.replace("{relationship}", this.getRelationship().toString());
      
      return message;
    }
    catch (java.io.IOException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
    catch (org.xml.sax.SAXException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
    catch (javax.xml.parsers.ParserConfigurationException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
    catch (com.terraframe.mojo.util.LocalizeException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
  }
  
}
