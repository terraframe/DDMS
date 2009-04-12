package dss.vector.solutions.mo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InfectivityMethodology.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class InfectivityMethodologyBase extends dss.vector.solutions.mo.AbstractTerm implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.mo.InfectivityMethodology";
  private static final long serialVersionUID = 1239517557417L;
  
  public InfectivityMethodologyBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static InfectivityMethodologyQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    InfectivityMethodologyQuery query = new InfectivityMethodologyQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static InfectivityMethodology get(String id)
  {
    return (InfectivityMethodology) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static dss.vector.solutions.mo.InfectivityMethodology[] getAll()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.mo.InfectivityMethodology.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static InfectivityMethodology lock(java.lang.String id)
  {
    InfectivityMethodology _instance = InfectivityMethodology.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static InfectivityMethodology unlock(java.lang.String id)
  {
    InfectivityMethodology _instance = InfectivityMethodology.get(id);
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
