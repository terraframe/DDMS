package dss.vector.solutions.entomology;

@com.terraframe.mojo.business.ClassSignature(hash = -2111715052)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MosquitoCollection.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MosquitoCollectionBase extends dss.vector.solutions.entomology.ConcreteMosquitoCollection implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.MosquitoCollection";
  public static java.lang.String COLLECTIONMETHOD = "collectionMethod";
  private static final long serialVersionUID = -2111715052;
  
  public MosquitoCollectionBase()
  {
    super();
  }
  
  public dss.vector.solutions.ontology.Term getCollectionMethod()
  {
    if (getValue(COLLECTIONMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(COLLECTIONMETHOD));
    }
  }
  
  public void validateCollectionMethod()
  {
    this.validateAttribute(COLLECTIONMETHOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCollectionMethodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MosquitoCollection.CLASS);
    return mdClassIF.definesAttribute(COLLECTIONMETHOD);
  }
  
  public void setCollectionMethod(dss.vector.solutions.ontology.Term value)
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
  
  public static MosquitoCollection getByKey(String key)
  {
    return (MosquitoCollection) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery getAdultDoseAssays(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.MosquitoCollection.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery getAdultDoseAssays(java.lang.String id, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    MosquitoCollection _instance = MosquitoCollection.get(id);
    return _instance.getAdultDoseAssays(sortAttribute, isAscending, pageSize, pageNumber);
  }
  
  public dss.vector.solutions.entomology.assay.KnockDownAssayQuery getKnockDownAssays(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.MosquitoCollection.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.entomology.assay.KnockDownAssayQuery getKnockDownAssays(java.lang.String id, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    MosquitoCollection _instance = MosquitoCollection.get(id);
    return _instance.getKnockDownAssays(sortAttribute, isAscending, pageSize, pageNumber);
  }
  
  public dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayQuery getLarvaeDoseAssays(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.MosquitoCollection.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayQuery getLarvaeDoseAssays(java.lang.String id, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    MosquitoCollection _instance = MosquitoCollection.get(id);
    return _instance.getLarvaeDoseAssays(sortAttribute, isAscending, pageSize, pageNumber);
  }
  
  public static dss.vector.solutions.entomology.MosquitoCollection searchByGeoEntityAndDate(dss.vector.solutions.geo.generated.GeoEntity geoEntity, java.util.Date collectionDate)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.MosquitoCollection.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.entomology.MosquitoCollection searchByGeoEntityAndDateAndCollectionMethod(dss.vector.solutions.geo.generated.GeoEntity geoEntity, java.util.Date collectionDate, dss.vector.solutions.ontology.Term collectionMethod)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.MosquitoCollection.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
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
