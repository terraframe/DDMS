package csu.mrc.ivcc.mdss.geo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TerrainMaster.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class TerrainMasterBase extends com.terraframe.mojo.system.EnumerationMaster implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.geo.TerrainMaster";
  private static final long serialVersionUID = 1236360373654L;
  
  public TerrainMasterBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static TerrainMasterQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    TerrainMasterQuery query = new TerrainMasterQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static TerrainMaster get(String id)
  {
    return (TerrainMaster) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static TerrainMaster getEnumeration(String enumName)
  {
    return (TerrainMaster) com.terraframe.mojo.business.Business.getEnumeration("csu.mrc.ivcc.mdss.geo.TerrainMaster",enumName);
  }
  
  public static TerrainMaster lock(java.lang.String id)
  {
    TerrainMaster _instance = TerrainMaster.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static TerrainMaster unlock(java.lang.String id)
  {
    TerrainMaster _instance = TerrainMaster.get(id);
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
