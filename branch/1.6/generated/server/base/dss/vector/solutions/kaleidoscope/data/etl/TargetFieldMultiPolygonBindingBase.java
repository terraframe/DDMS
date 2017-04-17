package dss.vector.solutions.kaleidoscope.data.etl;

@com.runwaysdk.business.ClassSignature(hash = -1754550410)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TargetFieldMultiPolygonBinding.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class TargetFieldMultiPolygonBindingBase extends dss.vector.solutions.kaleidoscope.data.etl.TargetFieldCoordinateBinding implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.data.etl.TargetFieldMultiPolygonBinding";
  private static final long serialVersionUID = -1754550410;
  
  public TargetFieldMultiPolygonBindingBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static TargetFieldMultiPolygonBindingQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    TargetFieldMultiPolygonBindingQuery query = new TargetFieldMultiPolygonBindingQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static TargetFieldMultiPolygonBinding get(String id)
  {
    return (TargetFieldMultiPolygonBinding) com.runwaysdk.business.Business.get(id);
  }
  
  public static TargetFieldMultiPolygonBinding getByKey(String key)
  {
    return (TargetFieldMultiPolygonBinding) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static TargetFieldMultiPolygonBinding lock(java.lang.String id)
  {
    TargetFieldMultiPolygonBinding _instance = TargetFieldMultiPolygonBinding.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static TargetFieldMultiPolygonBinding unlock(java.lang.String id)
  {
    TargetFieldMultiPolygonBinding _instance = TargetFieldMultiPolygonBinding.get(id);
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
