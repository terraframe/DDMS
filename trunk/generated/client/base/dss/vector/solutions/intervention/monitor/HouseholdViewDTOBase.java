package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = 1941796164)
public abstract class HouseholdViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.HouseholdView";
  private static final long serialVersionUID = 1941796164;
  
  protected HouseholdViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String HASBEENSPRAYED = "hasBeenSprayed";
  public static java.lang.String HASHOUSEHOLDNETS = "hasHouseholdNets";
  public static java.lang.String HASWINDOWS = "hasWindows";
  public static java.lang.String HOUSEHOLDNAME = "householdName";
  public static java.lang.String ID = "id";
  public static java.lang.String LASTSPRAYED = "lastSprayed";
  public static java.lang.String NETS = "nets";
  public static java.lang.String PEOPLE = "people";
  public static java.lang.String ROOF = "roof";
  public static java.lang.String ROOFINFO = "roofInfo";
  public static java.lang.String ROOMS = "rooms";
  public static java.lang.String SURVEYPOINT = "surveyPoint";
  public static java.lang.String URBAN = "urban";
  public static java.lang.String WALL = "wall";
  public static java.lang.String WALLINFO = "wallInfo";
  public static java.lang.String WINDOWTYPE = "windowType";
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void setConcreteId(String value)
  {
    if(value == null)
    {
      setValue(CONCRETEID, "");
    }
    else
    {
      setValue(CONCRETEID, value);
    }
  }
  
  public boolean isConcreteIdWritable()
  {
    return isWritable(CONCRETEID);
  }
  
  public boolean isConcreteIdReadable()
  {
    return isReadable(CONCRETEID);
  }
  
  public boolean isConcreteIdModified()
  {
    return isModified(CONCRETEID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getConcreteIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCRETEID).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.ResponseDTO> getHasBeenSprayed()
  {
    return (java.util.List<dss.vector.solutions.ResponseDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.ResponseDTO.CLASS, getEnumNames(HASBEENSPRAYED));
  }
  
  public java.util.List<String> getHasBeenSprayedEnumNames()
  {
    return getEnumNames(HASBEENSPRAYED);
  }
  
  public void addHasBeenSprayed(dss.vector.solutions.ResponseDTO enumDTO)
  {
    addEnumItem(HASBEENSPRAYED, enumDTO.toString());
  }
  
  public void removeHasBeenSprayed(dss.vector.solutions.ResponseDTO enumDTO)
  {
    removeEnumItem(HASBEENSPRAYED, enumDTO.toString());
  }
  
  public void clearHasBeenSprayed()
  {
    clearEnum(HASBEENSPRAYED);
  }
  
  public boolean isHasBeenSprayedWritable()
  {
    return isWritable(HASBEENSPRAYED);
  }
  
  public boolean isHasBeenSprayedReadable()
  {
    return isReadable(HASBEENSPRAYED);
  }
  
  public boolean isHasBeenSprayedModified()
  {
    return isModified(HASBEENSPRAYED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getHasBeenSprayedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(HASBEENSPRAYED).getAttributeMdDTO();
  }
  
  public Boolean getHasHouseholdNets()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(HASHOUSEHOLDNETS));
  }
  
  public void setHasHouseholdNets(Boolean value)
  {
    if(value == null)
    {
      setValue(HASHOUSEHOLDNETS, "");
    }
    else
    {
      setValue(HASHOUSEHOLDNETS, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isHasHouseholdNetsWritable()
  {
    return isWritable(HASHOUSEHOLDNETS);
  }
  
  public boolean isHasHouseholdNetsReadable()
  {
    return isReadable(HASHOUSEHOLDNETS);
  }
  
  public boolean isHasHouseholdNetsModified()
  {
    return isModified(HASHOUSEHOLDNETS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getHasHouseholdNetsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(HASHOUSEHOLDNETS).getAttributeMdDTO();
  }
  
  public Boolean getHasWindows()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(HASWINDOWS));
  }
  
  public void setHasWindows(Boolean value)
  {
    if(value == null)
    {
      setValue(HASWINDOWS, "");
    }
    else
    {
      setValue(HASWINDOWS, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isHasWindowsWritable()
  {
    return isWritable(HASWINDOWS);
  }
  
  public boolean isHasWindowsReadable()
  {
    return isReadable(HASWINDOWS);
  }
  
  public boolean isHasWindowsModified()
  {
    return isModified(HASWINDOWS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getHasWindowsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(HASWINDOWS).getAttributeMdDTO();
  }
  
  public String getHouseholdName()
  {
    return getValue(HOUSEHOLDNAME);
  }
  
  public void setHouseholdName(String value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDNAME, "");
    }
    else
    {
      setValue(HOUSEHOLDNAME, value);
    }
  }
  
  public boolean isHouseholdNameWritable()
  {
    return isWritable(HOUSEHOLDNAME);
  }
  
  public boolean isHouseholdNameReadable()
  {
    return isReadable(HOUSEHOLDNAME);
  }
  
  public boolean isHouseholdNameModified()
  {
    return isModified(HOUSEHOLDNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getHouseholdNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(HOUSEHOLDNAME).getAttributeMdDTO();
  }
  
  public Integer getLastSprayed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LASTSPRAYED));
  }
  
  public void setLastSprayed(Integer value)
  {
    if(value == null)
    {
      setValue(LASTSPRAYED, "");
    }
    else
    {
      setValue(LASTSPRAYED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLastSprayedWritable()
  {
    return isWritable(LASTSPRAYED);
  }
  
  public boolean isLastSprayedReadable()
  {
    return isReadable(LASTSPRAYED);
  }
  
  public boolean isLastSprayedModified()
  {
    return isModified(LASTSPRAYED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLastSprayedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LASTSPRAYED).getAttributeMdDTO();
  }
  
  public Integer getNets()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NETS));
  }
  
  public void setNets(Integer value)
  {
    if(value == null)
    {
      setValue(NETS, "");
    }
    else
    {
      setValue(NETS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNetsWritable()
  {
    return isWritable(NETS);
  }
  
  public boolean isNetsReadable()
  {
    return isReadable(NETS);
  }
  
  public boolean isNetsModified()
  {
    return isModified(NETS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNetsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NETS).getAttributeMdDTO();
  }
  
  public Integer getPeople()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PEOPLE));
  }
  
  public void setPeople(Integer value)
  {
    if(value == null)
    {
      setValue(PEOPLE, "");
    }
    else
    {
      setValue(PEOPLE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPeopleWritable()
  {
    return isWritable(PEOPLE);
  }
  
  public boolean isPeopleReadable()
  {
    return isReadable(PEOPLE);
  }
  
  public boolean isPeopleModified()
  {
    return isModified(PEOPLE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPeopleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PEOPLE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getRoof()
  {
    if(getValue(ROOF) == null || getValue(ROOF).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(ROOF));
    }
  }
  
  public String getRoofId()
  {
    return getValue(ROOF);
  }
  
  public void setRoof(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(ROOF, "");
    }
    else
    {
      setValue(ROOF, value.getId());
    }
  }
  
  public boolean isRoofWritable()
  {
    return isWritable(ROOF);
  }
  
  public boolean isRoofReadable()
  {
    return isReadable(ROOF);
  }
  
  public boolean isRoofModified()
  {
    return isModified(ROOF);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getRoofMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ROOF).getAttributeMdDTO();
  }
  
  public String getRoofInfo()
  {
    return getValue(ROOFINFO);
  }
  
  public void setRoofInfo(String value)
  {
    if(value == null)
    {
      setValue(ROOFINFO, "");
    }
    else
    {
      setValue(ROOFINFO, value);
    }
  }
  
  public boolean isRoofInfoWritable()
  {
    return isWritable(ROOFINFO);
  }
  
  public boolean isRoofInfoReadable()
  {
    return isReadable(ROOFINFO);
  }
  
  public boolean isRoofInfoModified()
  {
    return isModified(ROOFINFO);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getRoofInfoMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ROOFINFO).getAttributeMdDTO();
  }
  
  public Integer getRooms()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ROOMS));
  }
  
  public void setRooms(Integer value)
  {
    if(value == null)
    {
      setValue(ROOMS, "");
    }
    else
    {
      setValue(ROOMS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRoomsWritable()
  {
    return isWritable(ROOMS);
  }
  
  public boolean isRoomsReadable()
  {
    return isReadable(ROOMS);
  }
  
  public boolean isRoomsModified()
  {
    return isModified(ROOMS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getRoomsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ROOMS).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.intervention.monitor.SurveyPointDTO getSurveyPoint()
  {
    if(getValue(SURVEYPOINT) == null || getValue(SURVEYPOINT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.SurveyPointDTO.get(getRequest(), getValue(SURVEYPOINT));
    }
  }
  
  public String getSurveyPointId()
  {
    return getValue(SURVEYPOINT);
  }
  
  public void setSurveyPoint(dss.vector.solutions.intervention.monitor.SurveyPointDTO value)
  {
    if(value == null)
    {
      setValue(SURVEYPOINT, "");
    }
    else
    {
      setValue(SURVEYPOINT, value.getId());
    }
  }
  
  public boolean isSurveyPointWritable()
  {
    return isWritable(SURVEYPOINT);
  }
  
  public boolean isSurveyPointReadable()
  {
    return isReadable(SURVEYPOINT);
  }
  
  public boolean isSurveyPointModified()
  {
    return isModified(SURVEYPOINT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSurveyPointMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SURVEYPOINT).getAttributeMdDTO();
  }
  
  public Boolean getUrban()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(URBAN));
  }
  
  public void setUrban(Boolean value)
  {
    if(value == null)
    {
      setValue(URBAN, "");
    }
    else
    {
      setValue(URBAN, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isUrbanWritable()
  {
    return isWritable(URBAN);
  }
  
  public boolean isUrbanReadable()
  {
    return isReadable(URBAN);
  }
  
  public boolean isUrbanModified()
  {
    return isModified(URBAN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getUrbanMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(URBAN).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getWall()
  {
    if(getValue(WALL) == null || getValue(WALL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(WALL));
    }
  }
  
  public String getWallId()
  {
    return getValue(WALL);
  }
  
  public void setWall(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(WALL, "");
    }
    else
    {
      setValue(WALL, value.getId());
    }
  }
  
  public boolean isWallWritable()
  {
    return isWritable(WALL);
  }
  
  public boolean isWallReadable()
  {
    return isReadable(WALL);
  }
  
  public boolean isWallModified()
  {
    return isModified(WALL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getWallMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(WALL).getAttributeMdDTO();
  }
  
  public String getWallInfo()
  {
    return getValue(WALLINFO);
  }
  
  public void setWallInfo(String value)
  {
    if(value == null)
    {
      setValue(WALLINFO, "");
    }
    else
    {
      setValue(WALLINFO, value);
    }
  }
  
  public boolean isWallInfoWritable()
  {
    return isWritable(WALLINFO);
  }
  
  public boolean isWallInfoReadable()
  {
    return isReadable(WALLINFO);
  }
  
  public boolean isWallInfoModified()
  {
    return isModified(WALLINFO);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getWallInfoMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(WALLINFO).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getWindowType()
  {
    if(getValue(WINDOWTYPE) == null || getValue(WINDOWTYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(WINDOWTYPE));
    }
  }
  
  public String getWindowTypeId()
  {
    return getValue(WINDOWTYPE);
  }
  
  public void setWindowType(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(WINDOWTYPE, "");
    }
    else
    {
      setValue(WINDOWTYPE, value.getId());
    }
  }
  
  public boolean isWindowTypeWritable()
  {
    return isWritable(WINDOWTYPE);
  }
  
  public boolean isWindowTypeReadable()
  {
    return isReadable(WINDOWTYPE);
  }
  
  public boolean isWindowTypeModified()
  {
    return isModified(WINDOWTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getWindowTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(WINDOWTYPE).getAttributeMdDTO();
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.HouseholdViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.HouseholdViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO[] getItns()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.HouseholdViewDTO.CLASS, "getItns", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO[] getItns(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.HouseholdViewDTO.CLASS, "getItns", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO[] getSurveyedPeople()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.HouseholdViewDTO.CLASS, "getSurveyedPeople", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO[] getSurveyedPeople(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.HouseholdViewDTO.CLASS, "getSurveyedPeople", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static HouseholdViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (HouseholdViewDTO) dto;
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
