package dss.vector.solutions.stock;

@com.runwaysdk.business.ClassSignature(hash = 728469659)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to EventOptionMaster.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class EventOptionMasterBase extends com.runwaysdk.system.EnumerationMaster implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.stock.EventOptionMaster";
  private static final long serialVersionUID = 728469659;
  
  public EventOptionMasterBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static EventOptionMasterQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    EventOptionMasterQuery query = new EventOptionMasterQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static EventOptionMaster get(String id)
  {
    return (EventOptionMaster) com.runwaysdk.business.Business.get(id);
  }
  
  public static EventOptionMaster getByKey(String key)
  {
    return (EventOptionMaster) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static EventOptionMaster getEnumeration(String enumName)
  {
    return (EventOptionMaster) com.runwaysdk.business.Business.getEnumeration(dss.vector.solutions.stock.EventOptionMaster.CLASS ,enumName);
  }
  
  public static EventOptionMaster lock(java.lang.String id)
  {
    EventOptionMaster _instance = EventOptionMaster.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static EventOptionMaster unlock(java.lang.String id)
  {
    EventOptionMaster _instance = EventOptionMaster.get(id);
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
