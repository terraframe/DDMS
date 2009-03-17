package dss.vector.solutions.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AdminPost.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class AdminPostBase extends dss.vector.solutions.geo.generated.GeoEntity implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.AdminPost";
  private static final long serialVersionUID = 1237314868253L;
  
  public AdminPostBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static AdminPostQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    AdminPostQuery query = new AdminPostQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static AdminPost get(String id)
  {
    return (AdminPost) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static AdminPost lock(java.lang.String id)
  {
    AdminPost _instance = AdminPost.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static AdminPost unlock(java.lang.String id)
  {
    AdminPost _instance = AdminPost.get(id);
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
