package dss.vector.solutions.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MosquitoCollectionPoint.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MosquitoCollectionPointBase extends dss.vector.solutions.entomology.ConcreteMosquitoCollection implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.MosquitoCollectionPoint";
  private static final long serialVersionUID = 1239670269866L;
  
  public MosquitoCollectionPointBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static MosquitoCollectionPointQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    MosquitoCollectionPointQuery query = new MosquitoCollectionPointQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static MosquitoCollectionPoint get(String id)
  {
    return (MosquitoCollectionPoint) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static dss.vector.solutions.entomology.MosquitoCollectionPoint searchByGeoEntityAndDateAndCollectionMethod(dss.vector.solutions.geo.generated.GeoEntity geoEntity, java.util.Date collectionDate, dss.vector.solutions.mo.CollectionMethod collectionMethod)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.MosquitoCollectionPoint.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.entomology.MorphologicalSpecieGroupView[] searchByGeoEntityAndDate(dss.vector.solutions.geo.generated.GeoEntity geoEntity, java.util.Date startDate, java.util.Date endDate)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.MosquitoCollectionPoint.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static MosquitoCollectionPoint lock(java.lang.String id)
  {
    MosquitoCollectionPoint _instance = MosquitoCollectionPoint.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static MosquitoCollectionPoint unlock(java.lang.String id)
  {
    MosquitoCollectionPoint _instance = MosquitoCollectionPoint.get(id);
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
