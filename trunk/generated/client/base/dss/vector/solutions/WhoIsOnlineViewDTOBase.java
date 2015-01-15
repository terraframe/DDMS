package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = 144698640)
public abstract class WhoIsOnlineViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.WhoIsOnlineView";
  private static final long serialVersionUID = 144698640;
  
  protected WhoIsOnlineViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String LOCALE = "locale";
  public static java.lang.String USERNAME = "username";
  public String getLocale()
  {
    return getValue(LOCALE);
  }
  
  public void setLocale(String value)
  {
    if(value == null)
    {
      setValue(LOCALE, "");
    }
    else
    {
      setValue(LOCALE, value);
    }
  }
  
  public boolean isLocaleWritable()
  {
    return isWritable(LOCALE);
  }
  
  public boolean isLocaleReadable()
  {
    return isReadable(LOCALE);
  }
  
  public boolean isLocaleModified()
  {
    return isModified(LOCALE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLocaleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LOCALE).getAttributeMdDTO();
  }
  
  public String getUsername()
  {
    return getValue(USERNAME);
  }
  
  public void setUsername(String value)
  {
    if(value == null)
    {
      setValue(USERNAME, "");
    }
    else
    {
      setValue(USERNAME, value);
    }
  }
  
  public boolean isUsernameWritable()
  {
    return isWritable(USERNAME);
  }
  
  public boolean isUsernameReadable()
  {
    return isReadable(USERNAME);
  }
  
  public boolean isUsernameModified()
  {
    return isModified(USERNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getUsernameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(USERNAME).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.WhoIsOnlineViewDTO[] getViews(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.WhoIsOnlineViewDTO.CLASS, "getViews", _declaredTypes);
    return (dss.vector.solutions.WhoIsOnlineViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static WhoIsOnlineViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (WhoIsOnlineViewDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createSessionComponent(this);
    }
    else
    {
      getRequest().update(this);
    }
  }
  public void delete()
  {
    getRequest().delete(this.getId());
  }
  
}
