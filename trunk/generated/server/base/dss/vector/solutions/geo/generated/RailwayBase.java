package dss.vector.solutions.geo.generated;

@com.runwaysdk.business.ClassSignature(hash = -1786454798)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Railway.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class RailwayBase extends dss.vector.solutions.geo.generated.GeoEntity implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.Railway";
  private static final long serialVersionUID = -1786454798;
  
  public RailwayBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static RailwayQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    RailwayQuery query = new RailwayQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static Railway get(String id)
  {
    return (Railway) com.runwaysdk.business.Business.get(id);
  }
  
  public static Railway getByKey(String key)
  {
    return (Railway) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static Railway lock(java.lang.String id)
  {
    Railway _instance = Railway.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static Railway unlock(java.lang.String id)
  {
    Railway _instance = Railway.get(id);
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
