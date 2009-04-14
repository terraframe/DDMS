package dss.vector.solutions.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to NonSentinalSite.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class NonSentinalSiteBase extends dss.vector.solutions.geo.generated.AbstractSite implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.NonSentinalSite";
  private static final long serialVersionUID = 1239670265631L;
  
  public NonSentinalSiteBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static NonSentinalSiteQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    NonSentinalSiteQuery query = new NonSentinalSiteQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static NonSentinalSite get(String id)
  {
    return (NonSentinalSite) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static NonSentinalSite lock(java.lang.String id)
  {
    NonSentinalSite _instance = NonSentinalSite.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static NonSentinalSite unlock(java.lang.String id)
  {
    NonSentinalSite _instance = NonSentinalSite.get(id);
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
