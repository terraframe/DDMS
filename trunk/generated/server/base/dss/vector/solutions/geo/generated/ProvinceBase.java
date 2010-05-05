package dss.vector.solutions.geo.generated;

@com.runwaysdk.business.ClassSignature(hash = -2073096453)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Province.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ProvinceBase extends dss.vector.solutions.geo.generated.GeoEntity implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.Province";
  private static final long serialVersionUID = -2073096453;
  
  public ProvinceBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ProvinceQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    ProvinceQuery query = new ProvinceQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static Province get(String id)
  {
    return (Province) com.runwaysdk.business.Business.get(id);
  }
  
  public static Province getByKey(String key)
  {
    return (Province) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static Province lock(java.lang.String id)
  {
    Province _instance = Province.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static Province unlock(java.lang.String id)
  {
    Province _instance = Province.get(id);
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
