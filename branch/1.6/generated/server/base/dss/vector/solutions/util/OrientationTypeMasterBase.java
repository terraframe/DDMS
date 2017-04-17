package dss.vector.solutions.util;

@com.runwaysdk.business.ClassSignature(hash = 2033064871)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to OrientationTypeMaster.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class OrientationTypeMasterBase extends com.runwaysdk.system.EnumerationMaster implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.util.OrientationTypeMaster";
  private static final long serialVersionUID = 2033064871;
  
  public OrientationTypeMasterBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static OrientationTypeMasterQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    OrientationTypeMasterQuery query = new OrientationTypeMasterQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static OrientationTypeMaster get(String id)
  {
    return (OrientationTypeMaster) com.runwaysdk.business.Business.get(id);
  }
  
  public static OrientationTypeMaster getByKey(String key)
  {
    return (OrientationTypeMaster) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static OrientationTypeMaster getEnumeration(String enumName)
  {
    return (OrientationTypeMaster) com.runwaysdk.business.Business.getEnumeration(dss.vector.solutions.util.OrientationTypeMaster.CLASS ,enumName);
  }
  
  public static OrientationTypeMaster lock(java.lang.String id)
  {
    OrientationTypeMaster _instance = OrientationTypeMaster.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static OrientationTypeMaster unlock(java.lang.String id)
  {
    OrientationTypeMaster _instance = OrientationTypeMaster.get(id);
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
