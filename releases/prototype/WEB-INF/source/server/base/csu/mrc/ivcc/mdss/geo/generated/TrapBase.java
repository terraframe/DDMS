package csu.mrc.ivcc.mdss.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Trap.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class TrapBase extends csu.mrc.ivcc.mdss.geo.generated.GeoEntity implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.geo.generated.Trap";
  private static final long serialVersionUID = 1236803176818L;
  
  public TrapBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static TrapQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    TrapQuery query = new TrapQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static Trap get(String id)
  {
    return (Trap) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static Trap lock(java.lang.String id)
  {
    Trap _instance = Trap.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static Trap unlock(java.lang.String id)
  {
    Trap _instance = Trap.get(id);
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
