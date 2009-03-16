package csu.mrc.ivcc.mdss.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MosquitoCollectionPoint.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MosquitoCollectionPointBase extends csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollection implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPoint";
  public static java.lang.String COMPOSITECOLLECTION = "compositeCollection";
  private static final long serialVersionUID = 1237219391622L;
  
  public MosquitoCollectionPointBase()
  {
    super();
  }
  
  public csu.mrc.ivcc.mdss.entomology.CompositeMosquitoCollection getCompositeCollection()
  {
    try
    {
      return csu.mrc.ivcc.mdss.entomology.CompositeMosquitoCollection.get(getValue(COMPOSITECOLLECTION));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPoint.CLASS);
    return mdClassIF.definesAttribute(COMPOSITECOLLECTION);
  }
  
  public void setCompositeCollection(csu.mrc.ivcc.mdss.entomology.CompositeMosquitoCollection value)
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
  
  public static csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPoint searchByGeoEntityAndDate(csu.mrc.ivcc.mdss.geo.generated.GeoEntity geoEntity, java.util.Date collectionDate)
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
