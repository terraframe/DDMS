package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = -744921447)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to EpiDate.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class EpiDateBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.EpiDate";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String EPIYEAR = "epiYear";
  public static java.lang.String ID = "id";
  public static java.lang.String PERIOD = "period";
  public static java.lang.String PERIODTYPE = "periodType";
  public static java.lang.String STARTDATE = "startDate";
  private static final long serialVersionUID = -744921447;
  
  public EpiDateBase()
  {
    super();
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.EpiDate.CLASS);
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
  
  public Integer getEpiYear()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(EPIYEAR));
  }
  
  public void validateEpiYear()
  {
    this.validateAttribute(EPIYEAR);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEpiYearMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.EpiDate.CLASS);
    return mdClassIF.definesAttribute(EPIYEAR);
  }
  
  public void setEpiYear(Integer value)
  {
    if(value == null)
    {
      setValue(EPIYEAR, "");
    }
    else
    {
      setValue(EPIYEAR, java.lang.Integer.toString(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.EpiDate.CLASS);
    return mdClassIF.definesAttribute(ID);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.EpiDate.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.EpiDate.CLASS);
    return mdClassIF.definesAttribute(PERIODTYPE);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.EpiDate.CLASS);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static EpiDate get(String id)
  {
    return (EpiDate) com.terraframe.mojo.business.View.get(id);
  }
  
  public static dss.vector.solutions.general.EpiDate getInstanceByDate(java.util.Date startDate, java.util.Date endDate)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.EpiDate.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.general.EpiDate getInstanceByPeriod(dss.vector.solutions.surveillance.PeriodType periodType, java.lang.Integer period, java.lang.Integer epiYear)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.EpiDate.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public java.lang.Integer getNumberOfEpiWeeks()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.EpiDate.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.Integer getNumberOfEpiWeeks(java.lang.String id)
  {
    EpiDate _instance = EpiDate.get(id);
    return _instance.getNumberOfEpiWeeks();
  }
  
  public static java.util.Date snapToEpiWeek(java.util.Date startDate, java.lang.Boolean snapToFirstDay)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.EpiDate.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.util.Date snapToMonth(java.util.Date startDate, java.lang.Boolean snapToFirstDay)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.EpiDate.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.util.Date snapToQuarter(java.util.Date startDate, java.lang.Boolean snapToFirstDay)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.EpiDate.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.util.Date snapToSeason(java.util.Date startDate, java.lang.Boolean snapToFirstDay)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.EpiDate.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.util.Date snapToYear(java.util.Date startDate, java.lang.Boolean snapToFirstDay)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.EpiDate.java";
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
