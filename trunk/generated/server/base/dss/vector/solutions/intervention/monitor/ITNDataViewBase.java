package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -12443346)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ITNDataView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class ITNDataViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.ITNDataView";
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
  private static final long serialVersionUID = -12443346;
  
  public ITNDataViewBase()
  {
    super();
  }
  
  public String getBatchNumber()
  {
    return getValue(BATCHNUMBER);
  }
  
  public void validateBatchNumber()
  {
    this.validateAttribute(BATCHNUMBER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getBatchNumberMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDataView.CLASS);
    return mdClassIF.definesAttribute(BATCHNUMBER);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDataView.CLASS);
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
  
  public java.math.BigDecimal getCurrencyReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(CURRENCYRECEIVED));
  }
  
  public void validateCurrencyReceived()
  {
    this.validateAttribute(CURRENCYRECEIVED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCurrencyReceivedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDataView.CLASS);
    return mdClassIF.definesAttribute(CURRENCYRECEIVED);
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
  
  public Integer getDisplayNets()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DISPLAYNETS));
  }
  
  public void validateDisplayNets()
  {
    this.validateAttribute(DISPLAYNETS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDisplayNetsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDataView.CLASS);
    return mdClassIF.definesAttribute(DISPLAYNETS);
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
  
  public Integer getDisplayServices()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DISPLAYSERVICES));
  }
  
  public void validateDisplayServices()
  {
    this.validateAttribute(DISPLAYSERVICES);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDisplayServicesMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDataView.CLASS);
    return mdClassIF.definesAttribute(DISPLAYSERVICES);
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
  
  public Integer getDisplayTargetGroups()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DISPLAYTARGETGROUPS));
  }
  
  public void validateDisplayTargetGroups()
  {
    this.validateAttribute(DISPLAYTARGETGROUPS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDisplayTargetGroupsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDataView.CLASS);
    return mdClassIF.definesAttribute(DISPLAYTARGETGROUPS);
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
  
  public String getGeoId()
  {
    return getValue(GEOID);
  }
  
  public void validateGeoId()
  {
    this.validateAttribute(GEOID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGeoIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDataView.CLASS);
    return mdClassIF.definesAttribute(GEOID);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDataView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public Integer getNumberDistributed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERDISTRIBUTED));
  }
  
  public void validateNumberDistributed()
  {
    this.validateAttribute(NUMBERDISTRIBUTED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNumberDistributedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDataView.CLASS);
    return mdClassIF.definesAttribute(NUMBERDISTRIBUTED);
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
  
  public Integer getNumberSold()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERSOLD));
  }
  
  public void validateNumberSold()
  {
    this.validateAttribute(NUMBERSOLD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNumberSoldMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDataView.CLASS);
    return mdClassIF.definesAttribute(NUMBERSOLD);
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
  
  public Integer getPeriod()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIOD));
  }
  
  public void validatePeriod()
  {
    this.validateAttribute(PERIOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPeriodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDataView.CLASS);
    return mdClassIF.definesAttribute(PERIOD);
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
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.surveillance.PeriodType> getPeriodType()
  {
    return (java.util.List<dss.vector.solutions.surveillance.PeriodType>) getEnumValues(PERIODTYPE);
  }
  
  public void addPeriodType(dss.vector.solutions.surveillance.PeriodType value)
  {
    if(value != null)
    {
      addEnumItem(PERIODTYPE, value.getId());
    }
  }
  
  public void removePeriodType(dss.vector.solutions.surveillance.PeriodType value)
  {
    if(value != null)
    {
      removeEnumItem(PERIODTYPE, value.getId());
    }
  }
  
  public void clearPeriodType()
  {
    clearEnum(PERIODTYPE);
  }
  
  public void validatePeriodType()
  {
    this.validateAttribute(PERIODTYPE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPeriodTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDataView.CLASS);
    return mdClassIF.definesAttribute(PERIODTYPE);
  }
  
  public Integer getPeriodYear()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIODYEAR));
  }
  
  public void validatePeriodYear()
  {
    this.validateAttribute(PERIODYEAR);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPeriodYearMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDataView.CLASS);
    return mdClassIF.definesAttribute(PERIODYEAR);
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
  
  public Integer getReceivedForCommunityResponse()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RECEIVEDFORCOMMUNITYRESPONSE));
  }
  
  public void validateReceivedForCommunityResponse()
  {
    this.validateAttribute(RECEIVEDFORCOMMUNITYRESPONSE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getReceivedForCommunityResponseMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDataView.CLASS);
    return mdClassIF.definesAttribute(RECEIVEDFORCOMMUNITYRESPONSE);
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
  
  public Integer getReceivedForTargetGroups()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RECEIVEDFORTARGETGROUPS));
  }
  
  public void validateReceivedForTargetGroups()
  {
    this.validateAttribute(RECEIVEDFORTARGETGROUPS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getReceivedForTargetGroupsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDataView.CLASS);
    return mdClassIF.definesAttribute(RECEIVEDFORTARGETGROUPS);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ITNDataView get(String id)
  {
    return (ITNDataView) com.terraframe.mojo.business.View.get(id);
  }
  
  public void applyAll(dss.vector.solutions.intervention.monitor.ITNNet[] nets, dss.vector.solutions.intervention.monitor.ITNTargetGroup[] targetGroups, dss.vector.solutions.intervention.monitor.ITNService[] services)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void applyAll(java.lang.String id, dss.vector.solutions.intervention.monitor.ITNNet[] nets, dss.vector.solutions.intervention.monitor.ITNTargetGroup[] targetGroups, dss.vector.solutions.intervention.monitor.ITNService[] services)
  {
    ITNDataView _instance = ITNDataView.get(id);
    _instance.applyAll(nets, targetGroups, services);
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    ITNDataView _instance = ITNDataView.get(id);
    _instance.deleteConcrete();
  }
  
  public dss.vector.solutions.intervention.monitor.ITNNet[] getITNNets()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNNet[] getITNNets(java.lang.String id)
  {
    ITNDataView _instance = ITNDataView.get(id);
    return _instance.getITNNets();
  }
  
  public dss.vector.solutions.intervention.monitor.ITNService[] getITNServices()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNService[] getITNServices(java.lang.String id)
  {
    ITNDataView _instance = ITNDataView.get(id);
    return _instance.getITNServices();
  }
  
  public dss.vector.solutions.intervention.monitor.ITNTargetGroup[] getITNTargetGroups()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNTargetGroup[] getITNTargetGroups(java.lang.String id)
  {
    ITNDataView _instance = ITNDataView.get(id);
    return _instance.getITNTargetGroups();
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
