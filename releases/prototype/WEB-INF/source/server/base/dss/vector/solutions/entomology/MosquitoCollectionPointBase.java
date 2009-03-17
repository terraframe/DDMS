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
  public static java.lang.String COMPOSITECOLLECTION = "compositeCollection";
  private static final long serialVersionUID = 1237311451955L;
  
  public MosquitoCollectionPointBase()
  {
    super();
  }
  
  public dss.vector.solutions.entomology.CompositeMosquitoCollection getCompositeCollection()
  {
    try
    {
      return dss.vector.solutions.entomology.CompositeMosquitoCollection.get(getValue(COMPOSITECOLLECTION));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateCompositeCollection()
  {
    this.validateAttribute(COMPOSITECOLLECTION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCompositeCollectionMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MosquitoCollectionPoint.CLASS);
    return mdClassIF.definesAttribute(COMPOSITECOLLECTION);
  }
  
  public void setCompositeCollection(dss.vector.solutions.entomology.CompositeMosquitoCollection value)
  {
    if(value == null)
    {
      setValue(COMPOSITECOLLECTION, "");
    }
    else
    {
      setValue(COMPOSITECOLLECTION, value.getId());
    }
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
  
  public static dss.vector.solutions.entomology.MosquitoCollectionPoint searchByGeoEntityAndDate(dss.vector.solutions.geo.generated.GeoEntity geoEntity, java.util.Date collectionDate)
  {
    return null;
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
