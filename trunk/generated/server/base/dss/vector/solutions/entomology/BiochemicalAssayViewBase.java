package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = 1520309208)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to BiochemicalAssayView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class BiochemicalAssayViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.BiochemicalAssayView";
  public static java.lang.String ASSAY = "assay";
  public static java.lang.String COLLECTION = "collection";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String GENERATION = "generation";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTMETHOD = "identMethod";
  public static java.lang.String ISOFEMALE = "isofemale";
  public static java.lang.String MOSQUITOID = "mosquitoId";
  public static java.lang.String NUMBERELEVATED = "numberElevated";
  public static java.lang.String NUMBERTESTED = "numberTested";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SPECIES = "species";
  public static java.lang.String UNIQUEASSAYID = "uniqueAssayId";
  private static final long serialVersionUID = 1520309208;
  
  public BiochemicalAssayViewBase()
  {
    super();
  }
  
  public dss.vector.solutions.ontology.Term getAssay()
  {
    if (getValue(ASSAY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(ASSAY));
    }
  }
  
  public String getAssayId()
  {
    return getValue(ASSAY);
  }
  
  public void validateAssay()
  {
    this.validateAttribute(ASSAY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getAssayMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.BiochemicalAssayView.CLASS);
    return mdClassIF.definesAttribute(ASSAY);
  }
  
  public void setAssay(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(ASSAY, "");
    }
    else
    {
      setValue(ASSAY, value.getId());
    }
  }
  
  public dss.vector.solutions.entomology.MosquitoCollection getCollection()
  {
    if (getValue(COLLECTION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.entomology.MosquitoCollection.get(getValue(COLLECTION));
    }
  }
  
  public String getCollectionId()
  {
    return getValue(COLLECTION);
  }
  
  public void validateCollection()
  {
    this.validateAttribute(COLLECTION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCollectionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.BiochemicalAssayView.CLASS);
    return mdClassIF.definesAttribute(COLLECTION);
  }
  
  public void setCollection(dss.vector.solutions.entomology.MosquitoCollection value)
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
  
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void validateConcreteId()
  {
    this.validateAttribute(CONCRETEID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getConcreteIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.BiochemicalAssayView.CLASS);
    return mdClassIF.definesAttribute(CONCRETEID);
  }
  
  public void setConcreteId(String value)
  {
    if(value == null)
    {
      setValue(CONCRETEID, "");
    }
    else
    {
      setValue(CONCRETEID, value);
    }
  }
  
  public dss.vector.solutions.ontology.Term getGeneration()
  {
    if (getValue(GENERATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(GENERATION));
    }
  }
  
  public String getGenerationId()
  {
    return getValue(GENERATION);
  }
  
  public void validateGeneration()
  {
    this.validateAttribute(GENERATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getGenerationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.BiochemicalAssayView.CLASS);
    return mdClassIF.definesAttribute(GENERATION);
  }
  
  public void setGeneration(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(GENERATION, "");
    }
    else
    {
      setValue(GENERATION, value.getId());
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.BiochemicalAssayView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public dss.vector.solutions.ontology.Term getIdentMethod()
  {
    if (getValue(IDENTMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(IDENTMETHOD));
    }
  }
  
  public String getIdentMethodId()
  {
    return getValue(IDENTMETHOD);
  }
  
  public void validateIdentMethod()
  {
    this.validateAttribute(IDENTMETHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdentMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.BiochemicalAssayView.CLASS);
    return mdClassIF.definesAttribute(IDENTMETHOD);
  }
  
  public void setIdentMethod(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(IDENTMETHOD, "");
    }
    else
    {
      setValue(IDENTMETHOD, value.getId());
    }
  }
  
  public Boolean getIsofemale()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISOFEMALE));
  }
  
  public void validateIsofemale()
  {
    this.validateAttribute(ISOFEMALE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIsofemaleMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.BiochemicalAssayView.CLASS);
    return mdClassIF.definesAttribute(ISOFEMALE);
  }
  
  public void setIsofemale(Boolean value)
  {
    if(value == null)
    {
      setValue(ISOFEMALE, "");
    }
    else
    {
      setValue(ISOFEMALE, java.lang.Boolean.toString(value));
    }
  }
  
  public String getMosquitoId()
  {
    return getValue(MOSQUITOID);
  }
  
  public void validateMosquitoId()
  {
    this.validateAttribute(MOSQUITOID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getMosquitoIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.BiochemicalAssayView.CLASS);
    return mdClassIF.definesAttribute(MOSQUITOID);
  }
  
  public void setMosquitoId(String value)
  {
    if(value == null)
    {
      setValue(MOSQUITOID, "");
    }
    else
    {
      setValue(MOSQUITOID, value);
    }
  }
  
  public Integer getNumberElevated()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERELEVATED));
  }
  
  public void validateNumberElevated()
  {
    this.validateAttribute(NUMBERELEVATED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getNumberElevatedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.BiochemicalAssayView.CLASS);
    return mdClassIF.definesAttribute(NUMBERELEVATED);
  }
  
  public void setNumberElevated(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERELEVATED, "");
    }
    else
    {
      setValue(NUMBERELEVATED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getNumberTested()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERTESTED));
  }
  
  public void validateNumberTested()
  {
    this.validateAttribute(NUMBERTESTED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getNumberTestedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.BiochemicalAssayView.CLASS);
    return mdClassIF.definesAttribute(NUMBERTESTED);
  }
  
  public void setNumberTested(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERTESTED, "");
    }
    else
    {
      setValue(NUMBERTESTED, java.lang.Integer.toString(value));
    }
  }
  
  public dss.vector.solutions.ontology.Term getSex()
  {
    if (getValue(SEX).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(SEX));
    }
  }
  
  public String getSexId()
  {
    return getValue(SEX);
  }
  
  public void validateSex()
  {
    this.validateAttribute(SEX);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSexMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.BiochemicalAssayView.CLASS);
    return mdClassIF.definesAttribute(SEX);
  }
  
  public void setSex(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(SEX, "");
    }
    else
    {
      setValue(SEX, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getSpecies()
  {
    if (getValue(SPECIES).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(SPECIES));
    }
  }
  
  public String getSpeciesId()
  {
    return getValue(SPECIES);
  }
  
  public void validateSpecies()
  {
    this.validateAttribute(SPECIES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSpeciesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.BiochemicalAssayView.CLASS);
    return mdClassIF.definesAttribute(SPECIES);
  }
  
  public void setSpecies(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(SPECIES, "");
    }
    else
    {
      setValue(SPECIES, value.getId());
    }
  }
  
  public String getUniqueAssayId()
  {
    return getValue(UNIQUEASSAYID);
  }
  
  public void validateUniqueAssayId()
  {
    this.validateAttribute(UNIQUEASSAYID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getUniqueAssayIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.BiochemicalAssayView.CLASS);
    return mdClassIF.definesAttribute(UNIQUEASSAYID);
  }
  
  public void setUniqueAssayId(String value)
  {
    if(value == null)
    {
      setValue(UNIQUEASSAYID, "");
    }
    else
    {
      setValue(UNIQUEASSAYID, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static BiochemicalAssayView get(String id)
  {
    return (BiochemicalAssayView) com.runwaysdk.business.View.get(id);
  }
  
  public static dss.vector.solutions.entomology.BiochemicalAssayView[] applyAll(dss.vector.solutions.entomology.BiochemicalAssayView[] views)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.BiochemicalAssayView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.BiochemicalAssayView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    BiochemicalAssayView _instance = BiochemicalAssayView.get(id);
    _instance.deleteConcrete();
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
