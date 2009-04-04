package dss.vector.solutions.mo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ActiveIngredient.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class ActiveIngredientBase extends dss.vector.solutions.mo.AbstractTerm implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.mo.ActiveIngredient";
  private static final long serialVersionUID = 1238826360507L;
  
  public ActiveIngredientBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ActiveIngredientQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    ActiveIngredientQuery query = new ActiveIngredientQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static ActiveIngredient get(String id)
  {
    return (ActiveIngredient) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static dss.vector.solutions.mo.ActiveIngredient[] getAll()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.mo.ActiveIngredient.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static ActiveIngredient lock(java.lang.String id)
  {
    ActiveIngredient _instance = ActiveIngredient.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static ActiveIngredient unlock(java.lang.String id)
  {
    ActiveIngredient _instance = ActiveIngredient.get(id);
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
