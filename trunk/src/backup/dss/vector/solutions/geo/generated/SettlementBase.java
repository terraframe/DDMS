package dss.vector.solutions.geo.generated;

@com.runwaysdk.business.ClassSignature(hash = -1416669918)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Settlement.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class SettlementBase extends dss.vector.solutions.geo.generated.GeoEntity implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.Settlement";
  private static final long serialVersionUID = -1416669918;
  
  public SettlementBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static SettlementQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    SettlementQuery query = new SettlementQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static Settlement get(String id)
  {
    return (Settlement) com.runwaysdk.business.Business.get(id);
  }
  
  public static Settlement getByKey(String key)
  {
    return (Settlement) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static Settlement lock(java.lang.String id)
  {
    Settlement _instance = Settlement.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static Settlement unlock(java.lang.String id)
  {
    Settlement _instance = Settlement.get(id);
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
