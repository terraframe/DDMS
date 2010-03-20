package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 633357160)
public abstract class AreaStandardsViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.AreaStandardsView";
  private static final long serialVersionUID = 633357160;
  
  protected AreaStandardsViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AREASTANDARDSID = "areaStandardsId";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String HOUSEHOLD = "household";
  public static java.lang.String ID = "id";
  public static java.lang.String ROOM = "room";
  public static java.lang.String STARTDATE = "startDate";
  public static java.lang.String STRUCTUREAREA = "structureArea";
  public static java.lang.String TARGETUNIT = "targetUnit";
  public static java.lang.String UNITNOZZLEAREACOVERAGE = "unitNozzleAreaCoverage";
  public String getAreaStandardsId()
  {
    return getValue(AREASTANDARDSID);
  }
  
  public void setAreaStandardsId(String value)
  {
    if(value == null)
    {
      setValue(AREASTANDARDSID, "");
    }
    else
    {
      setValue(AREASTANDARDSID, value);
    }
  }
  
  public boolean isAreaStandardsIdWritable()
  {
    return isWritable(AREASTANDARDSID);
  }
  
  public boolean isAreaStandardsIdReadable()
  {
    return isReadable(AREASTANDARDSID);
  }
  
  public boolean isAreaStandardsIdModified()
  {
    return isModified(AREASTANDARDSID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAreaStandardsIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(AREASTANDARDSID).getAttributeMdDTO();
  }
  
  public java.util.Date getEndDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ENDDATE));
  }
  
  public void setEndDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(ENDDATE, "");
    }
    else
    {
      setValue(ENDDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isEndDateWritable()
  {
    return isWritable(ENDDATE);
  }
  
  public boolean isEndDateReadable()
  {
    return isReadable(ENDDATE);
  }
  
  public boolean isEndDateModified()
  {
    return isModified(ENDDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getEndDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(ENDDATE).getAttributeMdDTO();
  }
  
  public Float getHousehold()
  {
    return com.runwaysdk.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(HOUSEHOLD));
  }
  
  public void setHousehold(Float value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLD, "");
    }
    else
    {
      setValue(HOUSEHOLD, java.lang.Float.toString(value));
    }
  }
  
  public boolean isHouseholdWritable()
  {
    return isWritable(HOUSEHOLD);
  }
  
  public boolean isHouseholdReadable()
  {
    return isReadable(HOUSEHOLD);
  }
  
  public boolean isHouseholdModified()
  {
    return isModified(HOUSEHOLD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getHouseholdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(HOUSEHOLD).getAttributeMdDTO();
  }
  
  public Float getRoom()
  {
    return com.runwaysdk.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(ROOM));
  }
  
  public void setRoom(Float value)
  {
    if(value == null)
    {
      setValue(ROOM, "");
    }
    else
    {
      setValue(ROOM, java.lang.Float.toString(value));
    }
  }
  
  public boolean isRoomWritable()
  {
    return isWritable(ROOM);
  }
  
  public boolean isRoomReadable()
  {
    return isReadable(ROOM);
  }
  
  public boolean isRoomModified()
  {
    return isModified(ROOM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getRoomMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(ROOM).getAttributeMdDTO();
  }
  
  public java.util.Date getStartDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(STARTDATE));
  }
  
  public void setStartDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(STARTDATE, "");
    }
    else
    {
      setValue(STARTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isStartDateWritable()
  {
    return isWritable(STARTDATE);
  }
  
  public boolean isStartDateReadable()
  {
    return isReadable(STARTDATE);
  }
  
  public boolean isStartDateModified()
  {
    return isModified(STARTDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getStartDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(STARTDATE).getAttributeMdDTO();
  }
  
  public Float getStructureArea()
  {
    return com.runwaysdk.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(STRUCTUREAREA));
  }
  
  public void setStructureArea(Float value)
  {
    if(value == null)
    {
      setValue(STRUCTUREAREA, "");
    }
    else
    {
      setValue(STRUCTUREAREA, java.lang.Float.toString(value));
    }
  }
  
  public boolean isStructureAreaWritable()
  {
    return isWritable(STRUCTUREAREA);
  }
  
  public boolean isStructureAreaReadable()
  {
    return isReadable(STRUCTUREAREA);
  }
  
  public boolean isStructureAreaModified()
  {
    return isModified(STRUCTUREAREA);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getStructureAreaMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(STRUCTUREAREA).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.irs.TargetUnitDTO> getTargetUnit()
  {
    return (java.util.List<dss.vector.solutions.irs.TargetUnitDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.irs.TargetUnitDTO.CLASS, getEnumNames(TARGETUNIT));
  }
  
  public java.util.List<String> getTargetUnitEnumNames()
  {
    return getEnumNames(TARGETUNIT);
  }
  
  public void addTargetUnit(dss.vector.solutions.irs.TargetUnitDTO enumDTO)
  {
    addEnumItem(TARGETUNIT, enumDTO.toString());
  }
  
  public void removeTargetUnit(dss.vector.solutions.irs.TargetUnitDTO enumDTO)
  {
    removeEnumItem(TARGETUNIT, enumDTO.toString());
  }
  
  public void clearTargetUnit()
  {
    clearEnum(TARGETUNIT);
  }
  
  public boolean isTargetUnitWritable()
  {
    return isWritable(TARGETUNIT);
  }
  
  public boolean isTargetUnitReadable()
  {
    return isReadable(TARGETUNIT);
  }
  
  public boolean isTargetUnitModified()
  {
    return isModified(TARGETUNIT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getTargetUnitMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(TARGETUNIT).getAttributeMdDTO();
  }
  
  public Float getUnitNozzleAreaCoverage()
  {
    return com.runwaysdk.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(UNITNOZZLEAREACOVERAGE));
  }
  
  public void setUnitNozzleAreaCoverage(Float value)
  {
    if(value == null)
    {
      setValue(UNITNOZZLEAREACOVERAGE, "");
    }
    else
    {
      setValue(UNITNOZZLEAREACOVERAGE, java.lang.Float.toString(value));
    }
  }
  
  public boolean isUnitNozzleAreaCoverageWritable()
  {
    return isWritable(UNITNOZZLEAREACOVERAGE);
  }
  
  public boolean isUnitNozzleAreaCoverageReadable()
  {
    return isReadable(UNITNOZZLEAREACOVERAGE);
  }
  
  public boolean isUnitNozzleAreaCoverageModified()
  {
    return isModified(UNITNOZZLEAREACOVERAGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getUnitNozzleAreaCoverageMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(UNITNOZZLEAREACOVERAGE).getAttributeMdDTO();
  }
  
  public final void applyClone()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.AreaStandardsViewDTO.CLASS, "applyClone", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyClone(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.AreaStandardsViewDTO.CLASS, "applyClone", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.AreaStandardsViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.AreaStandardsViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.AreaStandardsViewDTO getMostRecent(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.AreaStandardsViewDTO.CLASS, "getMostRecent", _declaredTypes);
    return (dss.vector.solutions.irs.AreaStandardsViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.AreaStandardsViewQueryDTO getPage(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.AreaStandardsViewDTO.CLASS, "getPage", _declaredTypes);
    return (dss.vector.solutions.irs.AreaStandardsViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static AreaStandardsViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (AreaStandardsViewDTO) dto;
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
