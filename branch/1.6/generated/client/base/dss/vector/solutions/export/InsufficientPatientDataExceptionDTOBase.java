package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 2144798697)
public abstract class InsufficientPatientDataExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.InsufficientPatientDataException";
  private static final long serialVersionUID = 2144798697;
  
  public InsufficientPatientDataExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected InsufficientPatientDataExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public InsufficientPatientDataExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public InsufficientPatientDataExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public InsufficientPatientDataExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public InsufficientPatientDataExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public InsufficientPatientDataExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public InsufficientPatientDataExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DOBLABEL = "dobLabel";
  public static java.lang.String FIRSTNAMELABEL = "firstNameLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFIERLABEL = "identifierLabel";
  public static java.lang.String LASTNAMELABEL = "lastNameLabel";
  public static java.lang.String SEXLABEL = "sexLabel";
  public static java.lang.String TYPELABEL = "typeLabel";
  public String getDobLabel()
  {
    return getValue(DOBLABEL);
  }
  
  public void setDobLabel(String value)
  {
    if(value == null)
    {
      setValue(DOBLABEL, "");
    }
    else
    {
      setValue(DOBLABEL, value);
    }
  }
  
  public boolean isDobLabelWritable()
  {
    return isWritable(DOBLABEL);
  }
  
  public boolean isDobLabelReadable()
  {
    return isReadable(DOBLABEL);
  }
  
  public boolean isDobLabelModified()
  {
    return isModified(DOBLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDobLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DOBLABEL).getAttributeMdDTO();
  }
  
  public String getFirstNameLabel()
  {
    return getValue(FIRSTNAMELABEL);
  }
  
  public void setFirstNameLabel(String value)
  {
    if(value == null)
    {
      setValue(FIRSTNAMELABEL, "");
    }
    else
    {
      setValue(FIRSTNAMELABEL, value);
    }
  }
  
  public boolean isFirstNameLabelWritable()
  {
    return isWritable(FIRSTNAMELABEL);
  }
  
  public boolean isFirstNameLabelReadable()
  {
    return isReadable(FIRSTNAMELABEL);
  }
  
  public boolean isFirstNameLabelModified()
  {
    return isModified(FIRSTNAMELABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFirstNameLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FIRSTNAMELABEL).getAttributeMdDTO();
  }
  
  public String getIdentifierLabel()
  {
    return getValue(IDENTIFIERLABEL);
  }
  
  public void setIdentifierLabel(String value)
  {
    if(value == null)
    {
      setValue(IDENTIFIERLABEL, "");
    }
    else
    {
      setValue(IDENTIFIERLABEL, value);
    }
  }
  
  public boolean isIdentifierLabelWritable()
  {
    return isWritable(IDENTIFIERLABEL);
  }
  
  public boolean isIdentifierLabelReadable()
  {
    return isReadable(IDENTIFIERLABEL);
  }
  
  public boolean isIdentifierLabelModified()
  {
    return isModified(IDENTIFIERLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getIdentifierLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(IDENTIFIERLABEL).getAttributeMdDTO();
  }
  
  public String getLastNameLabel()
  {
    return getValue(LASTNAMELABEL);
  }
  
  public void setLastNameLabel(String value)
  {
    if(value == null)
    {
      setValue(LASTNAMELABEL, "");
    }
    else
    {
      setValue(LASTNAMELABEL, value);
    }
  }
  
  public boolean isLastNameLabelWritable()
  {
    return isWritable(LASTNAMELABEL);
  }
  
  public boolean isLastNameLabelReadable()
  {
    return isReadable(LASTNAMELABEL);
  }
  
  public boolean isLastNameLabelModified()
  {
    return isModified(LASTNAMELABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLastNameLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LASTNAMELABEL).getAttributeMdDTO();
  }
  
  public String getSexLabel()
  {
    return getValue(SEXLABEL);
  }
  
  public void setSexLabel(String value)
  {
    if(value == null)
    {
      setValue(SEXLABEL, "");
    }
    else
    {
      setValue(SEXLABEL, value);
    }
  }
  
  public boolean isSexLabelWritable()
  {
    return isWritable(SEXLABEL);
  }
  
  public boolean isSexLabelReadable()
  {
    return isReadable(SEXLABEL);
  }
  
  public boolean isSexLabelModified()
  {
    return isModified(SEXLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSexLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SEXLABEL).getAttributeMdDTO();
  }
  
  public String getTypeLabel()
  {
    return getValue(TYPELABEL);
  }
  
  public void setTypeLabel(String value)
  {
    if(value == null)
    {
      setValue(TYPELABEL, "");
    }
    else
    {
      setValue(TYPELABEL, value);
    }
  }
  
  public boolean isTypeLabelWritable()
  {
    return isWritable(TYPELABEL);
  }
  
  public boolean isTypeLabelReadable()
  {
    return isReadable(TYPELABEL);
  }
  
  public boolean isTypeLabelModified()
  {
    return isModified(TYPELABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTypeLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TYPELABEL).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{dobLabel}", this.getDobLabel().toString());
    template = template.replace("{firstNameLabel}", this.getFirstNameLabel().toString());
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{identifierLabel}", this.getIdentifierLabel().toString());
    template = template.replace("{lastNameLabel}", this.getLastNameLabel().toString());
    template = template.replace("{sexLabel}", this.getSexLabel().toString());
    template = template.replace("{typeLabel}", this.getTypeLabel().toString());
    
    return template;
  }
  
}
