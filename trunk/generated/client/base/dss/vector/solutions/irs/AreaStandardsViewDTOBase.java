package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -1141244008)
public abstract class AreaStandardsViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.AreaStandardsView";
  private static final long serialVersionUID = -1141244008;
  
  protected AreaStandardsViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AREASTANDARDSID = "areaStandardsId";
  public static java.lang.String HOUSEHOLD = "household";
  public static java.lang.String ID = "id";
  public static java.lang.String ROOM = "room";
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAreaStandardsIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(AREASTANDARDSID).getAttributeMdDTO();
  }
  
  public Float getHousehold()
  {
    return com.terraframe.mojo.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(HOUSEHOLD));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getHouseholdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(HOUSEHOLD).getAttributeMdDTO();
  }
  
  public Float getRoom()
  {
    return com.terraframe.mojo.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(ROOM));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getRoomMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(ROOM).getAttributeMdDTO();
  }
  
  public Float getStructureArea()
  {
    return com.terraframe.mojo.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(STRUCTUREAREA));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getStructureAreaMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(STRUCTUREAREA).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.irs.TargetUnitDTO> getTargetUnit()
  {
    return (java.util.List<dss.vector.solutions.irs.TargetUnitDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.irs.TargetUnitDTO.CLASS, getEnumNames(TARGETUNIT));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getTargetUnitMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(TARGETUNIT).getAttributeMdDTO();
  }
  
  public Float getUnitNozzleAreaCoverage()
  {
    return com.terraframe.mojo.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(UNITNOZZLEAREACOVERAGE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getUnitNozzleAreaCoverageMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(UNITNOZZLEAREACOVERAGE).getAttributeMdDTO();
  }
  
  public final void applyClone()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.AreaStandardsViewDTO.CLASS, "applyClone", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyClone(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.AreaStandardsViewDTO.CLASS, "applyClone", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.AreaStandardsViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.AreaStandardsViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.AreaStandardsViewDTO getMostRecent(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.AreaStandardsViewDTO.CLASS, "getMostRecent", _declaredTypes);
    return (dss.vector.solutions.irs.AreaStandardsViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static AreaStandardsViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
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
