package dss.vector.solutions.mo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Specie.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class SpecieBase extends dss.vector.solutions.mo.AbstractTerm implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.mo.Specie";
  private static final long serialVersionUID = 1239658617513L;
  
  public SpecieBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static SpecieQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    SpecieQuery query = new SpecieQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static Specie get(String id)
  {
    return (Specie) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static dss.vector.solutions.mo.Specie[] getAll()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.mo.Specie.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static Specie lock(java.lang.String id)
  {
    Specie _instance = Specie.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static Specie unlock(java.lang.String id)
  {
    Specie _instance = Specie.get(id);
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
