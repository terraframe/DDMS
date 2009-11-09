package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -1115066450)
public abstract class ITNDataViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.ITNDataView";
  private static final long serialVersionUID = -1115066450;
  
  protected ITNDataViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BATCHNUMBER = "batchNumber";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String CURRENCYRECEIVED = "currencyReceived";
  public static java.lang.String DISPLAYNETS = "displayNets";
  public static java.lang.String DISPLAYSERVICES = "displayServices";
  public static java.lang.String DISPLAYTARGETGROUPS = "displayTargetGroups";
  public static java.lang.String GEOID = "geoId";
  public static java.lang.String ID = "id";
  public static java.lang.String NUMBERDISTRIBUTED = "numberDistributed";
  public static java.lang.String NUMBERSOLD = "numberSold";
  public static java.lang.String PERIOD = "period";
  public static java.lang.String PERIODTYPE = "periodType";
  public static java.lang.String PERIODYEAR = "periodYear";
  public static java.lang.String RECEIVEDFORCOMMUNITYRESPONSE = "receivedForCommunityResponse";
  public static java.lang.String RECEIVEDFORTARGETGROUPS = "receivedForTargetGroups";
  public String getBatchNumber()
  {
    return getValue(BATCHNUMBER);
  }
  
  public void setBatchNumber(String value)
  {
    if(value == null)
    {
      setValue(BATCHNUMBER, "");
    }
    else
    {
      setValue(BATCHNUMBER, value);
    }
  }
  
  public boolean isBatchNumberWritable()
  {
    return isWritable(BATCHNUMBER);
  }
  
  public boolean isBatchNumberReadable()
  {
    return isReadable(BATCHNUMBER);
  }
  
  public boolean isBatchNumberModified()
  {
    return isModified(BATCHNUMBER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getBatchNumberMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BATCHNUMBER).getAttributeMdDTO();
  }
  
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getConcreteIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCRETEID).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getCurrencyReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(CURRENCYRECEIVED));
  }
  
  public void setCurrencyReceived(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(CURRENCYRECEIVED, "");
    }
    else
    {
      setValue(CURRENCYRECEIVED, value.toString());
    }
  }
  
  public boolean isCurrencyReceivedWritable()
  {
    return isWritable(CURRENCYRECEIVED);
  }
  
  public boolean isCurrencyReceivedReadable()
  {
    return isReadable(CURRENCYRECEIVED);
  }
  
  public boolean isCurrencyReceivedModified()
  {
    return isModified(CURRENCYRECEIVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getCurrencyReceivedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(CURRENCYRECEIVED).getAttributeMdDTO();
  }
  
  public Integer getDisplayNets()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DISPLAYNETS));
  }
  
  public void setDisplayNets(Integer value)
  {
    if(value == null)
    {
      setValue(DISPLAYNETS, "");
    }
    else
    {
      setValue(DISPLAYNETS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDisplayNetsWritable()
  {
    return isWritable(DISPLAYNETS);
  }
  
  public boolean isDisplayNetsReadable()
  {
    return isReadable(DISPLAYNETS);
  }
  
  public boolean isDisplayNetsModified()
  {
    return isModified(DISPLAYNETS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDisplayNetsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DISPLAYNETS).getAttributeMdDTO();
  }
  
  public Integer getDisplayServices()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DISPLAYSERVICES));
  }
  
  public void setDisplayServices(Integer value)
  {
    if(value == null)
    {
      setValue(DISPLAYSERVICES, "");
    }
    else
    {
      setValue(DISPLAYSERVICES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDisplayServicesWritable()
  {
    return isWritable(DISPLAYSERVICES);
  }
  
  public boolean isDisplayServicesReadable()
  {
    return isReadable(DISPLAYSERVICES);
  }
  
  public boolean isDisplayServicesModified()
  {
    return isModified(DISPLAYSERVICES);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDisplayServicesMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DISPLAYSERVICES).getAttributeMdDTO();
  }
  
  public Integer getDisplayTargetGroups()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DISPLAYTARGETGROUPS));
  }
  
  public void setDisplayTargetGroups(Integer value)
  {
    if(value == null)
    {
      setValue(DISPLAYTARGETGROUPS, "");
    }
    else
    {
      setValue(DISPLAYTARGETGROUPS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDisplayTargetGroupsWritable()
  {
    return isWritable(DISPLAYTARGETGROUPS);
  }
  
  public boolean isDisplayTargetGroupsReadable()
  {
    return isReadable(DISPLAYTARGETGROUPS);
  }
  
  public boolean isDisplayTargetGroupsModified()
  {
    return isModified(DISPLAYTARGETGROUPS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDisplayTargetGroupsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DISPLAYTARGETGROUPS).getAttributeMdDTO();
  }
  
  public String getGeoId()
  {
    return getValue(GEOID);
  }
  
  public void setGeoId(String value)
  {
    if(value == null)
    {
      setValue(GEOID, "");
    }
    else
    {
      setValue(GEOID, value);
    }
  }
  
  public boolean isGeoIdWritable()
  {
    return isWritable(GEOID);
  }
  
  public boolean isGeoIdReadable()
  {
    return isReadable(GEOID);
  }
  
  public boolean isGeoIdModified()
  {
    return isModified(GEOID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getGeoIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOID).getAttributeMdDTO();
  }
  
  public Integer getNumberDistributed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERDISTRIBUTED));
  }
  
  public void setNumberDistributed(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERDISTRIBUTED, "");
    }
    else
    {
      setValue(NUMBERDISTRIBUTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberDistributedWritable()
  {
    return isWritable(NUMBERDISTRIBUTED);
  }
  
  public boolean isNumberDistributedReadable()
  {
    return isReadable(NUMBERDISTRIBUTED);
  }
  
  public boolean isNumberDistributedModified()
  {
    return isModified(NUMBERDISTRIBUTED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberDistributedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERDISTRIBUTED).getAttributeMdDTO();
  }
  
  public Integer getNumberSold()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERSOLD));
  }
  
  public void setNumberSold(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERSOLD, "");
    }
    else
    {
      setValue(NUMBERSOLD, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberSoldWritable()
  {
    return isWritable(NUMBERSOLD);
  }
  
  public boolean isNumberSoldReadable()
  {
    return isReadable(NUMBERSOLD);
  }
  
  public boolean isNumberSoldModified()
  {
    return isModified(NUMBERSOLD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberSoldMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERSOLD).getAttributeMdDTO();
  }
  
  public Integer getPeriod()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIOD));
  }
  
  public void setPeriod(Integer value)
  {
    if(value == null)
    {
      setValue(PERIOD, "");
    }
    else
    {
      setValue(PERIOD, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPeriodWritable()
  {
    return isWritable(PERIOD);
  }
  
  public boolean isPeriodReadable()
  {
    return isReadable(PERIOD);
  }
  
  public boolean isPeriodModified()
  {
    return isModified(PERIOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPeriodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PERIOD).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.surveillance.PeriodTypeDTO> getPeriodType()
  {
    return (java.util.List<dss.vector.solutions.surveillance.PeriodTypeDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "dss.vector.solutions.surveillance.PeriodType", getEnumNames(PERIODTYPE));
  }
  
  public java.util.List<String> getPeriodTypeEnumNames()
  {
    return getEnumNames(PERIODTYPE);
  }
  
  public void addPeriodType(dss.vector.solutions.surveillance.PeriodTypeDTO enumDTO)
  {
    addEnumItem(PERIODTYPE, enumDTO.toString());
  }
  
  public void removePeriodType(dss.vector.solutions.surveillance.PeriodTypeDTO enumDTO)
  {
    removeEnumItem(PERIODTYPE, enumDTO.toString());
  }
  
  public void clearPeriodType()
  {
    clearEnum(PERIODTYPE);
  }
  
  public boolean isPeriodTypeWritable()
  {
    return isWritable(PERIODTYPE);
  }
  
  public boolean isPeriodTypeReadable()
  {
    return isReadable(PERIODTYPE);
  }
  
  public boolean isPeriodTypeModified()
  {
    return isModified(PERIODTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getPeriodTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(PERIODTYPE).getAttributeMdDTO();
  }
  
  public Integer getPeriodYear()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIODYEAR));
  }
  
  public void setPeriodYear(Integer value)
  {
    if(value == null)
    {
      setValue(PERIODYEAR, "");
    }
    else
    {
      setValue(PERIODYEAR, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPeriodYearWritable()
  {
    return isWritable(PERIODYEAR);
  }
  
  public boolean isPeriodYearReadable()
  {
    return isReadable(PERIODYEAR);
  }
  
  public boolean isPeriodYearModified()
  {
    return isModified(PERIODYEAR);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPeriodYearMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PERIODYEAR).getAttributeMdDTO();
  }
  
  public Integer getReceivedForCommunityResponse()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RECEIVEDFORCOMMUNITYRESPONSE));
  }
  
  public void setReceivedForCommunityResponse(Integer value)
  {
    if(value == null)
    {
      setValue(RECEIVEDFORCOMMUNITYRESPONSE, "");
    }
    else
    {
      setValue(RECEIVEDFORCOMMUNITYRESPONSE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isReceivedForCommunityResponseWritable()
  {
    return isWritable(RECEIVEDFORCOMMUNITYRESPONSE);
  }
  
  public boolean isReceivedForCommunityResponseReadable()
  {
    return isReadable(RECEIVEDFORCOMMUNITYRESPONSE);
  }
  
  public boolean isReceivedForCommunityResponseModified()
  {
    return isModified(RECEIVEDFORCOMMUNITYRESPONSE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getReceivedForCommunityResponseMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RECEIVEDFORCOMMUNITYRESPONSE).getAttributeMdDTO();
  }
  
  public Integer getReceivedForTargetGroups()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RECEIVEDFORTARGETGROUPS));
  }
  
  public void setReceivedForTargetGroups(Integer value)
  {
    if(value == null)
    {
      setValue(RECEIVEDFORTARGETGROUPS, "");
    }
    else
    {
      setValue(RECEIVEDFORTARGETGROUPS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isReceivedForTargetGroupsWritable()
  {
    return isWritable(RECEIVEDFORTARGETGROUPS);
  }
  
  public boolean isReceivedForTargetGroupsReadable()
  {
    return isReadable(RECEIVEDFORTARGETGROUPS);
  }
  
  public boolean isReceivedForTargetGroupsModified()
  {
    return isModified(RECEIVEDFORTARGETGROUPS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getReceivedForTargetGroupsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RECEIVEDFORTARGETGROUPS).getAttributeMdDTO();
  }
  
  public final void applyAll(dss.vector.solutions.intervention.monitor.ITNNetDTO[] nets, dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO[] targetGroups, dss.vector.solutions.intervention.monitor.ITNServiceDTO[] services)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.intervention.monitor.ITNNet;", "[Ldss.vector.solutions.intervention.monitor.ITNTargetGroup;", "[Ldss.vector.solutions.intervention.monitor.ITNService;"};
    Object[] _parameters = new Object[]{nets, targetGroups, services};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDataViewDTO.CLASS, "applyAll", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.intervention.monitor.ITNNetDTO[] nets, dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO[] targetGroups, dss.vector.solutions.intervention.monitor.ITNServiceDTO[] services)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.intervention.monitor.ITNNet;", "[Ldss.vector.solutions.intervention.monitor.ITNTargetGroup;", "[Ldss.vector.solutions.intervention.monitor.ITNService;"};
    Object[] _parameters = new Object[]{id, nets, targetGroups, services};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDataViewDTO.CLASS, "applyAll", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDataViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDataViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNNetDTO[] getITNNets()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDataViewDTO.CLASS, "getITNNets", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNNetDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNNetDTO[] getITNNets(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDataViewDTO.CLASS, "getITNNets", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNNetDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNServiceDTO[] getITNServices()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDataViewDTO.CLASS, "getITNServices", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNServiceDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNServiceDTO[] getITNServices(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDataViewDTO.CLASS, "getITNServices", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNServiceDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO[] getITNTargetGroups()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDataViewDTO.CLASS, "getITNTargetGroups", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO[] getITNTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDataViewDTO.CLASS, "getITNTargetGroups", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ITNDataViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (ITNDataViewDTO) dto;
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
