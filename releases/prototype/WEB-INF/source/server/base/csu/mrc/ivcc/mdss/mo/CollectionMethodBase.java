package csu.mrc.ivcc.mdss.mo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CollectionMethod.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class CollectionMethodBase extends csu.mrc.ivcc.mdss.mo.AbstractTerm implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.mo.CollectionMethod";
  private static final long serialVersionUID = 1236382963792L;
  
  public CollectionMethodBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static CollectionMethodQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    CollectionMethodQuery query = new CollectionMethodQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static CollectionMethod get(String id)
  {
    return (CollectionMethod) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static csu.mrc.ivcc.mdss.mo.CollectionMethod[] getAll()
  {
    return null;
  }
  
  public static CollectionMethod lock(java.lang.String id)
  {
    CollectionMethod _instance = CollectionMethod.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static CollectionMethod unlock(java.lang.String id)
  {
    CollectionMethod _instance = CollectionMethod.get(id);
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
