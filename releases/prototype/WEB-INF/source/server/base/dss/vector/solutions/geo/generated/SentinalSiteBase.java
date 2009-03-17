package dss.vector.solutions.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SentinalSite.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class SentinalSiteBase extends dss.vector.solutions.geo.generated.AbstractSite implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.SentinalSite";
  private static final long serialVersionUID = 1237311438279L;
  
  public SentinalSiteBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static SentinalSiteQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    SentinalSiteQuery query = new SentinalSiteQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static SentinalSite get(String id)
  {
    return (SentinalSite) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static SentinalSite lock(java.lang.String id)
  {
    SentinalSite _instance = SentinalSite.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static SentinalSite unlock(java.lang.String id)
  {
    SentinalSite _instance = SentinalSite.get(id);
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
