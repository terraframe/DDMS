package dss.vector.solutions.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to BreedingSite.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class BreedingSiteBase extends dss.vector.solutions.geo.generated.GeoEntity implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.BreedingSite";
  private static final long serialVersionUID = 1237240908618L;
  
  public BreedingSiteBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static BreedingSiteQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    BreedingSiteQuery query = new BreedingSiteQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static BreedingSite get(String id)
  {
    return (BreedingSite) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static BreedingSite lock(java.lang.String id)
  {
    BreedingSite _instance = BreedingSite.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static BreedingSite unlock(java.lang.String id)
  {
    BreedingSite _instance = BreedingSite.get(id);
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
