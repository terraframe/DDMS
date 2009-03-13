package csu.mrc.ivcc.mdss.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MorphologicalSpecieGroupView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MorphologicalSpecieGroupViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupView";
  public static java.lang.String COLLECTION = "collection";
  public static java.lang.String GROUPID = "groupId";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFICATIONMETHOD = "identificationMethod";
  public static java.lang.String QUANTITY = "quantity";
  public static java.lang.String SPECIE = "specie";
  private static final long serialVersionUID = 1236982476555L;
  
  public MorphologicalSpecieGroupViewBase()
  {
    super();
  }
  
  public csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollection getCollection()
  {
    try
    {
      return csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollection.get(getValue(COLLECTION));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupView.CLASS);
    return mdClassIF.definesAttribute(COLLECTION);
  }
  
  public void setCollection(csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollection value)
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public csu.mrc.ivcc.mdss.mo.IdentificationMethod getIdentificationMethod()
  {
    try
    {
      return csu.mrc.ivcc.mdss.mo.IdentificationMethod.get(getValue(IDENTIFICATIONMETHOD));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupView.CLASS);
    return mdClassIF.definesAttribute(IDENTIFICATIONMETHOD);
  }
  
  public void setIdentificationMethod(csu.mrc.ivcc.mdss.mo.IdentificationMethod value)
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupView.CLASS);
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
  
  public csu.mrc.ivcc.mdss.mo.Specie getSpecie()
  {
    try
    {
      return csu.mrc.ivcc.mdss.mo.Specie.get(getValue(SPECIE));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupView.CLASS);
    return mdClassIF.definesAttribute(SPECIE);
  }
  
  public void setSpecie(csu.mrc.ivcc.mdss.mo.Specie value)
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
  
  public static csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupView[] saveAll(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupView[] array)
  {
    return null;
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
