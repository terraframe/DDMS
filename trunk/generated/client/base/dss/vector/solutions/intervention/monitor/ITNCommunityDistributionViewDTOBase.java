package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = 1313585898)
public abstract class ITNCommunityDistributionViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.ITNCommunityDistributionView";
  private static final long serialVersionUID = 1313585898;
  
  protected ITNCommunityDistributionViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGENTFIRSTNAME = "agentFirstName";
  public static java.lang.String AGENTSURNAME = "agentSurname";
  public static java.lang.String BATCHNUMBER = "batchNumber";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String CURRENCYRECEIVED = "currencyReceived";
  public static java.lang.String DISPLAYNETS = "displayNets";
  public static java.lang.String DISPLAYTARGETGROUPS = "displayTargetGroups";
  public static java.lang.String DISTRIBUTIONLOCATION = "distributionLocation";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String ENTRYTYPE = "entryType";
  public static java.lang.String HASBATCHNUMBER = "hasBatchNumber";
  public static java.lang.String HOUSEHOLDADDRESS = "householdAddress";
  public static java.lang.String HOUSEHOLDNAME = "householdName";
  public static java.lang.String HOUSEHOLDSURNAME = "householdSurname";
  public static java.lang.String ID = "id";
  public static java.lang.String NUMBERRETRIEVED = "numberRetrieved";
  public static java.lang.String PRETREATED = "pretreated";
  public static java.lang.String RESIDENTS = "residents";
  public static java.lang.String RETRIEVED = "retrieved";
  public static java.lang.String SOLD = "sold";
  public static java.lang.String STARTDATE = "startDate";
  public String getAgentFirstName()
  {
    return getValue(AGENTFIRSTNAME);
  }
  
  public void setAgentFirstName(String value)
  {
    if(value == null)
    {
      setValue(AGENTFIRSTNAME, "");
    }
    else
    {
      setValue(AGENTFIRSTNAME, value);
    }
  }
  
  public boolean isAgentFirstNameWritable()
  {
    return isWritable(AGENTFIRSTNAME);
  }
  
  public boolean isAgentFirstNameReadable()
  {
    return isReadable(AGENTFIRSTNAME);
  }
  
  public boolean isAgentFirstNameModified()
  {
    return isModified(AGENTFIRSTNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAgentFirstNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(AGENTFIRSTNAME).getAttributeMdDTO();
  }
  
  public String getAgentSurname()
  {
    return getValue(AGENTSURNAME);
  }
  
  public void setAgentSurname(String value)
  {
    if(value == null)
    {
      setValue(AGENTSURNAME, "");
    }
    else
    {
      setValue(AGENTSURNAME, value);
    }
  }
  
  public boolean isAgentSurnameWritable()
  {
    return isWritable(AGENTSURNAME);
  }
  
  public boolean isAgentSurnameReadable()
  {
    return isReadable(AGENTSURNAME);
  }
  
  public boolean isAgentSurnameModified()
  {
    return isModified(AGENTSURNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAgentSurnameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(AGENTSURNAME).getAttributeMdDTO();
  }
  
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
  
  public dss.vector.solutions.ontology.TermDTO getDisplayNets()
  {
    if(getValue(DISPLAYNETS) == null || getValue(DISPLAYNETS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DISPLAYNETS));
    }
  }
  
  public void setDisplayNets(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DISPLAYNETS, "");
    }
    else
    {
      setValue(DISPLAYNETS, value.getId());
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getDisplayNetsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DISPLAYNETS).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getDisplayTargetGroups()
  {
    if(getValue(DISPLAYTARGETGROUPS) == null || getValue(DISPLAYTARGETGROUPS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DISPLAYTARGETGROUPS));
    }
  }
  
  public void setDisplayTargetGroups(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DISPLAYTARGETGROUPS, "");
    }
    else
    {
      setValue(DISPLAYTARGETGROUPS, value.getId());
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getDisplayTargetGroupsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DISPLAYTARGETGROUPS).getAttributeMdDTO();
  }
  
  public String getDistributionLocation()
  {
    return getValue(DISTRIBUTIONLOCATION);
  }
  
  public void setDistributionLocation(String value)
  {
    if(value == null)
    {
      setValue(DISTRIBUTIONLOCATION, "");
    }
    else
    {
      setValue(DISTRIBUTIONLOCATION, value);
    }
  }
  
  public boolean isDistributionLocationWritable()
  {
    return isWritable(DISTRIBUTIONLOCATION);
  }
  
  public boolean isDistributionLocationReadable()
  {
    return isReadable(DISTRIBUTIONLOCATION);
  }
  
  public boolean isDistributionLocationModified()
  {
    return isModified(DISTRIBUTIONLOCATION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getDistributionLocationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DISTRIBUTIONLOCATION).getAttributeMdDTO();
  }
  
  public java.util.Date getEndDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ENDDATE));
  }
  
  public void setEndDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(ENDDATE, "");
    }
    else
    {
      setValue(ENDDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getEndDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(ENDDATE).getAttributeMdDTO();
  }
  
  public Boolean getEntryType()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENTRYTYPE));
  }
  
  public void setEntryType(Boolean value)
  {
    if(value == null)
    {
      setValue(ENTRYTYPE, "");
    }
    else
    {
      setValue(ENTRYTYPE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEntryTypeWritable()
  {
    return isWritable(ENTRYTYPE);
  }
  
  public boolean isEntryTypeReadable()
  {
    return isReadable(ENTRYTYPE);
  }
  
  public boolean isEntryTypeModified()
  {
    return isModified(ENTRYTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getEntryTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENTRYTYPE).getAttributeMdDTO();
  }
  
  public Boolean getHasBatchNumber()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(HASBATCHNUMBER));
  }
  
  public void setHasBatchNumber(Boolean value)
  {
    if(value == null)
    {
      setValue(HASBATCHNUMBER, "");
    }
    else
    {
      setValue(HASBATCHNUMBER, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isHasBatchNumberWritable()
  {
    return isWritable(HASBATCHNUMBER);
  }
  
  public boolean isHasBatchNumberReadable()
  {
    return isReadable(HASBATCHNUMBER);
  }
  
  public boolean isHasBatchNumberModified()
  {
    return isModified(HASBATCHNUMBER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getHasBatchNumberMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(HASBATCHNUMBER).getAttributeMdDTO();
  }
  
  public String getHouseholdAddress()
  {
    return getValue(HOUSEHOLDADDRESS);
  }
  
  public void setHouseholdAddress(String value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDADDRESS, "");
    }
    else
    {
      setValue(HOUSEHOLDADDRESS, value);
    }
  }
  
  public boolean isHouseholdAddressWritable()
  {
    return isWritable(HOUSEHOLDADDRESS);
  }
  
  public boolean isHouseholdAddressReadable()
  {
    return isReadable(HOUSEHOLDADDRESS);
  }
  
  public boolean isHouseholdAddressModified()
  {
    return isModified(HOUSEHOLDADDRESS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getHouseholdAddressMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(HOUSEHOLDADDRESS).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getHouseholdNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(HOUSEHOLDNAME).getAttributeMdDTO();
  }
  
  public String getHouseholdSurname()
  {
    return getValue(HOUSEHOLDSURNAME);
  }
  
  public void setHouseholdSurname(String value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDSURNAME, "");
    }
    else
    {
      setValue(HOUSEHOLDSURNAME, value);
    }
  }
  
  public boolean isHouseholdSurnameWritable()
  {
    return isWritable(HOUSEHOLDSURNAME);
  }
  
  public boolean isHouseholdSurnameReadable()
  {
    return isReadable(HOUSEHOLDSURNAME);
  }
  
  public boolean isHouseholdSurnameModified()
  {
    return isModified(HOUSEHOLDSURNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getHouseholdSurnameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(HOUSEHOLDSURNAME).getAttributeMdDTO();
  }
  
  public Integer getNumberRetrieved()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERRETRIEVED));
  }
  
  public void setNumberRetrieved(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERRETRIEVED, "");
    }
    else
    {
      setValue(NUMBERRETRIEVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberRetrievedWritable()
  {
    return isWritable(NUMBERRETRIEVED);
  }
  
  public boolean isNumberRetrievedReadable()
  {
    return isReadable(NUMBERRETRIEVED);
  }
  
  public boolean isNumberRetrievedModified()
  {
    return isModified(NUMBERRETRIEVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberRetrievedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERRETRIEVED).getAttributeMdDTO();
  }
  
  public Boolean getPretreated()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PRETREATED));
  }
  
  public void setPretreated(Boolean value)
  {
    if(value == null)
    {
      setValue(PRETREATED, "");
    }
    else
    {
      setValue(PRETREATED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPretreatedWritable()
  {
    return isWritable(PRETREATED);
  }
  
  public boolean isPretreatedReadable()
  {
    return isReadable(PRETREATED);
  }
  
  public boolean isPretreatedModified()
  {
    return isModified(PRETREATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getPretreatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(PRETREATED).getAttributeMdDTO();
  }
  
  public Integer getResidents()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RESIDENTS));
  }
  
  public void setResidents(Integer value)
  {
    if(value == null)
    {
      setValue(RESIDENTS, "");
    }
    else
    {
      setValue(RESIDENTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isResidentsWritable()
  {
    return isWritable(RESIDENTS);
  }
  
  public boolean isResidentsReadable()
  {
    return isReadable(RESIDENTS);
  }
  
  public boolean isResidentsModified()
  {
    return isModified(RESIDENTS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getResidentsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RESIDENTS).getAttributeMdDTO();
  }
  
  public Boolean getRetrieved()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RETRIEVED));
  }
  
  public void setRetrieved(Boolean value)
  {
    if(value == null)
    {
      setValue(RETRIEVED, "");
    }
    else
    {
      setValue(RETRIEVED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isRetrievedWritable()
  {
    return isWritable(RETRIEVED);
  }
  
  public boolean isRetrievedReadable()
  {
    return isReadable(RETRIEVED);
  }
  
  public boolean isRetrievedModified()
  {
    return isModified(RETRIEVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getRetrievedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(RETRIEVED).getAttributeMdDTO();
  }
  
  public Boolean getSold()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SOLD));
  }
  
  public void setSold(Boolean value)
  {
    if(value == null)
    {
      setValue(SOLD, "");
    }
    else
    {
      setValue(SOLD, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isSoldWritable()
  {
    return isWritable(SOLD);
  }
  
  public boolean isSoldReadable()
  {
    return isReadable(SOLD);
  }
  
  public boolean isSoldModified()
  {
    return isModified(SOLD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getSoldMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(SOLD).getAttributeMdDTO();
  }
  
  public java.util.Date getStartDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(STARTDATE));
  }
  
  public void setStartDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(STARTDATE, "");
    }
    else
    {
      setValue(STARTDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getStartDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(STARTDATE).getAttributeMdDTO();
  }
  
  public final void applyAll(dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO[] nets, dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO[] targetGroups)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.intervention.monitor.ITNCommunityNet;", "[Ldss.vector.solutions.intervention.monitor.ITNCommunityTargetGroup;"};
    Object[] _parameters = new Object[]{nets, targetGroups};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO.CLASS, "applyAll", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO[] nets, dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO[] targetGroups)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.intervention.monitor.ITNCommunityNet;", "[Ldss.vector.solutions.intervention.monitor.ITNCommunityTargetGroup;"};
    Object[] _parameters = new Object[]{id, nets, targetGroups};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO.CLASS, "applyAll", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO[] getITNCommunityNets()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO.CLASS, "getITNCommunityNets", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO[] getITNCommunityNets(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO.CLASS, "getITNCommunityNets", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO[] getITNCommunityTargetGroups()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO.CLASS, "getITNCommunityTargetGroups", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO[] getITNCommunityTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO.CLASS, "getITNCommunityTargetGroups", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewQueryDTO getPage(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO.CLASS, "getPage", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ITNCommunityDistributionViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (ITNCommunityDistributionViewDTO) dto;
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
