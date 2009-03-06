package csu.mrc.ivcc.mdss.mo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to IdentificationMethod.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class IdentificationMethodBase extends csu.mrc.ivcc.mdss.mo.AbstractTerm implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.mo.IdentificationMethod";
  private static final long serialVersionUID = 1236360377474L;
  
  public IdentificationMethodBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static IdentificationMethodQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    IdentificationMethodQuery query = new IdentificationMethodQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static IdentificationMethod get(String id)
  {
    return (IdentificationMethod) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static csu.mrc.ivcc.mdss.mo.IdentificationMethod[] getAll()
  {
    return null;
  }
  
  public static IdentificationMethod lock(java.lang.String id)
  {
    IdentificationMethod _instance = IdentificationMethod.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static IdentificationMethod unlock(java.lang.String id)
  {
    IdentificationMethod _instance = IdentificationMethod.get(id);
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
