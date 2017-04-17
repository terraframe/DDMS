package dss.vector.solutions.kaleidoscope.data.etl;

@com.runwaysdk.business.ClassSignature(hash = -1949306550)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TargetFieldGeneratedBinding.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class TargetFieldGeneratedBindingBase extends dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBinding implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeneratedBinding";
  private static final long serialVersionUID = -1949306550;
  
  public TargetFieldGeneratedBindingBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static TargetFieldGeneratedBindingQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    TargetFieldGeneratedBindingQuery query = new TargetFieldGeneratedBindingQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static TargetFieldGeneratedBinding get(String id)
  {
    return (TargetFieldGeneratedBinding) com.runwaysdk.business.Business.get(id);
  }
  
  public static TargetFieldGeneratedBinding getByKey(String key)
  {
    return (TargetFieldGeneratedBinding) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static TargetFieldGeneratedBinding lock(java.lang.String id)
  {
    TargetFieldGeneratedBinding _instance = TargetFieldGeneratedBinding.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static TargetFieldGeneratedBinding unlock(java.lang.String id)
  {
    TargetFieldGeneratedBinding _instance = TargetFieldGeneratedBinding.get(id);
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
