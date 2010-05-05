package dss.vector.solutions.geo.generated;

@com.runwaysdk.business.ClassSignature(hash = -667532843)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SprayArea.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class SprayAreaBase extends dss.vector.solutions.geo.generated.GeoEntity implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.SprayArea";
  private static final long serialVersionUID = -667532843;
  
  public SprayAreaBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static SprayAreaQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    SprayAreaQuery query = new SprayAreaQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static SprayArea get(String id)
  {
    return (SprayArea) com.runwaysdk.business.Business.get(id);
  }
  
  public static SprayArea getByKey(String key)
  {
    return (SprayArea) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static SprayArea lock(java.lang.String id)
  {
    SprayArea _instance = SprayArea.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static SprayArea unlock(java.lang.String id)
  {
    SprayArea _instance = SprayArea.get(id);
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
