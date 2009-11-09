package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -771967767)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ITNDistributionView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class ITNDistributionViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.ITNDistributionView";
  public static java.lang.String BATCHNUMBER = "batchNumber";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String CURRENCYRECEIVED = "currencyReceived";
  public static java.lang.String DISTRIBUTIONDATE = "distributionDate";
  public static java.lang.String DISTRIBUTORNAME = "distributorName";
  public static java.lang.String DISTRIBUTORSURNAME = "distributorSurname";
  public static java.lang.String FACILITY = "facility";
  public static java.lang.String ID = "id";
  public static java.lang.String NET = "net";
  public static java.lang.String NUMBERSOLD = "numberSold";
  public static java.lang.String RECIPIENT = "recipient";
  public static java.lang.String SERVICE = "service";
  public static java.lang.String TARGETGROUPS = "targetGroups";
  private static final long serialVersionUID = -771967767;
  
  public ITNDistributionViewBase()
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDistributionView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDistributionView.CLASS);
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
  
  public Double getCurrencyReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(CURRENCYRECEIVED));
  }
  
  public void validateCurrencyReceived()
  {
    this.validateAttribute(CURRENCYRECEIVED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCurrencyReceivedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDistributionView.CLASS);
    return mdClassIF.definesAttribute(CURRENCYRECEIVED);
  }
  
  public void setCurrencyReceived(Double value)
  {
    if(value == null)
    {
      setValue(CURRENCYRECEIVED, "");
    }
    else
    {
      setValue(CURRENCYRECEIVED, java.lang.Double.toString(value));
    }
  }
  
  public java.util.Date getDistributionDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DISTRIBUTIONDATE));
  }
  
  public void validateDistributionDate()
  {
    this.validateAttribute(DISTRIBUTIONDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDistributionDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDistributionView.CLASS);
    return mdClassIF.definesAttribute(DISTRIBUTIONDATE);
  }
  
  public void setDistributionDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DISTRIBUTIONDATE, "");
    }
    else
    {
      setValue(DISTRIBUTIONDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public String getDistributorName()
  {
    return getValue(DISTRIBUTORNAME);
  }
  
  public void validateDistributorName()
  {
    this.validateAttribute(DISTRIBUTORNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDistributorNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDistributionView.CLASS);
    return mdClassIF.definesAttribute(DISTRIBUTORNAME);
  }
  
  public void setDistributorName(String value)
  {
    if(value == null)
    {
      setValue(DISTRIBUTORNAME, "");
    }
    else
    {
      setValue(DISTRIBUTORNAME, value);
    }
  }
  
  public String getDistributorSurname()
  {
    return getValue(DISTRIBUTORSURNAME);
  }
  
  public void validateDistributorSurname()
  {
    this.validateAttribute(DISTRIBUTORSURNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDistributorSurnameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDistributionView.CLASS);
    return mdClassIF.definesAttribute(DISTRIBUTORSURNAME);
  }
  
  public void setDistributorSurname(String value)
  {
    if(value == null)
    {
      setValue(DISTRIBUTORSURNAME, "");
    }
    else
    {
      setValue(DISTRIBUTORSURNAME, value);
    }
  }
  
  public String getFacility()
  {
    return getValue(FACILITY);
  }
  
  public void validateFacility()
  {
    this.validateAttribute(FACILITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getFacilityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDistributionView.CLASS);
    return mdClassIF.definesAttribute(FACILITY);
  }
  
  public void setFacility(String value)
  {
    if(value == null)
    {
      setValue(FACILITY, "");
    }
    else
    {
      setValue(FACILITY, value);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDistributionView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public dss.vector.solutions.ontology.Term getNet()
  {
    if (getValue(NET).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(NET));
    }
  }
  
  public void validateNet()
  {
    this.validateAttribute(NET);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNetMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDistributionView.CLASS);
    return mdClassIF.definesAttribute(NET);
  }
  
  public void setNet(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(NET, "");
    }
    else
    {
      setValue(NET, value.getId());
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDistributionView.CLASS);
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
  
  public dss.vector.solutions.intervention.monitor.ITNRecipient getRecipient()
  {
    if (getValue(RECIPIENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.ITNRecipient.get(getValue(RECIPIENT));
    }
  }
  
  public void validateRecipient()
  {
    this.validateAttribute(RECIPIENT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getRecipientMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDistributionView.CLASS);
    return mdClassIF.definesAttribute(RECIPIENT);
  }
  
  public void setRecipient(dss.vector.solutions.intervention.monitor.ITNRecipient value)
  {
    if(value == null)
    {
      setValue(RECIPIENT, "");
    }
    else
    {
      setValue(RECIPIENT, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getService()
  {
    if (getValue(SERVICE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(SERVICE));
    }
  }
  
  public void validateService()
  {
    this.validateAttribute(SERVICE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getServiceMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDistributionView.CLASS);
    return mdClassIF.definesAttribute(SERVICE);
  }
  
  public void setService(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(SERVICE, "");
    }
    else
    {
      setValue(SERVICE, value.getId());
    }
  }
  
  public Integer getTargetGroups()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGETGROUPS));
  }
  
  public void validateTargetGroups()
  {
    this.validateAttribute(TARGETGROUPS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTargetGroupsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.ITNDistributionView.CLASS);
    return mdClassIF.definesAttribute(TARGETGROUPS);
  }
  
  public void setTargetGroups(Integer value)
  {
    if(value == null)
    {
      setValue(TARGETGROUPS, "");
    }
    else
    {
      setValue(TARGETGROUPS, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ITNDistributionView get(String id)
  {
    return (ITNDistributionView) com.terraframe.mojo.business.View.get(id);
  }
  
  public void applyAll(dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroup[] targetGroups)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDistributionView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void applyAll(java.lang.String id, dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroup[] targetGroups)
  {
    ITNDistributionView _instance = ITNDistributionView.get(id);
    _instance.applyAll(targetGroups);
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDistributionView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    ITNDistributionView _instance = ITNDistributionView.get(id);
    _instance.deleteConcrete();
  }
  
  public dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroup[] getDistributionTargetGroups()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDistributionView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroup[] getDistributionTargetGroups(java.lang.String id)
  {
    ITNDistributionView _instance = ITNDistributionView.get(id);
    return _instance.getDistributionTargetGroups();
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
