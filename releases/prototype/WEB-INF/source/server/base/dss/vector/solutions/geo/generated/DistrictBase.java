package dss.vector.solutions.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to District.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class DistrictBase extends dss.vector.solutions.geo.generated.GeoEntity implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.District";
  private static final long serialVersionUID = 1238027429206L;
  
  public DistrictBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static DistrictQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    DistrictQuery query = new DistrictQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static District get(String id)
  {
    return (District) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static District lock(java.lang.String id)
  {
    District _instance = District.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static District unlock(java.lang.String id)
  {
    District _instance = District.get(id);
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
