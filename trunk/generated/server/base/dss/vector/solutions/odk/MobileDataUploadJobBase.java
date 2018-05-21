package dss.vector.solutions.odk;

@com.runwaysdk.business.ClassSignature(hash = -687345080)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MobileDataUploadJob.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class MobileDataUploadJobBase extends com.runwaysdk.system.scheduler.ExecutableJob implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.odk.MobileDataUploadJob";
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String FORMTYPE = "formType";
  public static java.lang.String LASTEXPORTDATE = "lastExportDate";
  public static java.lang.String QUERYCURSOR = "queryCursor";
  private static final long serialVersionUID = -687345080;
  
  public MobileDataUploadJobBase()
  {
    super();
  }
  
  public dss.vector.solutions.general.Disease getDisease()
  {
    if (getValue(DISEASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.Disease.get(getValue(DISEASE));
    }
  }
  
  public String getDiseaseId()
  {
    return getValue(DISEASE);
  }
  
  public void validateDisease()
  {
    this.validateAttribute(DISEASE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getDiseaseMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.odk.MobileDataUploadJob.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(DISEASE);
  }
  
  public void setDisease(dss.vector.solutions.general.Disease value)
  {
    if(value == null)
    {
      setValue(DISEASE, "");
    }
    else
    {
      setValue(DISEASE, value.getId());
    }
  }
  
  public void setDiseaseId(java.lang.String id)
  {
    if(id == null)
    {
      setValue(DISEASE, "");
    }
    else
    {
      setValue(DISEASE, id);
    }
  }
  
  public String getFormType()
  {
    return getValue(FORMTYPE);
  }
  
  public void validateFormType()
  {
    this.validateAttribute(FORMTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getFormTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.odk.MobileDataUploadJob.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(FORMTYPE);
  }
  
  public void setFormType(String value)
  {
    if(value == null)
    {
      setValue(FORMTYPE, "");
    }
    else
    {
      setValue(FORMTYPE, value);
    }
  }
  
  public java.util.Date getLastExportDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTEXPORTDATE));
  }
  
  public void validateLastExportDate()
  {
    this.validateAttribute(LASTEXPORTDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF getLastExportDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.odk.MobileDataUploadJob.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF)mdClassIF.definesAttribute(LASTEXPORTDATE);
  }
  
  public void setLastExportDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(LASTEXPORTDATE, "");
    }
    else
    {
      setValue(LASTEXPORTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATETIME_FORMAT).format(value));
    }
  }
  
  public String getQueryCursor()
  {
    return getValue(QUERYCURSOR);
  }
  
  public void validateQueryCursor()
  {
    this.validateAttribute(QUERYCURSOR);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getQueryCursorMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.odk.MobileDataUploadJob.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(QUERYCURSOR);
  }
  
  public void setQueryCursor(String value)
  {
    if(value == null)
    {
      setValue(QUERYCURSOR, "");
    }
    else
    {
      setValue(QUERYCURSOR, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static MobileDataUploadJobQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    MobileDataUploadJobQuery query = new MobileDataUploadJobQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static MobileDataUploadJob get(String id)
  {
    return (MobileDataUploadJob) com.runwaysdk.business.Business.get(id);
  }
  
  public static MobileDataUploadJob getByKey(String key)
  {
    return (MobileDataUploadJob) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static MobileDataUploadJob lock(java.lang.String id)
  {
    MobileDataUploadJob _instance = MobileDataUploadJob.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static MobileDataUploadJob unlock(java.lang.String id)
  {
    MobileDataUploadJob _instance = MobileDataUploadJob.get(id);
    _instance.unlock();
    
    return _instance;
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
