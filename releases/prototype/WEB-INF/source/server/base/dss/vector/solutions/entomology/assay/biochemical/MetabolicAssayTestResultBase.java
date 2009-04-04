package dss.vector.solutions.entomology.assay.biochemical;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MetabolicAssayTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MetabolicAssayTestResultBase extends dss.vector.solutions.entomology.assay.AssayTestResult implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResult";
  private static final long serialVersionUID = 1238826377731L;
  
  public MetabolicAssayTestResultBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static MetabolicAssayTestResultQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    MetabolicAssayTestResultQuery query = new MetabolicAssayTestResultQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static MetabolicAssayTestResult get(String id)
  {
    return (MetabolicAssayTestResult) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static MetabolicAssayTestResult lock(java.lang.String id)
  {
    MetabolicAssayTestResult _instance = MetabolicAssayTestResult.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static MetabolicAssayTestResult unlock(java.lang.String id)
  {
    MetabolicAssayTestResult _instance = MetabolicAssayTestResult.get(id);
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
