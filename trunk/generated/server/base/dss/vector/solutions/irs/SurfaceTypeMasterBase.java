package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -324924970)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SurfaceTypeMaster.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class SurfaceTypeMasterBase extends com.terraframe.mojo.system.EnumerationMaster implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.SurfaceTypeMaster";
  private static final long serialVersionUID = -324924970;
  
  public SurfaceTypeMasterBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static SurfaceTypeMasterQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    SurfaceTypeMasterQuery query = new SurfaceTypeMasterQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static SurfaceTypeMaster get(String id)
  {
    return (SurfaceTypeMaster) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static SurfaceTypeMaster getByKey(String key)
  {
    return (SurfaceTypeMaster) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public static SurfaceTypeMaster getEnumeration(String enumName)
  {
    return (SurfaceTypeMaster) com.terraframe.mojo.business.Business.getEnumeration("dss.vector.solutions.irs.SurfaceTypeMaster",enumName);
  }
  
  public static SurfaceTypeMaster lock(java.lang.String id)
  {
    SurfaceTypeMaster _instance = SurfaceTypeMaster.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static SurfaceTypeMaster unlock(java.lang.String id)
  {
    SurfaceTypeMaster _instance = SurfaceTypeMaster.get(id);
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
