package dss.vector.solutions.form;

@com.runwaysdk.business.ClassSignature(hash = 1892083182)
public abstract class HouseholdMismatchExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.form.HouseholdMismatchException";
  private static final long serialVersionUID = 1892083182;
  
  public HouseholdMismatchExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected HouseholdMismatchExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public HouseholdMismatchExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public HouseholdMismatchExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public HouseholdMismatchExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public HouseholdMismatchExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public HouseholdMismatchExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public HouseholdMismatchExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String HOUSEHOLDID = "householdId";
  public static java.lang.String ID = "id";
  public static java.lang.String NETID = "netId";
  public static java.lang.String PERSONID = "personId";
  public String getHouseholdId()
  {
    return getValue(HOUSEHOLDID);
  }
  
  public void setHouseholdId(String value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDID, "");
    }
    else
    {
      setValue(HOUSEHOLDID, value);
    }
  }
  
  public boolean isHouseholdIdWritable()
  {
    return isWritable(HOUSEHOLDID);
  }
  
  public boolean isHouseholdIdReadable()
  {
    return isReadable(HOUSEHOLDID);
  }
  
  public boolean isHouseholdIdModified()
  {
    return isModified(HOUSEHOLDID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getHouseholdIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(HOUSEHOLDID).getAttributeMdDTO();
  }
  
  public String getNetId()
  {
    return getValue(NETID);
  }
  
  public void setNetId(String value)
  {
    if(value == null)
    {
      setValue(NETID, "");
    }
    else
    {
      setValue(NETID, value);
    }
  }
  
  public boolean isNetIdWritable()
  {
    return isWritable(NETID);
  }
  
  public boolean isNetIdReadable()
  {
    return isReadable(NETID);
  }
  
  public boolean isNetIdModified()
  {
    return isModified(NETID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getNetIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(NETID).getAttributeMdDTO();
  }
  
  public String getPersonId()
  {
    return getValue(PERSONID);
  }
  
  public void setPersonId(String value)
  {
    if(value == null)
    {
      setValue(PERSONID, "");
    }
    else
    {
      setValue(PERSONID, value);
    }
  }
  
  public boolean isPersonIdWritable()
  {
    return isWritable(PERSONID);
  }
  
  public boolean isPersonIdReadable()
  {
    return isReadable(PERSONID);
  }
  
  public boolean isPersonIdModified()
  {
    return isModified(PERSONID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getPersonIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(PERSONID).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{householdId}", this.getHouseholdId().toString());
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{netId}", this.getNetId().toString());
    template = template.replace("{personId}", this.getPersonId().toString());
    
    return template;
  }
  
}
