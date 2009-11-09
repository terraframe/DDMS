package dss.vector.solutions.entomology;

@com.terraframe.mojo.business.ClassSignature(hash = -1816732849)
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
  public static java.lang.String GROUPID = "groupId";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFICATIONMETHOD = "identificationMethod";
  public static java.lang.String QUANTITY = "quantity";
  public static java.lang.String QUANTITYFEMALE = "quantityFemale";
  public static java.lang.String QUANTITYMALE = "quantityMale";
  public static java.lang.String SPECIE = "specie";
  private static final long serialVersionUID = -1816732849;
  
  public MorphologicalSpecieGroupViewBase()
  {
    super();
  }
  
  public dss.vector.solutions.entomology.ConcreteMosquitoCollection getCollection()
  {
    if (getValue(COLLECTION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.entomology.ConcreteMosquitoCollection.get(getValue(COLLECTION));
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
  
  public dss.vector.solutions.ontology.Term getIdentificationMethod()
  {
    if (getValue(IDENTIFICATIONMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(IDENTIFICATIONMETHOD));
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
  
  public void setIdentificationMethod(dss.vector.solutions.ontology.Term value)
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
  
  public dss.vector.solutions.ontology.Term getSpecie()
  {
    if (getValue(SPECIE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(SPECIE));
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
  
  public void setSpecie(dss.vector.solutions.ontology.Term value)
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
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.MorphologicalSpecieGroupView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    MorphologicalSpecieGroupView _instance = MorphologicalSpecieGroupView.get(id);
    _instance.deleteConcrete();
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
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
