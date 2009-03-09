package csu.mrc.ivcc.mdss.mo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InsecticideMethodology.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class InsecticideMethodologyBase extends csu.mrc.ivcc.mdss.mo.AbstractTerm implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.mo.InsecticideMethodology";
  private static final long serialVersionUID = 1236612272387L;
  
  public InsecticideMethodologyBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static InsecticideMethodologyQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    InsecticideMethodologyQuery query = new InsecticideMethodologyQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static InsecticideMethodology get(String id)
  {
    return (InsecticideMethodology) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static csu.mrc.ivcc.mdss.mo.InsecticideMethodology[] getAll()
  {
    return null;
  }
  
  public static InsecticideMethodology lock(java.lang.String id)
  {
    InsecticideMethodology _instance = InsecticideMethodology.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static InsecticideMethodology unlock(java.lang.String id)
  {
    InsecticideMethodology _instance = InsecticideMethodology.get(id);
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
