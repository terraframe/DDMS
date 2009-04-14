package dss.vector.solutions.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to UnitMaster.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class UnitMasterBase extends com.terraframe.mojo.system.EnumerationMaster implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.UnitMaster";
  private static final long serialVersionUID = 1239658575964L;
  
  public UnitMasterBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static UnitMasterQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    UnitMasterQuery query = new UnitMasterQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static UnitMaster get(String id)
  {
    return (UnitMaster) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static UnitMaster getEnumeration(String enumName)
  {
    return (UnitMaster) com.terraframe.mojo.business.Business.getEnumeration("dss.vector.solutions.entomology.assay.UnitMaster",enumName);
  }
  
  public static UnitMaster lock(java.lang.String id)
  {
    UnitMaster _instance = UnitMaster.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static UnitMaster unlock(java.lang.String id)
  {
    UnitMaster _instance = UnitMaster.get(id);
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
