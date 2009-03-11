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
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String GROUPID = "groupId";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFICATIONMETHOD = "identificationMethod";
  public static java.lang.String QUANTITY = "quantity";
  public static java.lang.String SPECIE = "specie";
  private static final long serialVersionUID = 1236803170007L;
  
  public MorphologicalSpecieGroupViewBase()
  {
    super();
  }
  
  public String getCollectionId()
  {
    return getValue(COLLECTIONID);
  }
  
  public void validateCollectionId()
  {
    this.validateAttribute(COLLECTIONID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCollectionIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupView.CLASS);
    return mdClassIF.definesAttribute(COLLECTIONID);
  }
  
  public void setCollectionId(String value)
  {
    if(value == null)
    {
      setValue(COLLECTIONID, "");
    }
    else
    {
      setValue(COLLECTIONID, value);
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
  
  public String getIdentificationMethod()
  {
    return getValue(IDENTIFICATIONMETHOD);
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
  
  public void setIdentificationMethod(String value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATIONMETHOD, "");
    }
    else
    {
      setValue(IDENTIFICATIONMETHOD, value);
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
  
  public String getSpecie()
  {
    return getValue(SPECIE);
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
  
  public void setSpecie(String value)
  {
    if(value == null)
    {
      setValue(SPECIE, "");
    }
    else
    {
      setValue(SPECIE, value);
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
