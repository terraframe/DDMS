package dss.vector.solutions.mo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MolecularAssayResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MolecularAssayResultBase extends dss.vector.solutions.mo.AbstractTerm implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.mo.MolecularAssayResult";
  private static final long serialVersionUID = 1239075033955L;
  
  public MolecularAssayResultBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static MolecularAssayResultQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    MolecularAssayResultQuery query = new MolecularAssayResultQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static MolecularAssayResult get(String id)
  {
    return (MolecularAssayResult) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static dss.vector.solutions.mo.MolecularAssayResult[] getAll()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.mo.MolecularAssayResult.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static MolecularAssayResult lock(java.lang.String id)
  {
    MolecularAssayResult _instance = MolecularAssayResult.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static MolecularAssayResult unlock(java.lang.String id)
  {
    MolecularAssayResult _instance = MolecularAssayResult.get(id);
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
