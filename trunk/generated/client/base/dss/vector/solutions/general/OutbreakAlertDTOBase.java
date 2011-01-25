package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -1346970973)
public abstract class OutbreakAlertDTOBase extends com.runwaysdk.business.InformationDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.OutbreakAlert";
  private static final long serialVersionUID = -1346970973;
  
  public OutbreakAlertDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String EMAILFAILURE = "emailFailure";
  public static java.lang.String ID = "id";
  public static java.lang.String MESSAGE = "message";
  public Boolean getEmailFailure()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(EMAILFAILURE));
  }
  
  public void setEmailFailure(Boolean value)
  {
    if(value == null)
    {
      setValue(EMAILFAILURE, "");
    }
    else
    {
      setValue(EMAILFAILURE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEmailFailureWritable()
  {
    return isWritable(EMAILFAILURE);
  }
  
  public boolean isEmailFailureReadable()
  {
    return isReadable(EMAILFAILURE);
  }
  
  public boolean isEmailFailureModified()
  {
    return isModified(EMAILFAILURE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEmailFailureMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(EMAILFAILURE).getAttributeMdDTO();
  }
  
  public String getMessage()
  {
    return getValue(MESSAGE);
  }
  
  public void setMessage(String value)
  {
    if(value == null)
    {
      setValue(MESSAGE, "");
    }
    else
    {
      setValue(MESSAGE, value);
    }
  }
  
  public boolean isMessageWritable()
  {
    return isWritable(MESSAGE);
  }
  
  public boolean isMessageReadable()
  {
    return isReadable(MESSAGE);
  }
  
  public boolean isMessageModified()
  {
    return isModified(MESSAGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMessageMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MESSAGE).getAttributeMdDTO();
  }
  
}
