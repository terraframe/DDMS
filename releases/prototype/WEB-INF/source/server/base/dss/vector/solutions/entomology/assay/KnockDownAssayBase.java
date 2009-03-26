package dss.vector.solutions.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to KnockDownAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class KnockDownAssayBase extends dss.vector.solutions.entomology.assay.AdultAssay implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.KnockDownAssay";
  private static final long serialVersionUID = 1238027433311L;
  
  public KnockDownAssayBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static KnockDownAssayQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    KnockDownAssayQuery query = new KnockDownAssayQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static KnockDownAssay get(String id)
  {
    return (KnockDownAssay) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static KnockDownAssay lock(java.lang.String id)
  {
    KnockDownAssay _instance = KnockDownAssay.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static KnockDownAssay unlock(java.lang.String id)
  {
    KnockDownAssay _instance = KnockDownAssay.get(id);
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
