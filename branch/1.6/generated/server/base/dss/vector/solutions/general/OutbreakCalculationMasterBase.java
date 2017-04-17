package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 1687586523)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to OutbreakCalculationMaster.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class OutbreakCalculationMasterBase extends com.runwaysdk.system.EnumerationMaster implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.OutbreakCalculationMaster";
  private static final long serialVersionUID = 1687586523;
  
  public OutbreakCalculationMasterBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static OutbreakCalculationMasterQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    OutbreakCalculationMasterQuery query = new OutbreakCalculationMasterQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static OutbreakCalculationMaster get(String id)
  {
    return (OutbreakCalculationMaster) com.runwaysdk.business.Business.get(id);
  }
  
  public static OutbreakCalculationMaster getByKey(String key)
  {
    return (OutbreakCalculationMaster) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static OutbreakCalculationMaster getEnumeration(String enumName)
  {
    return (OutbreakCalculationMaster) com.runwaysdk.business.Business.getEnumeration(dss.vector.solutions.general.OutbreakCalculationMaster.CLASS ,enumName);
  }
  
  public static OutbreakCalculationMaster lock(java.lang.String id)
  {
    OutbreakCalculationMaster _instance = OutbreakCalculationMaster.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static OutbreakCalculationMaster unlock(java.lang.String id)
  {
    OutbreakCalculationMaster _instance = OutbreakCalculationMaster.get(id);
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
