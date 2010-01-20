package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -1140742368)
public abstract class ZoneSprayDTOBase extends dss.vector.solutions.irs.AbstractSprayDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.ZoneSpray";
  private static final long serialVersionUID = -1140742368;
  
  protected ZoneSprayDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ZoneSprayDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BRANDFORINDEX = "brandForIndex";
  public static java.lang.String GEOENTITYFORINDEX = "geoEntityForIndex";
  public static java.lang.String SPRAYDATEFORINDEX = "sprayDateForIndex";
  public static java.lang.String SPRAYMETHODFORINDEX = "sprayMethodForIndex";
  public static java.lang.String SPRAYWEEK = "sprayWeek";
  public static java.lang.String SUPERVISOR = "supervisor";
  public static java.lang.String TARGET = "target";
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getBrandForIndexMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(BRANDFORINDEX).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGeoEntityForIndexMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITYFORINDEX).getAttributeMdDTO();
  }
  
  public java.util.Date getSprayDateForIndex()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SPRAYDATEFORINDEX));
  }
  
  public void setSprayDateForIndex(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SPRAYDATEFORINDEX, "");
    }
    else
    {
      setValue(SPRAYDATEFORINDEX, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getSprayDateForIndexMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(SPRAYDATEFORINDEX).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSprayMethodForIndexMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPRAYMETHODFORINDEX).getAttributeMdDTO();
  }
  
  public Integer getSprayWeek()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYWEEK));
  }
  
  public void setSprayWeek(Integer value)
  {
    if(value == null)
    {
      setValue(SPRAYWEEK, "");
    }
    else
    {
      setValue(SPRAYWEEK, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSprayWeekWritable()
  {
    return isWritable(SPRAYWEEK);
  }
  
  public boolean isSprayWeekReadable()
  {
    return isReadable(SPRAYWEEK);
  }
  
  public boolean isSprayWeekModified()
  {
    return isModified(SPRAYWEEK);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getSprayWeekMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SPRAYWEEK).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSupervisorMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SUPERVISOR).getAttributeMdDTO();
  }
  
  public Integer getTarget()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET));
  }
  
  public void setTarget(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET, "");
    }
    else
    {
      setValue(TARGET, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTargetWritable()
  {
    return isWritable(TARGET);
  }
  
  public boolean isTargetReadable()
  {
    return isReadable(TARGET);
  }
  
  public boolean isTargetModified()
  {
    return isModified(TARGET);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTargetMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET).getAttributeMdDTO();
  }
  
  public static final java.io.InputStream exportQueryToCSV(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{queryXML, config, savedSearchId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "exportQueryToCSV", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.io.InputStream exportQueryToExcel(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{queryXML, config, savedSearchId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "exportQueryToExcel", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.TeamSprayStatusViewDTO[] getStatusViews()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "getStatusViews", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayStatusViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.TeamSprayStatusViewDTO[] getStatusViews(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "getStatusViews", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayStatusViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.ZoneSprayViewDTO getView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.ZoneSprayViewDTO getView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.ZoneSprayViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.ZoneSprayViewDTO lockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.terraframe.mojo.business.ValueQueryDTO queryIRS(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String queryXML, java.lang.String config, java.lang.String sortBy, java.lang.Boolean ascending, java.lang.Integer pageNumber, java.lang.Integer pageSize)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{queryXML, config, sortBy, ascending, pageNumber, pageSize};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "queryIRS", _declaredTypes);
    return (com.terraframe.mojo.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.ZoneSprayViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.ZoneSprayViewDTO unlockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.terraframe.mojo.business.ValueQueryDTO xmlToValueQuery(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String xml, java.lang.String[] selectedUniversals, java.lang.Boolean includeGeometry)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ljava.lang.String;", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{xml, selectedUniversals, includeGeometry};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "xmlToValueQuery", _declaredTypes);
    return (com.terraframe.mojo.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.irs.ZoneSprayDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
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
  
  public static dss.vector.solutions.irs.ZoneSprayQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.irs.ZoneSprayQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.irs.ZoneSpray", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.irs.ZoneSprayDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.irs.ZoneSprayDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
