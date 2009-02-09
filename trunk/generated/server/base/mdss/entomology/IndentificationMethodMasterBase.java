package mdss.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to IndentificationMethodMaster.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class IndentificationMethodMasterBase extends com.terraframe.mojo.system.EnumerationMaster implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "mdss.entomology.IndentificationMethodMaster";
  private static final long serialVersionUID = 1234203357163L;
  
  public IndentificationMethodMasterBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static IndentificationMethodMasterQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    IndentificationMethodMasterQuery query = new IndentificationMethodMasterQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static IndentificationMethodMaster get(String id)
  {
    return (IndentificationMethodMaster) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static IndentificationMethodMaster getEnumeration(String enumName)
  {
    return (IndentificationMethodMaster) com.terraframe.mojo.business.Business.getEnumeration("mdss.entomology.IndentificationMethodMaster",enumName);
  }
  
  public static IndentificationMethodMaster lock(java.lang.String id)
  {
    IndentificationMethodMaster _instance = IndentificationMethodMaster.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static IndentificationMethodMaster unlock(java.lang.String id)
  {
    IndentificationMethodMaster _instance = IndentificationMethodMaster.get(id);
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
