package dss.vector.solutions.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ConcreteMosquitoCollection.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class ConcreteMosquitoCollectionBase extends dss.vector.solutions.entomology.AbstractMosquitoCollection implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.ConcreteMosquitoCollection";
  public static java.lang.String DATECOLLECTED = "dateCollected";
  public static java.lang.String GEOENTITY = "geoEntity";
  private static final long serialVersionUID = 1238027450005L;
  
  public ConcreteMosquitoCollectionBase()
  {
    super();
  }
  
  public java.util.Date getDateCollected()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DATECOLLECTED));
  }
  
  public void validateDateCollected()
  {
    this.validateAttribute(DATECOLLECTED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDateCollectedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ConcreteMosquitoCollection.CLASS);
    return mdClassIF.definesAttribute(DATECOLLECTED);
  }
  
  public void setDateCollected(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DATECOLLECTED, "");
    }
    else
    {
      setValue(DATECOLLECTED, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public dss.vector.solutions.geo.generated.GeoEntity getGeoEntity()
  {
    try
    {
      return dss.vector.solutions.geo.generated.GeoEntity.get(getValue(GEOENTITY));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateGeoEntity()
  {
    this.validateAttribute(GEOENTITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGeoEntityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ConcreteMosquitoCollection.CLASS);
    return mdClassIF.definesAttribute(GEOENTITY);
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntity value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ConcreteMosquitoCollectionQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    ConcreteMosquitoCollectionQuery query = new ConcreteMosquitoCollectionQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static ConcreteMosquitoCollection get(String id)
  {
    return (ConcreteMosquitoCollection) com.terraframe.mojo.business.Business.get(id);
  }
  
  public dss.vector.solutions.entomology.MorphologicalSpecieGroupView[] getMorphologicalSpecieGroups()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.ConcreteMosquitoCollection.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.entomology.MorphologicalSpecieGroupView[] getMorphologicalSpecieGroups(java.lang.String id)
  {
    ConcreteMosquitoCollection _instance = ConcreteMosquitoCollection.get(id);
    return _instance.getMorphologicalSpecieGroups();
  }
  
  public static ConcreteMosquitoCollection lock(java.lang.String id)
  {
    ConcreteMosquitoCollection _instance = ConcreteMosquitoCollection.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static ConcreteMosquitoCollection unlock(java.lang.String id)
  {
    ConcreteMosquitoCollection _instance = ConcreteMosquitoCollection.get(id);
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
