package dss.vector.solutions.surveillance;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TreatmentMethodGrid.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class TreatmentMethodGridBase extends dss.vector.solutions.surveillance.AbstractGrid implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.TreatmentMethodGrid";
  private static final long serialVersionUID = 1239670267331L;
  
  public TreatmentMethodGridBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static TreatmentMethodGridQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    TreatmentMethodGridQuery query = new TreatmentMethodGridQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public dss.vector.solutions.surveillance.CaseTreatmentMethod addAggregatedCase(dss.vector.solutions.surveillance.AggregatedCase aggregatedCase)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentMethod) addParent(aggregatedCase, dss.vector.solutions.surveillance.CaseTreatmentMethod.CLASS);
  }
  
  public void removeAggregatedCase(dss.vector.solutions.surveillance.AggregatedCase aggregatedCase)
  {
    removeAllParents(aggregatedCase, dss.vector.solutions.surveillance.CaseTreatmentMethod.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.surveillance.AggregatedCase> getAllAggregatedCase()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.surveillance.AggregatedCase>) getParents(dss.vector.solutions.surveillance.CaseTreatmentMethod.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.surveillance.CaseTreatmentMethod> getAllAggregatedCaseRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.surveillance.CaseTreatmentMethod>) getParentRelationships(dss.vector.solutions.surveillance.CaseTreatmentMethod.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public dss.vector.solutions.surveillance.CaseTreatmentMethod getAggregatedCaseRel(dss.vector.solutions.surveillance.AggregatedCase aggregatedCase)
  {
    com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.surveillance.CaseTreatmentMethod> iterator = (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.surveillance.CaseTreatmentMethod>) getRelationshipsWithParent(aggregatedCase, dss.vector.solutions.surveillance.CaseTreatmentMethod.CLASS);
    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      else
      {
        return null;
      }
    }
    finally
    {
      iterator.close();
    }
  }
  
  public static TreatmentMethodGrid get(String id)
  {
    return (TreatmentMethodGrid) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static dss.vector.solutions.surveillance.TreatmentMethodGrid[] getAll()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.TreatmentMethodGrid.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static TreatmentMethodGrid lock(java.lang.String id)
  {
    TreatmentMethodGrid _instance = TreatmentMethodGrid.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static TreatmentMethodGrid unlock(java.lang.String id)
  {
    TreatmentMethodGrid _instance = TreatmentMethodGrid.get(id);
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
