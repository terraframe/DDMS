package dss.vector.solutions.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to UninterestingSpecieGroupView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class UninterestingSpecieGroupViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.UninterestingSpecieGroupView";
  public static java.lang.String COLLECTION = "collection";
  public static java.lang.String GROUPID = "groupId";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFICATIONMETHOD = "identificationMethod";
  public static java.lang.String QUANTITY = "quantity";
  public static java.lang.String SAMPLEID = "sampleId";
  public static java.lang.String SPECIE = "specie";
  private static final long serialVersionUID = 1239517531322L;
  
  public UninterestingSpecieGroupViewBase()
  {
    super();
  }
  
  public dss.vector.solutions.entomology.AbstractMosquitoCollection getCollection()
  {
    try
    {
      return dss.vector.solutions.entomology.AbstractMosquitoCollection.get(getValue(COLLECTION));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.UninterestingSpecieGroupView.CLASS);
    return mdClassIF.definesAttribute(COLLECTION);
  }
  
  public void setCollection(dss.vector.solutions.entomology.AbstractMosquitoCollection value)
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.UninterestingSpecieGroupView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.UninterestingSpecieGroupView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.UninterestingSpecieGroupView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.UninterestingSpecieGroupView.CLASS);
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
  
  public String getSampleId()
  {
    return getValue(SAMPLEID);
  }
  
  public void validateSampleId()
  {
    this.validateAttribute(SAMPLEID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSampleIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.UninterestingSpecieGroupView.CLASS);
    return mdClassIF.definesAttribute(SAMPLEID);
  }
  
  public void setSampleId(String value)
  {
    if(value == null)
    {
      setValue(SAMPLEID, "");
    }
    else
    {
      setValue(SAMPLEID, value);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.UninterestingSpecieGroupView.CLASS);
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
  
  public static UninterestingSpecieGroupView get(String id)
  {
    return (UninterestingSpecieGroupView) com.terraframe.mojo.business.View.get(id);
  }
  
  public static dss.vector.solutions.entomology.UninterestingSpecieGroupView[] saveAll(dss.vector.solutions.entomology.UninterestingSpecieGroupView[] array)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.UninterestingSpecieGroupView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
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
