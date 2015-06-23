package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = -938691288)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CycleJob.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class CycleJobBase extends com.runwaysdk.system.scheduler.ExecutableJob implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.CycleJob";
  public static java.lang.String IMAGEHEIGHT = "imageHeight";
  public static java.lang.String IMAGEWIDTH = "imageWidth";
  public static java.lang.String JOBNAME = "jobName";
  public static java.lang.String LAYERID = "layerId";
  public static java.lang.String SAVEDMAP = "savedMap";
  private static final long serialVersionUID = -938691288;
  
  public CycleJobBase()
  {
    super();
  }
  
  public Integer getImageHeight()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(IMAGEHEIGHT));
  }
  
  public void validateImageHeight()
  {
    this.validateAttribute(IMAGEHEIGHT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getImageHeightMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.CycleJob.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(IMAGEHEIGHT);
  }
  
  public void setImageHeight(Integer value)
  {
    if(value == null)
    {
      setValue(IMAGEHEIGHT, "");
    }
    else
    {
      setValue(IMAGEHEIGHT, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getImageWidth()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(IMAGEWIDTH));
  }
  
  public void validateImageWidth()
  {
    this.validateAttribute(IMAGEWIDTH);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getImageWidthMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.CycleJob.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(IMAGEWIDTH);
  }
  
  public void setImageWidth(Integer value)
  {
    if(value == null)
    {
      setValue(IMAGEWIDTH, "");
    }
    else
    {
      setValue(IMAGEWIDTH, java.lang.Integer.toString(value));
    }
  }
  
  public String getJobName()
  {
    return getValue(JOBNAME);
  }
  
  public void validateJobName()
  {
    this.validateAttribute(JOBNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getJobNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.CycleJob.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(JOBNAME);
  }
  
  public void setJobName(String value)
  {
    if(value == null)
    {
      setValue(JOBNAME, "");
    }
    else
    {
      setValue(JOBNAME, value);
    }
  }
  
  public String getLayerId()
  {
    return getValue(LAYERID);
  }
  
  public void validateLayerId()
  {
    this.validateAttribute(LAYERID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getLayerIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.CycleJob.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(LAYERID);
  }
  
  public void setLayerId(String value)
  {
    if(value == null)
    {
      setValue(LAYERID, "");
    }
    else
    {
      setValue(LAYERID, value);
    }
  }
  
  public dss.vector.solutions.query.SavedMap getSavedMap()
  {
    if (getValue(SAVEDMAP).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.query.SavedMap.get(getValue(SAVEDMAP));
    }
  }
  
  public String getSavedMapId()
  {
    return getValue(SAVEDMAP);
  }
  
  public void validateSavedMap()
  {
    this.validateAttribute(SAVEDMAP);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getSavedMapMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.CycleJob.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(SAVEDMAP);
  }
  
  public void setSavedMap(dss.vector.solutions.query.SavedMap value)
  {
    if(value == null)
    {
      setValue(SAVEDMAP, "");
    }
    else
    {
      setValue(SAVEDMAP, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static CycleJobQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    CycleJobQuery query = new CycleJobQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static CycleJob get(String id)
  {
    return (CycleJob) com.runwaysdk.business.Business.get(id);
  }
  
  public static CycleJob getByKey(String key)
  {
    return (CycleJob) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static CycleJob lock(java.lang.String id)
  {
    CycleJob _instance = CycleJob.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static CycleJob unlock(java.lang.String id)
  {
    CycleJob _instance = CycleJob.get(id);
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
