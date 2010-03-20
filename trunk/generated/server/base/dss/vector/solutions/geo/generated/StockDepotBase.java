package dss.vector.solutions.geo.generated;

@com.runwaysdk.business.ClassSignature(hash = 1658100397)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to StockDepot.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class StockDepotBase extends dss.vector.solutions.geo.generated.GeoEntity implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.StockDepot";
  private static final long serialVersionUID = 1658100397;
  
  public StockDepotBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static StockDepotQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    StockDepotQuery query = new StockDepotQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static StockDepot get(String id)
  {
    return (StockDepot) com.runwaysdk.business.Business.get(id);
  }
  
  public static StockDepot getByKey(String key)
  {
    return (StockDepot) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static StockDepot lock(java.lang.String id)
  {
    StockDepot _instance = StockDepot.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static StockDepot unlock(java.lang.String id)
  {
    StockDepot _instance = StockDepot.get(id);
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
