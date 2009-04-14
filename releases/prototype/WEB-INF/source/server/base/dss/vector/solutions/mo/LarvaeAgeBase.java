package dss.vector.solutions.mo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LarvaeAge.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class LarvaeAgeBase extends dss.vector.solutions.mo.AbstractTerm implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.mo.LarvaeAge";
  private static final long serialVersionUID = 1239658647646L;
  
  public LarvaeAgeBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static LarvaeAgeQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    LarvaeAgeQuery query = new LarvaeAgeQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static LarvaeAge get(String id)
  {
    return (LarvaeAge) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static dss.vector.solutions.mo.LarvaeAge[] getAll()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.mo.LarvaeAge.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static LarvaeAge lock(java.lang.String id)
  {
    LarvaeAge _instance = LarvaeAge.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static LarvaeAge unlock(java.lang.String id)
  {
    LarvaeAge _instance = LarvaeAge.get(id);
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
