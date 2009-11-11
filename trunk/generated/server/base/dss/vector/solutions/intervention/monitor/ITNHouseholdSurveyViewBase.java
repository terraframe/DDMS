package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -720328089)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ITNHouseholdSurveyView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class ITNHouseholdSurveyViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView";
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
  private static final long serialVersionUID = -720328089;
  
  public ITNHouseholdSurveyViewBase()
  {
    super();
  }
  
  public String getAgentFirstName()
  {
    return getValue(AGENTFIRSTNAME);
  }
  
  public void validateAgentFirstName()
  {
    this.validateAttribute(AGENTFIRSTNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAgentFirstNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(AGENTFIRSTNAME);
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
  
  public String getAgentSurname()
  {
    return getValue(AGENTSURNAME);
  }
  
  public void validateAgentSurname()
  {
    this.validateAttribute(AGENTSURNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAgentSurnameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(AGENTSURNAME);
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
  
  public dss.vector.solutions.ontology.Term getBoughtProvider()
  {
    if (getValue(BOUGHTPROVIDER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(BOUGHTPROVIDER));
    }
  }
  
  public void validateBoughtProvider()
  {
    this.validateAttribute(BOUGHTPROVIDER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getBoughtProviderMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(BOUGHTPROVIDER);
  }
  
  public void setBoughtProvider(dss.vector.solutions.ontology.Term value)
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
  
  public Integer getChildResidents()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CHILDRESIDENTS));
  }
  
  public void validateChildResidents()
  {
    this.validateAttribute(CHILDRESIDENTS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getChildResidentsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(CHILDRESIDENTS);
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
  
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void validateConcreteId()
  {
    this.validateAttribute(CONCRETEID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getConcreteIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(CONCRETEID);
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
  
  public Integer getDamagedItns()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DAMAGEDITNS));
  }
  
  public void validateDamagedItns()
  {
    this.validateAttribute(DAMAGEDITNS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDamagedItnsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(DAMAGEDITNS);
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
  
  public dss.vector.solutions.ontology.Term getDisplayNets()
  {
    if (getValue(DISPLAYNETS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(DISPLAYNETS));
    }
  }
  
  public void validateDisplayNets()
  {
    this.validateAttribute(DISPLAYNETS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDisplayNetsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(DISPLAYNETS);
  }
  
  public void setDisplayNets(dss.vector.solutions.ontology.Term value)
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
  
  public dss.vector.solutions.ontology.Term getDisplayNonUseReasons()
  {
    if (getValue(DISPLAYNONUSEREASONS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(DISPLAYNONUSEREASONS));
    }
  }
  
  public void validateDisplayNonUseReasons()
  {
    this.validateAttribute(DISPLAYNONUSEREASONS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDisplayNonUseReasonsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(DISPLAYNONUSEREASONS);
  }
  
  public void setDisplayNonUseReasons(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(DISPLAYNONUSEREASONS, "");
    }
    else
    {
      setValue(DISPLAYNONUSEREASONS, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getDisplayTargetGroups()
  {
    if (getValue(DISPLAYTARGETGROUPS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(DISPLAYTARGETGROUPS));
    }
  }
  
  public void validateDisplayTargetGroups()
  {
    this.validateAttribute(DISPLAYTARGETGROUPS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDisplayTargetGroupsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(DISPLAYTARGETGROUPS);
  }
  
  public void setDisplayTargetGroups(dss.vector.solutions.ontology.Term value)
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
  
  public java.util.Date getEndDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ENDDATE));
  }
  
  public void validateEndDate()
  {
    this.validateAttribute(ENDDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEndDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(ENDDATE);
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
  
  public dss.vector.solutions.ontology.Term getFreeProvider()
  {
    if (getValue(FREEPROVIDER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(FREEPROVIDER));
    }
  }
  
  public void validateFreeProvider()
  {
    this.validateAttribute(FREEPROVIDER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getFreeProviderMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(FREEPROVIDER);
  }
  
  public void setFreeProvider(dss.vector.solutions.ontology.Term value)
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
  
  public Integer getHangingItns()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HANGINGITNS));
  }
  
  public void validateHangingItns()
  {
    this.validateAttribute(HANGINGITNS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getHangingItnsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(HANGINGITNS);
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
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public Integer getItns()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ITNS));
  }
  
  public void validateItns()
  {
    this.validateAttribute(ITNS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getItnsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(ITNS);
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
  
  public Boolean getKnowWashFrequency()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(KNOWWASHFREQUENCY));
  }
  
  public void validateKnowWashFrequency()
  {
    this.validateAttribute(KNOWWASHFREQUENCY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getKnowWashFrequencyMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(KNOWWASHFREQUENCY);
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
  
  public Integer getMonthReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MONTHRECEIVED));
  }
  
  public void validateMonthReceived()
  {
    this.validateAttribute(MONTHRECEIVED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getMonthReceivedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(MONTHRECEIVED);
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
  
  public Boolean getNetsObtained()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(NETSOBTAINED));
  }
  
  public void validateNetsObtained()
  {
    this.validateAttribute(NETSOBTAINED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNetsObtainedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(NETSOBTAINED);
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
  
  public Integer getOtherItns()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OTHERITNS));
  }
  
  public void validateOtherItns()
  {
    this.validateAttribute(OTHERITNS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOtherItnsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(OTHERITNS);
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
  
  public Integer getPregnantResidents()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREGNANTRESIDENTS));
  }
  
  public void validatePregnantResidents()
  {
    this.validateAttribute(PREGNANTRESIDENTS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPregnantResidentsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(PREGNANTRESIDENTS);
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
  
  public String getQuestionnaireNumber()
  {
    return getValue(QUESTIONNAIRENUMBER);
  }
  
  public void validateQuestionnaireNumber()
  {
    this.validateAttribute(QUESTIONNAIRENUMBER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getQuestionnaireNumberMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(QUESTIONNAIRENUMBER);
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
  
  public Integer getResidents()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RESIDENTS));
  }
  
  public void validateResidents()
  {
    this.validateAttribute(RESIDENTS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getResidentsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(RESIDENTS);
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
  
  public Boolean getRetreated()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RETREATED));
  }
  
  public void validateRetreated()
  {
    this.validateAttribute(RETREATED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getRetreatedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(RETREATED);
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
  
  public dss.vector.solutions.ontology.Term getRetreatmentPeriod()
  {
    if (getValue(RETREATMENTPERIOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(RETREATMENTPERIOD));
    }
  }
  
  public void validateRetreatmentPeriod()
  {
    this.validateAttribute(RETREATMENTPERIOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getRetreatmentPeriodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(RETREATMENTPERIOD);
  }
  
  public void setRetreatmentPeriod(dss.vector.solutions.ontology.Term value)
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
  
  public java.util.Date getStartDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(STARTDATE));
  }
  
  public void validateStartDate()
  {
    this.validateAttribute(STARTDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getStartDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(STARTDATE);
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
  
  public String getSurveyLocation()
  {
    return getValue(SURVEYLOCATION);
  }
  
  public void validateSurveyLocation()
  {
    this.validateAttribute(SURVEYLOCATION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSurveyLocationMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(SURVEYLOCATION);
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
  
  public Boolean getUsedEveryNight()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(USEDEVERYNIGHT));
  }
  
  public void validateUsedEveryNight()
  {
    this.validateAttribute(USEDEVERYNIGHT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getUsedEveryNightMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(USEDEVERYNIGHT);
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
  
  public Integer getUsedItns()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(USEDITNS));
  }
  
  public void validateUsedItns()
  {
    this.validateAttribute(USEDITNS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getUsedItnsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(USEDITNS);
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
  
  public Integer getWashFrequency()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(WASHFREQUENCY));
  }
  
  public void validateWashFrequency()
  {
    this.validateAttribute(WASHFREQUENCY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getWashFrequencyMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(WASHFREQUENCY);
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
  
  public dss.vector.solutions.ontology.Term getWashInterval()
  {
    if (getValue(WASHINTERVAL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(WASHINTERVAL));
    }
  }
  
  public void validateWashInterval()
  {
    this.validateAttribute(WASHINTERVAL);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getWashIntervalMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(WASHINTERVAL);
  }
  
  public void setWashInterval(dss.vector.solutions.ontology.Term value)
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
  
  public dss.vector.solutions.ontology.Term getWashed()
  {
    if (getValue(WASHED).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(WASHED));
    }
  }
  
  public void validateWashed()
  {
    this.validateAttribute(WASHED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getWashedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(WASHED);
  }
  
  public void setWashed(dss.vector.solutions.ontology.Term value)
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
  
  public Integer getYearReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEARRECEIVED));
  }
  
  public void validateYearReceived()
  {
    this.validateAttribute(YEARRECEIVED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getYearReceivedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS);
    return mdClassIF.definesAttribute(YEARRECEIVED);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ITNHouseholdSurveyView get(String id)
  {
    return (ITNHouseholdSurveyView) com.terraframe.mojo.business.View.get(id);
  }
  
  public void applyAll(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNet[] nets, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroup[] targetGroups, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReason[] reasons)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void applyAll(java.lang.String id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNet[] nets, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroup[] targetGroups, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReason[] reasons)
  {
    ITNHouseholdSurveyView _instance = ITNHouseholdSurveyView.get(id);
    _instance.applyAll(nets, targetGroups, reasons);
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    ITNHouseholdSurveyView _instance = ITNHouseholdSurveyView.get(id);
    _instance.deleteConcrete();
  }
  
  public dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNet[] getITNHouseholdSurveyNets()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNet[] getITNHouseholdSurveyNets(java.lang.String id)
  {
    ITNHouseholdSurveyView _instance = ITNHouseholdSurveyView.get(id);
    return _instance.getITNHouseholdSurveyNets();
  }
  
  public dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReason[] getITNHouseholdSurveyNonUseReason()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReason[] getITNHouseholdSurveyNonUseReason(java.lang.String id)
  {
    ITNHouseholdSurveyView _instance = ITNHouseholdSurveyView.get(id);
    return _instance.getITNHouseholdSurveyNonUseReason();
  }
  
  public dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroup[] getITNHouseholdSurveyTargetGroups()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroup[] getITNHouseholdSurveyTargetGroups(java.lang.String id)
  {
    ITNHouseholdSurveyView _instance = ITNHouseholdSurveyView.get(id);
    return _instance.getITNHouseholdSurveyTargetGroups();
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewQuery getPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
