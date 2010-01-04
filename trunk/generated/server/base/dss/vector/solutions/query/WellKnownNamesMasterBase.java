package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 389194261)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to WellKnownNamesMaster.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class WellKnownNamesMasterBase extends com.terraframe.mojo.system.EnumerationMaster implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.WellKnownNamesMaster";
  private static final long serialVersionUID = 389194261;
  
  public WellKnownNamesMasterBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static WellKnownNamesMasterQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    WellKnownNamesMasterQuery query = new WellKnownNamesMasterQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static WellKnownNamesMaster get(String id)
  {
    return (WellKnownNamesMaster) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static WellKnownNamesMaster getByKey(String key)
  {
    return (WellKnownNamesMaster) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public static WellKnownNamesMaster getEnumeration(String enumName)
  {
    return (WellKnownNamesMaster) com.terraframe.mojo.business.Business.getEnumeration("dss.vector.solutions.query.WellKnownNamesMaster",enumName);
  }
  
  public static WellKnownNamesMaster lock(java.lang.String id)
  {
    WellKnownNamesMaster _instance = WellKnownNamesMaster.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static WellKnownNamesMaster unlock(java.lang.String id)
  {
    WellKnownNamesMaster _instance = WellKnownNamesMaster.get(id);
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
