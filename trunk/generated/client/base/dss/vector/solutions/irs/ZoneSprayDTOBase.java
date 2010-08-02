package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -12845599)
public abstract class ZoneSprayDTOBase extends dss.vector.solutions.irs.AbstractSprayDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.ZoneSpray";
  private static final long serialVersionUID = -12845599;
  
  protected ZoneSprayDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ZoneSprayDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BRANDFORINDEX = "brandForIndex";
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String GEOENTITYFORINDEX = "geoEntityForIndex";
  public static java.lang.String SPRAYDATEFORINDEX = "sprayDateForIndex";
  public static java.lang.String SPRAYMETHODFORINDEX = "sprayMethodForIndex";
  public static java.lang.String SUPERVISOR = "supervisor";
  public dss.vector.solutions.irs.InsecticideBrandDTO getBrandForIndex()
  {
    if(getValue(BRANDFORINDEX) == null || getValue(BRANDFORINDEX).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.InsecticideBrandDTO.get(getRequest(), getValue(BRANDFORINDEX));
    }
  }
  
  public void setBrandForIndex(dss.vector.solutions.irs.InsecticideBrandDTO value)
  {
    if(value == null)
    {
      setValue(BRANDFORINDEX, "");
    }
    else
    {
      setValue(BRANDFORINDEX, value.getId());
    }
  }
  
  public boolean isBrandForIndexWritable()
  {
    return isWritable(BRANDFORINDEX);
  }
  
  public boolean isBrandForIndexReadable()
  {
    return isReadable(BRANDFORINDEX);
  }
  
  public boolean isBrandForIndexModified()
  {
    return isModified(BRANDFORINDEX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getBrandForIndexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(BRANDFORINDEX).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.general.DiseaseDTO getDisease()
  {
    if(getValue(DISEASE) == null || getValue(DISEASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.DiseaseDTO.get(getRequest(), getValue(DISEASE));
    }
  }
  
  public void setDisease(dss.vector.solutions.general.DiseaseDTO value)
  {
    if(value == null)
    {
      setValue(DISEASE, "");
    }
    else
    {
      setValue(DISEASE, value.getId());
    }
  }
  
  public boolean isDiseaseWritable()
  {
    return isWritable(DISEASE);
  }
  
  public boolean isDiseaseReadable()
  {
    return isReadable(DISEASE);
  }
  
  public boolean isDiseaseModified()
  {
    return isModified(DISEASE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDiseaseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DISEASE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getGeoEntityForIndex()
  {
    if(getValue(GEOENTITYFORINDEX) == null || getValue(GEOENTITYFORINDEX).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(GEOENTITYFORINDEX));
    }
  }
  
  public void setGeoEntityForIndex(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(GEOENTITYFORINDEX, "");
    }
    else
    {
      setValue(GEOENTITYFORINDEX, value.getId());
    }
  }
  
  public boolean isGeoEntityForIndexWritable()
  {
    return isWritable(GEOENTITYFORINDEX);
  }
  
  public boolean isGeoEntityForIndexReadable()
  {
    return isReadable(GEOENTITYFORINDEX);
  }
  
  public boolean isGeoEntityForIndexModified()
  {
    return isModified(GEOENTITYFORINDEX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getGeoEntityForIndexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITYFORINDEX).getAttributeMdDTO();
  }
  
  public java.util.Date getSprayDateForIndex()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SPRAYDATEFORINDEX));
  }
  
  public void setSprayDateForIndex(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SPRAYDATEFORINDEX, "");
    }
    else
    {
      setValue(SPRAYDATEFORINDEX, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isSprayDateForIndexWritable()
  {
    return isWritable(SPRAYDATEFORINDEX);
  }
  
  public boolean isSprayDateForIndexReadable()
  {
    return isReadable(SPRAYDATEFORINDEX);
  }
  
  public boolean isSprayDateForIndexModified()
  {
    return isModified(SPRAYDATEFORINDEX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getSprayDateForIndexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(SPRAYDATEFORINDEX).getAttributeMdDTO();
  }
  
  public String getSprayMethodForIndex()
  {
    return getValue(SPRAYMETHODFORINDEX);
  }
  
  public void setSprayMethodForIndex(String value)
  {
    if(value == null)
    {
      setValue(SPRAYMETHODFORINDEX, "");
    }
    else
    {
      setValue(SPRAYMETHODFORINDEX, value);
    }
  }
  
  public boolean isSprayMethodForIndexWritable()
  {
    return isWritable(SPRAYMETHODFORINDEX);
  }
  
  public boolean isSprayMethodForIndexReadable()
  {
    return isReadable(SPRAYMETHODFORINDEX);
  }
  
  public boolean isSprayMethodForIndexModified()
  {
    return isModified(SPRAYMETHODFORINDEX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSprayMethodForIndexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPRAYMETHODFORINDEX).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.SupervisorDTO getSupervisor()
  {
    if(getValue(SUPERVISOR) == null || getValue(SUPERVISOR).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SupervisorDTO.get(getRequest(), getValue(SUPERVISOR));
    }
  }
  
  public void setSupervisor(dss.vector.solutions.irs.SupervisorDTO value)
  {
    if(value == null)
    {
      setValue(SUPERVISOR, "");
    }
    else
    {
      setValue(SUPERVISOR, value.getId());
    }
  }
  
  public boolean isSupervisorWritable()
  {
    return isWritable(SUPERVISOR);
  }
  
  public boolean isSupervisorReadable()
  {
    return isReadable(SUPERVISOR);
  }
  
  public boolean isSupervisorModified()
  {
    return isModified(SUPERVISOR);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSupervisorMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SUPERVISOR).getAttributeMdDTO();
  }
  
  public static final java.io.InputStream exportQueryToCSV(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{queryXML, config, savedSearchId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "exportQueryToCSV", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.io.InputStream exportQueryToExcel(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{queryXML, config, savedSearchId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "exportQueryToExcel", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.TeamSprayStatusViewDTO[] getStatusViews()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "getStatusViews", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayStatusViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.TeamSprayStatusViewDTO[] getStatusViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "getStatusViews", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayStatusViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.ZoneSprayViewDTO getView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.ZoneSprayViewDTO getView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.ZoneSprayViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.ZoneSprayViewDTO lockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.business.ValueQueryDTO queryIRS(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String queryXML, java.lang.String config, java.lang.String sortBy, java.lang.Boolean ascending, java.lang.Integer pageNumber, java.lang.Integer pageSize)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{queryXML, config, sortBy, ascending, pageNumber, pageSize};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "queryIRS", _declaredTypes);
    return (com.runwaysdk.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.ZoneSprayViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.ZoneSprayViewDTO unlockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.business.ValueQueryDTO xmlToValueQuery(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String xml, java.lang.String[] selectedUniversals, java.lang.Boolean includeGeometry)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ljava.lang.String;", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{xml, selectedUniversals, includeGeometry};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "xmlToValueQuery", _declaredTypes);
    return (com.runwaysdk.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.irs.ZoneSprayDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.irs.ZoneSprayDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createBusiness(this);
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
  
  public static dss.vector.solutions.irs.ZoneSprayQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.irs.ZoneSprayQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.irs.ZoneSprayDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.irs.ZoneSprayDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
