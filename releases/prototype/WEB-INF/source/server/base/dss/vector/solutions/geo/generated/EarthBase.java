package dss.vector.solutions.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Earth.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class EarthBase extends dss.vector.solutions.geo.generated.GeoEntity implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.Earth";
  private static final long serialVersionUID = 1239670251907L;
  
  public EarthBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static EarthQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    EarthQuery query = new EarthQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static Earth get(String id)
  {
    return (Earth) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static dss.vector.solutions.geo.generated.Earth getEarthInstance()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.Earth.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static Earth lock(java.lang.String id)
  {
    Earth _instance = Earth.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static Earth unlock(java.lang.String id)
  {
    Earth _instance = Earth.get(id);
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
