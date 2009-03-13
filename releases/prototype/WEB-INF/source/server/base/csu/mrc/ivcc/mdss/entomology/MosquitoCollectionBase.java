package csu.mrc.ivcc.mdss.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MosquitoCollection.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MosquitoCollectionBase extends csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollection implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.MosquitoCollection";
  public static java.lang.String COLLECTIONMETHOD = "collectionMethod";
  private static final long serialVersionUID = 1236982461539L;
  
  public MosquitoCollectionBase()
  {
    super();
  }
  
  public csu.mrc.ivcc.mdss.mo.CollectionMethod getCollectionMethod()
  {
    try
    {
      return csu.mrc.ivcc.mdss.mo.CollectionMethod.get(getValue(COLLECTIONMETHOD));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateCollectionMethod()
  {
    this.validateAttribute(COLLECTIONMETHOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCollectionMethodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.MosquitoCollection.CLASS);
    return mdClassIF.definesAttribute(COLLECTIONMETHOD);
  }
  
  public void setCollectionMethod(csu.mrc.ivcc.mdss.mo.CollectionMethod value)
  {
    if(value == null)
    {
      setValue(COLLECTIONMETHOD, "");
    }
    else
    {
      setValue(COLLECTIONMETHOD, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static MosquitoCollectionQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    MosquitoCollectionQuery query = new MosquitoCollectionQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static MosquitoCollection get(String id)
  {
    return (MosquitoCollection) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static csu.mrc.ivcc.mdss.entomology.MosquitoCollection searchByGeoEntityAndDate(csu.mrc.ivcc.mdss.geo.generated.GeoEntity geoEntity, java.util.Date collectionDate)
  {
    return null;
  }
  
  public static MosquitoCollection lock(java.lang.String id)
  {
    MosquitoCollection _instance = MosquitoCollection.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static MosquitoCollection unlock(java.lang.String id)
  {
    MosquitoCollection _instance = MosquitoCollection.get(id);
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
