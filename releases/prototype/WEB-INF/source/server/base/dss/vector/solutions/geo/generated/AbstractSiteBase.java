package dss.vector.solutions.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AbstractSite.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class AbstractSiteBase extends dss.vector.solutions.geo.generated.GeoEntity implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.AbstractSite";
  private static final long serialVersionUID = 1237314872104L;
  
  public AbstractSiteBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static AbstractSiteQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    AbstractSiteQuery query = new AbstractSiteQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static AbstractSite get(String id)
  {
    return (AbstractSite) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static AbstractSite lock(java.lang.String id)
  {
    AbstractSite _instance = AbstractSite.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static AbstractSite unlock(java.lang.String id)
  {
    AbstractSite _instance = AbstractSite.get(id);
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
