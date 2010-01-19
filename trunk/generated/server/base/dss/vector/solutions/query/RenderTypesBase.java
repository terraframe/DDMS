package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -571478751)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to RenderTypes.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class RenderTypesBase extends com.terraframe.mojo.system.EnumerationMaster implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.RenderTypes";
  private static final long serialVersionUID = -571478751;
  
  public RenderTypesBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static RenderTypesQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    RenderTypesQuery query = new RenderTypesQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static RenderTypes get(String id)
  {
    return (RenderTypes) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static RenderTypes getByKey(String key)
  {
    return (RenderTypes) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public static RenderTypes getEnumeration(String enumName)
  {
    return (RenderTypes) com.terraframe.mojo.business.Business.getEnumeration("dss.vector.solutions.query.RenderTypes",enumName);
  }
  
  public static RenderTypes lock(java.lang.String id)
  {
    RenderTypes _instance = RenderTypes.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static RenderTypes unlock(java.lang.String id)
  {
    RenderTypes _instance = RenderTypes.get(id);
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
