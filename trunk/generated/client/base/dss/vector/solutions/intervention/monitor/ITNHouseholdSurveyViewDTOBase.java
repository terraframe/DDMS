package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -565965279)
public abstract class ITNHouseholdSurveyViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView";
  private static final long serialVersionUID = -565965279;
  
  protected ITNHouseholdSurveyViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGENTFIRSTNAME = "agentFirstName";
  public static java.lang.String AGENTSURNAME = "agentSurname";
  public static java.lang.String BOUGHTPROVIDER = "boughtProvider";
  public static java.lang.String CHILDRESIDENTS = "childResidents";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DAMAGEDITNS = "damagedItns";
  public static java.lang.String DISPLAYNETS = "displayNets";
  public static java.lang.String DISPLAYNONUSEREASONS = "displayNonUseReasons";
  public static java.lang.String DISPLAYTARGETGROUPS = "displayTargetGroups";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String FREEPROVIDER = "freeProvider";
  public static java.lang.String HANGINGITNS = "hangingItns";
  public static java.lang.String ID = "id";
  public static java.lang.String ITNS = "itns";
  public static java.lang.String KNOWWASHFREQUENCY = "knowWashFrequency";
  public static java.lang.String MONTHRECEIVED = "monthReceived";
  public static java.lang.String NETSOBTAINED = "netsObtained";
  public static java.lang.String OTHERITNS = "otherItns";
  public static java.lang.String PREGNANTRESIDENTS = "pregnantResidents";
  public static java.lang.String QUESTIONNAIRENUMBER = "questionnaireNumber";
  public static java.lang.String RESIDENTS = "residents";
  public static java.lang.String RETREATED = "retreated";
  public static java.lang.String RETREATMENTPERIOD = "retreatmentPeriod";
  public static java.lang.String STARTDATE = "startDate";
  public static java.lang.String SURVEYLOCATION = "surveyLocation";
  public static java.lang.String USEDEVERYNIGHT = "usedEveryNight";
  public static java.lang.String USEDITNS = "usedItns";
  public static java.lang.String WASHFREQUENCY = "washFrequency";
  public static java.lang.String WASHINTERVAL = "washInterval";
  public static java.lang.String WASHED = "washed";
  public static java.lang.String YEARRECEIVED = "yearReceived";
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
  
  public dss.vector.solutions.ontology.TermDTO getBoughtProvider()
  {
    if(getValue(BOUGHTPROVIDER) == null || getValue(BOUGHTPROVIDER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(BOUGHTPROVIDER));
    }
  }
  
  public void setBoughtProvider(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(BOUGHTPROVIDER, "");
    }
    else
    {
      setValue(BOUGHTPROVIDER, value.getId());
    }
  }
  
  public boolean isBoughtProviderWritable()
  {
    return isWritable(BOUGHTPROVIDER);
  }
  
  public boolean isBoughtProviderReadable()
  {
    return isReadable(BOUGHTPROVIDER);
  }
  
  public boolean isBoughtProviderModified()
  {
    return isModified(BOUGHTPROVIDER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getBoughtProviderMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(BOUGHTPROVIDER).getAttributeMdDTO();
  }
  
  public Integer getChildResidents()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CHILDRESIDENTS));
  }
  
  public void setChildResidents(Integer value)
  {
    if(value == null)
    {
      setValue(CHILDRESIDENTS, "");
    }
    else
    {
      setValue(CHILDRESIDENTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isChildResidentsWritable()
  {
    return isWritable(CHILDRESIDENTS);
  }
  
  public boolean isChildResidentsReadable()
  {
    return isReadable(CHILDRESIDENTS);
  }
  
  public boolean isChildResidentsModified()
  {
    return isModified(CHILDRESIDENTS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getChildResidentsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CHILDRESIDENTS).getAttributeMdDTO();
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
  
  public Integer getDamagedItns()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DAMAGEDITNS));
  }
  
  public void setDamagedItns(Integer value)
  {
    if(value == null)
    {
      setValue(DAMAGEDITNS, "");
    }
    else
    {
      setValue(DAMAGEDITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDamagedItnsWritable()
  {
    return isWritable(DAMAGEDITNS);
  }
  
  public boolean isDamagedItnsReadable()
  {
    return isReadable(DAMAGEDITNS);
  }
  
  public boolean isDamagedItnsModified()
  {
    return isModified(DAMAGEDITNS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDamagedItnsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DAMAGEDITNS).getAttributeMdDTO();
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
  
  public Integer getDisplayNonUseReasons()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DISPLAYNONUSEREASONS));
  }
  
  public void setDisplayNonUseReasons(Integer value)
  {
    if(value == null)
    {
      setValue(DISPLAYNONUSEREASONS, "");
    }
    else
    {
      setValue(DISPLAYNONUSEREASONS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDisplayNonUseReasonsWritable()
  {
    return isWritable(DISPLAYNONUSEREASONS);
  }
  
  public boolean isDisplayNonUseReasonsReadable()
  {
    return isReadable(DISPLAYNONUSEREASONS);
  }
  
  public boolean isDisplayNonUseReasonsModified()
  {
    return isModified(DISPLAYNONUSEREASONS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDisplayNonUseReasonsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DISPLAYNONUSEREASONS).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getFreeProvider()
  {
    if(getValue(FREEPROVIDER) == null || getValue(FREEPROVIDER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(FREEPROVIDER));
    }
  }
  
  public void setFreeProvider(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(FREEPROVIDER, "");
    }
    else
    {
      setValue(FREEPROVIDER, value.getId());
    }
  }
  
  public boolean isFreeProviderWritable()
  {
    return isWritable(FREEPROVIDER);
  }
  
  public boolean isFreeProviderReadable()
  {
    return isReadable(FREEPROVIDER);
  }
  
  public boolean isFreeProviderModified()
  {
    return isModified(FREEPROVIDER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getFreeProviderMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(FREEPROVIDER).getAttributeMdDTO();
  }
  
  public Integer getHangingItns()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HANGINGITNS));
  }
  
  public void setHangingItns(Integer value)
  {
    if(value == null)
    {
      setValue(HANGINGITNS, "");
    }
    else
    {
      setValue(HANGINGITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isHangingItnsWritable()
  {
    return isWritable(HANGINGITNS);
  }
  
  public boolean isHangingItnsReadable()
  {
    return isReadable(HANGINGITNS);
  }
  
  public boolean isHangingItnsModified()
  {
    return isModified(HANGINGITNS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getHangingItnsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(HANGINGITNS).getAttributeMdDTO();
  }
  
  public Integer getItns()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ITNS));
  }
  
  public void setItns(Integer value)
  {
    if(value == null)
    {
      setValue(ITNS, "");
    }
    else
    {
      setValue(ITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isItnsWritable()
  {
    return isWritable(ITNS);
  }
  
  public boolean isItnsReadable()
  {
    return isReadable(ITNS);
  }
  
  public boolean isItnsModified()
  {
    return isModified(ITNS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getItnsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ITNS).getAttributeMdDTO();
  }
  
  public Boolean getKnowWashFrequency()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(KNOWWASHFREQUENCY));
  }
  
  public void setKnowWashFrequency(Boolean value)
  {
    if(value == null)
    {
      setValue(KNOWWASHFREQUENCY, "");
    }
    else
    {
      setValue(KNOWWASHFREQUENCY, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isKnowWashFrequencyWritable()
  {
    return isWritable(KNOWWASHFREQUENCY);
  }
  
  public boolean isKnowWashFrequencyReadable()
  {
    return isReadable(KNOWWASHFREQUENCY);
  }
  
  public boolean isKnowWashFrequencyModified()
  {
    return isModified(KNOWWASHFREQUENCY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getKnowWashFrequencyMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(KNOWWASHFREQUENCY).getAttributeMdDTO();
  }
  
  public Integer getMonthReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MONTHRECEIVED));
  }
  
  public void setMonthReceived(Integer value)
  {
    if(value == null)
    {
      setValue(MONTHRECEIVED, "");
    }
    else
    {
      setValue(MONTHRECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isMonthReceivedWritable()
  {
    return isWritable(MONTHRECEIVED);
  }
  
  public boolean isMonthReceivedReadable()
  {
    return isReadable(MONTHRECEIVED);
  }
  
  public boolean isMonthReceivedModified()
  {
    return isModified(MONTHRECEIVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getMonthReceivedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(MONTHRECEIVED).getAttributeMdDTO();
  }
  
  public Boolean getNetsObtained()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(NETSOBTAINED));
  }
  
  public void setNetsObtained(Boolean value)
  {
    if(value == null)
    {
      setValue(NETSOBTAINED, "");
    }
    else
    {
      setValue(NETSOBTAINED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isNetsObtainedWritable()
  {
    return isWritable(NETSOBTAINED);
  }
  
  public boolean isNetsObtainedReadable()
  {
    return isReadable(NETSOBTAINED);
  }
  
  public boolean isNetsObtainedModified()
  {
    return isModified(NETSOBTAINED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getNetsObtainedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(NETSOBTAINED).getAttributeMdDTO();
  }
  
  public Integer getOtherItns()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OTHERITNS));
  }
  
  public void setOtherItns(Integer value)
  {
    if(value == null)
    {
      setValue(OTHERITNS, "");
    }
    else
    {
      setValue(OTHERITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOtherItnsWritable()
  {
    return isWritable(OTHERITNS);
  }
  
  public boolean isOtherItnsReadable()
  {
    return isReadable(OTHERITNS);
  }
  
  public boolean isOtherItnsModified()
  {
    return isModified(OTHERITNS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOtherItnsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OTHERITNS).getAttributeMdDTO();
  }
  
  public Integer getPregnantResidents()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREGNANTRESIDENTS));
  }
  
  public void setPregnantResidents(Integer value)
  {
    if(value == null)
    {
      setValue(PREGNANTRESIDENTS, "");
    }
    else
    {
      setValue(PREGNANTRESIDENTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPregnantResidentsWritable()
  {
    return isWritable(PREGNANTRESIDENTS);
  }
  
  public boolean isPregnantResidentsReadable()
  {
    return isReadable(PREGNANTRESIDENTS);
  }
  
  public boolean isPregnantResidentsModified()
  {
    return isModified(PREGNANTRESIDENTS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPregnantResidentsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREGNANTRESIDENTS).getAttributeMdDTO();
  }
  
  public String getQuestionnaireNumber()
  {
    return getValue(QUESTIONNAIRENUMBER);
  }
  
  public void setQuestionnaireNumber(String value)
  {
    if(value == null)
    {
      setValue(QUESTIONNAIRENUMBER, "");
    }
    else
    {
      setValue(QUESTIONNAIRENUMBER, value);
    }
  }
  
  public boolean isQuestionnaireNumberWritable()
  {
    return isWritable(QUESTIONNAIRENUMBER);
  }
  
  public boolean isQuestionnaireNumberReadable()
  {
    return isReadable(QUESTIONNAIRENUMBER);
  }
  
  public boolean isQuestionnaireNumberModified()
  {
    return isModified(QUESTIONNAIRENUMBER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getQuestionnaireNumberMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(QUESTIONNAIRENUMBER).getAttributeMdDTO();
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
  
  public Boolean getRetreated()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RETREATED));
  }
  
  public void setRetreated(Boolean value)
  {
    if(value == null)
    {
      setValue(RETREATED, "");
    }
    else
    {
      setValue(RETREATED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isRetreatedWritable()
  {
    return isWritable(RETREATED);
  }
  
  public boolean isRetreatedReadable()
  {
    return isReadable(RETREATED);
  }
  
  public boolean isRetreatedModified()
  {
    return isModified(RETREATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getRetreatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(RETREATED).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getRetreatmentPeriod()
  {
    if(getValue(RETREATMENTPERIOD) == null || getValue(RETREATMENTPERIOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(RETREATMENTPERIOD));
    }
  }
  
  public void setRetreatmentPeriod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(RETREATMENTPERIOD, "");
    }
    else
    {
      setValue(RETREATMENTPERIOD, value.getId());
    }
  }
  
  public boolean isRetreatmentPeriodWritable()
  {
    return isWritable(RETREATMENTPERIOD);
  }
  
  public boolean isRetreatmentPeriodReadable()
  {
    return isReadable(RETREATMENTPERIOD);
  }
  
  public boolean isRetreatmentPeriodModified()
  {
    return isModified(RETREATMENTPERIOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getRetreatmentPeriodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(RETREATMENTPERIOD).getAttributeMdDTO();
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
  
  public String getSurveyLocation()
  {
    return getValue(SURVEYLOCATION);
  }
  
  public void setSurveyLocation(String value)
  {
    if(value == null)
    {
      setValue(SURVEYLOCATION, "");
    }
    else
    {
      setValue(SURVEYLOCATION, value);
    }
  }
  
  public boolean isSurveyLocationWritable()
  {
    return isWritable(SURVEYLOCATION);
  }
  
  public boolean isSurveyLocationReadable()
  {
    return isReadable(SURVEYLOCATION);
  }
  
  public boolean isSurveyLocationModified()
  {
    return isModified(SURVEYLOCATION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSurveyLocationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SURVEYLOCATION).getAttributeMdDTO();
  }
  
  public Boolean getUsedEveryNight()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(USEDEVERYNIGHT));
  }
  
  public void setUsedEveryNight(Boolean value)
  {
    if(value == null)
    {
      setValue(USEDEVERYNIGHT, "");
    }
    else
    {
      setValue(USEDEVERYNIGHT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isUsedEveryNightWritable()
  {
    return isWritable(USEDEVERYNIGHT);
  }
  
  public boolean isUsedEveryNightReadable()
  {
    return isReadable(USEDEVERYNIGHT);
  }
  
  public boolean isUsedEveryNightModified()
  {
    return isModified(USEDEVERYNIGHT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getUsedEveryNightMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(USEDEVERYNIGHT).getAttributeMdDTO();
  }
  
  public Integer getUsedItns()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(USEDITNS));
  }
  
  public void setUsedItns(Integer value)
  {
    if(value == null)
    {
      setValue(USEDITNS, "");
    }
    else
    {
      setValue(USEDITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUsedItnsWritable()
  {
    return isWritable(USEDITNS);
  }
  
  public boolean isUsedItnsReadable()
  {
    return isReadable(USEDITNS);
  }
  
  public boolean isUsedItnsModified()
  {
    return isModified(USEDITNS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getUsedItnsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(USEDITNS).getAttributeMdDTO();
  }
  
  public Integer getWashFrequency()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(WASHFREQUENCY));
  }
  
  public void setWashFrequency(Integer value)
  {
    if(value == null)
    {
      setValue(WASHFREQUENCY, "");
    }
    else
    {
      setValue(WASHFREQUENCY, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isWashFrequencyWritable()
  {
    return isWritable(WASHFREQUENCY);
  }
  
  public boolean isWashFrequencyReadable()
  {
    return isReadable(WASHFREQUENCY);
  }
  
  public boolean isWashFrequencyModified()
  {
    return isModified(WASHFREQUENCY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getWashFrequencyMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(WASHFREQUENCY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getWashInterval()
  {
    if(getValue(WASHINTERVAL) == null || getValue(WASHINTERVAL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(WASHINTERVAL));
    }
  }
  
  public void setWashInterval(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(WASHINTERVAL, "");
    }
    else
    {
      setValue(WASHINTERVAL, value.getId());
    }
  }
  
  public boolean isWashIntervalWritable()
  {
    return isWritable(WASHINTERVAL);
  }
  
  public boolean isWashIntervalReadable()
  {
    return isReadable(WASHINTERVAL);
  }
  
  public boolean isWashIntervalModified()
  {
    return isModified(WASHINTERVAL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getWashIntervalMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(WASHINTERVAL).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getWashed()
  {
    if(getValue(WASHED) == null || getValue(WASHED).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(WASHED));
    }
  }
  
  public void setWashed(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(WASHED, "");
    }
    else
    {
      setValue(WASHED, value.getId());
    }
  }
  
  public boolean isWashedWritable()
  {
    return isWritable(WASHED);
  }
  
  public boolean isWashedReadable()
  {
    return isReadable(WASHED);
  }
  
  public boolean isWashedModified()
  {
    return isModified(WASHED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getWashedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(WASHED).getAttributeMdDTO();
  }
  
  public Integer getYearReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEARRECEIVED));
  }
  
  public void setYearReceived(Integer value)
  {
    if(value == null)
    {
      setValue(YEARRECEIVED, "");
    }
    else
    {
      setValue(YEARRECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isYearReceivedWritable()
  {
    return isWritable(YEARRECEIVED);
  }
  
  public boolean isYearReceivedReadable()
  {
    return isReadable(YEARRECEIVED);
  }
  
  public boolean isYearReceivedModified()
  {
    return isModified(YEARRECEIVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getYearReceivedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(YEARRECEIVED).getAttributeMdDTO();
  }
  
  public final void applyAll(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO[] nets, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO[] targetGroups, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO[] reasons)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNet;", "[Ldss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroup;", "[Ldss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReason;"};
    Object[] _parameters = new Object[]{nets, targetGroups, reasons};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO.CLASS, "applyAll", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO[] nets, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO[] targetGroups, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO[] reasons)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNet;", "[Ldss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroup;", "[Ldss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReason;"};
    Object[] _parameters = new Object[]{id, nets, targetGroups, reasons};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO.CLASS, "applyAll", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO[] getITNHouseholdSurveyNets()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO.CLASS, "getITNHouseholdSurveyNets", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO[] getITNHouseholdSurveyNets(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO.CLASS, "getITNHouseholdSurveyNets", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO[] getITNHouseholdSurveyNonUseReason()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO.CLASS, "getITNHouseholdSurveyNonUseReason", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO[] getITNHouseholdSurveyNonUseReason(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO.CLASS, "getITNHouseholdSurveyNonUseReason", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO[] getITNHouseholdSurveyTargetGroups()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO.CLASS, "getITNHouseholdSurveyTargetGroups", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO[] getITNHouseholdSurveyTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO.CLASS, "getITNHouseholdSurveyTargetGroups", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewQueryDTO getPage(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO.CLASS, "getPage", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ITNHouseholdSurveyViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (ITNHouseholdSurveyViewDTO) dto;
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
