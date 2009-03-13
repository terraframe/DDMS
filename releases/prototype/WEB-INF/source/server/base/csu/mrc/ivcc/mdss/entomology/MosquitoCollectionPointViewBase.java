package csu.mrc.ivcc.mdss.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MosquitoCollectionPointView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MosquitoCollectionPointViewBase extends csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupView implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPointView";
  public static java.lang.String DATECOLLECTED = "dateCollected";
  public static java.lang.String GEOENTITY = "geoEntity";
  private static final long serialVersionUID = 1236982465143L;
  
  public MosquitoCollectionPointViewBase()
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPointView.CLASS);
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
  
  public csu.mrc.ivcc.mdss.geo.generated.GeoEntity getGeoEntity()
  {
    try
    {
      return csu.mrc.ivcc.mdss.geo.generated.GeoEntity.get(getValue(GEOENTITY));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPointView.CLASS);
    return mdClassIF.definesAttribute(GEOENTITY);
  }
  
  public void setGeoEntity(csu.mrc.ivcc.mdss.geo.generated.GeoEntity value)
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
  
  public static MosquitoCollectionPointView get(String id)
  {
    return (MosquitoCollectionPointView) com.terraframe.mojo.business.View.get(id);
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: Mosquito Collection Point View";
    }
    else
    {
      return super.toString();
    }
  }
}
