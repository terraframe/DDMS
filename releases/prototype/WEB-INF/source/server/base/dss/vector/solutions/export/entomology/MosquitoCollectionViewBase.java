package dss.vector.solutions.export.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MosquitoCollectionView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MosquitoCollectionViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.entomology.MosquitoCollectionView";
  public static java.lang.String COLLECTIONMETHOD = "collectionMethod";
  public static java.lang.String DATECOLLECTED = "dateCollected";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 1239658602501L;
  
  public MosquitoCollectionViewBase()
  {
    super();
  }
  
  public String getCollectionMethod()
  {
    return getValue(COLLECTIONMETHOD);
  }
  
  public void validateCollectionMethod()
  {
    this.validateAttribute(COLLECTIONMETHOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCollectionMethodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.MosquitoCollectionView.CLASS);
    return mdClassIF.definesAttribute(COLLECTIONMETHOD);
  }
  
  public void setCollectionMethod(String value)
  {
    if(value == null)
    {
      setValue(COLLECTIONMETHOD, "");
    }
    else
    {
      setValue(COLLECTIONMETHOD, value);
    }
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.MosquitoCollectionView.CLASS);
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
  
  public String getGeoEntity()
  {
    return getValue(GEOENTITY);
  }
  
  public void validateGeoEntity()
  {
    this.validateAttribute(GEOENTITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGeoEntityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.MosquitoCollectionView.CLASS);
    return mdClassIF.definesAttribute(GEOENTITY);
  }
  
  public void setGeoEntity(String value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value);
    }
  }
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.MosquitoCollectionView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static MosquitoCollectionView get(String id)
  {
    return (MosquitoCollectionView) com.terraframe.mojo.business.View.get(id);
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
