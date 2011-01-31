package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 1580112990)
public abstract class OutbreakAlertDTOBase extends com.runwaysdk.business.InformationDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.OutbreakAlert";
  private static final long serialVersionUID = 1580112990;
  
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
  public static java.lang.String MESSAGETEXT = "messageText";
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
  
  public String getMessageText()
  {
    return getValue(MESSAGETEXT);
  }
  
  public void setMessageText(String value)
  {
    if(value == null)
    {
      setValue(MESSAGETEXT, "");
    }
    else
    {
      setValue(MESSAGETEXT, value);
    }
  }
  
  public boolean isMessageTextWritable()
  {
    return isWritable(MESSAGETEXT);
  }
  
  public boolean isMessageTextReadable()
  {
    return isReadable(MESSAGETEXT);
  }
  
  public boolean isMessageTextModified()
  {
    return isModified(MESSAGETEXT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getMessageTextMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(MESSAGETEXT).getAttributeMdDTO();
  }
  
}
