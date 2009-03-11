package csu.mrc.ivcc.mdss.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Locality.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class LocalityBase extends csu.mrc.ivcc.mdss.geo.generated.GeoEntity implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.geo.generated.Locality";
  private static final long serialVersionUID = 1236803156990L;
  
  public LocalityBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static LocalityQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    LocalityQuery query = new LocalityQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static Locality get(String id)
  {
    return (Locality) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static Locality lock(java.lang.String id)
  {
    Locality _instance = Locality.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static Locality unlock(java.lang.String id)
  {
    Locality _instance = Locality.get(id);
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
