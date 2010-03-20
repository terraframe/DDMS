package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 1628448708)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ThresholdCalculationMethodMaster.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class ThresholdCalculationMethodMasterBase extends com.runwaysdk.system.EnumerationMaster implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.ThresholdCalculationMethodMaster";
  private static final long serialVersionUID = 1628448708;
  
  public ThresholdCalculationMethodMasterBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ThresholdCalculationMethodMasterQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    ThresholdCalculationMethodMasterQuery query = new ThresholdCalculationMethodMasterQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static ThresholdCalculationMethodMaster get(String id)
  {
    return (ThresholdCalculationMethodMaster) com.runwaysdk.business.Business.get(id);
  }
  
  public static ThresholdCalculationMethodMaster getByKey(String key)
  {
    return (ThresholdCalculationMethodMaster) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static ThresholdCalculationMethodMaster getEnumeration(String enumName)
  {
    return (ThresholdCalculationMethodMaster) com.runwaysdk.business.Business.getEnumeration("dss.vector.solutions.general.ThresholdCalculationMethodMaster",enumName);
  }
  
  public static ThresholdCalculationMethodMaster lock(java.lang.String id)
  {
    ThresholdCalculationMethodMaster _instance = ThresholdCalculationMethodMaster.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static ThresholdCalculationMethodMaster unlock(java.lang.String id)
  {
    ThresholdCalculationMethodMaster _instance = ThresholdCalculationMethodMaster.get(id);
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
