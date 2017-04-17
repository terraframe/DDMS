package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = 476246473)
public abstract class GeoSynonymViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.GeoSynonymView";
  private static final long serialVersionUID = 476246473;
  
  protected GeoSynonymViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String GEOSYNONYMID = "geoSynonymId";
  public static java.lang.String ID = "id";
  public static java.lang.String SYNONYMNAME = "synonymName";
  public String getGeoSynonymId()
  {
    return getValue(GEOSYNONYMID);
  }
  
  public void setGeoSynonymId(String value)
  {
    if(value == null)
    {
      setValue(GEOSYNONYMID, "");
    }
    else
    {
      setValue(GEOSYNONYMID, value);
    }
  }
  
  public boolean isGeoSynonymIdWritable()
  {
    return isWritable(GEOSYNONYMID);
  }
  
  public boolean isGeoSynonymIdReadable()
  {
    return isReadable(GEOSYNONYMID);
  }
  
  public boolean isGeoSynonymIdModified()
  {
    return isModified(GEOSYNONYMID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGeoSynonymIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOSYNONYMID).getAttributeMdDTO();
  }
  
  public String getSynonymName()
  {
    return getValue(SYNONYMNAME);
  }
  
  public void setSynonymName(String value)
  {
    if(value == null)
    {
      setValue(SYNONYMNAME, "");
    }
    else
    {
      setValue(SYNONYMNAME, value);
    }
  }
  
  public boolean isSynonymNameWritable()
  {
    return isWritable(SYNONYMNAME);
  }
  
  public boolean isSynonymNameReadable()
  {
    return isReadable(SYNONYMNAME);
  }
  
  public boolean isSynonymNameModified()
  {
    return isModified(SYNONYMNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSynonymNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SYNONYMNAME).getAttributeMdDTO();
  }
  
  public static GeoSynonymViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (GeoSynonymViewDTO) dto;
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
