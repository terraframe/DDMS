package dss.vector.solutions.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MorphologicalSpecieGroupView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MorphologicalSpecieGroupViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.MorphologicalSpecieGroupView";
  public static java.lang.String COLLECTION = "collection";
  public static java.lang.String DATECOLLECTED = "dateCollected";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String GROUPID = "groupId";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFICATIONMETHOD = "identificationMethod";
  public static java.lang.String QUANTITY = "quantity";
  public static java.lang.String QUANTITYFEMALE = "quantityFemale";
  public static java.lang.String QUANTITYMALE = "quantityMale";
  public static java.lang.String SPECIE = "specie";
  private static final long serialVersionUID = 1238826367254L;
  
  public MorphologicalSpecieGroupViewBase()
  {
    super();
  }
  
  public dss.vector.solutions.entomology.ConcreteMosquitoCollection getCollection()
  {
    try
    {
      return dss.vector.solutions.entomology.ConcreteMosquitoCollection.get(getValue(COLLECTION));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateCollection()
  {
    this.validateAttribute(COLLECTION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCollectionMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.CLASS);
    return mdClassIF.definesAttribute(COLLECTION);
  }
  
  public void setCollection(dss.vector.solutions.entomology.ConcreteMosquitoCollection value)
  {
    if(value == null)
    {
      setValue(COLLECTION, "");
    }
    else
    {
      setValue(COLLECTION, value.getId());
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.CLASS);
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
  
  public String getGroupId()
  {
    return getValue(GROUPID);
  }
  
  public void validateGroupId()
  {
    this.validateAttribute(GROUPID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGroupIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.CLASS);
    return mdClassIF.definesAttribute(GROUPID);
  }
  
  public void setGroupId(String value)
  {
    if(value == null)
    {
      setValue(GROUPID, "");
    }
    else
    {
      setValue(GROUPID, value);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public dss.vector.solutions.mo.IdentificationMethod getIdentificationMethod()
  {
    try
    {
      return dss.vector.solutions.mo.IdentificationMethod.get(getValue(IDENTIFICATIONMETHOD));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateIdentificationMethod()
  {
    this.validateAttribute(IDENTIFICATIONMETHOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdentificationMethodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.CLASS);
    return mdClassIF.definesAttribute(IDENTIFICATIONMETHOD);
  }
  
  public void setIdentificationMethod(dss.vector.solutions.mo.IdentificationMethod value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATIONMETHOD, "");
    }
    else
    {
      setValue(IDENTIFICATIONMETHOD, value.getId());
    }
  }
  
  public Integer getQuantity()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITY));
  }
  
  public void validateQuantity()
  {
    this.validateAttribute(QUANTITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getQuantityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.CLASS);
    return mdClassIF.definesAttribute(QUANTITY);
  }
  
  public void setQuantity(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITY, "");
    }
    else
    {
      setValue(QUANTITY, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getQuantityFemale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYFEMALE));
  }
  
  public void validateQuantityFemale()
  {
    this.validateAttribute(QUANTITYFEMALE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getQuantityFemaleMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.CLASS);
    return mdClassIF.definesAttribute(QUANTITYFEMALE);
  }
  
  public void setQuantityFemale(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYFEMALE, "");
    }
    else
    {
      setValue(QUANTITYFEMALE, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getQuantityMale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYMALE));
  }
  
  public void validateQuantityMale()
  {
    this.validateAttribute(QUANTITYMALE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getQuantityMaleMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.CLASS);
    return mdClassIF.definesAttribute(QUANTITYMALE);
  }
  
  public void setQuantityMale(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYMALE, "");
    }
    else
    {
      setValue(QUANTITYMALE, java.lang.Integer.toString(value));
    }
  }
  
  public dss.vector.solutions.mo.Specie getSpecie()
  {
    try
    {
      return dss.vector.solutions.mo.Specie.get(getValue(SPECIE));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateSpecie()
  {
    this.validateAttribute(SPECIE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSpecieMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.CLASS);
    return mdClassIF.definesAttribute(SPECIE);
  }
  
  public void setSpecie(dss.vector.solutions.mo.Specie value)
  {
    if(value == null)
    {
      setValue(SPECIE, "");
    }
    else
    {
      setValue(SPECIE, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static MorphologicalSpecieGroupView get(String id)
  {
    return (MorphologicalSpecieGroupView) com.terraframe.mojo.business.View.get(id);
  }
  
  public static dss.vector.solutions.entomology.MorphologicalSpecieGroupView[] saveAll(dss.vector.solutions.entomology.MorphologicalSpecieGroupView[] array)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.MorphologicalSpecieGroupView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: Morphological Specie Group View";
    }
    else
    {
      return super.toString();
    }
  }
}
