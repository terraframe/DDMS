package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = 1230365134)
public abstract class EpiConfigurationDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.EpiConfiguration";
  private static final long serialVersionUID = 1230365134;
  
  protected EpiConfigurationDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFirstDayMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FIRSTDAY).getAttributeMdDTO();
  }
  
  public static EpiConfigurationDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
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
