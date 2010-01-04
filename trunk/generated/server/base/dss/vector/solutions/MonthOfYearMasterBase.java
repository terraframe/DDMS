package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = -1797435626)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MonthOfYearMaster.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MonthOfYearMasterBase extends com.terraframe.mojo.system.EnumerationMaster implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.MonthOfYearMaster";
  private static final long serialVersionUID = -1797435626;
  
  public MonthOfYearMasterBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static MonthOfYearMasterQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    MonthOfYearMasterQuery query = new MonthOfYearMasterQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static MonthOfYearMaster get(String id)
  {
    return (MonthOfYearMaster) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static MonthOfYearMaster getByKey(String key)
  {
    return (MonthOfYearMaster) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public static MonthOfYearMaster getEnumeration(String enumName)
  {
    return (MonthOfYearMaster) com.terraframe.mojo.business.Business.getEnumeration("dss.vector.solutions.MonthOfYearMaster",enumName);
  }
  
  public static MonthOfYearMaster lock(java.lang.String id)
  {
    MonthOfYearMaster _instance = MonthOfYearMaster.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static MonthOfYearMaster unlock(java.lang.String id)
  {
    MonthOfYearMaster _instance = MonthOfYearMaster.get(id);
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
