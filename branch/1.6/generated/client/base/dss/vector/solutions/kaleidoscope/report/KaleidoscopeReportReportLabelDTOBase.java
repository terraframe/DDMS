package dss.vector.solutions.kaleidoscope.report;

@com.runwaysdk.business.ClassSignature(hash = -53107361)
public abstract class KaleidoscopeReportReportLabelDTOBase extends com.runwaysdk.business.LocalStructDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportReportLabel";
  private static final long serialVersionUID = -53107361;
  
  protected KaleidoscopeReportReportLabelDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given StructDTO into a new DTO.
  * 
  * @param structDTO The StructDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected KaleidoscopeReportReportLabelDTOBase(com.runwaysdk.business.LocalStructDTO structDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(structDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DENGUE_DEFAULTLOCALE = "dENGUE_defaultLocale";
  public static java.lang.String DEFAULTLOCALE = "defaultLocale";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String MALARIA_DEFAULTLOCALE = "mALARIA_defaultLocale";
  public static java.lang.String SITEMASTER = "siteMaster";
  public String getKeyName()
  {
    return getValue(KEYNAME);
  }
  
  public void setKeyName(String value)
  {
    if(value == null)
    {
      setValue(KEYNAME, "");
    }
    else
    {
      setValue(KEYNAME, value);
    }
  }
  
  public boolean isKeyNameWritable()
  {
    return isWritable(KEYNAME);
  }
  
  public boolean isKeyNameReadable()
  {
    return isReadable(KEYNAME);
  }
  
  public boolean isKeyNameModified()
  {
    return isModified(KEYNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getKeyNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(KEYNAME).getAttributeMdDTO();
  }
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public boolean isSiteMasterWritable()
  {
    return isWritable(SITEMASTER);
  }
  
  public boolean isSiteMasterReadable()
  {
    return isReadable(SITEMASTER);
  }
  
  public boolean isSiteMasterModified()
  {
    return isModified(SITEMASTER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSiteMasterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SITEMASTER).getAttributeMdDTO();
  }
  
  public static KaleidoscopeReportReportLabelDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (KaleidoscopeReportReportLabelDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createStruct(this);
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
  
  public static dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportReportLabelQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportReportLabelQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportReportLabelDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
}
