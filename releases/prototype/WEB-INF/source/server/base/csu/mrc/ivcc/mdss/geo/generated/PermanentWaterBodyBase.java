package csu.mrc.ivcc.mdss.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PermanentWaterBody.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class PermanentWaterBodyBase extends csu.mrc.ivcc.mdss.geo.generated.BreedingSite implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.geo.generated.PermanentWaterBody";
  private static final long serialVersionUID = 1237219388975L;
  
  public PermanentWaterBodyBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static PermanentWaterBodyQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    PermanentWaterBodyQuery query = new PermanentWaterBodyQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static PermanentWaterBody get(String id)
  {
    return (PermanentWaterBody) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static PermanentWaterBody lock(java.lang.String id)
  {
    PermanentWaterBody _instance = PermanentWaterBody.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static PermanentWaterBody unlock(java.lang.String id)
  {
    PermanentWaterBody _instance = PermanentWaterBody.get(id);
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
