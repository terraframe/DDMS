package dss.vector.solutions.mo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Generation.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class GenerationBase extends dss.vector.solutions.mo.AbstractTerm implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.mo.Generation";
  private static final long serialVersionUID = 1237423106833L;
  
  public GenerationBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static GenerationQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    GenerationQuery query = new GenerationQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static Generation get(String id)
  {
    return (Generation) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static dss.vector.solutions.mo.Generation[] getAll()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.mo.Generation.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static Generation lock(java.lang.String id)
  {
    Generation _instance = Generation.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static Generation unlock(java.lang.String id)
  {
    Generation _instance = Generation.get(id);
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
