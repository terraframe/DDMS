package mdss.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SpecieMaster.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class SpecieMasterBase extends com.terraframe.mojo.system.EnumerationMaster implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "mdss.entomology.SpecieMaster";
  private static final long serialVersionUID = 1234203359040L;
  
  public SpecieMasterBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static SpecieMasterQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    SpecieMasterQuery query = new SpecieMasterQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static SpecieMaster get(String id)
  {
    return (SpecieMaster) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static SpecieMaster getEnumeration(String enumName)
  {
    return (SpecieMaster) com.terraframe.mojo.business.Business.getEnumeration("mdss.entomology.SpecieMaster",enumName);
  }
  
  public static SpecieMaster lock(java.lang.String id)
  {
    SpecieMaster _instance = SpecieMaster.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static SpecieMaster unlock(java.lang.String id)
  {
    SpecieMaster _instance = SpecieMaster.get(id);
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
