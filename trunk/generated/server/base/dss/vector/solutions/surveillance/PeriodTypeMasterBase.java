package dss.vector.solutions.surveillance;

@com.runwaysdk.business.ClassSignature(hash = 733426139)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PeriodTypeMaster.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class PeriodTypeMasterBase extends com.runwaysdk.system.EnumerationMaster implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.PeriodTypeMaster";
  public static java.lang.String MAXIMUMPERIOD = "maximumPeriod";
  private static final long serialVersionUID = 733426139;
  
  public PeriodTypeMasterBase()
  {
    super();
  }
  
  public Integer getMaximumPeriod()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MAXIMUMPERIOD));
  }
  
  public void validateMaximumPeriod()
  {
    this.validateAttribute(MAXIMUMPERIOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getMaximumPeriodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.PeriodTypeMaster.CLASS);
    return mdClassIF.definesAttribute(MAXIMUMPERIOD);
  }
  
  public void setMaximumPeriod(Integer value)
  {
    if(value == null)
    {
      setValue(MAXIMUMPERIOD, "");
    }
    else
    {
      setValue(MAXIMUMPERIOD, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static PeriodTypeMasterQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    PeriodTypeMasterQuery query = new PeriodTypeMasterQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static PeriodTypeMaster get(String id)
  {
    return (PeriodTypeMaster) com.runwaysdk.business.Business.get(id);
  }
  
  public static PeriodTypeMaster getByKey(String key)
  {
    return (PeriodTypeMaster) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static PeriodTypeMaster getEnumeration(String enumName)
  {
    return (PeriodTypeMaster) com.runwaysdk.business.Business.getEnumeration(dss.vector.solutions.surveillance.PeriodTypeMaster.CLASS ,enumName);
  }
  
  public static PeriodTypeMaster lock(java.lang.String id)
  {
    PeriodTypeMaster _instance = PeriodTypeMaster.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static PeriodTypeMaster unlock(java.lang.String id)
  {
    PeriodTypeMaster _instance = PeriodTypeMaster.get(id);
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
