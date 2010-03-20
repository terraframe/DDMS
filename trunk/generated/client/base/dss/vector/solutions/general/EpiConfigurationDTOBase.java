package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -240273202)
public abstract class EpiConfigurationDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.EpiConfiguration";
  private static final long serialVersionUID = -240273202;
  
  protected EpiConfigurationDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String FIRSTDAY = "firstDay";
  public static java.lang.String ID = "id";
  public String getFirstDay()
  {
    return getValue(FIRSTDAY);
  }
  
  public void setFirstDay(String value)
  {
    if(value == null)
    {
      setValue(FIRSTDAY, "");
    }
    else
    {
      setValue(FIRSTDAY, value);
    }
  }
  
  public boolean isFirstDayWritable()
  {
    return isWritable(FIRSTDAY);
  }
  
  public boolean isFirstDayReadable()
  {
    return isReadable(FIRSTDAY);
  }
  
  public boolean isFirstDayModified()
  {
    return isModified(FIRSTDAY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFirstDayMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FIRSTDAY).getAttributeMdDTO();
  }
  
  public static EpiConfigurationDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (EpiConfigurationDTO) dto;
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
