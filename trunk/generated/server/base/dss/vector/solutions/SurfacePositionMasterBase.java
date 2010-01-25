package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = 908867950)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SurfacePositionMaster.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class SurfacePositionMasterBase extends com.terraframe.mojo.system.EnumerationMaster implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.SurfacePositionMaster";
  private static final long serialVersionUID = 908867950;
  
  public SurfacePositionMasterBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static SurfacePositionMasterQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    SurfacePositionMasterQuery query = new SurfacePositionMasterQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static SurfacePositionMaster get(String id)
  {
    return (SurfacePositionMaster) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static SurfacePositionMaster getByKey(String key)
  {
    return (SurfacePositionMaster) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public static SurfacePositionMaster getEnumeration(String enumName)
  {
    return (SurfacePositionMaster) com.terraframe.mojo.business.Business.getEnumeration("dss.vector.solutions.SurfacePositionMaster",enumName);
  }
  
  public static SurfacePositionMaster lock(java.lang.String id)
  {
    SurfacePositionMaster _instance = SurfacePositionMaster.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static SurfacePositionMaster unlock(java.lang.String id)
  {
    SurfacePositionMaster _instance = SurfacePositionMaster.get(id);
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
