package dss.vector.solutions.mo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ResistanceMethodology.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class ResistanceMethodologyBase extends dss.vector.solutions.mo.AbstractTerm implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.mo.ResistanceMethodology";
  private static final long serialVersionUID = 1239572479978L;
  
  public ResistanceMethodologyBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ResistanceMethodologyQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    ResistanceMethodologyQuery query = new ResistanceMethodologyQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static ResistanceMethodology get(String id)
  {
    return (ResistanceMethodology) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static dss.vector.solutions.mo.ResistanceMethodology[] getAll()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.mo.ResistanceMethodology.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static ResistanceMethodology lock(java.lang.String id)
  {
    ResistanceMethodology _instance = ResistanceMethodology.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static ResistanceMethodology unlock(java.lang.String id)
  {
    ResistanceMethodology _instance = ResistanceMethodology.get(id);
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
